package reservation;

import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Reservation {

	public static final String DEFAULT_CONF_NUM = "AAAA";
	private String confirmNum;
	private GregorianCalendar startDate;
	private GregorianCalendar endDate;
	
	// constructs a Reservation-derived instance with received confirmation number, sets dates to today's date
	//  sets year, month and day with today's date.  Time is thus set to 0:00:00 for any equals test.
	public Reservation(String confirmation)
	{
		this.setConfirmationNumber(confirmation);  
		GregorianCalendar today = new GregorianCalendar();  // today's year, month, day
		this.endDate = new GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH));
		this.startDate = new GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH));

	}
	
	// receives confirmation number, validates it is length 4 and only alphanumeric characters,
	// if invalid number received uses DEFAULT_CONF_NUM
	public void setConfirmationNumber(String confirmation) {

		if(confirmation.length() != 4 || !utils.MyUtils.isValid(confirmation))
			this.confirmNum = DEFAULT_CONF_NUM;
		else 
			this.confirmNum = confirmation.toUpperCase();
	}
	//receives date - a string of mm/dd/yy that is VALID date
	// precondition - received date is a valid month day year combination in given format
	// NOTE: if changing both start and end dates, ALWAYS change end date before changing start date
	public void setStartDate(String date)
	{
		GregorianCalendar aDate=utils.MyUtils.stringToDate(date);
		if(aDate.compareTo(this.getEndDate())<= 0)
			this.startDate = aDate;
		// no change if received date is after end date
	}

	//receives date - a string of mm/dd/yy that is VALID date
	// precondition - received date is a valid month day year combination in given format
	public void setEndDate(String date)
	{
		GregorianCalendar aDate=utils.MyUtils.stringToDate(date);
		if(aDate.compareTo(this.getStartDate())>= 0)
			this.endDate = aDate;
		// no change if received date is before start date
	}
	//override of toString
	// receives: nothing
	// returns: a String with confirmation number, start and end dates on 1 line
	public String toString()
	{
		String temp = "";
		temp += "confirmation #: " + this.getConfirmationNumber() + " ";
		temp += "starts: " + utils.MyUtils.dateToString(this.getStartDate()) + " ";
		temp += "ends: " + utils.MyUtils.dateToString(this.getEndDate())+ " ";
		return temp;
	}

	// receives :nothing
	// returns this instance's ending date
	public GregorianCalendar getEndDate() {

		return  this.endDate;
	}
	// receives: nothing
	// returns this instance's start date
	public GregorianCalendar getStartDate() {

		return this.startDate;
	}// receives :nothing
	// returns this instance's  end date converted to a String mm/dd/yyyy
	public String getEndDateAsString() {

		return  (utils.MyUtils.dateToString(this.endDate));
	}
	// receives: nothing
	// returns this instance's start date converted to a String mm/dd/yyyy
	public String getStartDateAsString() {

		return (utils.MyUtils.dateToString(this.startDate));
	}
	// receives: nothing
	// returns this instance's confirmation number
	public String getConfirmationNumber() {
		return this.confirmNum;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((confirmNum == null) ? 0 : confirmNum.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		if (confirmNum == null) {
			if (other.confirmNum != null)
				return false;
		} else if (!confirmNum.equals(other.confirmNum))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
}// end of Reservation abstract class
