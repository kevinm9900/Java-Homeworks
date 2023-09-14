package transaction;

import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class TransactionUtilsImpl 
{
	public static Transaction readFromScanner(Scanner inFile)
	{
		String transType = "";
		String id = "";
		String dateStr = "";
		double amt = 0.0;
		int checkNum = 0;
		String rec = "";
		String loc = "";
		
		if(inFile.hasNext())
		{
			transType = inFile.next();
		}
		else
		{
			return null;
		} 
		if(inFile.hasNext())
		{
			id = inFile.next();
		}
		else
		{
			return null;
		}
		if(inFile.hasNext())
		{
			dateStr = inFile.next();
		}
		else
		{
			return null;
		}
		if(inFile.hasNextDouble())
		{
			amt = inFile.nextDouble();
			inFile.nextLine();
		}
		else
		{
			return null;
		}
		GregorianCalendar gc= utils.MyUtils.stringToDate(dateStr);
		if(transType.toUpperCase().equals("DEPOSIT"))
		{
			Deposit d1 = new Deposit(id,gc,amt);
			return d1;
		}
		else if(transType.toUpperCase().equals("DEBITCARD"))
		{
			if(inFile.hasNext())
			{
				loc = inFile.nextLine();
			}
			else
			{
				return null;
			}
			DebitCard dc1 = new DebitCard(id,gc,amt,loc);
			return dc1;
		}
		else if(transType.toUpperCase().equals("CHECK"))
		{
			if(inFile.hasNextInt())
			{
				checkNum = inFile.nextInt();
			}
			else
			{
				return null;
			}
			if(inFile.hasNext())
			{
				rec = inFile.nextLine();
			}
			else
			{
				return null;
			}
			Check c1 = new Check(id,gc,amt,checkNum,rec);
			return c1;
		}
		else
		{
			return null;
		}
	
	}
	public static void writeToFile(PrintWriter outFile, Transaction trans)
	{
		if(trans instanceof Deposit)
		{
			Deposit d1 = (Deposit)trans;
			outFile.println("DEPOSIT " + d1.getTransactionId() + " " + d1.getDateAsString() + " " + d1.getAmount());
		}
		else if(trans instanceof DebitCard)
		{
			DebitCard dc1 = (DebitCard)trans;
			outFile.println("DEBITCARD " + dc1.getTransactionId() + " " + dc1.getDateAsString() + " " + dc1.getAmount());
			outFile.println(dc1.getLocation());
		}
		else if(trans instanceof Check)
		{
			Check c1 = (Check) trans;
			outFile.println("CHECK " + c1.getTransactionId() + " " + c1.getDateAsString() + " " + c1.getAmount());
			outFile.println(c1.getCheckNumber() + " " + c1.getRecipient());

		}
	}
}
