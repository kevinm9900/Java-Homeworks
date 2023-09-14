package transaction;

import java.util.GregorianCalendar;

public class Check extends Transaction {
private String recipient;
private int checkNumber;
public static final String DEFAULT_RECIPIENT = "$$$$";
public static final int MAX_NUMBER = 9999;

public Check()
{
	super();
	this.checkNumber = 0;
	this.recipient = DEFAULT_RECIPIENT;
}

public Check(String aId, GregorianCalendar aDate, double aAmount, int aCheckNum, String aRecipient)
{
	super(aId, aDate, aAmount);
	this.setCheckNumber(aCheckNum);
	this.setRecipient(aRecipient);
}

public void setCheckNumber(int aNum)
{
	if(aNum < 0 || aNum> MAX_NUMBER)
	{
		this.checkNumber = 0;
	}
	else
	{
		this.checkNumber = aNum;
	}
}

public int getCheckNumber()
{
	return this.checkNumber;
}

public String getRecipient()
{
	return this.recipient;
}

public void setRecipient(String aRecipient)
{
	String result = utils.MyUtils.properFormat(aRecipient);
	if(result.equals(""))
	{
		this.recipient = DEFAULT_RECIPIENT;
	}
	else
	{
		this.recipient = result;
	}
}

public String toString()
{
	String retValue = "";
	retValue += "CHECK: " + super.toString()+ "#" + this.getCheckNumber() + "" + "to" + this.getRecipient();
	return retValue;
}
}
