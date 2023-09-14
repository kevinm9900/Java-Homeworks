package reservation;

import java.util.Objects;

public class RentalCar {

	public static final String DEFAULT_VIN = "0000";
	public static final CarMake DEFAULT_CARMAKE = CarMake.TOYOTA;
	public static final int MAX_YEAR = 2020;
	public static final int MIN_YEAR = 2015;
	private String vin;
	private CarMake carMake;
	private int year;

	public RentalCar()
	{

		this.vin = DEFAULT_VIN;
		this.carMake = DEFAULT_CARMAKE;
		this.year = MAX_YEAR;

	}

	public RentalCar(String aVin, int aYear, CarMake aMake)
	{

		this.setVin(aVin);
		this.setYear(aYear);
		this.setMake(aMake);

	}
	public String toString()
	{

		String retValue = "VIN: " +this.getVin() + " Year: " +this.getYear()+ " Car Make: " + this.getMake();
		return retValue;

	}

	public String getVin()
	{

		return this.vin;

	}

	public CarMake getMake()
	{

		return this.carMake;

	}

	public int getYear()
	{

		return this.year;

	}

	public void setVin(String aVin)
	{

		if(!(aVin.length() == 4))
		{

			this.vin = DEFAULT_VIN;

		}
		else 	
		{		
			int count = utils.MyUtils.countLettersOrDigits(aVin);
			if(!(count == 4))
			{

				this.vin = DEFAULT_VIN;

			}
			else
			{
				aVin = aVin.toUpperCase();
				this.vin = aVin;
			}
		}
		}

		public void setMake(CarMake aMake)
		{

			this.carMake = aMake;

		}

		public void setYear (int aYear)
		{

			if(aYear <= 2020 && aYear >= 2015)
			{

				this.year = aYear;

			}

			else

			{

				this.year = MAX_YEAR;

			}
		}
		public boolean equals(Object o)
		{
			if(this == o) return true;
			if (!(o instanceof RentalCar)) return false;
			RentalCar rentalCar = (RentalCar) o;
			return getYear() == rentalCar.getYear() &&
					Objects.equals(getVin(), rentalCar.getVin()) &&
					getMake() == rentalCar.getMake();
		}
		public int hashCode()
		{
			return Objects.hash(getVin(),getMake(),getYear());
		}
	}



