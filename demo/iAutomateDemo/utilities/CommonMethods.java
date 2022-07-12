package com.project.utilities;

import java.text.Collator;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.project.testbase.TestBase;

public class CommonMethods extends TestBase {
	WebDriver driver;
	ControlActions controlActions;
	public CommonMethods(WebDriver driver){
		this.driver =driver;
		controlActions = new  ControlActions(driver);
		PageFactory.initElements(driver, this);	
	}
	
	/**
	 * This method is used to split the given string using Delimiter '|'
	 * @author hingorani_a
	 * @param stringValueWithSeparator Value to be split
	 * @return String[] This return an array after splitting the values
	 */
	public static String[] splitAndGetString(String stringValueWithSeparator) {
		String[] getValues = stringValueWithSeparator.split("[|]");
		return getValues;
	}
	
	/**
	 * This method is used to split the given string using Delimiter '|'
	 * @author hingorani_a
	 * @param stringValueWithSeparator Value to be split
	 * @param separator Separator that needs to be used for string split
	 * @return String[] This return an array after splitting the values
	 */
	public static String[] splitAndGetString(String stringValueWithSeparator, String separator) {
		String[] getValues = stringValueWithSeparator.split(separator);
		return getValues;
	}
 
	public static String createDateTimeStamp() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date sysdate = new Date();
		return formatter.format(sysdate);
	}
	
	/**
	 * This method is used to create a dynamic string by appending DateTime with format 'dd.MM_HH.mm.ss'
	 * @author pashine_a
	 * @param value The String value is taken in the original value to which DateTime stamp is appended
	 * @return This method returns String with appended value
	 */
	public static String dynamicText(String value) {
		String newName = null;
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM_HH.mm.ss");
			LocalDateTime now = LocalDateTime.now();
			newName = value+"_"+dtf.format(now);
		}catch(Exception e) {
			System.out.println(e);
		}
		return newName;
	}
	
	/**
	 * This method is used to create a dynamic string by appending DateTime with format 'ddMMHHmmss' 
	 * This dynamic value is without any special characters
	 * @author pashine_a
	 * @param value The String value is taken in the original value to which DateTime stamp is appended
	 * @return This method returns String with appended value
	 * 	 */
	public static String dynamicStrictText(String value) {
		String newName = null;
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMHHmmss");
			LocalDateTime now = LocalDateTime.now();
			newName = value+"-"+dtf.format(now);
		}catch(Exception e) {
			System.out.println(e);
		}
		return newName;
	}
	
	/**
	 * This method is used to reload current page using driver
	 * @author hingorani_a
	 * @param driver Pass on the current driver instance
	 */
	public void reloadCurrentPage(WebDriver driver) {
		try {
			driver.navigate().refresh();
			logInfo("Reloaded current page");
		}
		catch(Exception e) {
			logError("Failed to reload current page");
		}
	}
	
	/**
	 * This method is used to verify what sorting is applied to a column with String data type values
	 * @author hingorani_a
	 * @param element The list of WebElements on which sorting operation is 
	 * to be performed 
	 * @return String This returns String as SORTING.ASC for Ascending sort,
	 * as SORTING.DSC for Descending sort and SORTING.EQL for when values are same/equal
	 */
	public static String verifySortingForColumn(List<WebElement> element) {
		int compare = 0,ascCount = 0,dscCount = 0,sortCount =0;
		int listLimit = 8;
		String first = null, second = null;
		try {

			if(element.size()<8)
				listLimit = element.size()-1;

			for(int l=0;l<listLimit;l++) {
				first = element.get(l).getText();
				second = element.get(l+1).getText();
				compare = first.compareToIgnoreCase(second);
				if(compare==0) {
					sortCount++;
					logInfo("Equal value for - 1) " + element.get(l) + " 2) " + element.get(l+1));
				}

				else if(compare>0) {
					dscCount++;
					logInfo("Descending value for - 1) " + element.get(l) + " 2) " + element.get(l+1));
				}

				else if(compare<0) {
					ascCount++;
					logInfo("Ascending value for - 1) " + element.get(l) + " 2) " + element.get(l+1));
				}
			}

			if(sortCount==listLimit) {
				return SORTING.EQL;
			}else if(sortCount+ascCount==listLimit) {
				return SORTING.ASC;
			}else if(sortCount+dscCount==listLimit){
				return SORTING.DSC;
			}

			logError("Could not verify sorting for element " + element);
			return null;
		}
		catch(Exception e) {
			logError("Failed to verify sorting of data" +  
					 e.getMessage());
			return null;
		}	
	}
	
	/**
	 * This method is used to get variable value mentioned if it is available in the HashMap
	 * @author hingorani_a
	 * @param dataFromExcel Nested HashMap such that - this HashMap has TCG name and Nested HashMap of variable details
	 * @param className The class name for which you want to fetch variable details
	 * @param variableName The variable name for which you want to fetch variable detail
	 * @return String This returns String variable value as per mentioned className and variableName
	 */
	public static String getPrereqVariableValue(HashMap<String, HashMap<String, String>> dataFromExcel, String className, String variableName) {
		String columnValue = null;
		try {
			if(dataFromExcel != null) {
				for (Entry<String, HashMap<String, String>> dataEntry : dataFromExcel.entrySet()) {
				    String TCGName = dataEntry.getKey();
				    HashMap<String, String> prereqValues = dataEntry.getValue();
				    
				    if(TCGName.equalsIgnoreCase(className)) {
				    	for (Map.Entry<String, String> nameEntry : prereqValues.entrySet()) {
					        String columnName = nameEntry.getKey();
					        if(columnName.equalsIgnoreCase(variableName)) {
					        	columnValue = nameEntry.getValue();
					        	logInfo("For variable - " + variableName + " value found is " + columnValue);
					        }
					    }
				    }
				    else 
				    	logError("No class found with name - " + className);
				}
			}
			else
				logError("The hashmap sent as paramter is null");
		}
		catch(Exception e) {
			logError("For class " + className + ", no value found for variable " + variableName
					+ e.getMessage());
		}
		return columnValue;
	}
	
	/**
	 * This method is used to validate the string as per the Regex expression mentioned
	 * @author hingorani_a
	 * @param stringToValidate The string to be validated against Regex expression
	 * @param regexString The regex expression to be validated against
	 * @return boolean This returns true if the value passed matches with the mentioned Regex expression
	 */
	public static boolean validateByRegex(String stringToValidate, String regexString) {
		try {
		      //Creating a pattern object
		      Pattern pattern = Pattern.compile(regexString);
		      //Matching the compiled pattern in the String
		      Matcher matcher = pattern.matcher(stringToValidate);
		      boolean bool = matcher.matches();
		      if(bool) {
		         System.out.println("String is valid as per regex");
		         return true;
		      } else {
		         System.out.println("String is not valid as per regex");
		         return false;
		      }
		}
		catch(Exception e) {
			logError("Failed to validate " + stringToValidate + " against pattern " + regexString
					+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to get value of the list of Elements mentioned
	 * @author hingorani_a
	 * @param listOfElements The Element list for which values are needed
	 * @return List This returns list of String values corresponding to the 
	 * list of Elements sent as a parameter to this function
	 */
	public static List<String> getListOfValuesFromElements(List<WebElement> listOfElements) {
		List<String> valueList = new ArrayList<String>();
		try {
			for(WebElement element : listOfElements) {
				valueList.add(element.getAttribute("innerText").trim());
			}
			logInfo("Retrieved and added list of values");
			return valueList;
		}
		catch(Exception e) {
			logError("Failed to get list of values - "
					+ e.getMessage());
			return valueList;
		}	
	}
	
	/**
	 * This method is used to verify what sorting is applied to a column with Double data type values
	 * @author dahale_p
	 * @param element The list of WebElements on which sorting operation is 
	 * to be performed 
	 * @return String This returns String as SORTING.ASC for Ascending sort,
	 * as SORTING.DSC for Descending sort and SORTING.EQL for when values are same/equal
	 */
	public static String verifySortingForColumnDoubleType(List<WebElement> element) { 
		int ascCount = 0,dscCount = 0,sortCount =0;
		int listLimit = 8;
		double first = 0, second = 0;
		
		try {

			if(element.size()<8)
				listLimit = element.size()-1; 

			for(int l=0;l<listLimit;l++) {
				first = Double.parseDouble(element.get(l).getText());
				second =  Double.parseDouble(element.get(l+1).getText());
				if(first==second) {
					sortCount++;
					logInfo("Equal value for - 1) " + element.get(l) + " 2) " + element.get(l+1));
				}

				else if(first>second) {
					dscCount++;
					logInfo("Descending value for - 1) " + element.get(l) + " 2) " + element.get(l+1));
				}

				else if(first<second) {
					ascCount++;
					logInfo("Ascending value for - 1) " + element.get(l) + " 2) " + element.get(l+1));
				}
			}

			if(sortCount==listLimit){ 
				//This Returns if sorting is Equal,if all values are same till the listLimit count
				return SORTING.EQL;
			}else if(sortCount+ascCount==listLimit) {
				//This Returns if sorting is Ascending
				return SORTING.ASC;
			}else if(sortCount+dscCount==listLimit){
				//This Returns if sorting is Descending
				return SORTING.DSC;
			}
			
			logError("Could not verify sorting for element " + element);
			return null;
		}
		catch(Exception e) {
			logError("Failed to verify sorting of data" +  
					 e.getMessage());
			return null;
		}	
	}
	
	/**
	 * This method is used to verify sorted elements on UI with sorted elements by code
	 * @author hingorani_a
	 * @param element The list of WebElements on which sorting operation is 
	 * to be performed 
	 * @param reverseSort If we want to verify the values in Descending order
	 * @return boolean This returns as true if sorting is verified as Ascending or Descending
	 */
	public static boolean verifySortingForColumnHavingSpecialChars(List<WebElement> element, boolean reverseSort) {
		int sortCount =0;
		int listLimit = 8;
		String elementFromUI = null, elementFromSortedList = null;
		List<String> listOfStrings = new ArrayList<String>();
		try {

			if(element.size()<8)
				listLimit = element.size()-1;
			
			for(int l=0;l<listLimit;l++) {
				listOfStrings.add(element.get(l).getText());
			}
			
			if(!reverseSort)
				Collections.sort(listOfStrings, Collator.getInstance(Locale.US));
			else
				Collections.sort(listOfStrings, Collator.getInstance(Locale.US).reversed());

			for(int l=0;l<listLimit;l++) {
				elementFromUI = element.get(l).getText();
				elementFromSortedList = listOfStrings.get(l);
				
				if(elementFromUI.equalsIgnoreCase(elementFromSortedList)) {
					sortCount++;
					logInfo("Match found for value - " + elementFromUI);
				}
			}

			if(sortCount==listLimit) {
				return true;
			}

			logError("Could not verify sorting for element " + element);
			return false;
		}
		catch(Exception e) {
			logError("Failed to verify sorting of data" +  
					 e.getMessage());
			return false;
		}	
	}


	public static class SORTING {
		public static final String ASC = "ASC";
		public static final String DSC = "DSC";
		public static final String EQL = "EQL";
	}
	
}
