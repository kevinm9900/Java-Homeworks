package transactionlist;

import java.util.GregorianCalendar;

import transaction.Transaction;

public class TransactionListLinkedListImpl implements TransactionList
{
	private MyNode <Transaction> first;
	private int count;
	
public TransactionListLinkedListImpl()
{
	this.first = null;
	this.count = 0;
}
public String toString()
{
	String retValue = "";
	MyNode <Transaction> current = this.first;
	while(current != null)
	{
		retValue += current.data + "\n";
		current = current.link;
	}
	return retValue;
}

public int getSize()
{
	return count;
}

public boolean contains (Transaction transaction)
{
	MyNode<Transaction> current = this.first;
	while(current != null)
	{
		if(current.data.equals(transaction))
		{
			return true;
		}
		current = current.link;
		}
	return false;
	}
public boolean add(Transaction transaction)
{
	if(this.getSize() == MAX_SIZE)
		return false;
	if(this.contains(transaction))
		return false;
	MyNode <Transaction> node = new MyNode<Transaction>();
	node.data = transaction;
	node.link = null;
	MyNode<Transaction> previous = null;
	MyNode<Transaction> current = this.first;
	while (current != null)
	{
		if(current.data.compareTo(transaction) < 0)
			break;
		previous = current;
		current = current.link;
	}
	if(previous==null)
	{
		node.link = this.first;
		this.first = node;
	}
	else
	{
		node.link = previous.link;
		previous.link = node;
	}
	this.count ++;
	return true;
}

//receives: nothing
	// returns:  the transaction in the list at given position. 
	//       uses zero-based positions, so 0 is the position of the first transaction in the list
	//       returns null if received position is out of range for this transaction list instance.
	//  Example use:  
	//        TransactionList tList = new TransactionListImpl("transactions.txt");    
	// 		  Transaction t1 = list.get(0);
	//        assertTrue(t1!=null);
	public Transaction get(int position) {
		if (count == 0)
			return null;
		MyNode <Transaction> curr = this.first;
		if (position == 0) 
			return curr.data;
		MyNode <Transaction> prev = null;
		int i = 0;
		while (i <= position)
		{
			if (i == position)
			{
				return curr.data;
			} // end if
			else if (curr.link != null)
			{
				prev = curr;
				curr = curr.link;
			}
			else
			{
				break;
			}
			i++;
		}
		return null;
	}

	//receives: nothing
	// returns:  the position of received transaction in the list 
	//       (uses equals method which is overloaded when matching)
	//       returns -1 if received transaction is not found in current list at any position
	//        TransactionList list = new TransactionListImpl("transaction.txt");
	//        Deposit d2 = new Deposit("aabb","3/15/2017", 100.00).
	//		  int position = list.find(d2);
	//	      assertTrue(position != -1);
	public int find(Transaction transaction) {
		if (count == 0)
			return -1;
		MyNode <Transaction> curr = this.first;
		int i = 0;
		while (curr != null)
		{
			if (curr.data.getTransactionId().equals(transaction.getTransactionId()))
				return i;
			curr = curr.link;
			i++;
		}
		return -1;
	}

	//receives: nothing
	// returns: returns a String of all transactions in current list instance that occurred on given date
	// each transaction is separated by a newline character. Returns an empty string
	// if no transactions occurred on received date.
	public String getTransactionListByDate(GregorianCalendar date) {
		
		String temp = "";
		int matches = 0;
		MyNode <Transaction> curr = this.first;
		MyNode <Transaction> prev = null;
		while (curr != null)
		{
			if (curr.data.getDateCalendar().equals(date))
			{
				temp += curr.data + "\n";
				matches++;
			}
			prev = curr;
			curr = curr.link;
		}
		if (matches == 0)
			return "";
		return temp;
	}


	// receives: nothing
	// returns:  transaction  in this list instance that matches given transaction, removes it from list
	//       list remains sorted by date after removal. (most recent to least recent)
	//       if transaction is not in the list instance, returns null
	public Transaction remove(Transaction transaction) {

		int loc = this.find(transaction);
		if (loc == -1)
			return null;
		MyNode <Transaction> prev = null;
		MyNode <Transaction> curr = this.first;
		while (curr != null)
		{
			if (curr.data.equals(transaction))
				break;
			prev = curr;
			curr = curr.link;
		}
		Transaction retTrans = curr.data;
		if (prev == null)
			this.first = this.first.link;
		else
			prev.link = curr.link;
		count--;
		return retTrans;
	}

	//receives: nothing
	// task: all transactions removed from this list instance, 
	public void clear() {
		this.count = 0;
		this.first = null;
	}
}

