package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import recursion.RecursiveMethods;

class Lab14Test {
	
	@Test
	public void test()
	{
		String sName="";
		sName=utils.MyUtils.getNameFromStudent();
		System.out.println("********BEGIN TESTING Lab 14 FOR " + sName + " ***********");
		testFact();
		testFibonacci();
		testTriangle();
		testSilly();
		testGcd();
		testRecv();
		testHanoi();
		testMergeSort();
		
		System.out.println("********END OF TESTING For Lab 14  for " + sName+ " ***********");
	}
	

	public void testMergeSort()
	{
		System.out.println("---------\nMergeSort Testing");
		int [] list1 = {};
		int [] list2 = {123};
		int [] list3 = {35, 7};
		int [] list4 = {44, 15, -3, 22, 33, 15, -22, 88, 100, 3};
		int [] list = null;
		
		list = list1;
		System.out.print("list: [ ");
		for(int i=0; i< list.length; i++)
		{
			System.out.print(list[i] + " ");
		}
		System.out.println("] \n");
		RecursiveMethods.mergeSort(list,  0, list.length-1);
		for(int i=0; i< list.length-1; i++)
		{
			assertTrue(list[i] <= list[i+1]);
		}
		System.out.print("list: [ ");
		for(int i=0; i< list.length; i++)
		{
			System.out.print(list[i] + " ");
		}
		System.out.println("] \n");
		list = list2;
		System.out.print("list: [ ");
		for(int i=0; i< list.length; i++)
		{
			System.out.print(list[i] + " ");
		}
		System.out.println("] \n");
		RecursiveMethods.mergeSort(list,  0, list.length-1);
		for(int i=0; i< list.length-1; i++)
		{
			assertTrue(list[i] <= list[i+1]);
		} 
		System.out.print("list: [ ");
		for(int i=0; i< list.length; i++)
		{
			System.out.print(list[i] + " ");
		}
		System.out.println("] \n");
		list = list3;
		System.out.print("list: [ ");
		for(int i=0; i< list.length; i++)
		{
			System.out.print(list[i] + " ");
		}
		System.out.println("] \n");
		RecursiveMethods.mergeSort(list,  0, list.length-1);
		for(int i=0; i< list.length-1; i++)
		{
			assertTrue(list[i]<= list[i+1]);
		}
		System.out.print("list: [ ");
		for(int i=0; i< list.length; i++)
		{
			System.out.print(list[i] + " ");
		}
		System.out.println("] \n");
		list = list4;
		System.out.print("list: [ ");
		for(int i=0; i< list.length; i++)
		{
			System.out.print(list[i] + " ");
		}
		System.out.println("] \n");
		RecursiveMethods.mergeSort(list,  0, list.length-1);
		for(int i=0; i< list.length-1; i++)
		{
			assertTrue(list[i]<= list[i+1]);
		}
		System.out.print("list: [ ");
		for(int i=0; i< list.length; i++)
		{
			System.out.print(list[i] + " ");
		}
		System.out.println("] \n");
		
		System.out.println("---------\n********SUCCESS, testMergeSort completed");
	}

	public void testFact()
	{
		System.out.println("---------\n Testing Factorial");
		assertEquals(-1, RecursiveMethods.fact(-8));
		assertEquals(-1, RecursiveMethods.fact(-1));
		assertEquals(1, RecursiveMethods.fact(1));
		assertEquals(1, RecursiveMethods.fact(0));
		assertEquals(2, RecursiveMethods.fact(2));
		assertEquals(6, RecursiveMethods.fact(3));
		assertEquals(120, RecursiveMethods.fact(5));
		assertEquals(720, RecursiveMethods.fact(6));
		System.out.println("----\n****SUCCESS, testFact completed.");
		
	}
	
	public void testSilly()
	{
		System.out.println("---------\nsilly  Testing");
		assertEquals(5, RecursiveMethods.silly(3,2));
		assertEquals(39, RecursiveMethods.silly(-23,-12));
		assertEquals(5, RecursiveMethods.silly(-3,-22));
		assertEquals(59, RecursiveMethods.silly(12,25));
		assertEquals(3, RecursiveMethods.silly(10,10));
		assertEquals(257729, RecursiveMethods.silly(30, 90));
		assertEquals(3, RecursiveMethods.silly(0,0));
		assertEquals(52438031, RecursiveMethods.silly(10,100));
		System.out.println("-------\n****SUCCESS, testSilly completed.");
	}

	public void testFibonacci()
	{
		System.out.println("---------\nFibonacci Testing");
		assertEquals(0, RecursiveMethods.fib(1));
		assertEquals(1, RecursiveMethods.fib(2));
		assertEquals(1, RecursiveMethods.fib(3));
		assertEquals(-1, RecursiveMethods.fib(0));
		assertEquals(-1, RecursiveMethods.fib(-1));
		assertEquals(-1, RecursiveMethods.fib(-22));
		assertEquals(2, RecursiveMethods.fib(4));
		assertEquals(3, RecursiveMethods.fib(5));
		assertEquals(5, RecursiveMethods.fib(6));
		assertEquals(4181, RecursiveMethods.fib(20));
		assertEquals(5702887, RecursiveMethods.fib(35));
		System.out.println("SUCCESS, testFibonacci completed.");
	}
	
	public void testTriangle()
	{
		System.out.println("---------\nPascal's Triangle Testing");
		assertEquals(0, RecursiveMethods.fib(1));
		assertEquals(0, RecursiveMethods.triangle(1,2));
		assertEquals(0, RecursiveMethods.triangle(-1,2));
		assertEquals(0, RecursiveMethods.triangle(-3, -5));
		assertEquals(1, RecursiveMethods.triangle(1,1));
		assertEquals(1, RecursiveMethods.triangle(1,0));
		assertEquals(1, RecursiveMethods.triangle(2,2));
		assertEquals(2, RecursiveMethods.triangle(2,1));
		assertEquals(15, RecursiveMethods.triangle(6,2));
		assertEquals(3003, RecursiveMethods.triangle(14,8));
		assertEquals(15504, RecursiveMethods.triangle(20,15));
		System.out.println("--------\n***SUCCESS, testTriangle completed.");
	}
	
	public void testHanoi()
	{
		System.out.println("---------\nTowers of Hanoi Testing");
		assertEquals(0, RecursiveMethods.fib(1));
		char source='A';
		char middle='B';
		char dest = 'C';
		RecursiveMethods.hanoi(-100,source,middle,dest);
		RecursiveMethods.hanoi(-1,source,middle,dest);
		RecursiveMethods.hanoi(0,source,middle,dest);
		System.out.println("Move 1 ring...");
		RecursiveMethods.hanoi(1,source,middle,dest);
		System.out.println("Move 2 rings...");
		RecursiveMethods.hanoi(2,source,middle,dest);
		System.out.println("Move 4 rings...");
		RecursiveMethods.hanoi(4,source,middle,dest);
		System.out.println("-----------\n***SUCCESS, testHanoi completed.");
	}
	
	public void testGcd()
	{
		System.out.println("---------\nGreatest Common Divisor Testing");
		assertEquals(0, RecursiveMethods.fib(1));
		assertEquals(-1, RecursiveMethods.gcd(-1,0));
		assertEquals(-1, RecursiveMethods.gcd(1,-1));
		assertEquals(-1, RecursiveMethods.gcd(-1,-10));
		assertEquals(1, RecursiveMethods.gcd(1,0));
		assertEquals(0, RecursiveMethods.gcd(0,0));
		assertEquals(1, RecursiveMethods.gcd(0,1));
		assertEquals(5, RecursiveMethods.gcd(0,5));
		assertEquals(1, RecursiveMethods.gcd(1,0));
		assertEquals(12, RecursiveMethods.gcd(12,12));
		assertEquals(6, RecursiveMethods.gcd(6,12));
		assertEquals(1, RecursiveMethods.gcd(3,31));
		assertEquals(1, RecursiveMethods.gcd(13,37));
		assertEquals(7, RecursiveMethods.gcd(511,7));
		System.out.println("--------\n*****SUCCESS, testGcd completed.");
	}
	
	public void testRecv()
	{
		System.out.println("---------\nTest of Recursive  ");
		assertEquals(0, RecursiveMethods.fib(1));
		assertEquals(0, RecursiveMethods.recv(-1,0));
		assertEquals(-1, RecursiveMethods.recv(-1,1));
		assertEquals(1, RecursiveMethods.recv(1,1));
		assertEquals(2, RecursiveMethods.recv(2,1));
		assertEquals(-2, RecursiveMethods.recv(-2,1));
		assertEquals(0, RecursiveMethods.recv(0, -3));
		assertEquals(0, RecursiveMethods.recv(0, 42));
		assertEquals(3577, RecursiveMethods.recv(511,7));
		System.out.println("---------\n*******SUCCESS, testRecv completed.");
	}

	
}
