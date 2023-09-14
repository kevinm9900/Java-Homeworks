package transaction;

import java.util.GregorianCalendar;

public class DebitCard extends Transaction {
	
	private String location;
	public static final String DEFAULT_LOCATION = "$$$$";

	public DebitCard()
	{
		super();
	}
	
	public DebitCard(String aId, GregorianCalendar aDate, double aAmount, String aLocation)
	{
		super(aId, aDate, aAmount);
		this.setLocation(aLocation);
	}
	
	public String toString()
	{
		String retValue = "";
		retValue += "DEBITCARD: " + super.toString() + "" + this.getLocation();
		return retValue;
	}
	
	public String getLocation()
	{
		return this.location;
	}
	
	public void setLocation(String aLocation)
	{
		String result = utils.MyUtils.properFormat(aLocation);
		if(result.equals(""))
		{
			this.location = DEFAULT_LOCATION;
		}
		else
		{
			this.location = result;		
		}
	}
}
