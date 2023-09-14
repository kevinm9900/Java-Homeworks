package reservation; 

import java.io.PrintWriter;
import java.util.Scanner;
public class RentalCarUtilsImpl 
{
	public static RentalCar readFromScanner(Scanner inFile)
	{
		String carVin = "";
		int carYear = 0;
		String carMake= "";
		
		if(inFile.hasNext())
		{
			carVin= inFile.next();
		}
		else 
		{
			return null;
		}
		if(inFile.hasNextInt())
		{
			carYear= inFile.nextInt();
		}
		else
		{
			return null;
		}
		if(inFile.hasNext())
		{
			carMake=inFile.next().trim().toUpperCase();
			inFile.nextLine();
		}
		else
		{
			return null;
		}
		
		RentalCar car= new RentalCar();
		
		car.setVin(carVin);
		car.setYear(carYear);

		
		CarMake aMake= CarMake.TOYOTA;   //used TOYOTA as a place holder
		try
		{
			aMake = CarMake.valueOf(carMake); 
//			car.setMake(aMake);		
		} 										
		catch(IllegalArgumentException  e)  //is the name you assign to the exception you receive
		{
			System.out.println("Invalid String for houseType found:" + e);
			return null;
		}  				//if the try is not valid it will return the error message
//		car.setMake(aMake);
		
		return car;   //return the house created and populated	
	}
	public static void writeToFile(PrintWriter outFile, RentalCar car)
	{
		outFile.println(car.getVin());
		outFile.println(car.getYear());
		outFile.println(car.getMake());	
	}
}