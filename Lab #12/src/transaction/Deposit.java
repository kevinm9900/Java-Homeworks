package transaction;

import java.util.GregorianCalendar;

public class Deposit extends Transaction {

	public Deposit()
	{
		super();
	}
	
	public Deposit(String aId, GregorianCalendar aDate, double aAmount)
	{
		super(aId, aDate, aAmount);
	}
	
	public String toString()
	{
		String retValue = "";
		retValue += "DEPOSIT: " + super.toString();
		return retValue;
	}
}
