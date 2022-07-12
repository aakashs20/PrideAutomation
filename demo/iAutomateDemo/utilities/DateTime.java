package com.project.utilities;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.project.safetychain.webapp.pageobjects.CommonPage.TIMEZONE;
import com.project.testbase.TestBase;

public class DateTime extends TestBase {

	public static TimeZone timeZone;
	public Actions action;
	public Calendar calendar;
	public DateTime(){
	}
	public DateTime(WebDriver driver){
		this.driver = driver;
		action = new Actions(this.driver);
		PageFactory.initElements(driver, this);
	}

	/** This method is used to get Today's Day
	 * @author hingorani_a
	 * @param dayFormat - The format in which you want to get Day data
	 * @param timezone - The timezone for which you need Today's day
	 * @return String - This returns value of Today's Day as per timezone and format
	 */
	public String getTodaysDay(String dayFormat, String timezone) {
		timeZone = TimeZone.getTimeZone(timezone);
		Date date = new Date();
		DateFormat requiredFormat = new SimpleDateFormat(dayFormat);
		requiredFormat.setTimeZone(timeZone);
		String strCurrentDay = requiredFormat.format(date).toUpperCase();
		return strCurrentDay;
	}

	/** This method is used to get Current time
	 * @author hingorani_a
	 * @param timeFormat - The format in which you want to get Current time
	 * @param timezone - The timezone for which you need current time
	 * @return String - This returns value of Current time as per timezone and format
	 */
	public String getCurrentTime(String timeFormat, String timezone) {
		timeZone = TimeZone.getTimeZone(timezone);
		DateFormat dateFormat = new SimpleDateFormat(timeFormat);
		Calendar cal = Calendar.getInstance(timeZone);
		dateFormat.setTimeZone(cal.getTimeZone());
		String currentTime = dateFormat.format(cal.getTime());
		return currentTime;
	}

	/** This method is used to get Today's Date
	 * @author hingorani_a
	 * @param dateFormat - The format in which you want to get Today's date
	 * @param timezone - The timezone for which you need today's date
	 * @return String - This returns value of Today's date as per timezone and format
	 */
	public String getTodayDate(String dateFormat, String timezone) {
		timeZone = TimeZone.getTimeZone(timezone);
		Date todayDate = new Date();
		DateFormat todayDateFormat = new SimpleDateFormat(dateFormat);
		todayDateFormat.setTimeZone(timeZone);
		String strTodayDate = todayDateFormat.format(todayDate);
		return strTodayDate;
	}

	/**
	 * This method is used to add number of days to the current date
	 * 
	 * @author dahale_p
	 * @param numberOfDays - Number of days as String--"1"
	 * @param dateFormat - The format of date in which you want to add days
	 * @return String as number of added dates
	 */
	public String AddDaystoToddaysDate(int numberOfDays, String dateFormat) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, numberOfDays);
			String newDate = sdf.format(cal.getTime());
			return newDate;
		}
		catch(Exception e) {
			throw e;
		}
	}

	/**
	 * This method is used to format date
	 * 
	 * @author dahale_p
	 * @param date - Date --String
	 * @param dateformat - Date format --String
	 * @return String -- formated dated
	 * @throws ParseException
	 */
	public static String formatDate(String date, String dateformat) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat outputFormat = new SimpleDateFormat(dateformat);

		Date originformat = sdf.parse(date);
		String duedate = outputFormat.format(originformat);
		return duedate;
	}


	/** This method is used to get Timezone that can be used by DateTime methods
	 * @author hingorani_a
	 * @param timezone The timezone of your User
	 * @return String This returns value of Timezone as per DateTime
	 */
	public String getTimezone(String timezone) {
		switch(timezone) {
		case TIMEZONE.USEASTERN:
			return "US/Eastern";
		case TIMEZONE.USPACIFIC:
			return "US/Pacific";
		case TIMEZONE.REPUBLICOFINDIA:
			return "IST";
		case TIMEZONE.GREENWICHMEANTIME:
			return "GMT";
		default:
			logError("Incorrect timezone option");
			return null;
		}
	}

	/**
	 * 'setDate' method is used to set Date in  'MM/dd/yyyy' format
	 * @param element - Web Element where the date needs to be set
	 * @param duration - Duration of time i.e Month, Year, Week of Month, Day of Month 
	 * @param value - Value to be added in the duration
	 * @author - pashine_a 
	 */
	public void setDate(WebElement element, String duration, int value) {
		try {
			String pattern = "MM/dd/yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Calendar calendar = Calendar.getInstance();
			if(duration.equalsIgnoreCase("Year")) {
				calendar.add(Calendar.YEAR, value);
			}
			if(duration.equalsIgnoreCase("Month")) {	
				calendar.add(Calendar.MONTH, value);
			}
			if(duration.equalsIgnoreCase("Week")) {
				calendar.add(Calendar.WEEK_OF_MONTH, value);
			}
			if(duration.equalsIgnoreCase("Day")) {
				calendar.add(Calendar.DAY_OF_MONTH, value);
			}
			String date = simpleDateFormat.format(calendar.getTime());
			action.moveToElement(element).build().perform();
			element.sendKeys(date);
			threadsleep(2000);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 'setTime' method is used to set Time in  'hh/HH:mm' or 'hh/HH:mm aa' format
	 * @param element - WebElement where time needs to be set.
	 * @param hourCount - Hour Count to be added [int]
	 * @param minuteCount - Minute Count be added [int]
	 * @param hour12Format - Set true if 12 hour format is followed for time in the Date Time value [boolean]
	 * @param am_pmMarker - Set true if format has AM and PM in the Date Time value [boolean]
	 * @author - pashine_a 
	 */
	public void setTime(WebElement element, int hourCount, int minuteCount, boolean hour12Format, boolean am_pmMarker) {
		try {
			String pattern;
			if(am_pmMarker) {
				if(hour12Format) {
					pattern = "hh:mm aa";
				}else {
					pattern = "HH:mm aa";
				}
			}else {
				if(hour12Format) {
					pattern = "hh:mm";
				}else {
					pattern = "HH:mm";
				}
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.HOUR, hourCount);
			calendar.add(Calendar.MINUTE, minuteCount);
			String time = simpleDateFormat.format(calendar.getTime());
			action.moveToElement(element).build().perform();
			element.sendKeys(time);
			logInfo(time);
			threadsleep(2000);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 'setDateTime' method is used to set Date-Time in  'MM/dd/yyyy hh/HH:mm' or 'MM/dd/yyyy hh/HH:mm aa' format
	 * @param element - WebElement where time needs to be set.
	 * @param duration - Duration of time i.e Month, Year, Week of Month, Day of Month
	 * @param value -  Value to be added in the duration
	 * @param hourCount - Hour Count to be added [int]
	 * @param minuteCount - Minute Count be added [int]
	 * @param hour12Format - Set true if 12 hour format is followed for time in the Date Time value [boolean]
	 * @param am_pmMarker - Set true if format has AM and PM in the Date Time value [boolean]
	 * @author - pashine_a 
	 */
	public void setDateTime(WebElement element, String duration, int value, 
			int hourCount, int minuteCount,boolean hour12Format, boolean am_pmMarker) {
		try {
			String pattern;
			if(am_pmMarker) {
				if(hour12Format) {
					pattern = "MM/dd/yyyy hh:mm aa";			

				}else {
					pattern = "MM/dd/yyyy HH:mm aa";			
				}
			}else {
				if(hour12Format) {
					pattern = "MM/dd/yyyy hh:mm";			

				}else {
					pattern = "MM/dd/yyyy HH:mm";			
				}
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Calendar calendar = Calendar.getInstance();
			if(duration.equalsIgnoreCase("Year")) {
				calendar.add(Calendar.YEAR, value);
			}
			if(duration.equalsIgnoreCase("Month")) {	
				calendar.add(Calendar.MONTH, value);
			}
			if(duration.equalsIgnoreCase("Week")) {
				calendar.add(Calendar.WEEK_OF_MONTH, value);
			}
			if(duration.equalsIgnoreCase("Day")) {
				calendar.add(Calendar.DAY_OF_MONTH, value);
			}
			calendar.add(Calendar.HOUR, hourCount);
			calendar.add(Calendar.MINUTE, minuteCount);
			String dateTime = simpleDateFormat.format(calendar.getTime());
			action.moveToElement(element).build().perform();
			element.sendKeys(dateTime);
			threadsleep(2000);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 'getDate' method is used to get Date in  'MM/dd/yyyy' format
	 * @param duration - Duration of time i.e Month, Year, Week of Month, Day of Month
	 * @param value - Value to be added in the duration
	 * @return date - Fetched Date
	 * @author - pashine_a 
	 */
	public String getDate(String duration, int value) {
		String date = null;
		try {
			String pattern = "MM/dd/yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Calendar calendar = Calendar.getInstance();
			if(duration.equalsIgnoreCase("Year")) {
				calendar.add(Calendar.YEAR, value);
			}
			if(duration.equalsIgnoreCase("Month")) {	
				calendar.add(Calendar.MONTH, value);
			}
			if(duration.equalsIgnoreCase("Week")) {
				calendar.add(Calendar.WEEK_OF_MONTH, value);
			}
			if(duration.equalsIgnoreCase("Day")) {
				calendar.add(Calendar.DAY_OF_MONTH, value);
			}
			date = simpleDateFormat.format(calendar.getTime());
		}catch(Exception e) {
		}
		return date;
	}

	/**
	 * 'getTime' method is used to get Time in  'hh/HH:mm' or 'hh/HH:mm aa' format
	 * @param hourCount - Hour Count to be added [int]
	 * @param minuteCount - Minute Count be added [int]
	 * @param hour12Format - Set true if 12 hour format is followed for time in the Date Time value [boolean]
	 * @param am_pmMarker - Set true if format has AM and PM in the Date Time value [boolean]
	 * @author - pashine_a 
	 */
	public String getTime(int hourCount, int minuteCount, boolean hour12Format, boolean am_pmMarker) {
		String time = null;
		try {
			String pattern;
			if(am_pmMarker) {
				if(hour12Format) {
					pattern = "hh:mm aa";
				}else {
					pattern = "HH:mm aa";
				}
			}else {
				if(hour12Format) {
					pattern = "hh:mm";
				}else {
					pattern = "HH:mm";
				}
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.HOUR, hourCount);
			calendar.add(Calendar.MINUTE, minuteCount);
			time = simpleDateFormat.format(calendar.getTime());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	public String getTime() {
		String time = null;
		try {
			String pattern;
			pattern = "HH:mmmmmmm";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Calendar calendar = Calendar.getInstance();
			time = simpleDateFormat.format(calendar.getTime());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	public String getTime(TimeZone timezone, int addDays,String duration, boolean seconds) {
		String time = null;
		try {
			String pattern;
			if(seconds) {
				pattern = "HH:mm:ss"+".0000000";
			}
			else {
				pattern = "HH:mm:00"+".0000000";
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			simpleDateFormat.setTimeZone(timezone);
			Calendar calendar = Calendar.getInstance();
			switch(duration) {
			case "MINUTE":
				calendar.add(Calendar.MINUTE, addDays);
				break;
			case "HOUR":
				calendar.add(Calendar.HOUR, addDays);
				break;
			case "NOW":
				Calendar.getInstance();
			}
			time = simpleDateFormat.format(calendar.getTime());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	public String getDate() {
		String date = null;
		try {
			String pattern;
			pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Calendar calendar = Calendar.getInstance();
			date = simpleDateFormat.format(calendar.getTime());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public String getDate(TimeZone timezone, int addDays) {
		String date = null;
		try {
			String pattern;
			pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			simpleDateFormat.setTimeZone(timezone);
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, addDays);
			date = simpleDateFormat.format(calendar.getTime());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public String getTime(String pattern) {
		String time = null;
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Calendar calendar = Calendar.getInstance();
			time = simpleDateFormat.format(calendar.getTime());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	public String convertedDateTime(String dateValue) throws ParseException {
		DateFormat formatterIST = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		formatterIST.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		Date date = formatterIST.parse(dateValue);
		DateFormat formatterUTC = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.0000000Z'");
		formatterUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
		String output = formatterUTC.format(date);
		return output;
	}

	/**
	 * 'getDateTime' method is used to get Date-Time in  'MM/dd/yyyy hh/HH:mm' or 'MM/dd/yyyy hh/HH:mm aa' format
	 * @param duration - Duration of time i.e Month, Year, Week of Month, Day of Month
	 * @param value - Value to be added in the duration
	 * @param hourCount - Hour Count to be added [int]
	 * @param minuteCount - Minute Count be added [int]
	 * @param hour12Format - Set true if 12 hour format is followed for time in the Date Time value [boolean]
	 * @param am_pmMarker - Set true if format has AM and PM in the Date Time value [boolean]
	 * @return date 
	 * @author - pashine_a 
	 */
	public String getDateTime(String duration, int value, 
			int hourCount, int minuteCount,boolean hour12Format, boolean am_pmMarker) {
		String dateTime = null;
		try {
			String pattern;
			if(am_pmMarker) {
				if(hour12Format) {
					pattern = "MM/dd/yyyy hh:mm aa";			

				}else {
					pattern = "MM/dd/yyyy HH:mm aa";			
				}
			}else {
				if(hour12Format) {
					pattern = "MM/dd/yyyy hh:mm";			

				}else {
					pattern = "MM/dd/yyyy HH:mm";			
				}
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Calendar calendar = Calendar.getInstance();
			if(duration.equalsIgnoreCase("Year")) {
				calendar.add(Calendar.YEAR, value);
			}
			if(duration.equalsIgnoreCase("Month")) {	
				calendar.add(Calendar.MONTH, value);
			}
			if(duration.equalsIgnoreCase("Week")) {
				calendar.add(Calendar.WEEK_OF_MONTH, value);
			}
			if(duration.equalsIgnoreCase("Day")) {
				calendar.add(Calendar.DAY_OF_MONTH, value);
			}
			calendar.add(Calendar.HOUR, hourCount);
			calendar.add(Calendar.MINUTE, minuteCount);
			dateTime = simpleDateFormat.format(calendar.getTime());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dateTime;
	}

	public String getDateTimeValue(String pattern) {
		String date = null;
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Calendar calendar = Calendar.getInstance();
			date = simpleDateFormat.format(calendar.getTime());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * This method is used to set the Year in 'yyyy' format in the webelement 
	 * @param element - WebElement where year needs to be set.
	 * @param value - Value to be added in the current year.
	 */
	public void setYear(WebElement element,int value) {
		try {
			action = new Actions(this.driver);
			String pattern = "yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.YEAR, value); 
			String year = simpleDateFormat.format(calendar.getTime());
			action.moveToElement(element).build().perform();
			element.sendKeys(year);
			threadsleep(2000);
		}catch(Exception e) {
		}
	}

	/** This method is used to get Timezone that can be used by DateTime methods
	 * @author hingorani_a
	 * @param d1 Date1 to compare
	 * @param d2 Date2 to compare
	 * @param hour12Format Set true if 12 hour format is followed for time in the Date Time value
	 * @param am_pmMarker Set true if format has AM and PM in the Date Time value
	 * @return ComparisonValue This returns value from Enum ComparisonValue as LESSER, GREATER or EQUALS
	 */
	public ComparisonValue compareDates(String d1,String d2, Boolean hour12Format, Boolean am_pmMarker)
	{
		String pattern;

		try{

			if(am_pmMarker==null && hour12Format==null) {
				pattern = "MM/dd/yyyy";
			}
			else {
				if(am_pmMarker) {
					if(hour12Format) 
						pattern = "MM/dd/yyyy hh:mm aa";			
					else 
						pattern = "MM/dd/yyyy HH:mm aa";			
				}	
				else {
					if(hour12Format) 
						pattern = "MM/dd/yyyy hh:mm";			
					else
						pattern = "MM/dd/yyyy HH:mm";			

				}
			}

			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			Date date1 = sdf.parse(d1);
			Date date2 = sdf.parse(d2);

			System.out.println("Date1"+sdf.format(date1));
			System.out.println("Date2"+sdf.format(date2));System.out.println();

			// Date object is having 3 methods namely after,before and equals for comparing
			// after() will return true if and only if date1 is after date 2
			if(date1.after(date2)){
				logInfo("Date1 is after Date2");
				return ComparisonValue.GREATER;
			}

			// before() will return true if and only if date1 is before date2
			else if(date1.before(date2)){
				logInfo("Date1 is before Date2");
				return ComparisonValue.LESSER;
			}

			//equals() returns true if both the dates are equal
			else if(date1.equals(date2)){
				logInfo("Date1 is equal Date2");
				return ComparisonValue.EQUALS;
			}
			else {
				logInfo("Could not compare Date1 and Date2");
				return null;
			}

		}
		catch(Exception e){
			logError("Failed to compare Date1 and Date2 - "
					+ e.getMessage());
			return null;
		}
	}

	/**
	 * This method is used to subtract Time from current date/time as per format
	 * @author hingorani_a
	 * @param dateTime Date and Time on which we need to subtract minutes
	 * @param dateFormat The format of the date and time
	 * @param numberOfMins Number of minutes to subtract
	 * @return String The date time post subtraction
	 */
	public String subtractTime(String dateTime, String dateFormat, int numberOfMins) {

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);

			System.out.println("Before subtraction of minutes from date: "+ dateTime);

			LocalDateTime datetime = LocalDateTime.parse(dateTime,formatter);

			datetime=datetime.minusMinutes(numberOfMins);

			String afterSubtraction=datetime.format(formatter);
			System.out.println("After 30 minutes subtraction from date: "+afterSubtraction);
			return afterSubtraction;
		}
		catch(Exception e) {
			throw e;
		}
	}

	/**
	 * This method is used to add or subtract days from a Date
	 * @author hingorani_a
	 * @param date The date from which we need to add/subtract days
	 * @param days Number of days to be added/subtracted
	 * @param dateFormat The format of the date
	 * @return String The date post addition or subtraction
	 */
	public String addSubtractDaysFromDate(Date date, int days, String dateFormat) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);

		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String newDate = sdf.format(cal.getTime());
		return newDate;
	}

	/**
	 * This method is used to convert a date or date time String to Date
	 * @author hingorani_a
	 * @param date The date which we want to convert to Date type
	 * @param dateFormat The format of the date
	 * @return Date The date after conversion
	 */
	public Date getDate(String date, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date convertedDate = null;
		try {
			convertedDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return convertedDate;
	}

	/**
	 * This method is used to verify the Sorting order of the Dates provided in List of Elements
	 * @author hingorani_a
	 * @param elements List of WebElements that contains value of Dates to be compared to verify sorting order
	 * @param hour12Format Set true if 12 hour format is followed for time in the Date Time value [boolean]
	 * @param amPmMarker Set true if format has AM and PM in the Date Time value [boolean]
	 * @param dateOnly Set it as true if only Date needs to be verified else false. Example : 07/11/2020
	 * @param dateTime Set it as true if only Date and Time needs to be verified else false. Example : 07/11/2020 12:33
	 * @param dateTimeAmPmMarker Set it as true if only Date, Time and AM/PM marker needs to be verified else false. Example : 07/11/2020 12:33 PM
	 * @return ComparisonValue The enum value from ComparisonValue is returned.
	 * Returns GREATER if sorting order is descending
	 * Returns LESSER if sorting order is ascending
	 * Returns EQUALS if sorting order is same/equal
	 */
	public ComparisonValue verifySortingForColumn(List<WebElement> elements, boolean hour12Format, boolean amPmMarker,
			boolean dateOnly, boolean dateTime, boolean dateTimeAmPmMarker) {
		ComparisonValue compare = null;
		int ascCount = 0,dscCount = 0,sortCount =0;
		int listLimit = 8;
		String first = null, second = null;
		String firstdate = null;
		String seconddate = null;
		try {

			if(elements.size()<8)
				listLimit = elements.size()-1;

			for(int l=0;l<listLimit;l++) {
				first = elements.get(l).getText();
				second = elements.get(l+1).getText();
				String firstDate[] = CommonMethods.splitAndGetString(first, " ");
				String secondDate[] = CommonMethods.splitAndGetString(second, " ");

				if(dateOnly){
					firstdate = firstDate[0];
					seconddate =secondDate[0];
				}
				else if(dateTime){
					firstdate = firstDate[0] + " " + firstDate[1];
					seconddate =secondDate[0] + " " + secondDate[1];
				}
				else if(dateTimeAmPmMarker){
					firstdate = firstDate[0] + " " + firstDate[1] + " " + firstDate[2];
					seconddate =secondDate[0] + " " + secondDate[1] + " " + secondDate[2];
				}

				compare = compareDates(firstdate, seconddate, hour12Format, amPmMarker);
				if(compare.equals(ComparisonValue.EQUALS)) {
					sortCount++;
					logInfo("Equal value for - 1) " + elements.get(l) + " 2) " + elements.get(l+1));

				}

				else if(compare.equals(ComparisonValue.GREATER)) {
					dscCount++;
					logInfo("Descending value for - 1) " + elements.get(l) + " 2) " + elements.get(l+1));
				}

				else if(compare.equals(ComparisonValue.LESSER)) {
					ascCount++;
					logInfo("Ascending value for - 1) " + elements.get(l) + " 2) " + elements.get(l+1));
				}
			}

			if(sortCount==listLimit){
				//This Returns if sorting is Equal,if all values are same till the listLimit count
				return ComparisonValue.EQUALS;
			}else if(sortCount+ascCount==listLimit) {
				//This Returns if sorting is Ascending
				return ComparisonValue.LESSER;
			}else if(sortCount+dscCount==listLimit){
				//This Returns if sorting is Descending
				return ComparisonValue.GREATER;
			}

			logError("Could not verify sorting for element " + elements);
			return null;
		}
		catch(Exception e) {
			logError("Failed to verify sorting of data" +
					e.getMessage());
			return null;
		}
	}

	/**
	 * this method is used to verify format of the provided value
	 * @param format
	 * @param value
	 * @return boolean. It return true if the format matches with provided date
	 * @author - pashine_a 
	 */
	public boolean  verifyDateTimeFormat(String format, String value) {
		Date date = null;
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			date = simpleDateFormat.parse(value);
			if(!((simpleDateFormat.format(date)).equals(value))) {
				logError("Date format not matches");
				return false;
			}
			logInfo("Date format matches");
			return true;
		}catch(Exception e) {
			logError("Failed to verify date format - " + e.getMessage());
			return false;
		}
	}

	/* 'getDate_Time' method is used to get current Date/Time in required format
	 * @param format
	 * @return String - Fetched Date/Time
	 * @author - pashine_a 
	 */
	public String getDateTime(String format) {
		String dateTime = null;
		try {
			String pattern = format;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Calendar calendar = Calendar.getInstance();
			dateTime = simpleDateFormat.format(calendar.getTime());
		}catch(Exception e) {
			logError("Failed to get date as per given format - "+format + " - "+ e.getMessage());
		}
		return dateTime;
	}

	/** This method is used to get Timezone that can be used by DateTime methods
	 * @author hingorani_a
	 * @param dateFormat The date format in which comparison is to be done
	 * @param d1 Date1 to compare
	 * @param d2 Date2 to compare
	 * @return ComparisonValue This returns value from Enum ComparisonValue as LESSER, GREATER or EQUALS
	 */
	public ComparisonValue compareDates(String dateFormat, String d1,String d2)
	{
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			Date date1 = sdf.parse(d1);
			Date date2 = sdf.parse(d2);

			System.out.println("Date1"+sdf.format(date1));
			System.out.println("Date2"+sdf.format(date2));System.out.println();

			// Date object is having 3 methods namely after,before and equals for comparing
			// after() will return true if and only if date1 is after date 2
			if(date1.after(date2)){
				logInfo("Date1 is after Date2");
				return ComparisonValue.GREATER;
			}

			// before() will return true if and only if date1 is before date2
			else if(date1.before(date2)){
				logInfo("Date1 is before Date2");
				return ComparisonValue.LESSER;
			}

			//equals() returns true if both the dates are equal
			else if(date1.equals(date2)){
				logInfo("Date1 is equal Date2");
				return ComparisonValue.EQUALS;
			}
			else {
				logInfo("Could not compare Date1 and Date2");
				return null;
			}

		}
		catch(Exception e){
			logError("Failed to compare Date1 and Date2 - "
					+ e.getMessage());
			return null;
		}
	}

	/**
	 * This method is used to verify the Sorting order of the Dates provided in List of Elements
	 * @author hingorani_a
	 * @param elements List of WebElements that contains value of Dates to be compared to verify sorting order
	 * @param dateFormat The date format in which comparison is to be done
	 * @param dateOnly Set it as true if only Date needs to be verified else false. Example : 07/11/2020
	 * @param dateTime Set it as true if only Date and Time needs to be verified else false. Example : 07/11/2020 12:33
	 * @param dateTimeAmPmMarker Set it as true if only Date, Time and AM/PM marker needs to be verified else false. Example : 07/11/2020 12:33 PM
	 * @return ComparisonValue The enum value from ComparisonValue is returned.
	 * Returns GREATER if sorting order is descending
	 * Returns LESSER if sorting order is ascending
	 * Returns EQUALS if sorting order is same/equal
	 */
	public ComparisonValue verifySortingForColumn(List<WebElement> elements, String dateFormat,
			boolean dateOnly, boolean dateTime, boolean dateTimeAmPmMarker) {
		ComparisonValue compare = null;
		int ascCount = 0,dscCount = 0,sortCount =0;
		int listLimit = 8;
		String first = null, second = null;
		String firstdate = null;
		String seconddate = null;
		try {

			if(elements.size()<8)
				listLimit = elements.size()-1;

			for(int l=0;l<listLimit;l++) {
				first = elements.get(l).getText();
				second = elements.get(l+1).getText();
				String firstDate[] = CommonMethods.splitAndGetString(first, " ");
				String secondDate[] = CommonMethods.splitAndGetString(second, " ");

				if(dateOnly){
					firstdate = firstDate[0];
					seconddate =secondDate[0];
				}
				else if(dateTime){
					firstdate = firstDate[0] + " " + firstDate[1];
					seconddate =secondDate[0] + " " + secondDate[1];
				}
				else if(dateTimeAmPmMarker){
					firstdate = firstDate[0] + " " + firstDate[1] + " " + firstDate[2];
					seconddate =secondDate[0] + " " + secondDate[1] + " " + secondDate[2];
				}

				compare = compareDates(dateFormat, firstdate, seconddate);
				if(compare.equals(ComparisonValue.EQUALS)) {
					sortCount++;
					logInfo("Equal value for - 1) " + elements.get(l) + " 2) " + elements.get(l+1));

				}

				else if(compare.equals(ComparisonValue.GREATER)) {
					dscCount++;
					logInfo("Descending value for - 1) " + elements.get(l) + " 2) " + elements.get(l+1));
				}

				else if(compare.equals(ComparisonValue.LESSER)) {
					ascCount++;
					logInfo("Ascending value for - 1) " + elements.get(l) + " 2) " + elements.get(l+1));
				}
			}

			if(sortCount==listLimit){
				//This Returns if sorting is Equal,if all values are same till the listLimit count
				return ComparisonValue.EQUALS;
			}else if(sortCount+ascCount==listLimit) {
				//This Returns if sorting is Ascending
				return ComparisonValue.LESSER;
			}else if(sortCount+dscCount==listLimit){
				//This Returns if sorting is Descending
				return ComparisonValue.GREATER;
			}

			logError("Could not verify sorting for element " + elements);
			return null;
		}
		catch(Exception e) {
			logError("Failed to verify sorting of data" +
					e.getMessage());
			return null;
		}
	}

	public enum ComparisonValue {
		GREATER, LESSER, EQUALS
	}


	public long findDiff(ZonedDateTime startdatetime, ZonedDateTime endDateTime,String duration) {
		long diff = 0;
		switch(duration) {
		case "HOURS":
			diff = ChronoUnit.HOURS.between(endDateTime, startdatetime);
			break;
		case "MINUTES":
			diff = ChronoUnit.MINUTES.between(endDateTime, startdatetime);
			break;
		case "DAYS":
			diff = ChronoUnit.DAYS.between(endDateTime, startdatetime);
			break;
		}
		return diff;
	}

	public static String convertToNewFormat(String dateStr) throws ParseException {//2022-03-28T17:31:38.843Z[GMT]
		TimeZone utc = TimeZone.getTimeZone("UTC");
		SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		SimpleDateFormat destFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sourceFormat.setTimeZone(utc);
		Date convertedDate = sourceFormat.parse(dateStr);
		return destFormat.format(convertedDate);
	}

	/* 'getDate_Time' method is used to get current Date/Time in required format(with Hour/Minute addition)
	 * @param format
	 * @param  hourCount
	 * @param minuteCount
	 * @return String - Fetched Date/Time
	 * @author - pashine_a 
	 */
	public String getDateTime(String format, int hourCount, int minuteCount) {
		String dateTime = null;
		try {
			String pattern = format;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.HOUR, hourCount);
			calendar.add(Calendar.MINUTE, minuteCount);
			dateTime = simpleDateFormat.format(calendar.getTime());
		}catch(Exception e) {
			logError("Failed to get date as per given format - "+format + " - "+ e.getMessage());
		}
		return dateTime;
	}
}
