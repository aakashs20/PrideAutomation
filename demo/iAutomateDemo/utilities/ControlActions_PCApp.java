package com.project.utilities;


import java.text.SimpleDateFormat;
import java.util.*;

import com.project.testbase.TestBase;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ControlActions_PCApp extends TestBase {
	WindowsDriver<WebElement> winAppDriver;
	public WebDriverWait wait;	
	public WebDriverWait shortWait;
	public Actions action;

	public ControlActions_PCApp(WindowsDriver<WebElement> winAppDriver){
		this.winAppDriver = winAppDriver;
		PageFactory.initElements(this.winAppDriver, this);	
		action = new Actions(this.winAppDriver);
		wait = new WebDriverWait(winAppDriver, 30, 10);
	}

	/**
	 * This method is used to perform click action on element
	 * @author pashine_a
	 * @param null
	 * @return null
	 */
	public void click(WebElement element){
		try {
			action.moveToElement(element).build().perform();			
			wait.until(ExpectedConditions.elementToBeClickable(element)); 
			element.click();	
			threadsleep(2000);
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * This method is used to perform set text action on element
	 * @author pashine_a
	 * @param null
	 * @return null
	 */
	public void sendKeys(WebElement element, String value){
		try {
			//	wait.until(ExpectedConditions.elementToBeClickable(element)); 
			action.moveToElement(element).build().perform();			
			element.clear();
			element.sendKeys(value);
			threadsleep(2000);
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * This method is used check that element is displayed
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean isElementDisplayed(String xPath) {
		WebElement element = null;
		boolean displayStatus = false;
		try { 
			threadsleep(2000);
			if(winAppDriver.findElements(By.xpath(xPath)).isEmpty()) {
				return false;
			}
			try {
				element = winAppDriver.findElement(By.xpath(xPath));
				displayStatus = element.isDisplayed();
			}catch(StaleElementReferenceException sere) {
				return false;
			}catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return displayStatus;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isElementDisplayed(WebElement element) {
		try {
			threadsleep(2000);
			return element.isDisplayed();
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getElementText(WebElement element) {
		String text = null;
		try { 
			threadsleep(2000);
			text = element.getText();
			return text;
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void waitForElementToBeVisisble(WebElement element) {
		try { 
			wait.until(ExpectedConditions.visibilityOf(element)); 
			threadsleep(2000);
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	public void waitForElementToBeInVisisble(WebElement element) {
		try { 
			threadsleep(2000);
			WebDriverWait wait1 = new WebDriverWait(winAppDriver, 120, 10);
			wait1.until(ExpectedConditions.invisibilityOf(element)); 
			threadsleep(2000);
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void waitForElementToGetInVisisble(String xPath) {
		WebElement element = null;
		try { 
			threadsleep(2000);
			if(!winAppDriver.findElements(By.xpath(xPath)).isEmpty()) {
				element = winAppDriver.findElement(By.xpath(xPath));
				while(true) {
					if(!element.isDisplayed()) {
						break;
					}
				}
			}
			threadsleep(2000);
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public boolean isElementAvailable(String xPath) {
		try { 
			threadsleep(2000);
			if(winAppDriver.findElements(By.xpath(xPath)).isEmpty()) {
				return false;
			}
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public WebElement getDynamicElement(String xPath, String replaceText, String replaceTextTo){
		WebElement element = null;
		String finalXPath = null;
		try{
			finalXPath = xPath.replaceAll(replaceText, replaceTextTo);
			element = winAppDriver.findElement(By.xpath(finalXPath));
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return element;
	}

	public String getDynamicXPath(String xPath, String replaceText, String replaceTextTo){
		String finalXPath = null;
		try{
			finalXPath = xPath.replaceAll(replaceText, replaceTextTo);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return finalXPath;
	}

	public void clickElement(WebElement element){
		try {
			action.moveToElement(element).build().perform();			
			element.click();	
			threadsleep(2000);
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public String getDayDate(int count){
		try {
			String pattern = "d";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, count);
			String date = simpleDateFormat.format(calendar.getTime());
			return date;	
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<WebElement> getDynamicElements(String xPath, String replaceText, String replaceTextTo){
		List<WebElement> elementList = null;
		String finalXPath = null;
		try{
			finalXPath = xPath.replaceAll(replaceText, replaceTextTo);
			elementList = winAppDriver.findElements(By.xpath(finalXPath)); 
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return elementList;
	}


	public void sendKeys(WebElement element, Keys enter) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element)); 
			action.moveToElement(element).build().perform();			
			element.clear();
			element.sendKeys(enter);
			threadsleep(2000);
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}		
	}

	public void waitForElementToBePresent(String xPath) {
		By locator = By.xpath(xPath);
		try { 
			threadsleep(2000);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator)); 
			threadsleep(2000);
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List<WebElement> getListOfElements(String xPath){
		List<WebElement> element;
		try{
			element = winAppDriver.findElements(By.xpath(xPath));
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return element;
	}

	public void simpleClick(WebElement element){
		try {
			action.moveToElement(element).build().perform();			
			element.click();	
			threadsleep(2000);
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void clickTillElementFound(WebElement element){
		while(true) {
			try {
				element.click();	
				threadsleep(2000);
				break;
			}catch (NoSuchElementException e) {
			}catch (Exception e) {
				e.printStackTrace();
				throw e;

			}
		}
	}

	/*
	 * @Purpose 'getDate' method is used to get Date in provided format
	 * @param format
	 * @param duration
	 * @param value
	 * @author - pashine_a 
	 */
	public String getDate(String format, String duration, int value) {
		String dateDetails = null;
		try {
			String pattern = format;
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
			dateDetails = simpleDateFormat.format(calendar.getTime());
		}catch(Exception e) {

		}
		return dateDetails;
	}

	/**
	 * This method is used to perform double click action on element
	 * @author pashine_a
	 * @param null
	 * @return null
	 */
	public void doubleClick(WebElement element){
		try {
			action.moveToElement(element).build().perform();			
			action.doubleClick(element).build().perform();			
			threadsleep(2000);
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}


	public void clear(WebElement element){
		try {			
			action.moveToElement(element).build().perform();			
			element.clear();
			threadsleep(2000);
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * This method is used to hover on element
	 * @author pashine_a
	 * @param element
	 * @return null
	 */
	public void hoverElement(WebElement element){
		try {
			action.moveToElement(element).build().perform();			
			threadsleep(1000);
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * This method is used to perform multiple selection click actions on elements
	 * @author pashine_a
	 * @param fileNames List
	 * @return null
	 */
	public boolean selectMultiple(List<String> fileNames){
		try {
			WebElement element;
			action.keyDown(Keys.CONTROL).build().perform();
			for(int i=0;i<fileNames.size();i++) {
				element = winAppDriver.findElement(By.xpath("//*[@Name='"+fileNames.get(i)+"']"));
				action.moveToElement(element).build().perform();		
				element.click();
			}
			action.keyUp(Keys.CONTROL).build().perform();
			threadsleep(4000);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is used to set value & click on different element
	 * @author pashine_a
	 * @param element1
	 * @param value
	 * @param element2
	 * @return null
	 */
	public boolean setTextAndCLick(WebElement element1, String value, WebElement element2){
		try {
			element1.sendKeys(value);
			element2.click();	
			threadsleep(2000);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}

