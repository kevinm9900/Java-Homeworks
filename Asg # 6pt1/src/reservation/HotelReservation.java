package reservation;

public class HotelReservation extends Reservation {

	private String hotelName;
	private int numPersons;
	public static final String DEFAULT_HOTEL_NAME = "$$$$";

	public HotelReservation(String confirmNumber)
	{
		super(confirmNumber);
		this.numPersons = 1;
		this.hotelName = DEFAULT_HOTEL_NAME;
	}
	public void setNumPersons(int aNumPersons)
	{
		if(aNumPersons <= 0 || aNumPersons> 100)
		{
			this.numPersons = 1;
		}
		else
		{
			this.numPersons = aNumPersons;
		}
	}

	public int getNumPersons()
	{
		return this.numPersons;
	}

	public String getHotelName()
	{
		return this.hotelName;
	}

	public void setHotelName(String aHotelName)
	{
		String result = utils.MyUtils.properFormat(aHotelName);
		if(result.equals(""))
		{
			this.hotelName = DEFAULT_HOTEL_NAME;
		}
		else
		{
			this.hotelName = result;
		}
	}

	public String toString()
	{
		String retValue = "";
		retValue += "HOTEL: " + super.toString()+ "#" + this.getNumPersons() + "" + "to" + this.getHotelName();
		return retValue;
	}
}
