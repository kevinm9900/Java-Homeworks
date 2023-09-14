package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.junit.jupiter.api.Test;

import transaction.Check;
import transaction.DebitCard;
import transaction.Deposit;
import transaction.Transaction;
import transaction.TransactionUtilsImpl;
import transactionlist.TransactionList;
import transactionlist.TransactionListLinkedListImpl;
import transactionlist.TransactionListUtilsImpl;

class Lab12Test {

	@Test
	public void test() {
		utils.MyUtils.printTimeStamp("BEGINS");
		String sName = utils.MyUtils.getNameFromStudent();
		testTransactionMethods();
		testTransactionListAddContainsSize();
		testTransactionListFindGet();
		testTransactionListRemoveClear();
		testTransactionListUtils();

		utils.MyUtils.printTimeStamp("ENDS for " + sName);
	}

	public void testTransactionListRemoveClear() {
		System.out.println("-----------------\nTesting List Add Contains Size");
		TransactionList list = new TransactionListLinkedListImpl();
		int size = list.getSize();
		assertEquals(0, size);
		Deposit d1 = new Deposit();
		GregorianCalendar date =  new GregorianCalendar(Transaction.DEFAULT_YEAR, 5, 11);
		GregorianCalendar date2 =  new GregorianCalendar(Transaction.DEFAULT_YEAR, 6, 11);
		GregorianCalendar date3 =  new GregorianCalendar(Transaction.DEFAULT_YEAR, 5, 10);
		GregorianCalendar date4 =  new GregorianCalendar(Transaction.DEFAULT_YEAR-1, 5, 10);
		GregorianCalendar date5 =  new GregorianCalendar(Transaction.DEFAULT_YEAR+1, 5, 10);
		d1.setAmount(100.00);
		d1.setTransactionId("123xyz");
		d1.setDate(date);
		boolean res = list.add(d1);
		assertTrue(res);
		DebitCard dc1 = new DebitCard("dc1999", date2, 200.00, "utility Austin tx");
		res = list.add(dc1);
		assertTrue(res);
		Check c1 = new Check("ccc111", date3, 300, 4001, "Bmx Rentals");
		res = list.add(c1);
		assertTrue(res);
	    Transaction trans = list.remove(c1);
	    assertEquals(c1, trans);
	    assertEquals(2, list.getSize());
	    trans = list.remove(c1);
	    assertEquals(null, trans);
	    trans = list.remove(dc1);
	    assertEquals(dc1, trans);
	    assertEquals(1, list.getSize());
	    trans = list.remove(dc1);
	    assertEquals(null, trans);
	    trans = list.remove(d1);
	    assertEquals(d1,trans);
	    assertEquals(0, list.getSize());
	    trans = list.remove(d1);
	    assertEquals(null,trans);
	    list.add(d1);
	    list.add(dc1);
	    list.add(c1);
	    assertEquals(3, list.getSize());
	    list.clear();
	    assertEquals(0, list.getSize());
	    String out = list.toString();
	    assertEquals(0, utils.MyUtils.numberLines(out));
	    list.add(d1);
	    assertEquals(1, list.getSize());
	    out = list.toString();
	    assertEquals(1, utils.MyUtils.numberLines(out));
	    list.clear();
	    assertEquals(0, list.getSize());
	    out = list.toString();
	    assertEquals(0, utils.MyUtils.numberLines(out));
	    
	    

	}

	public void testTransactionListFindGet() {
		System.out.println("-----------------\nTesting List find() get() getByDate()");
		TransactionList list = new TransactionListLinkedListImpl();
		int size = list.getSize();
		assertEquals(0, size);
		Deposit d1 = new Deposit();
		GregorianCalendar date =  new GregorianCalendar(Transaction.DEFAULT_YEAR, 5, 11);
		GregorianCalendar date2 =  new GregorianCalendar(Transaction.DEFAULT_YEAR, 6, 11);
		GregorianCalendar date3 =  new GregorianCalendar(Transaction.DEFAULT_YEAR, 5, 10);
		GregorianCalendar date4 =  new GregorianCalendar(Transaction.DEFAULT_YEAR-1, 5, 10);
		GregorianCalendar date5 =  new GregorianCalendar(Transaction.DEFAULT_YEAR+1, 5, 10);
		d1.setAmount(100.00);
		d1.setTransactionId("123xyz");
		d1.setDate(date);
		int res = list.find(d1);
		assertEquals(-1, res);
		DebitCard dc1 = new DebitCard("dc1999", date2, 200.00, "utility Austin tx");
		res = list.find(dc1);
		assertEquals(-1, res);
		Check c1 = new Check("ccc111", date3, 300, 4001, "Bmx Rentals");
		Check c2 = new Check("ccc333", date5, 2000.00, 5006, "hays county taxes");
		res = list.find(c1);
		assertEquals(-1, res);
		list.add(c1); 
		list.add(dc1); 
		list.add(d1);
		System.out.println("List:\n" + list);
		res = list.find(dc1);
		assertEquals(0, res);
		res = list.find(d1);
		assertEquals(1, res);
	    res = list.find(c1);
	    assertEquals(2, res);
	    res = list.find(c2);
	    assertEquals(-1, res);
	    
	    Transaction trans = list.get(0);
	    assertEquals(dc1, trans);
	    trans = list.get(1);
	    assertEquals(d1, trans);
	    trans = list.get(2);
	    assertEquals(c1, trans);
	    trans = list.get(3);
	    assertEquals(null, trans);
	    trans = list.get(4);
	    assertEquals(null, trans);
	    trans = list.get(-3);
	    assertEquals(null, trans);
	    
	    System.out.println("List:\n" + list);
	    Check c3 = new Check("ccc333", date3, 700, 4007, "United Airlines");
		list.add(c3);
		assertEquals(4, list.getSize());
		String out = list.getTransactionListByDate(date3);
		System.out.println("For date: " + utils.MyUtils.dateToString(date3));
		System.out.println(out);
		res = utils.MyUtils.numberLines(out);
		assertEquals(2, res);
		out = list.getTransactionListByDate(date5);
		System.out.println("For date: " + utils.MyUtils.dateToString(date5));
		System.out.println(out);
		res = utils.MyUtils.numberLines(out);
		assertEquals("", out);
		assertEquals(0, res);
		out = list.getTransactionListByDate(date2);
		System.out.println("For date: " + utils.MyUtils.dateToString(date2));
		System.out.println(out);
		res = utils.MyUtils.numberLines(out);
		assertEquals(1, res);
		list = new TransactionListLinkedListImpl();
		out = list.getTransactionListByDate(date5);
		assertEquals("", out);
	}

	public void testTransactionListAddContainsSize()
	{
		System.out.println("-----------------\nTesting List Add Contains Size");
		TransactionList list = new TransactionListLinkedListImpl();
		int size = list.getSize();
		assertEquals(0, size);
		Deposit d1 = new Deposit();
		GregorianCalendar date =  new GregorianCalendar(Transaction.DEFAULT_YEAR, 5, 11);
		GregorianCalendar date2 =  new GregorianCalendar(Transaction.DEFAULT_YEAR, 6, 11);
		GregorianCalendar date3 =  new GregorianCalendar(Transaction.DEFAULT_YEAR, 5, 10);
		GregorianCalendar date4 =  new GregorianCalendar(Transaction.DEFAULT_YEAR-1, 5, 10);
		GregorianCalendar date5 =  new GregorianCalendar(Transaction.DEFAULT_YEAR+1, 5, 10);
		d1.setAmount(100.00);
		d1.setTransactionId("123xyz");
		d1.setDate(date);
		boolean res = list.contains(d1);
		assertFalse(res);
		DebitCard dc1 = new DebitCard("dc1999", date2, 200.00, "utility Austin tx");
		res = list.contains(dc1);
		assertFalse(res);
		Check c1 = new Check("ccc111", date3, 300, 4001, "Bmx Rentals");
		res = list.contains(c1);
		assertFalse(res);
		res = list.add(d1);
		assertTrue(res);
		System.out.println("Added " + d1 + " to list");
		size = list.getSize();
		assertEquals(1, size);
		res = list.contains(d1);
		assertTrue(res);
		res = list.add(dc1);
		assertTrue(res);
		System.out.println("Added " + dc1 + " to list");
		size=list.getSize();
		assertEquals(2, size);
		res = list.contains(dc1);
		assertTrue(res);
		res = list.add(c1);
		assertTrue(res);
		res = list.contains(c1);
		assertTrue(res);
		System.out.println("Added " + c1 + " to list");
		size=list.getSize();
		assertEquals(3, size);
		System.out.println("Current list of " + list.getSize() + " transactions:\n" + list);
		DebitCard dc2 = new DebitCard("dc2222", date4, 600.00, "Pebble beach resorts");
		res = list.add(dc2);
		assertTrue(res);
		res = list.add(dc2);
		assertFalse(res);
		size=list.getSize();
		assertEquals(4, size);
		Check c2 = new Check("ccc333", date5, 2000.00, 5006, "hays county taxes");
		res = list.add(c2);
		assertTrue(res);
		res = list.contains(c2);
		assertTrue(res);
		res = list.add(c2);
		assertFalse(res);
		size = list.getSize();
		assertEquals(5, size);
		String out = list.toString();
		size = utils.MyUtils.numberLines(out);
		assertEquals(5, size);
		System.out.println("Testing that transactions are added in order by MOST RECENT DATE at beginning");
		StringTokenizer tk = new StringTokenizer(out, "\n");
		String exp = "6/10/2021";
		String line = tk.nextToken();
		assertTrue(line.contains(exp));
		System.out.println ("1)Transaction in most recent order: " + line);
		exp = "7/11/2020";
		line = tk.nextToken();
		assertTrue(line.contains(exp));
		System.out.println ("2)Transaction in most recent order: " + line);
		exp = "6/11/2020";
		line = tk.nextToken();
		assertTrue(line.contains(exp));
		System.out.println ("3)Transaction in most recent order: " + line);
		exp = "6/10/2020";
		line = tk.nextToken();
		assertTrue(line.contains(exp));
		System.out.println ("4)Transaction in most recent order: " + line);
		exp = "6/10/2019";
		line = tk.nextToken();
		assertTrue(line.contains(exp));
		System.out.println ("5)Transaction in most recent order: " + line);
		System.out.println("Current list of " + list.getSize() + " transactions:\n" + list);

		list = new TransactionListLinkedListImpl();
		String id="aaaa"; 
		String location = "";
		int num=10;
		double amt = 100.0;
		int mon = 1; int day = 1;
		for(int i=0; i<TransactionList.MAX_SIZE+5; i++)
		{
			String newId = id + num;
			num++;
			amt +=10.0;
			location = "HEB # " + num;
			GregorianCalendar date7 = new GregorianCalendar(2021, mon, day);
			day++;
			DebitCard dc7 = new DebitCard(newId,date7,amt, location);
			list.add(dc7);
		}
		for (int i=0; i< list.getSize()-1; i++)
		{
			// check that list is in order by date
			boolean result = list.get(i).compareTo(list.get(i+1))>=0;
			assertTrue(result);
			System.out.println(list.get(i).getDateAsString()+ ">= " + list.get(i+1).getDateAsString());
		}
		System.out.println("Current list of " + list.getSize() + " transactions:\n" + list);
		assertEquals(list.getSize(), TransactionList.MAX_SIZE);
	}
	public void testTransactionMethods()
	{
		testDepositConstructorsAndAccessors();
		testDebitCardConstructorsAndAccessors();
		testCheckConstructorsAndAccessors();
		testDepositModifiers();
		testDebitCardModifiers();
		testCheckModifiers();
		testEquals();

	}
	public void testEquals()
	{
		Deposit d1 = new Deposit();
		Deposit d2 = new Deposit();
		int hc1 = 0;
		int hc2 = 0;
		System.out.println("d1 = " + d1);
		System.out.println("d2 = " + d2);
		boolean result = d1.equals(d2);
		assertTrue(result);
		result = d2.equals(d1);
		assertTrue(result);
		hc1 = d1.hashCode();
		hc2 = d2.hashCode();
		System.out.println("hash1 = " + hc1 + " hash2 = " + hc2);
		assertEquals(hc1, hc2);

		GregorianCalendar date =  new GregorianCalendar(Transaction.DEFAULT_YEAR, 5, 11);
		d1.setDate(date);
		result = d1.equals(d2);

		System.out.println("d1 = " + d1);
		System.out.println("d2 = " + d2);
		assertFalse(result);
		hc1 = d1.hashCode();
		hc2 = d2.hashCode();
		assertFalse(hc1== hc2);
		System.out.println("hash1 = " + hc1 + " hash2 = " + hc2);
		d2.setDate(d1.getDateCalendar());
		System.out.println("d1 = " + d1);
		System.out.println("d2 = " + d2);
		result = d1.equals(d2);
		assertTrue(result);
		hc1 = d1.hashCode();
		hc2 = d2.hashCode();
		System.out.println("hash1 = " + hc1 + " hash2 = " + hc2);
		assertTrue(hc1==hc2);
		String str = "aaa111";
		d1.setTransactionId(str);
		result = d1.equals(d2);
		System.out.println("d1 = " + d1);
		System.out.println("d2 = " + d2);
		assertFalse(result);
		d2.setTransactionId(str);
		result = d2.equals(d1);
		System.out.println("d1 = " + d1);
		System.out.println("d2 = " + d2);
		assertTrue(result);

	}
	public void testDebitCardModifiers() {
		System.out.println("-----------------\nTesting DebitCard Modifiers");	
		DebitCard dc1 = new DebitCard();
		GregorianCalendar date =  new GregorianCalendar(Transaction.DEFAULT_YEAR, 5, 11);
		System.out.println ("dc1 = " + dc1);
		dc1.setLocation("  ");
		String result = dc1.getLocation();
		assertEquals(DebitCard.DEFAULT_LOCATION, result);
		dc1.setLocation("");
		result = dc1.getLocation();
		assertEquals(DebitCard.DEFAULT_LOCATION, result);
		dc1.setDate(date);
		String str = "6/11/2020";
		result = dc1.getDateAsString();
		assertEquals(str, result);
		date =  new GregorianCalendar(Transaction.MAX_YEAR+1, 5, 11);
		dc1.setDate(date);
		result = dc1.getDateAsString();
		assertEquals("1/1/2020", result);
		date =  new GregorianCalendar(Transaction.MIN_YEAR-1, 5, 11);
		dc1.setDate(date);
		result = dc1.getDateAsString();
		assertEquals("1/1/2020", result);
		date =  new GregorianCalendar(Transaction.MIN_YEAR, 5, 11);
		dc1.setDate(date);
		result = dc1.getDateAsString();
		assertEquals("6/11/"+Transaction.MIN_YEAR, result);
		date =  new GregorianCalendar(Transaction.MAX_YEAR, 0,1);
		dc1.setDate(date);
		result = dc1.getDateAsString();
		assertEquals("1/1/"+Transaction.MAX_YEAR, result);
		dc1.setLocation("  p  f   ");
		String loc = dc1.getLocation();
		result = "P F";
		assertEquals(result, loc);
		double amt = -100.00;
		dc1.setAmount(amt);
		double diff = 0.0001;
		double res = dc1.getAmount()-amt;
		assertTrue(res<=diff);
		amt = 100.00;
		dc1.setAmount(amt);
		diff = 0.0001;
		res = dc1.getAmount()-amt;
		assertTrue(res<=diff);
		System.out.println ("dc1 = " + dc1);
	}
	public void testDepositModifiers() {
		System.out.println("-----------------\nTesting Deposit Modifiers");	
		Deposit d1 = new Deposit();
		GregorianCalendar date =  new GregorianCalendar(Transaction.DEFAULT_YEAR, 5, 11);


		d1.setDate(date);
		String str = "6/11/2020";
		String result = d1.getDateAsString();
		assertEquals(str, result);
		date =  new GregorianCalendar(Transaction.MAX_YEAR+1, 5, 11);
		d1.setDate(date);
		result = d1.getDateAsString();
		assertEquals("1/1/2020", result);
		date =  new GregorianCalendar(Transaction.MIN_YEAR-1, 5, 11);
		d1.setDate(date);
		result = d1.getDateAsString();
		assertEquals("1/1/2020", result);
		date =  new GregorianCalendar(Transaction.MIN_YEAR, 5, 11);
		d1.setDate(date);
		result = d1.getDateAsString();
		assertEquals("6/11/"+Transaction.MIN_YEAR, result);
		date =  new GregorianCalendar(Transaction.MAX_YEAR, 0,1);
		d1.setDate(date);
		result = d1.getDateAsString();
		assertEquals("1/1/"+Transaction.MAX_YEAR, result);
		double amt = -100.00;
		d1.setAmount(amt);
		double diff = 0.0001;
		double res = d1.getAmount()-amt;
		assertTrue(res<=diff);
		amt = 100.00;
		d1.setAmount(amt);
		diff = 0.0001;
		res = d1.getAmount()-amt;
		assertTrue(res<=diff);
		System.out.println ("d1 = " + d1);
	}

	public void testCheckModifiers() {
		System.out.println("-----------------\nTesting DebitCard Modifiers");	
		Check c1 = new Check();
		GregorianCalendar date =  new GregorianCalendar(Transaction.DEFAULT_YEAR, 5, 11);
		System.out.println ("c1 = " + c1);
		c1.setRecipient("  ");
		String result = c1.getRecipient();
		assertEquals(Check.DEFAULT_RECIPIENT, result);
		c1.setRecipient("");
		result = c1.getRecipient();
		assertEquals(Check.DEFAULT_RECIPIENT, result);
		c1.setDate(date);
		String str = "6/11/2020";
		result = c1.getDateAsString();
		assertEquals(str, result);
		date =  new GregorianCalendar(Transaction.MAX_YEAR+1, 5, 11);
		c1.setDate(date);
		result = c1.getDateAsString();
		assertEquals("1/1/2020", result);
		date =  new GregorianCalendar(Transaction.MIN_YEAR-1, 5, 11);
		c1.setDate(date);
		result = c1.getDateAsString();
		assertEquals("1/1/2020", result);
		date =  new GregorianCalendar(Transaction.MIN_YEAR, 5, 11);
		c1.setDate(date);
		result = c1.getDateAsString();
		assertEquals("6/11/"+Transaction.MIN_YEAR, result);
		date =  new GregorianCalendar(Transaction.MAX_YEAR, 0,1);
		c1.setDate(date);
		result = c1.getDateAsString();
		assertEquals("1/1/"+Transaction.MAX_YEAR, result);
		c1.setRecipient("  p  f   ");
		String loc = c1.getRecipient();
		result = "P F";
		assertEquals(result, loc);
		int num = 1;
		c1.setCheckNumber(num);
		int ret = c1.getCheckNumber();
		assertEquals(num, ret);
		c1.setCheckNumber(Check.MAX_NUMBER);
		ret = c1.getCheckNumber();
		assertEquals(Check.MAX_NUMBER, ret);
		c1.setCheckNumber(Check.MAX_NUMBER+1);
		ret = c1.getCheckNumber();
		assertEquals(0, ret);
		c1.setCheckNumber(Check.MAX_NUMBER-1);
		ret = c1.getCheckNumber();
		assertEquals(Check.MAX_NUMBER-1, ret);
		double amt = -100.00;
		c1.setAmount(amt);
		double diff = 0.0001;
		double res = c1.getAmount()-amt;
		assertTrue(res<=diff);
		amt = 100.00;
		c1.setAmount(amt);
		diff = 0.0001;
		res = c1.getAmount()-amt;
		assertTrue(res<=diff);
		System.out.println ("c1 = " + c1);
	}


	public void testDebitCardConstructorsAndAccessors() {
		System.out.println("-----------------\nTesting DebitCard Constructors and Accessors");
		DebitCard dc1 = new DebitCard();
		GregorianCalendar defaultDate =  new GregorianCalendar(Transaction.DEFAULT_YEAR, 0, 1);
		System.out.println ("dc1 = " + dc1);
		String result = dc1.toString();
		String str = "DEBITCARD";
		assertTrue(result.contains(str));
		str = "1/1/2020";
		assertTrue(result.contains(str));
		str = "0.0";
		assertTrue(result.contains(str));
		int count = utils.MyUtils.numberLines(result);
		assertEquals(0, count);
		GregorianCalendar date1 = new GregorianCalendar(2021, 2, 18);
		dc1 = new DebitCard("xx1",date1, 100.0, "NB ELECTRIC  ");
		System.out.println ("dc1 = " + dc1);
		String loc = dc1.getLocation();
		result = "Nb Electric";
		assertEquals(result, loc);
		result = "3/18/2021";
		str = dc1.getDateAsString();
		assertEquals(result,str);
		result = Transaction.DEFAULT_ID;
		str = dc1.getTransactionId();
		assertEquals(result, str);
		result = "100.0";
		str = dc1.getAmount()+"";
		assertEquals(result, str);
		date1 = new GregorianCalendar(2018, 9, 27);
		dc1 = new DebitCard("xx1kkk",date1, -8.0, " The CORNER sTORe  ");
		System.out.println ("dc1 = " + dc1);
		loc = dc1.getLocation();
		result = "The Corner Store";
		assertEquals(result, loc);
		result = "1/1/2020";
		str = dc1.getDateAsString();
		assertEquals(result,str);
		result = "xx1kkk";
		str = dc1.getTransactionId();
		assertEquals(result, str);
		result = "-8.0";
		str = dc1.getAmount()+"";
		assertEquals(result, str);
		date1 = new GregorianCalendar(2040, 9, 27);
		dc1 = new DebitCard("&fgc;88",date1, 50.0, "   ");
		System.out.println ("dc1 = " + dc1);
		loc = dc1.getLocation();
		result = DebitCard.DEFAULT_LOCATION;
		assertEquals(result, loc);
		result = "1/1/2020";
		str = dc1.getDateAsString();
		assertEquals(result,str);
		result = Transaction.DEFAULT_ID;
		str = dc1.getTransactionId();
		assertEquals(result, str);
		result = "50.0";
		str = dc1.getAmount()+"";
		assertEquals(result, str);
	}

	public void testCheckConstructorsAndAccessors() {
		System.out.println("-----------------\nTesting Check Constructors and Accessors");
		Check c1 = new Check();
		GregorianCalendar defaultDate =  new GregorianCalendar(Transaction.DEFAULT_YEAR, 0, 1);
		System.out.println ("c1 = " + c1);
		String result = c1.toString();
		String str = "CHECK";
		assertTrue(result.contains(str));
		str = "1/1/2020";
		assertTrue(result.contains(str));
		str = "0.0";
		assertTrue(result.contains(str));
		int count = utils.MyUtils.numberLines(result);
		assertEquals(0, count);
		GregorianCalendar date1 = new GregorianCalendar(2021, 2, 18);
		c1 = new Check(";kkk#a1",date1, 100.0, 19999, "River rUn CondoS  ");
		System.out.println ("c1 = " + c1);
		String loc = c1.getRecipient();
		result = "River Run Condos";
		assertEquals(result, loc);
		result = "3/18/2021";
		str = c1.getDateAsString();
		assertEquals(result,str);
		result = Transaction.DEFAULT_ID;
		str = c1.getTransactionId();
		assertEquals(result, str);
		result = "100.0";
		str = c1.getAmount()+"";
		assertEquals(result, str);
		int num = c1.getCheckNumber();
		assertEquals(0, num);
		date1 = new GregorianCalendar(2018, 9, 27);
		c1 = new Check("xx1kkk",date1, 90.0, 4150," AMERICAN canCER  SOCieTY  ");
		System.out.println ("c1 = " + c1);
		loc = c1.getRecipient();
		result = "American Cancer Society";
		assertEquals(result, loc);
		result = "1/1/2020";
		str = c1.getDateAsString();
		assertEquals(result,str);
		result = "xx1kkk";
		str = c1.getTransactionId();
		assertEquals(result, str);
		result = "90.0";
		str = c1.getAmount()+"";
		assertEquals(result, str);
		num = c1.getCheckNumber();
		assertEquals(4150, num);
		date1 = new GregorianCalendar(1900, 9, 27);
		c1 = new Check("&fgc;88",date1, 150.0, -2,  "    ");
		System.out.println ("c1 = " + c1);
		loc = c1.getRecipient();
		result = Check.DEFAULT_RECIPIENT; 
		assertEquals(result, loc);
		result = "1/1/2020";
		str = c1.getDateAsString();
		assertEquals(result,str);
		result = Transaction.DEFAULT_ID;
		str = c1.getTransactionId();
		assertEquals(result, str);
		result = "150.0";
		str = c1.getAmount()+"";
		assertEquals(result, str);
	}
	public void testDepositConstructorsAndAccessors() {
		System.out.println("-----------------\nTesting Deposit Constructors and Accessors");
		Deposit d1 = new Deposit();
		GregorianCalendar defaultDate =  new GregorianCalendar(Transaction.DEFAULT_YEAR, 0, 1);
		System.out.println ("c1 = " + d1);
		String result = d1.toString();
		String str = "DEPOSIT";
		assertTrue(result.contains(str));
		str = "1/1/2020";
		assertTrue(result.contains(str));
		str = "0.0";
		assertTrue(result.contains(str));
		int count = utils.MyUtils.numberLines(result);
		assertEquals(0, count);
		GregorianCalendar date1 = new GregorianCalendar(2021, 2, 18);
		d1 = new Deposit(";kkk#a1",date1, 100.0);
		System.out.println ("d1 = " + d1);
		result = "3/18/2021";
		str = d1.getDateAsString();
		assertEquals(result,str);
		result = Transaction.DEFAULT_ID;
		str = d1.getTransactionId();
		assertEquals(result, str);
		result = "100.0";
		str = d1.getAmount()+"";
		assertEquals(result, str);

		date1 = new GregorianCalendar(2018, 9, 27);
		d1 = new Deposit("333444",date1, 90.0);
		System.out.println ("d1 = " + d1);

		result = "1/1/2020";
		str = d1.getDateAsString();
		assertEquals(result,str);
		result = "333444";
		str = d1.getTransactionId();
		assertEquals(result, str);
		result = "90.0";
		str = d1.getAmount()+"";
		assertEquals(result, str);
		date1 = new GregorianCalendar(1900, 9, 27);
		d1 = new Deposit("&fgc;88",date1, 150.0);
		System.out.println ("d1 = " + d1);
		result = "1/1/2020";
		str = d1.getDateAsString();
		assertEquals(result,str);
		result = Transaction.DEFAULT_ID;
		str = d1.getTransactionId();
		assertEquals(result, str);
		result = "150.0";
		str = d1.getAmount()+"";
		assertEquals(result, str);
	}


	public void testTransactionListUtils()
	{
		System.out.println("\n------------------------------------------------------");
		System.out.println("\n**** Now testing TransactionUtilsImpl methods ****");
		String fileName="";
		Scanner inFile = null;
		PrintWriter outFile = null;

		int count = 0;
		fileName= "transactionEmpty.txt";
		try 
		{
			inFile = new Scanner(new File(fileName));
			System.out.println("****Output of " + fileName + ":");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("FAILURE error, cannot open file: " + fileName +
					" now exiting....");
			assertTrue(false);
			return;
		}
		fileName="transactionEmptyOut.txt";
		try {
			outFile = new PrintWriter(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("FAILURE Error, cannot open file " + fileName + " for writing");
			return;
		}
		count = 0;
		while(inFile.hasNext())
		{
			Transaction trans = TransactionUtilsImpl.readFromScanner(inFile);
			if(trans != null)
			{
				count++;
				System.out.println("Just read: " + trans);
				TransactionUtilsImpl.writeToFile(outFile, trans);
			}
		}
		assertEquals(count, 0);
		fileName= "transactionEmptyOut.txt";
		try 
		{
			inFile = new Scanner(new File(fileName));
			System.out.println("****Output of " + fileName + ":");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("FAILURE error, cannot open file: " + fileName +
					" now exiting....");
			assertTrue(false);
			return;
		}

		count = 0;
		while(inFile.hasNext())
		{
			Transaction trans = TransactionUtilsImpl.readFromScanner(inFile);
			if(trans != null)
			{
				count++;
				System.out.println("Just read: " + trans);
			}
		}
		assertEquals(count, 0);
		inFile.close();
		count = 0;
		fileName= "transaction1.txt";
		try 
		{
			inFile = new Scanner(new File(fileName));
			System.out.println("****Output of " + fileName + ":");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("FAILURE error, cannot open file: " + fileName +
					" now exiting....");
			assertTrue(false);
			return;
		}
		fileName="transaction1Out.txt";
		try {
			outFile = new PrintWriter(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("FAILURE Error, cannot open file " + fileName + " for writing");
			return;
		}
		count = 0;
		while(inFile.hasNext())
		{
			Transaction trans = TransactionUtilsImpl.readFromScanner(inFile);
			if(trans != null)
			{
				count++;
				System.out.println("Just read: " + trans);
				TransactionUtilsImpl.writeToFile(outFile, trans);
			}
		}
		outFile.close();
		assertEquals(count, 1);
		fileName= "transaction1Out.txt";
		try 
		{
			inFile = new Scanner(new File(fileName));
			System.out.println("****Output of " + fileName + ":");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("FAILURE error, cannot open file: " + fileName +
					" now exiting....");
			assertTrue(false);
			return;
		}

		count = 0;
		while(inFile.hasNext())
		{
			Transaction trans = TransactionUtilsImpl.readFromScanner(inFile);
			if(trans != null)
			{
				count++;
				System.out.println("Just read: " + trans);
			}
		}
		assertEquals(count, 1);
		inFile.close();
		count = 0;
		fileName= "transaction3.txt";
		try 
		{
			inFile = new Scanner(new File(fileName));
			System.out.println("****Output of " + fileName + ":");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("FAILURE error, cannot open file: " + fileName +
					" now exiting....");
			assertTrue(false);
			return;
		}
		fileName="transaction3Out.txt";
		try {
			outFile = new PrintWriter(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("FAILURE Error, cannot open file " + fileName + " for writing");
			return;
		}
		count = 0;
		while(inFile.hasNext())
		{
			Transaction trans = TransactionUtilsImpl.readFromScanner(inFile);
			if(trans != null)
			{
				count++;
				System.out.println("Just read: " + trans);
				TransactionUtilsImpl.writeToFile(outFile, trans);
			}
		}
		outFile.close();
		assertEquals(count, 3);
		fileName= "transaction3Out.txt";
		try 
		{
			inFile = new Scanner(new File(fileName));
			System.out.println("****Output of " + fileName + ":");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("FAILURE error, cannot open file: " + fileName +
					" now exiting....");
			assertTrue(false);
			return;
		}

		count = 0;
		while(inFile.hasNext())
		{
			Transaction trans = TransactionUtilsImpl.readFromScanner(inFile);
			if(trans != null)
			{
				count++;
				System.out.println("Just read: " + trans);
			}
		}
		assertEquals(count, 3);
		inFile.close();
		count = 0;
		fileName= "transaction12.txt";
		try 
		{
			inFile = new Scanner(new File(fileName));
			System.out.println("****Output of " + fileName + ":");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("FAILURE error, cannot open file: " + fileName +
					" now exiting....");
			assertTrue(false);
			return;
		}
		fileName="transaction12Out.txt";
		try {
			outFile = new PrintWriter(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("FAILURE Error, cannot open file " + fileName + " for writing");
			return;
		}
		count = 0;
		while(inFile.hasNext())
		{
			Transaction trans = TransactionUtilsImpl.readFromScanner(inFile);
			if(trans != null)
			{
				count++;
				System.out.println("Just read: " + trans);
				TransactionUtilsImpl.writeToFile(outFile, trans);
			}
		}
		outFile.close();
		assertEquals(12, count);
		fileName= "transaction12Out.txt";
		try 
		{
			inFile = new Scanner(new File(fileName));
			System.out.println("****Output of " + fileName + ":");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("FAILURE error, cannot open file: " + fileName +
					" now exiting....");
			assertTrue(false);
			return;
		}

		count = 0;
		while(inFile.hasNext())
		{
			Transaction trans = TransactionUtilsImpl.readFromScanner(inFile);
			if(trans != null)
			{
				count++;
				System.out.println("Just read: " + trans);
			}
		}
		assertEquals(count, 12);
		inFile.close();
		fileName= "transaction12.txt";
		try 
		{
			inFile = new Scanner(new File(fileName));
			System.out.println("****Output of " + fileName + ":");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("FAILURE error, cannot open file: " + fileName +
					" now exiting....");
			assertTrue(false);
			return;
		}
		TransactionList list = TransactionListUtilsImpl.readFromScanner(inFile);
		inFile.close();
		assertEquals(TransactionList.MAX_SIZE,list.getSize());
		System.out.println("read from file of 12 Transactions: \n"+list);
		fileName="transactionOut.txt";
		try {
			outFile = new PrintWriter(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("FAILURE Error, cannot open file " + fileName + " for writing");
			return;
		}
		TransactionListUtilsImpl.writeToFile(outFile,  list);
		outFile.close();
		fileName= "transactionOut.txt";
		try 
		{
			inFile = new Scanner(new File(fileName));
			System.out.println("****Read of " + fileName + ":");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("FAILURE error, cannot open file: " + fileName +
					" now exiting....");
			assertTrue(false);
			return;
		}
		TransactionList list1 = TransactionListUtilsImpl.readFromScanner(inFile);
		assertEquals(list1.getSize(), TransactionList.MAX_SIZE);
		System.out.println("list of 12 --> only room for " + list1.getSize()+ "\n" + list1);
		Deposit dep = (Deposit)list1.get(1);
		assertEquals(dep.getDateAsString(), "9/23/2019");
		assertEquals(dep.getTransactionId(), "56pe32");
		DebitCard debcard = (DebitCard) list1.get(3);
		assertEquals(debcard.getLocation(), "Diamond Shamrock Station");
		assertEquals(debcard.getTransactionId(), "yy2edr");
		Check check = (Check) list1.get(0);
		assertEquals(check.getRecipient(), "Walgreens Pharmacy Inc.");
		assertEquals(check.getCheckNumber(), 4108);
		System.out.println("+++Testing  " + dep);
		System.out.println("+++Testing  " + debcard);
		System.out.println("+++Testing  " + check);
		
		
		assertEquals(list.getSize(), list1.getSize());

		fileName= "transaction1.txt";
		try 
		{
			inFile = new Scanner(new File(fileName));
			System.out.println("****Output of " + fileName + ":");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("FAILURE error, cannot open file: " + fileName +
					" now exiting....");
			assertTrue(false);
			return;
		}
		list = TransactionListUtilsImpl.readFromScanner(inFile);
		inFile.close();
		assertEquals(1,list.getSize());
		System.out.println("read from file of 1 Transaction: \n"+list);
		fileName="transactionOut.txt";
		try {
			outFile = new PrintWriter(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("FAILURE Error, cannot open file " + fileName + " for writing");
			return;
		}
		TransactionListUtilsImpl.writeToFile(outFile,  list);
		outFile.close();
		fileName= "transactionOut.txt";
		try 
		{
			inFile = new Scanner(new File(fileName));
			System.out.println("****Read of " + fileName + ":");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("FAILURE error, cannot open file: " + fileName +
					" now exiting....");
			assertTrue(false);
			return;
		}
		list1 = TransactionListUtilsImpl.readFromScanner(inFile);
		assertEquals(list.getSize(), list1.getSize());

		fileName= "transactionEmpty.txt";
		try 
		{
			inFile = new Scanner(new File(fileName));
			System.out.println("****Output of " + fileName + ":");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("FAILURE error, cannot open file: " + fileName +
					" now exiting....");
			assertTrue(false);
			return;
		}
		list = TransactionListUtilsImpl.readFromScanner(inFile);
		inFile.close();
		assertEquals(0,list.getSize());
		System.out.println("read from file of 0 Transactions: \n"+list);
		fileName="transactionOut.txt";
		try {
			outFile = new PrintWriter(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("FAILURE Error, cannot open file " + fileName + " for writing");
			return;
		}
		TransactionListUtilsImpl.writeToFile(outFile,  list);
		outFile.close();
		fileName= "transactionOut.txt";
		try 
		{
			inFile = new Scanner(new File(fileName));
			System.out.println("****Read of " + fileName + ":");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("FAILURE error, cannot open file: " + fileName +
					" now exiting....");
			assertTrue(false);
			return;
		}
		list1 = TransactionListUtilsImpl.readFromScanner(inFile);
		assertEquals(list.getSize(), list1.getSize());


		System.out.println("**** End of testTransactionUtils() **** \n");
	}




}
