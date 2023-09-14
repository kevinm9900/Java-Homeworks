package reservation;

import java.io.PrintWriter;
import java.util.Scanner;

import reservation.HotelReservation;
import reservation.CarReservation;
import reservation.FlightReservation;

public class ReservationUtilsImpl 
{
	//receives: inFile, a Scanner instance already open and ready for reading 
	//  tries to read one Reservation derived instance from file, 
	//if successful, creates Reservation-derived instance, populates it, returns it, assumes data in order expected
	//returns null if no data on inFile to read, returns populated Reservation-derived instance otherwise
	public static Reservation readFromScanner (Scanner inFile)
	{
		if (!inFile.hasNext()) {
			return null;
		}
		Reservation aReservation = null;
		String dataType = inFile.next().trim();
		String confirmationID;
		String startDate;
		String endDate;
		String personCount;
		String hotelName;
		String vin;
		String make;
		String year;
		String depart;
		String arrive;
		if (!dataType.equals("HOTEL") && !dataType.equals("CAR") && !dataType.equals("FLIGHT")) {
			return null;
		}
		if (inFile.hasNext()) {
			confirmationID = inFile.next();
		}
		else {
			return null;
		}
		if (inFile.hasNext()) {
			startDate = inFile.next();
		}
		else {
			return null;
		}
		if (inFile.hasNext()) {
			endDate = inFile.nextLine().trim();
		}
		else {
			return null;
		}
		if (dataType.equals("HOTEL")) {
			if (inFile.hasNext()) {
				personCount = inFile.next();
			}
			else {
				return null;
			}
			if (inFile.hasNext()) {
				hotelName = inFile.nextLine().trim();
			}
			else {
				return null;
			}
			HotelReservation aHotel = new HotelReservation(confirmationID);
			aHotel.setEndDate(startDate);
			aHotel.setStartDate(endDate);
			aHotel.setHotelName(hotelName);
			Integer a = Integer.valueOf(personCount);
			aHotel.setNumPersons(a);
			aReservation = aHotel;
		}
		else if (dataType.equals("CAR")) {
			if (inFile.hasNext()) {
				vin = inFile.next();
			}
			else {
				return null;
			}
			if (inFile.hasNext()) {
				year = inFile.next();
			}
			else {
				return null;
			}
			if (inFile.hasNextLine()) {
				make = inFile.nextLine().trim();
			}
			else {
				return null;
			}
			CarReservation aCar = new CarReservation(confirmationID);
			aCar.setStartDate(startDate);
			aCar.setEndDate(endDate);
			CarMake aMake = CarMake.valueOf(make);
			Integer years = Integer.valueOf(year);
			RentalCar current = new RentalCar(vin, years, aMake);
			aCar.setCar(current);
			aReservation = aCar;
		}
		else if (dataType.equals("FLIGHT")) {
			if (inFile.hasNext()) {
				depart = inFile.next();
			}
			else {
				return null;
			}
			if (inFile.hasNext()) {
				arrive = inFile.nextLine().trim();
			}
			else {
				return null;
			}
			FlightReservation aFlight = new FlightReservation(confirmationID);
			aFlight.setStartDate(startDate);
			aFlight.setEndDate(endDate);
			aFlight.setArrivalCode(arrive);
			aFlight.setDepartCode(depart);
			aReservation = aFlight;
			
		}
	
		return aReservation;
	}

	//receives: reservation to write, outFile, already open and ready to be written to 
	//writes received reservation instance to file in same format as read in with 
	//including leading HOTEL or CAR or FLIGHT on first line of output
	//returns: nothing
	public static void writeToFile(PrintWriter outFile, Reservation reservation) {
		if (reservation instanceof HotelReservation) {
			HotelReservation aHotel = (HotelReservation) reservation;
			outFile.print("HOTEL ");
			outFile.print(aHotel.getConfirmationNumber() + " ");
			outFile.print(aHotel.getStartDateAsString() + " ");
			outFile.print(aHotel.getEndDateAsString());
			outFile.println();
			outFile.print(aHotel.getNumPersons() + " ");
			outFile.print(aHotel.getHotelName());
			outFile.println();
		}
		else if (reservation instanceof CarReservation) {
			CarReservation aCar = (CarReservation) reservation;
			outFile.print("CAR ");
			outFile.print(aCar.getConfirmationNumber() + " ");
			outFile.print(aCar.getStartDateAsString() + " ");
			outFile.print(aCar.getEndDateAsString());
			outFile.println();
			RentalCar current = aCar.getCar();
			outFile.print(current.getVin() + " ");
			outFile.print(current.getYear() + " ");
			outFile.print(current.getMake());
			outFile.println();
		}
		else if (reservation instanceof FlightReservation) {
			FlightReservation aFlight = (FlightReservation) reservation;
			outFile.print("FLIGHT ");
			outFile.print(aFlight.getConfirmationNumber() + " ");
			outFile.print(aFlight.getStartDateAsString() + " ");
			outFile.print(aFlight.getEndDateAsString() + " ");
			outFile.println();
			outFile.print(aFlight.getDepartCode() + " ");
			outFile.print(aFlight.getArrivalCode());
			outFile.println();
		}
	}
}
