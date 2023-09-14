package transactionlist;

import java.io.PrintWriter;

import java.util.GregorianCalendar;
import java.util.Scanner;

import transaction.Check;
import transaction.DebitCard;
import transaction.Deposit;
import transaction.Transaction;
import transaction.TransactionUtilsImpl;

public class TransactionListUtilsImpl {
	
	//receives: inFile, a Scanner, already open and ready to read from
	// returns: a TransactionList instance containing all/any Transactions found
	// on received inFile, if none found the returned list is left empty.
	public static TransactionList readFromScanner(Scanner inFile)
	{
		TransactionList list = new TransactionListLinkedListImpl();
		while (inFile.hasNext())
		{
			Transaction trans = TransactionUtilsImpl.readFromScanner(inFile);
			if(trans!= null)
			{
				list.add(trans);
			}
		}
		return list;
	}
	// receives: outFile - PrintWriter to write to, already open and ready for writing
	// transList - a TransactionList instance (not null, maybe or may not be empty)
	// task: writes each transaction from transList to outFile
	public static void writeToFile(PrintWriter out, TransactionList list)
	{
		for (int index = 0; index<list.getSize(); index++)
		{
			Transaction trans = list.get(index);
			TransactionUtilsImpl.writeToFile(out, trans);
		}
	}
}