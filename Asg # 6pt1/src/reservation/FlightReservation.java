package reservation;

public class FlightReservation extends Reservation
{
	private String departAir;
	private String arriveAir;
	
	public FlightReservation (String confirmNumber)
	{
		super(confirmNumber);
		this.departAir = "AUS";
		this.arriveAir = "AUS";
	}
	
	public String toString()
	{
		String temp = "";
		temp += "FLIGHT: " + super.toString();
		temp += "Depart: " + this.getDepartCode() + " Arrive: " + this.getArrivalCode();
		return temp;
	}
	
	public String getDepartCode()
	{
		return this.departAir;
	}
	
	public String getArrivalCode()
	{
		return this.arriveAir;
	}
	
	public void setDepartCode (String aCode)
	{
		if(aCode.length() == 3)
		{
			boolean valid = true;

			for(int i = 0; i<aCode.length(); i++)
			{
				char ch = aCode.charAt(i);
				if(!Character.isLetter(ch))
				{
					valid = false;
				}
			}

			if(valid == true)
			{
				aCode = aCode.toUpperCase();
				this.departAir = aCode;
			}
		}
		else
		{
			this.departAir = "AUS";
		}
	}
	
	public void setArrivalCode (String aCode)
	{
		if(aCode.length() == 3)
		{
			boolean valid = true;

			for(int i = 0; i<aCode.length(); i++)
			{
				char ch = aCode.charAt(i);
				if(!Character.isLetter(ch))
				{
					valid = false;
				}
			}

			if(valid == true)
			{
				aCode = aCode.toUpperCase();
				this.arriveAir = aCode;
			}
		}
		else
		{
			this.arriveAir = "AUS";
		}
	}
}