package reservation;

import java.util.GregorianCalendar;

public class CarReservation extends Reservation
{
	private RentalCar car;
	public CarReservation (String confirmNum)
	{
		super(confirmNum);
		car = new RentalCar();
	}
	public CarReservation(String aConfirmNum, GregorianCalendar aStartDate, GregorianCalendar aEndDate, RentalCar aRentalCar)
	{
	super(aConfirmNum);
	this.setCar(aRentalCar);		
	}
	public void setCar(RentalCar aCar)
	{
		this.car = aCar;
	}
	public RentalCar getCar()
	{
		return this.car;
	}
	public String toString()
	{
		String retValue = "";
		retValue += "CAR: " + super.toString()+ " "+ this.getCar();
		return retValue;
	}
}
			