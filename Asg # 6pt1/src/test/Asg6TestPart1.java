package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import reservation.CarMake;
import reservation.CarReservation;
import reservation.FlightReservation;
import reservation.HotelReservation;
import reservation.RentalCar;
import reservation.Reservation;
import reservation.ReservationUtilsImpl;


class Asg6TestPart1 {

	private String sName="Some Student";
	@Test
	public void test() {
		System.out.println("Spring 2020 Assignment #6 Test PART 1");

		sName=utils.MyUtils.getNameFromStudent();
		utils.MyUtils.printTimeStamp("Starting time: ");
		System.out.println("\n----------------********BEGIN TESTING Part 1 FOR " + sName + " ***********");
		reservationTestHotel();
        reservationTestCar();
		reservationTestFlight();
		reservationTestEqualsAndHashCode();
		reservationTestFileIO();
		utils.MyUtils.printTimeStamp("Ending time: ");
		System.out.println("\n----------------********END OF TESTING  Part 1 FOR " + sName + " ***********");
	}

	public void reservationTestEqualsAndHashCode() {
		utils.MyUtils.printTimeStamp("\n---------------- " +sName + " -- BEGINS reservationTestEqualsAndHashCode()");
		GregorianCalendar date1 = new GregorianCalendar(2020, 8, 15);
		GregorianCalendar date2 = new GregorianCalendar(2020, 8, 21);
		GregorianCalendar today = new GregorianCalendar();
		int month = today.get(Calendar.MONTH);
		int day = today.get(Calendar.DAY_OF_MONTH);
		int year = today.get(Calendar.YEAR);
		GregorianCalendar date3 = new GregorianCalendar(year, month, day);
		FlightReservation fres1 = new FlightReservation("F222");
		FlightReservation fres2 = new FlightReservation("F333");
		boolean result = fres1.equals(fres2);
		int hc = fres1.hashCode();
		int hc2 = fres2.hashCode();
		assertFalse(hc == hc2);
		assertFalse(result);
		result = fres1.equals(fres1);
		assertTrue(result);
		result = fres2.equals(fres1);
		assertFalse(result);
		fres2.setConfirmationNumber("F222");
		result = fres1.equals(fres2);
		assertTrue(result);
		hc = fres1.hashCode();
		hc2 = fres2.hashCode();
		System.out.println("Hash codes: " + hc + " == " + hc2);
		assertTrue(hc == hc2);

		fres1.setArrivalCode("CLE");
		fres2.setArrivalCode("CLE");
		hc = fres1.hashCode();
		hc2 = fres2.hashCode();
		System.out.println("Hash codes: " + hc + " == " + hc2);
		assertTrue(hc == hc2);

		HotelReservation h1 = new HotelReservation("hr11");
		HotelReservation h2 = new HotelReservation("hr11");
		result = h1.equals(h2);
		hc = h1.hashCode();
		hc2 = h2.hashCode();
		assertTrue(hc==hc2);
		assertTrue(result);
		System.out.println("Hash codes: " + hc + " == " + hc2);
		h1.setEndDate("12/2/2020");
		result = h1.equals(h2);
		hc = h1.hashCode();
		hc2 = h2.hashCode();
		assertFalse(hc==hc2);
		assertFalse(result);
		System.out.println("Hash codes: " + hc + " not equal to " + hc2);
		h2 = new HotelReservation("hr11");
		result = h1.equals(h2);
		h2.setStartDate("1/1/2020");
		result = h1.equals(h2);
		hc = h1.hashCode();
		hc2 = h2.hashCode();
		assertFalse(hc==hc2);
		assertFalse(result);
		System.out.println("Hash codes: " + hc + " not equal to " + hc2);
		h2 = new HotelReservation("hr22");
		result = h1.equals(h2);
		hc = h1.hashCode();
		hc2 = h2.hashCode();
		assertFalse(hc==hc2);
		assertFalse(result);
		System.out.println("Hash codes: " + hc + " not equal to " + hc2);
	}

	public void reservationTestFlight()
	{
		utils.MyUtils.printTimeStamp("\n----------------" + sName + " -- BEGINS reservationTestFlight()");
		GregorianCalendar date1 = new GregorianCalendar(2020, 8, 15);
		GregorianCalendar date2 = new GregorianCalendar(2020, 8, 21);
		GregorianCalendar today = new GregorianCalendar();
		int month = today.get(Calendar.MONTH);
		int day = today.get(Calendar.DAY_OF_MONTH);
		int year = today.get(Calendar.YEAR);
		GregorianCalendar date3 = new GregorianCalendar(year, month, day);
		FlightReservation fres1 = new FlightReservation("F222");
		FlightReservation fres2 = new FlightReservation("F333");
		fres1.setArrivalCode("CLE");
		assertTrue(fres1.getArrivalCode().equals("CLE"));
		fres1.setDepartCode("kkkccc333");
		assertTrue(fres1.getDepartCode().equals("AUS"));
		fres1.setArrivalCode("CLEVELAND");
		assertTrue(fres1.getArrivalCode().equals("AUS"));
		fres1.setArrivalCode("aus");
		assertEquals(fres1.getArrivalCode(), "AUS");
		fres1.setDepartCode("atl");
		assertTrue(fres1.getDepartCode().equals("ATL"));
		assertTrue(fres1.getStartDate().equals(date3));
		assertTrue(fres1.getEndDate().equals(date3));
		fres1.setEndDate("8/10/2020");
		fres1.setStartDate("8/1/2020");
		assertTrue(fres1.getStartDate().equals(utils.MyUtils.stringToDate("8/1/2020")));
		assertTrue(fres1.getEndDate().equals(utils.MyUtils.stringToDate("8/10/2020")));

		String out = fres1.toString();
		System.out.println("Flight reservation: " + out);
		assertTrue(out.contains("8/1/2020"));
		assertTrue(out.contains("ATL"));
		assertTrue(out.contains("AUS"));
		assertTrue(out.contains("FLIGHT"));

		utils.MyUtils.printTimeStamp(sName + " -- ENDS reservationTestFlight()");
	}
	public void reservationTestCar()
	{
		utils.MyUtils.printTimeStamp("\n----------------" +sName + " -- BEGINS reservationTestCar()");
		GregorianCalendar date1 = new GregorianCalendar(2020, 9, 15);
		GregorianCalendar date2 = new GregorianCalendar(2020, 9, 21);
		GregorianCalendar today = new GregorianCalendar();
		int month = today.get(Calendar.MONTH);
		int day = today.get(Calendar.DAY_OF_MONTH);
		int year = today.get(Calendar.YEAR);
		GregorianCalendar date3 = new GregorianCalendar(year, month, day);

		RentalCar car1 = new RentalCar(); // default car
		RentalCar car2 = new RentalCar("8899", 2018, CarMake.NISSAN);

		CarReservation carRes1 = new CarReservation("XXYY");
		CarReservation carRes2 = new CarReservation("[]86");


		assertTrue(carRes2.getConfirmationNumber().equals("AAAA"));
		assertTrue(carRes1.getConfirmationNumber().equals("XXYY"));
		carRes1.setEndDate("10/21/2020");
		carRes1.setStartDate("10/15/2020");
		assertTrue(carRes1.getStartDate().equals(date1));
		assertTrue(carRes2.getEndDate().equals(date3));

		assertTrue(carRes1.getStartDate().equals(date1));
		assertTrue(carRes1.getEndDate().equals(date2));
		assertTrue(carRes2.getEndDate().equals(date3));
		carRes1.setEndDate("10/3/2020");
		assertTrue(carRes1.getEndDate().equals(date2));
		carRes1.setStartDate("10/22/2020");
		assertTrue(carRes1.getStartDate().equals(date1));
		carRes1.setStartDate("10/21/2020");
		assertTrue(carRes1.getStartDate().equals(date2));
		carRes1.setStartDate("10/15/2020");
		assertTrue(carRes1.getStartDate().equals(date1));
		carRes1.setEndDate("10/14/2020");
		assertTrue(carRes1.getEndDate().equals(date2));
		carRes1.setEndDate("10/15/2020");
		assertTrue(carRes1.getEndDate().equals(date1));

		RentalCar car3 = carRes1.getCar();
		assertTrue(car3.equals(car1));
		assertTrue(car3.getVin().equals("0000"));
		assertTrue(car3.getYear()== today.get(Calendar.YEAR));
		assertTrue(car3.getMake().equals(CarMake.TOYOTA));
		carRes1.setCar(car2);
		car3 = carRes1.getCar();
		assertTrue(car3.equals(car2));
		assertTrue(car3.getVin().equals("8899"));
		assertTrue(car3.getYear()== 2018);
		assertTrue(car3.getMake().equals(CarMake.NISSAN));

		String out = carRes1.toString();
		System.out.println("Car Reservation: " + out);
		assertTrue(out.contains("2018"));
		assertTrue(out.contains("8899"));
		assertTrue(out.contains("NISSAN"));
		assertTrue(out.contains("CAR"));
		utils.MyUtils.printTimeStamp(sName + " -- ENDS reservationTestCar()");
	}
	public void reservationTestHotel()
	{
		utils.MyUtils.printTimeStamp("\n----------------" +sName + " -- BEGINS reservationTestHotel()");
		GregorianCalendar date1 = new GregorianCalendar(2020, 4, 15);
		GregorianCalendar date2 = new GregorianCalendar(2020, 4, 21);
		GregorianCalendar today = new GregorianCalendar();
		int month = today.get(Calendar.MONTH);
		int day = today.get(Calendar.DAY_OF_MONTH);
		int year = today.get(Calendar.YEAR);

		GregorianCalendar date3 = new GregorianCalendar(year, month, day);
		System.out.println("Today is: " + utils.MyUtils.dateToString(today));
		HotelReservation hotel1 = new HotelReservation("1111");
		assertTrue(hotel1.getConfirmationNumber().equals("1111"));
		assertTrue(hotel1.getStartDate().equals(date3));
		assertTrue(hotel1.getEndDate().equals(date3));
		assertTrue(hotel1.getHotelName().equals("$$$$"));
		hotel1.setHotelName("     ");
		assertTrue(hotel1.getHotelName().equals(HotelReservation.DEFAULT_HOTEL_NAME));
		hotel1.setHotelName("");
		assertTrue(hotel1.getHotelName().equals(HotelReservation.DEFAULT_HOTEL_NAME));
		hotel1.setHotelName("hyatt hill country   reSorT");

		assertTrue(hotel1.getHotelName().equals("Hyatt Hill Country Resort"));
		assertTrue(hotel1.getNumPersons()==1);
		hotel1.setNumPersons(101);
		assertEquals(1, hotel1.getNumPersons());
		hotel1.setNumPersons(-3);
		assertEquals(1, hotel1.getNumPersons());
		hotel1.setNumPersons(2000);
		assertEquals(1, hotel1.getNumPersons());
		HotelReservation hotel2 = new HotelReservation("abcd");
		assertTrue(hotel2.getConfirmationNumber().equals("ABCD"));
		hotel2.setConfirmationNumber(");39");
		assertTrue(hotel2.getConfirmationNumber().equals("AAAA"));
		hotel2.setConfirmationNumber("445566");
		assertTrue(hotel2.getConfirmationNumber().equals("AAAA"));
		hotel2.setConfirmationNumber("  ");
		assertTrue(hotel2.getConfirmationNumber().equals("AAAA"));
		hotel2.setConfirmationNumber("");
		assertTrue(hotel2.getConfirmationNumber().equals("AAAA"));
		hotel2.setConfirmationNumber("ab;;");
		assertTrue(hotel2.getConfirmationNumber().equals("AAAA"));
		hotel1.setEndDate("5/21/2020");
		hotel1.setStartDate("5/15/2020");
		assertTrue(hotel1.getStartDate().equals(date1));
		assertTrue(hotel1.getEndDate().equals(date2));

		assertTrue(hotel1.getStartDate().equals(date1));
		assertTrue(hotel1.getEndDate().equals(date2));
		assertTrue(hotel1.getNumPersons()==1);
		assertTrue(hotel2.getNumPersons()==1);
		hotel1.setNumPersons(0);
		assertTrue(hotel1.getNumPersons()==1);
		hotel1.setNumPersons(100);
		assertTrue(hotel1.getNumPersons()==100);
		hotel1.setNumPersons(101);
		assertTrue(hotel1.getNumPersons()==1);
		hotel1.setNumPersons(1);
		assertTrue(hotel1.getNumPersons()==1);
		hotel1.setNumPersons(-65);
		assertTrue(hotel1.getNumPersons()==1);

		String out1 = hotel1.toString();
		String out2 = hotel2.toString();
		System.out.println("hotel1: " + hotel1);
		System.out.println("hotel2: " + hotel2);
		assertTrue(out1.contains("Hyatt Hill Country Resort"));
		assertTrue(out1.contains("1111"));
		assertTrue(out1.contains("HOTEL"));
		assertTrue(out1.contains("5/15/2020"));
		assertTrue(out1.contains("5/21/2020"));
		assertTrue(out2.contains("AAAA"));
		assertTrue(out2.contains("$$$$"));
		assertTrue(out2.contains("HOTEL"));

		utils.MyUtils.printTimeStamp(sName + " -- ENDS reservationTestHotel()");
	}

	public void reservationTestFileIO()
	{
		utils.MyUtils.printTimeStamp("\n-----------------" +sName + " -- BEGINS reservationTestFileIO()");
		System.out.println("********Now testing methods in ReservationUtilsImpl");

		Scanner inputFile = null;
		PrintWriter outputFile = null;
		String filename = "reservation6.txt";
		try {
			inputFile = new Scanner(new File(filename));

			System.out.println("SUCCESS opened file: " + filename + " for input");
		}catch(FileNotFoundException e) {
			System.out.println("FAILURE cannot open file: " + filename + " for input" +
					" EXIT ON FAILURE TO OPEN FILE.");
			System.exit(0);
		}
		Reservation res1 = null;
		int resCount = 0;
		while(inputFile.hasNext()){
			res1 = ReservationUtilsImpl.readFromScanner(inputFile);
			if(res1 != null)
			{
				resCount ++;
				System.out.println("Just read: " + res1);
			}
		}
		inputFile.close();
		if(resCount == 6)
			System.out.println("SUCCESS read " + resCount + " reservations from " + filename);
		else
			System.out.println("FAILURE should have read " + resCount + " reservations from " + filename);

		System.out.println(sName + " ********Now Testing writeToFile...");
		filename = "reservationEmpty.txt";
		try {
			outputFile = new PrintWriter(new FileWriter(new File(filename)));

		}catch (IOException e) {

			System.out.println("FAILURE, cannot open file " + filename + " for output, EXITING on FAILURE");
			System.exit(0);	
		}
		outputFile.close();
		filename = "reservation6.txt";
		try {
			inputFile = new Scanner(new File(filename));

			System.out.println("SUCCESS opened file: " + filename + " for input");
		}catch(FileNotFoundException e) {
			System.out.println("FAILURE cannot open file: " + filename + " for input" +
					" EXIT ON FAILURE TO OPEN FILE.");
			System.exit(0);
		}
		filename = "reservation6Out.txt";
		try {
			outputFile = new PrintWriter(new FileWriter(new File(filename)));

		}catch (IOException e) {

			System.out.println("FAILURE, cannot open file " + filename + " for output, EXITING on FAILURE");
			System.exit(0);	
		}
		res1 = null;
		resCount = 0;
		while(inputFile.hasNext()){
			res1 = ReservationUtilsImpl.readFromScanner(inputFile);
			if(res1 != null)
			{
				resCount ++;
				System.out.println("Just read: " + res1);
				ReservationUtilsImpl.writeToFile(outputFile, res1);
			}
		}
		inputFile.close();
		outputFile.close();
		if(resCount == 6)
			System.out.println("SUCCESS read " + resCount + " reservations from " + filename);
		else
			System.out.println("FAILURE should have read " + resCount + " reservations from " + filename);

		filename = "reservation6Out.txt";
		try {
			inputFile = new Scanner(new File(filename));

			System.out.println("SUCCESS opened file: " + filename + " for input");
		}catch(FileNotFoundException e) {
			System.out.println("FAILURE cannot open file: " + filename + " for input" +
					" EXIT ON FAILURE TO OPEN FILE.");
			System.exit(0);
		}
		res1 = null;
		resCount = 0;
		while(inputFile.hasNext()){
			res1 = ReservationUtilsImpl.readFromScanner(inputFile);
			if(res1 != null)
			{
				resCount ++;
				System.out.println("Just read: " + res1);
			}
		}
		inputFile.close();
		if(resCount == 6)
			System.out.println("SUCCESS read " + resCount + " reservations from " + filename);
		else
			System.out.println("FAILURE should have read " + 6 + " reservations from " + filename);

		filename = "reservation1.txt";
		try {
			inputFile = new Scanner(new File(filename));

			System.out.println("SUCCESS opened file: " + filename + " for input");
		}catch(FileNotFoundException e) {
			System.out.println("FAILURE cannot open file: " + filename + " for input" +
					" EXIT ON FAILURE TO OPEN FILE.");
			System.exit(0);
		}
		filename = "reservation1Out.txt";
		try {
			outputFile = new PrintWriter(new FileWriter(new File(filename)));

		}catch (IOException e) {

			System.out.println("FAILURE, cannot open file " + filename + " for output, EXITING on FAILURE");
			System.exit(0);	
		}
		res1 = null;
		resCount = 0;
		while(inputFile.hasNext()){
			res1 = ReservationUtilsImpl.readFromScanner(inputFile);
			if(res1 != null)
			{
				resCount ++;
				System.out.println("Just read: " + res1);
				ReservationUtilsImpl.writeToFile(outputFile, res1);
			}
		}
		inputFile.close();
		outputFile.close();
		assertEquals(1,resCount);
		if(resCount == 1)
			System.out.println("SUCCESS read " + resCount + " reservations from " + filename);
		else
			System.out.println("FAILURE should have read " + 1 + " reservations from " + filename);
	}
}