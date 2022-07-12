package com.project.utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.testbase.TestBase;
import com.project.utilities.enums.WaitType;


import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ControlActions extends TestBase {
	WebDriver driver;
	public Actions action;
	public WebDriverWait wait;	
	public WebDriverWait shortWait;
	public CommonPage commonPage;

	public ControlActions(WebDriver driver){
		this.driver = driver;
		action = new Actions(this.driver);
		wait = new WebDriverWait(driver, 20);
		shortWait = new WebDriverWait(driver, 0, 500);
		PageFactory.initElements(driver, this);
	}

	public static Robot rs = null;

	/**
	 * This method is used to find whether the WebElement is displayed or not
	 * It is done using 'by' locating mechanism
	 * @param element The WebElement to be verified if it is displayed or not
	 */
	public void isElementDisplayed(By element) {
		try {
			driver.findElement(element).isDisplayed();
		} catch (ElementNotVisibleException e) {
			throw e;
		} catch (StaleElementReferenceException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * This method is used to find whether the WebElement is displayed or not
	 * @param webElement The WebElement to be verified if it is displayed or not
	 * @return boolean This returns true if the element is displayed
	 */
	public boolean isElementDisplayed(WebElement webElement) {
		try {
			webElement.isDisplayed();
			return true;
		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
			return false;
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}

	}

	/*
	 * @Purpose - This method 'isElementDisplayed' is used to find whether the webelement is displayed or not 
	 */
	public void isElementPresent(WebElement element) {
		try { 
			element.isDisplayed();
		} catch (NoSuchElementException e) {
			throw e;
		} catch (StaleElementReferenceException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	/* @author v-amahajan
	 * This method is used to check if element is displayed
	 * @param WebElement
	 */
	public boolean isElementDisplay(WebElement element) {
		try {
			return element.isDisplayed();
		} 
		catch(Exception e) {
			return false;
		}
	}


	/**
	 * This method is used to navigate to a Url passed in the argument and maximizes the window
	 * Also waits till page loads
	 * @param url The url where you want to be navigated
	 */
	public  void getUrl(String url){
		try{
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);}
		catch(Exception e){
			e.printStackTrace();
			Assert.fail("Page Not loaded within 40 Seconds ");
		}

	}

	/**
	 * This method takes WebElement as an argument and using action class move and click on the Web Element
	 * @param element The WebElement to be clicked
	 */
	public  void clickbutton(WebElement element) {
		isElementPresent(element);
		try {
			action.moveToElement(element).click();			
			action.build().perform();
		} catch (ElementNotVisibleException e) {
			throw e;

		} catch (StaleElementReferenceException e) {
			throw e;

		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * This method takes WebElement as an argument and using action class move and click on the WebElement
	 * Also internally check whether element present or not
	 * @param element The WebElement to be clicked
	 */
	public  void clickOnElement(WebElement element) {
		isElementPresent(element);
		try{
			driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		}catch(Exception e){
			throw e;
		}
		isElementPresent(element);
		try {
			action.moveToElement(element).build().perform();
			action.click().build().perform();
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * This method is used to select value from dropdown as per the visible value
	 * @param value The visible value to be set from dropdown
	 * @param element The dropdown element using By locating mechanism 
	 */
	public  void selectDropDown(String value, By element) {

		try {
			Select select = new Select(driver.findElement(element));
			select.selectByVisibleText(value);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * This method is used to click on the WebElement and then waits for 2 seconds
	 * @param element The element to be clicked 
	 */
	public  void click(WebElement element) {
		try {
			element.click();
			threadsleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/*
	 * @Purpose - This method 'sendKeys' takes string value and webElement as arguments and 
	 * type string value in the element
	 */
	/**
	 * This method is used to take the value, clears the element and types string value in the element
	 * It waits for 2 seconds after setting the value to element
	 * @param element The element to be set with text
	 * @param mesg The message/text to be set to the WebElement 
	 */
	public  void sendKeys(WebElement element, String mesg) {
		try {
			//WaitforelementToBeClickable(element);
			element.clear();
			//action.moveToElement(element).build().perform(); ---- not able to upload document because of this 
			element.sendKeys(mesg);
			threadsleep(2000);
		} catch (Exception e) {
			throw e;
		}
	}
	/**
	 * This method is used to switch to the frame based on the index provided in the argument
	 * @param index The index of the frame you want to switch to
	 */
	public  void switchToframeindex(int index) {
		try {
			driver.switchTo().frame(index);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * This method is used to switch to the frame based on the Name or ID provided in the argument
	 * @param NameorID The name of the frame window, the id of the frame or iframe element, 
	 * or the (zero-based) index
	 */
	public  void switchToframeNameorID(String NameorID) {
		try {
			driver.switchTo().frame(NameorID);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * This method is used to switch to the default i.e main document
	 */
	public  void switchToDefault() {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * This method is used to send Keyboard TAB to an active element using action class
	 */
	public  void actionclickTab() {
		try {
			action.sendKeys(Keys.TAB).build().perform();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * This method is used to perform Keyboard "double tab" to an active element using action class
	 */
	public  void actionclickDoubleTab() {
		try {
			action.sendKeys(Keys.TAB, Keys.TAB).build().perform();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * This method is used to press "Enter" key to an active element using action class
	 */
	public  void actionEnter() {
		try {
			action.sendKeys(Keys.ENTER);
			action.build().perform();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * This method is used to press "Down" key to an active element using action class
	 */
	public void actionDown() {
		try {
			action.sendKeys(Keys.DOWN);
			action.build().perform();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * This method is used to refresh current displayed WebPage. 
	 * It wait for 5 seconds post refreshing the WebPage.
	 */
	public  void refreshPage() {
		try {
			driver.navigate().refresh();
			commonPage = new CommonPage(driver);
			commonPage.Sync();
			threadsleep(5000);
		} catch (Exception e) {
			throw e;

		}
	}

	/**
	 * This method is used to provide selenium explicit wait on condition, visibility of element 
	 * @param element The WebElement to be checked for visibility
	 */
	public  void waitforElementToBeDisplayed(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));

		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * This method is used to provide selenium explicit wait on condition, visibility of elements 
	 * @param element The List of WebElement to be checked for visibility
	 */
	public  void waitforvisibilityOfAllElements(List<WebElement> element) {
		try {
			wait.until(ExpectedConditions.visibilityOfAllElements(element));
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * This method is used to provide selenium explicit wait on condition, elementToBeClickable 
	 * @param element The WebElement to be checked if it is visible and enabled such that you can click it.
	 */
	public void WaitforelementToBeClickable(WebElement element)
	{   
		try{
			wait.until(ExpectedConditions.elementToBeClickable(element)); 
		}
		catch(Exception e){
			throw e;
		}
	}

	/**
	 * This method is used to provide selenium explicit wait on condition, elementSelectionStateToBe 
	 * @param element The WebElement to be selected
	 * @param selected State of the selection state of the element
	 */
	public void WaitforelementSelectionStateToBe(WebElement element,boolean selected)
	{
		wait.until(ExpectedConditions.elementSelectionStateToBe(element, selected));

	}

	/**
	 * This method is used to provide selenium explicit wait on condition, frameToBeAvailableAndSwitchToIt 
	 * @param element The value used to find the frame (index)
	 */
	public void frameToBeAvailableAndSwitchToIt(int element)
	{
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));

	}

	/**
	 * This method is used to provide selenium explicit wait on condition, invisibilityOfAllElements 
	 * @param listOfItemInSearchResult The List of WebElements are checked for their invisibility
	 */
	public void WaitforinvisibilityOf(List<WebElement> listOfItemInSearchResult)
	{
		wait.until(ExpectedConditions.invisibilityOfAllElements(listOfItemInSearchResult));
	}

	/**
	 * This method is used to provide selenium explicit wait on condition, elementToBeSelected 
	 * @param element The WebElement to be selected
	 */
	public void WaitforelementToBeSelected(WebElement element)
	{
		wait.until(ExpectedConditions.elementToBeSelected(element));
	}

	/**
	 * This method is used to provide selenium explicit wait on condition, textToBePresentInElementValue 
	 * @param element The WebElement whose text is to be verified
	 * @param text The text to be verified against the text present in the element's value attribute
	 */
	public void waifortextToBePresentInElementValue(WebElement element,String text)
	{
		wait.until(ExpectedConditions.textToBePresentInElementValue(element, text));
	}

	/** 
	 * This method is used to provide selenium explicit wait on condition, alertIsPresent
	 * It lets us wait till the alert is present
	 */
	public  void waitforAlertToBeDisplayed() {
		try {
			wait.until(ExpectedConditions.alertIsPresent());

		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * This method is used to move to an element using action class and perform click action
	 * @param element The WebElement to be clicked
	 */
	public  void moveToElementAction(WebElement element) {
		try {
			action.moveToElement(element).click();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * This method is used to send values using action class
	 * @param str The text value to be set
	 */
	public  void actionSendKeys(String str) {
		try {
			action.sendKeys(str);
		} catch (Exception e) {
			throw e;

		}
	}

	/**
	 * This method is used to build the action and then perform the action
	 */
	public  void buildPerform() {
		try {
			action.build().perform();
		} catch (Exception e) {
			throw e;

		}
	}

	/**
	 * This method is used to create an object of Select class
	 * @param element The WebElement to be checked if the given element is, indeed, a SELECT tag.
	 * @return This returns the select object 
	 */
	public Select select(WebElement element)
	{
		Select select = new Select(element);
		return select;
	}

	/**
	 * This method executes the specified string command in a separate process using
	 * an invocation of the form exec(command)
	 * @param Filepath A specified system command
	 */
	public  void uploadFnmFile(String Filepath) {
		try {
			Runtime.getRuntime().exec(Filepath);

			Thread.sleep(5000);
		} catch (IOException e) {

			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to create an object of robot class
	 * @return This returns the Robot object
	 */
	public  Robot robot() {
		try {
			rs = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * This method is used to upload file in the Webpage using robot class keyevent
	 * @param filepath The pathname
	 */
	public  void fileUpload(String filepath) {
		try {
			File file = new File(filepath);
			String str = file.getAbsolutePath();
			StringSelection selection = new StringSelection(str);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(selection, selection);
			rs = robot();
			rs.keyPress(KeyEvent.VK_CONTROL);
			rs.keyPress(KeyEvent.VK_V);
			rs.keyRelease(KeyEvent.VK_V);
			rs.keyRelease(KeyEvent.VK_CONTROL);
			rs.keyPress(KeyEvent.VK_ENTER);
			rs.keyRelease(KeyEvent.VK_ENTER);
			rs.keyRelease(KeyEvent.VK_ENTER);
			rs.delay(5000);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 *  This method is used to close the Print window.
	 */
	public void ClosePrintWindow() {
		try {
			rs = robot();
			rs.keyPress(KeyEvent.VK_ESCAPE); 
			rs.keyRelease(KeyEvent.VK_ESCAPE);
			//act.sendKeys(Keys.ESCAPE);

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to select value from dropdown as per the visible value
	 * @param value The visible value to be set from dropdown
	 * @param element The dropdown element  
	 */
	public void selectDropDownValue(String value, WebElement element) {
		try {
			Select select = new Select(element);
			select.selectByVisibleText(value);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * This method is used to select value from dropdown as per the value
	 * @param value The value to be set from dropdown. Select all options that have a value matching the argument. 
	 * That is, when given "value attribute" this would select an option between the tags 'option'
	 * @param element The dropdown element  
	 */
	public void selectDropDown(String value, WebElement element) {
		try {
			Select select = new Select(element);
			select.selectByValue(value);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * This method is used to select value from dropdown as per the value
	 * @param i The option at this index will be selected
	 * @param element The dropdown element  
	 */
	public void selectDropDownByIndex(int i,WebElement element){
		try{
			Select select = new Select(element);
			select.selectByIndex(i);
		}catch(Exception e){
			throw e;
		}
	}

	public void selectDefaultOption(WebElement element) {
		try {
			Select select = new Select(element);
			WebElement option = select.getFirstSelectedOption();
			defaultItemOption = option.getText();

		} catch (Exception e) {
			throw e;
		}
	}

	public void switchToframeId(int id) {
		try {
			driver.switchTo().frame(id);
		} catch (Exception e) {
			throw e;
		}
	}

	public void switchToframeByLocators(WebElement element) {
		try {
			driver.switchTo().frame(element);

		} catch (StaleElementReferenceException e) {
			System.err.println("Page Refreshed Because of Stale element issue ");
			driver.navigate().refresh();
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			waitForFrameToAvailable(element);

		}
		catch (Exception e) {
			throw e;
		}
	}

	/** 
	 * This method is used to provide selenium explicit wait on condition, frameToBeAvailableAndSwitchToIt
	 * @param element The WebElement used to find the frame 
	 */
	public void waitForFrameToAvailable(WebElement element ){
		try{
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		}
		catch(Exception e){
			throw e;
		}
	}

	/** 
	 * This method is used to scroll to the end of the page using action class's keys
	 */
	public void actionScrollDown(){
		try {
			action.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();		}
		catch (Exception e) {
			throw e;
		}
	}

	/** 
	 * This method is used to scroll to the start of the page using action class's keys
	 */
	public void actionScrollUp(){
		try {
			action.keyDown(Keys.CONTROL).sendKeys(Keys.HOME).perform();		} 
		catch (Exception e) {
			throw e;
		}
	}

	/** 
	 * This method is used to click, send text and then Tab from the WebElement we need to set text in
	 * Tab is performed using action class's keys
	 * @param element The WebElement to which we need to set text
	 * @param str The text to be set
	 */
	public void actionSendKeys(WebElement element, String str){
		try {
			action.click(element).sendKeys(str).sendKeys(Keys.TAB).build().perform();;
		} catch (Exception e) {
			throw e;
		}
	}

	/** 
	 * This method is used to double click on the element having text and perform backspace operation
	 * Backspace is performed using action class's keys
	 * @param element The WebElement to be cleared
	 */
	public void actionClearTextBox(WebElement element){
		try{
			action.click(element).doubleClick().sendKeys(Keys.BACK_SPACE).build().perform();

		}catch(Exception e){
			throw e;
		}
	}

	/** 
	 * This method is used to perform double Tab operation. Tab is performed using action class's keys
	 */
	public void clickDoubleTab() {
		try {
			action.sendKeys(Keys.TAB, Keys.TAB).build().perform();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * This method is used to provide selenium explicit wait on condition, elementToBeClickable 
	 * @param locator It is used to find the WebElement
	 * @return The WebElement once it is located and clickable 
	 */
	public WebElement perform_waitUntilClickable(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * This method is used to provide selenium explicit wait on condition, visibilityOfElementLocated 
	 * @param locator It is used to find the WebElement
	 * @return The WebElement once it is located and clickable 
	 */
	public WebElement perform_waitUntilVisibility(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * This method is used to provide selenium explicit wait on condition, presenceOfElementLocated 
	 * @param locator It is used to find the WebElement
	 * @return The WebElement once it is located 
	 */
	public WebElement perform_waitUntilPresent(By locator) {
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * This method is used to provide selenium explicit wait on condition, invisibilityOfElementLocated 
	 * @param locator It is used to find the WebElement
	 * @return Boolean This returns true if the element is not displayed or 
	 * the element doesn't exist or stale element
	 */
	public Boolean perform_waitUntilNotVisible(By locator) {
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	/**
	 * This method is used to provide selenium explicit wait on condition, invisibilityOfElementLocated 
	 * @param xPath It is used to find the WebElement using xpath
	 * @return Boolean This returns true if the element is not displayed or 
	 * the element doesn't exist or stale element
	 */
	public Boolean perform_waitUntilNotVisible(String xPath) {
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPath)));
	}

	/**
	 * This method is used to provide selenium explicit wait on condition, invisibilityOf 
	 * @param element The WebElement to be checked for its invisibility
	 * @return Boolean This returns true when elements is not visible anymore
	 */
	public Boolean perform_waitUntilNotVisible(WebElement element) {
		return wait.until(ExpectedConditions.invisibilityOf(element));
	}

	/**
	 * This method is used to provide selenium explicit wait on condition, elementToBeClickable 
	 * @param element The WebElement to be checked if it's clickable or not
	 * @return The WebElement once it is clickable 
	 */
	public WebElement perform_waitUntilClickable(WebElement element) {
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * This method is used to provide selenium explicit wait on condition, visibilityOf 
	 * @param element The WebElement to be checked for its visibility
	 * @return The WebElement once it is visible
	 */
	public WebElement perform_waitUntilVisibility(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * This method is used to switch to the frame based on the element
	 * @param element The frame element to switch to
	 */
	public  void switchToframeByIFrameElement(WebElement element) {
		try {
			driver.switchTo().frame(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to scroll to the specified element
	 * @param element The element we need to scroll to
	 */
	public void perform_scrollToElement_ByElement(WebElement element){
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * This method is used to click on the specified element
	 * @param element The element to be clicked
	 */
	public boolean perform_clickElement_ByElement(WebElement element){
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			return true;
		}
		catch(Exception e) {
			logError("Failed to click element via JavascriptExecutor - "
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to set value to the element
	 * @author hingorani_a
	 * @param element The element to be set with value
	 * @param value Value to be set
	 */
	public void perform_setValue_ByElement(WebElement element, String value){
		((JavascriptExecutor) driver).executeAsyncScript("arguments[0].value='"+value+"'", element);
	}

	/**
	 * This method is used to provide selenium explicit wait on condition, presenceOfAllElementsLocatedBy
	 * @param locator It is used to find the element
	 * @return WebElement This returns list of WebElements
	 */
	public List<WebElement> perform_waitUntilListPresent(By locator) {
		List<WebElement> placedReviews_PresentElements;
		try {
			placedReviews_PresentElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			placedReviews_PresentElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		}
		return placedReviews_PresentElements;
	}

	/**
	 * This method is used to provide selenium explicit wait on condition, visibilityOfAllElementsLocatedBy
	 * @param locator It is used to find the element
	 * @return WebElement This returns list of WebElements
	 */
	public List<WebElement> perform_waitUntilListVisible(By locator) {
		List<WebElement> placedReviews_VisibleElements;
		try {
			placedReviews_VisibleElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			placedReviews_VisibleElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}
		return placedReviews_VisibleElements;
	}

	/**
	 * This method is used to retrieve list of WebElements using By locating mechanism 
	 * @param locator It is used to find the element
	 * @return List This returns list of WebElements
	 */
	public List<WebElement> perform_waitUntilListDefault(By locator) {
		List<WebElement> placedReviews_FindedBuDefaultElements;
		try {
			placedReviews_FindedBuDefaultElements = driver.findElements(locator);
		}
		catch(StaleElementReferenceException ex)
		{
			placedReviews_FindedBuDefaultElements =  driver.findElements(locator);
		}
		return placedReviews_FindedBuDefaultElements;
	}

	/**
	 * This method is used to click outside of an element
	 */
	public void perform_click_outside_of_element(){
		perform_GetElementByXPath("//body").click();
	}

	/**
	 * This method is used to get Value of the list of Elements mentioned
	 * @param webElementsList The Element list for whose values are needed
	 * @return ArrayList This returns list of values corresponding to the 
	 * list of Elements sent as a parameter to this function
	 */
	public ArrayList<String> perform_convertListOfWebElements_ToListOfStrings(List<WebElement> webElementsList){
		ArrayList<String> listOfStrings = new ArrayList<>();
		for(WebElement element:webElementsList){
			listOfStrings.add(element.getText());
		}
		return listOfStrings;
	}

	/**
	 * This method is used to navigate to a Url passed in the argument
	 * Also waits till page loads until 30 seconds
	 * @param url The url where you want to be navigated
	 */
	public void perform_OpenUrl(String url) {
		driver.manage().timeouts().pageLoadTimeout(
				30, TimeUnit.SECONDS);
		try {
			driver.get(url);
		} catch (Exception ex) {
			System.out.println("Page was not loaded in 30 seconds." + ex);
		}
	}


	/**
	 * This method is used to get the current WebPage's url
	 * @return String The value of url is returned
	 */
	public String perform_GetCurrentUrl(){
		return driver.getCurrentUrl();
	}

	//@Step("Click on the element")
	public void perform_ClickOnElement(WebElement webElement){
		webElement.click();
		/*        try {
            waitUntilElementClickable(webElement).click();
        }catch(ElementClickInterceptedException e){
            waitUntilElementVisibility(webElement).click();
        }*/
	}

	/**
	 * This method is used to Type text into element by XPath.
	 * After setting the element with text it wait till 5 seconds
	 * @param xPath The xpath to get WebElement
	 * @param value The text to be set to WebElement
	 * @param clear If set as true, the contents of the WebElement will be cleared
	 * @return boolean This returns true if the value is set successfully
	 */
	public boolean perform_PutText(String xPath, String value, boolean clear) {
		try {
			WebElement element = perform_GetElementByXPath(xPath);
			perform_ClickOnElement(element);
			if(clear) {
				element.clear();
			}
			element.sendKeys(value);
			threadsleep(5000);
			return true;
		}
		catch(Exception e) {
			logError("Failed to set text - " + value + " to element : "
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to Get Element By XPath and Specific Condition : WaitType
	 * @param xPath The xpath to get WebElement
	 * @param expCond Use Enum WaitType to set this value.
	 *  When value is set to WhenClickable, it uses selenium explicit wait condition, elementToBeClickable 
	 *  When value is set to WhenVisible, it uses selenium explicit wait condition, visibilityOfElementLocated 
	 *  When value is set to WhenPresent, it uses selenium explicit wait condition, presenceOfElementLocated 
	 *  If no value is used from the above, the element will be retrieved normally using findElement
	 * @return WebElement This returns the first matching element on the current page 
	 */
	public WebElement perform_GetElementByXPath_WithWaitType(String xPath, WaitType expCond){
		switch (expCond){
		case WhenClickable:
			return perform_waitUntilClickable(By.xpath(xPath));
		case WhenVisible:
			return perform_waitUntilVisibility(By.xpath(xPath));
		case WhenPresent:
			return perform_waitUntilPresent(By.xpath(xPath));
		default:
			return driver.findElement(By.xpath(xPath));
		}
	}

	/**
	 * This method is used to Get Element By XPath and Specific Condition : WaitType
	 * @param xPath The xpath to get WebElement
	 * @param expCond Use Enum WaitType to set this value.
	 *  When value is set to WhenVisible, it uses selenium explicit wait condition, visibilityOfElementLocated 
	 *  When value is set to WhenPresent, it uses selenium explicit wait condition, presenceOfElementLocated 
	 *  If no value is used from the above, the element will be retrieved normally using findElement
	 * @return List This returns a list of all WebElements, or an empty list if nothing matches
	 */
	public List<WebElement> perform_GetListOfElementsByXPath_WithWaitType(String xPath, WaitType expCond){
		switch (expCond){
		case WhenVisible:
			return perform_waitUntilListVisible(By.xpath(xPath));
		case WhenPresent:
			return perform_waitUntilListPresent(By.xpath(xPath));
		default:
			return perform_waitUntilListDefault(By.xpath(xPath));
		}
	}

	/**
	 * This method is used to Get Element By XPath by trying (WaitUntilClickable, 
	 * WaitUntilVisibility, WaitUntilPresent, defaultFindElement)
	 * @param xPath The xpath to get WebElement
	 * @return WebElement This returns the first matching element on the current page 
	 */
	public WebElement perform_GetElementByXPath(String xPath){
		WebElement webElement;
		try{
			webElement = perform_waitUntilClickable(By.xpath(xPath));
		}catch(TimeoutException e){
			webElement = perform_waitUntilVisibility(By.xpath(xPath));
		}
		try{
			webElement = perform_waitUntilPresent(By.xpath(xPath));
		}catch(TimeoutException e2){
			webElement = driver.findElement(By.xpath(xPath));
		}
		return webElement;
	}

	/**
	 * This method is used to dynamically retrieve WebElement.
	 * @author hingorani_a
	 * @param xPath The xpath to be manipulated
	 * @param replaceWith String to be replaced
	 * @param replaceTo String to be replaced with
	 * @return WebElement This returns WebElement after dynamically retrieval of it using xpath
	 */
	public WebElement perform_GetElementByXPath(String xPath, String replaceWith, String replaceTo){
		WebElement webElement = null;
		String finalXpath = null;
		try{
			finalXpath = xPath.replaceAll(replaceWith, replaceTo);
			webElement = driver.findElement(By.xpath(finalXpath));
		}
		catch(Exception e){
			logError("Failed to get element with xpath - " + finalXpath + " - "
					+ e.getMessage());
		}
		return webElement;
	}

	/**
	 * This method is used to create dynamic XPath for an element.
	 * @author hingorani_a
	 * @param xPath The xpath to be manipulated
	 * @param replaceWith String to be replaced
	 * @param replaceTo String to be replaced with
	 * @return String This returns xpath created dynamically
	 */
	public String perform_GetDynamicXPath(String xPath, String replaceWith, String replaceTo){
		String finalXpath = null;
		try{
			finalXpath = xPath.replaceAll(replaceWith, replaceTo);
		}
		catch(Exception e){
			logError("Failed to get dynamic xpath - " + finalXpath + " - "
					+ e.getMessage());
		}
		return finalXpath;
	}

	/**
	 * This method is used to retrieve List of WebElements using xpath
	 * @param xPath The xpath to get elements
	 * @return List This returns List of WebElements once located
	 */
	public List<WebElement> perform_GetListOfElementsByXPath(String xPath){
		return perform_waitUntilListVisible(By.xpath(xPath));
	}

	/**
	 * This method is used to dynamically retrieve List of WebElements
	 * @author hingorani_a
	 * @param xPath The xpath to be manipulated
	 * @param replaceWith String to be replaced
	 * @param replaceTo String to be replaced with
	 * @return List This returns List of WebElements after dynamically retrieval of it using xpath
	 */
	public List<WebElement> perform_GetListOfElementsByXPath(String xPath, String replaceWith, String replaceTo){
		List<WebElement> webElement = null;
		String finalXpath = null;
		try{
			finalXpath = xPath.replaceAll(replaceWith, replaceTo);
			webElement = driver.findElements(By.xpath(finalXpath));
			webElement = perform_waitUntilListPresent(By.xpath(finalXpath));
		}catch(Exception e){
			logError("Failed to get elements with xpath - " + finalXpath + " - "
					+ e.getMessage());
		}
		return webElement;
	}

	/**
	 * This method is used to verify if text of Element is equal to the one provided
	 * @author hingorani_a
	 * @param element Element whose text is to be compared
	 * @param value Value to be compared with 
	 * @return boolean This returns boolean value as per the comparison.
	 * Returns true, if the Element text equals to the passed value
	 */
	public boolean perform_CheckIfElementTextEquals(WebElement element, String value){
		boolean checkIfPresent = false;
		logInfo("Verify Element text : " +  element.getText() + " Equals value : " + value);
		if (element.getText().equalsIgnoreCase(value)) {
			checkIfPresent = true;
		}
		return checkIfPresent;
	}

	/**
	 * This method is used to verify if text of Element is contains the value provided
	 * @author hingorani_a
	 * @param element Element whose text is to be compared
	 * @param value Value to be compared with 
	 * @return boolean This returns boolean value as per the comparison.
	 * Returns true, if the Element text contains the passed value
	 */
	public boolean perform_CheckIfElementTextContains(WebElement element, String value){
		boolean checkIfPresent = false;
		logInfo("Verify Element text : " +  element.getText() + " Contains value : " + value);
		if (element.getText().contains(value)) {
			checkIfPresent = true;
		}
		return checkIfPresent;
	}

	/**
	 * This method is used to verify if an Element is present in the provided List of WebElements
	 * @param list List of WebElements on which presence of an element will be verified
	 * @param element The element whose presence need to be verified 
	 * @return boolean This returns boolean value as per the comparison.
	 * Returns true, if the Element is present
	 */
	public Boolean perform_CheckIfelementPresentInTheList(List<WebElement> list, String element){
		Boolean checkIfPresent = false;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getText().equals(element)) {
				list.get(i).click();
				checkIfPresent = true;
				break;
			}
		}
		return checkIfPresent;
	}

	/**
	 * This method is used to verify if both the provided List of WebElements have same elements
	 * @param listA First List of WebElements
	 * @param listB Second List of WebElements
	 * @return boolean This returns boolean value as per the comparison.
	 * Returns true, if the both the List of WebElements are same
	 */
	public Boolean perform_CheckIfElementsInTheListsAreSame(ArrayList<String> listA, ArrayList<String> listB){
		if(!(listA.size()==listB.size())){
			return false;
		}
		for (int i = 0; i < listA.size(); i++) {
			for (int j = 0; j < listB.size(); j++) {
				if(!listA.get(i).equals(listB.get(j))){
					System.out.println("THIS ELEMENTS ARE NOT THE SAME: "+listA.get(i)+"="+listB.get(j));
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * This method is used to verify if both the provided List of WebElements have same text values
	 * @param listA First List of WebElements
	 * @param listB Second List of WebElements
	 * @return boolean This returns boolean value as per the comparison.
	 * Returns true, if the both the List of WebElements have same text values
	 */
	public Boolean perform_CheckIfElementsInTheListsAreSame(List<WebElement> listA, List<WebElement> listB){
		if(!(listA.size()==listB.size())){
			return false;
		}
		for (int i = 0; i < listA.size(); i++) {
			for (int j = 0; j < listB.size(); j++) {
				if(!listA.get(i).getText().equals(listB.get(j).getText())){
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * This method is used to verify if the List of Strings is same as text values of the List of WebElements 
	 * @param listA ArrayList of Strings to be compared
	 * @param listB List of WebElements
	 * @return boolean This returns boolean value as per the comparison.
	 * Returns true, if List of Strings is same as text values of the List of WebElements
	 */
	public Boolean perform_CheckIfElementsInTheListsAreSame(ArrayList<String> listA, List<WebElement> listB){
		if(!(listA.size()==listB.size())){
			return false;
		}
		for (int i = 0; i < listA.size(); i++) {
			if(!listA.get(i).equals(listB.get(i).getText())){
				return false;
			}
		}
		return true;
	}

	/**
	 * This method is used to create an object of Select class
	 * @param xPath The WebElement to be retrived using xpath.
	 * It is then checked if the given element is, indeed, a SELECT tag.
	 * @return This returns the select object 
	 */
	public Select perform_GetDropDownSelectionByXPath(String xPath){
		return new Select(perform_GetElementByXPath(xPath));
	}

	/**
	 * This method is used to click on the specified element
	 * @param element The element to be clicked
	 */
	public void perform_ClickWithJavaScriptExecutor(WebElement element){
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	/**
	 * This method is used to wait until new window is opened
	 * @return boolean This returns true if the presence of new window is acknowledged
	 */
	public boolean waitForNewWindow(int timeoutMilisec){
		boolean flag = false;
		int counter = 0;
		while(!flag){
			try {
				Set<String> winId = driver.getWindowHandles();
				System.out.println("WINDOW HANDLES SIZE-->> "+driver.getWindowHandles().size());
				if(winId.size() > 1){
					flag = true;
					return flag;
				}
				Thread.sleep(500);
				counter=counter+500;
				if(counter > timeoutMilisec){
					return flag;
				}
			} catch (Exception e) {
				//System.out.println(e.getMessage());
				return false;
			}
		}
		return flag;
	}

	/**
	 * This method is used to get Value of the list of Elements mentioned
	 * @param element The Element list for whose values are needed
	 * @return List This returns list of values corresponding to the 
	 * list of Elements sent as a parameter to this function
	 */
	public static List<String> getList(List<WebElement> element){

		List<String> list=new ArrayList<String>();                             

		List<WebElement> elements = element;

		System.out.println("Number of elements:" +elements.size());
		for (int i=0; i<elements.size();i++){

			list.add(elements.get(i).getText());

		}
		return list;        
	}

	/**
	 * This method is used to perform Java Script click upon the element retrieved by selector
	 * @author hingorani_a
	 * @param driver Current webdriver instance
	 * @param selector The JavaScript selector to retrieve element 
	 * @return boolean This returns boolean true after Java Script click is performed
	 */
	public boolean JSClick(WebDriver driver, String selector)
	{
		try
		{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			String clickElementByJS = "document.querySelector(\"" + selector + "\")" + ".click()";
			js.executeScript(clickElementByJS);
			return true;
		}
		catch (Exception e)
		{
			logError("Failed to perform JS Click - " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to set value for any element from a List of Elements
	 * @author hingorani_a
	 * @param driver Current webdriver instance
	 * @param selector The JavaScript selector used to represent the list of elements
	 * @param text The unique innerText of element to represent an element
	 * @return boolean This returns boolean true after performing click action on the 
	 * desired element
	 */
	public boolean JSSetValueFromList(WebDriver driver, String selector, String text)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		try
		{
			String setElement = "var list = document.querySelectorAll(\""+selector+"\")\n" + 
					"var myVal = [...list ].filter(e => e.innerText == \""+text+"\")\n" + 
					"if(myVal.length == 1 ) { myVal[0].click(); }";
			js.executeScript(setElement);
			return true;

		}
		catch (Exception e)
		{
			logError("Failed to perform JS Set for an element from list - " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to set value to Kendo Dropdown element
	 * made of UL and LI as option values.
	 * @author hingorani_a
	 * @param driver Current webdriver instance
	 * @param selector The JavaScript selector to click on dropdown so that 
	 * dropdown list is opened
	 * @param optionXpath Xpath of the value to be set
	 * @return boolean This returns boolean true after setting value to Kendo Dropdown
	 */
	public boolean setKendoDropDownValue(WebDriver driver, String selector, String optionXpath){
		try {
			if(!JSClick(driver, selector)) {
				return false;
			}
			logInfo("Clicked to open drop down list");
			WebElement ddlOptionToSelect = driver.findElement(By.xpath(optionXpath)); 
			perform_scrollToElement_ByElement(ddlOptionToSelect);
			ddlOptionToSelect.click();
			return true;
		}
		catch(Exception e){
			logError("Failed to set Kendo Dropdown element with - " + optionXpath + " - "
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to select value from dropdown as per the value
	 * @author hingorani_a
	 * @param value The value to be set from dropdown. Select all options that have a value matching the argument. 
	 * That is, when given "value attribute" this would select an option between the tags 'option'
	 * @param element The dropdown element  
	 * @return boolean This returns true once desired element is selected
	 */
	public boolean selectDropdown(String value, WebElement element) {
		try {
			Select select = new Select(element);
			select.selectByValue(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * This method is used to click on an element after trying elementToBeClickable
	 * using action class. It wait for 2 seconds after clicking on the element
	 *  @author - pashine_a 
	 */
	public  void clickElement(WebElement element) {
		try {
			WaitforelementToBeClickable(element);
			action.moveToElement(element).build().perform();			
			element.click();
			threadsleep(2000);
		}catch (StaleElementReferenceException e) {
			try {
				action.moveToElement(element).build().perform();			
				element.click();
				Thread.sleep(2000);
			}catch(Exception e1) {
				throw e;
			}
		}catch (Exception e) {
			throw e;
		}

	}

	/**
	 * This method is used to double click on an element after trying elementToBeClickable
	 * using action class. It wait for 2 seconds after clicking on the element
	 *  @author - pashine_a 
	 *  @param element The element to be double clicked
	 */
	public  void doubleClick(WebElement element) {
		try {
			WaitforelementToBeClickable(element);
			action.moveToElement(element).build().perform();	
			action.doubleClick().build().perform();
			threadsleep(2000);
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * This method is used to double click on an element after trying elementToBeClickable
	 * using action class. It wait for 2 seconds after clicking on the element
	 * @author - pashine_a 
	 * @param element The element to be double clicked
	 * @throws Exception 
	 */
	public void doubleClickElement(WebElement element) throws Exception {
		try {
			WaitforelementToBeClickable(element);
			action.moveToElement(element).build().perform();	
			action.doubleClick().build().perform();
			Thread.sleep(2000);
		}
		catch (Exception e) {
			throw e;
		}

	}

	/**
	 * This method is used to Clear and Set text to an element using actions class
	 * after trying elementToBeClickable. It waits for 2 seconds after setting the value 
	 * @author pashine_a
	 * @param element The element to which text is to be set
	 * @param value The value to be set
	 */
	public void setValue(WebElement element,String value) {
		try {
			WaitforelementToBeClickable(element);
			action.moveToElement(element).build().perform();
			element.clear();
			element.sendKeys(value);
			threadsleep(2000);
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * This method is used to Set text to an element using actions class
	 * after trying elementToBeClickable. It waits for 2 seconds after setting the value 
	 * @author pashine_a
	 * @param element The element to which text is to be set
	 * @param value The value to be set
	 */
	public void sendKey(WebElement element,Keys value) {
		try {
			WaitforelementToBeClickable(element);
			action.moveToElement(element).build().perform();
			element.sendKeys(value);
			threadsleep(2000);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * This method is used to wait for few seconds before verifying whether element is displayed
	 * @author - pashine_a
	 * @param element The element to be verified if it is displayed or not
	 * @return This return true is the element is displayed
	 */
	public boolean isElementDisplayedOnPage(WebElement element) {
		try {		
			commonPage = new CommonPage(driver);
			commonPage.Sync();
			threadsleep(2000);
			return element.isDisplayed();
		} catch(Exception e) {
			return false;
		}
	}

	/**
	 * This method takes source and target webElements as arguments to perform drag and drop operation
	 * After dragging the element it waits for 2 seconds
	 * @author thakker_k 
	 * @param sourceElement The element to be dragged from
	 * @param targetElement The element to be dragged to 
	 */
	public void dragdrop(WebElement sourceElement,WebElement targetElement) {
		try {
			WaitforelementToBeClickable(sourceElement);
			action.moveToElement(sourceElement).build().perform();	
			WaitforelementToBeClickable(targetElement);
			action.dragAndDrop(sourceElement, targetElement).build().perform();
			threadsleep(2000);
			logInfo("Drag & Drop is performed");
		} catch (Exception e) {	
			logInfo("Issue in performing Drag & Drop");
			throw e;
		}
	}

	/**
	 * This method takes waits for an element until it is retrieved. It loops until StaleElementReferenceException 
	 * is by passed and element is retrieved
	 * @author hingorani_a 
	 * @param xpath The xpath to retrieve element which can be Stale
	 * @return WebElement This returns element once retrieved
	 */
	public WebElement WaitUntilElementIsStale(String xpath)
	{   
		boolean staleElement = true; 
		WebElement element = null;
		while(staleElement){

			try{
				element = driver.findElement(By.xpath(xpath));
				staleElement = false;
			} catch(StaleElementReferenceException e){
				staleElement = true;
			}
		}
		return element;
	}


	/**
	 * This method is used to check that element having the xPath is present or not 
	 * @author pashine_a
	 * @param xPath The xpath to verify if element is displayed or not
	 * @return boolean This returns true if element is displayed
	 */
	public boolean isElementDisplayed(String xPath) {
		WebElement element = null;
		try { 
			commonPage = new CommonPage(driver);
			commonPage.Sync();
			//threadsleep(2000);
			if(driver.findElements(By.xpath(xPath)).isEmpty()) {
				return false;
			}
			element = driver.findElement(By.xpath(xPath));
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is used to set date in 'MM/dd/yyyy' format. Post setting Date it waits for 2 seconds
	 * @author pashine_a 
	 * @param element The element to be set with date
	 * @param duration Put the following values for this Parameter : Year/Month/Week/Day
	 * @param value The amount of date or time to be added to the field
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
		}
	}

	/**
	 * This method is used to set Time in 'hh/HH:mm' or 'hh/HH:mm aa' format. 
	 * Post setting Date it waits for 2 seconds
	 * @author pashine_a 
	 * @param element The element to be set with Time
	 * @param hourCount The amount of date or time to be added to the field as per Calendar.HOUR
	 * @param minuteCount The amount of date or time to be added to the field as per Calendar.MINUTE
	 * @param hour12Format If set as true, the format would be 'hh:mm aa'/'hh:mm' else 'HH:mm aa'/'HH:mm'
	 * @param am_pmMarker If set as true, the format would be 'hh:mm aa'/'HH:mm aa' else 'hh:mm'/'HH:mm'
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
			threadsleep(2000);
		}catch(Exception e) {
		}
	}

	/**
	 * This method is used to set Date in 'MM/dd/yyyy hh/HH:mm' or 'MM/dd/yyyy hh/HH:mm aa' format. 
	 * Post setting Date it waits for 2 seconds
	 * @author pashine_a 
	 * @param element The element to be set with Date
	 * @param duration Put the following values for this Parameter : Year/Month/Week/Day
	 * @param value The amount of date or time to be added to the field
	 * @param hourCount The amount of date or time to be added to the field as per Calendar.HOUR
	 * @param minuteCount The amount of date or time to be added to the field as per Calendar.MINUTE
	 * @param hour12Format If set as true, the format would be 'hh:mm aa'/'hh:mm' else 'HH:mm aa'/'HH:mm'
	 * @param am_pmMarker If set as true, the format would be 'hh:mm aa'/'HH:mm aa' else 'hh:mm'/'HH:mm
	 * 
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
		}
	}

	/**
	 * This method is used to Hover over the mentioned element using actions class
	 * @param element The element to be hovered on
	 */
	public  void hoverElement(WebElement element) {
		try {
			action.moveToElement(element).build().perform();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * This method is used to check that element having the xPath is present or not 
	 * by verifying list contains elements or not
	 * @author pashine_a
	 * @param xPath The xPath to retrieve element
	 * @return boolean This returns true if the element is present
	 */
	public boolean isElementAvailable(String xPath) {
		try { 
			commonPage = new CommonPage(driver);
			commonPage.Sync();
			if(driver.findElements(By.xpath(xPath)).isEmpty()) {
				return false;
			}
			return true;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is used to check that element having the xPath is present or not 
	 * @author pashine_a
	 * @param xPath The xPath to retrieve element
	 * @return List This returns List of Elements retrieved using xPath
	 */
	public List<WebElement> getElements(String xPath) {
		try { 
			return driver.findElements(By.xpath(xPath));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This method takes WebElement, moves to it and then performs click action using actions class
	 * Post clicking on element it waits for 2-4 seconds
	 * @author pashine_a 
	 * @param element The element to be clicked
	 */
	public boolean moveclickElement(WebElement element) {
		try {
			action.moveToElement(element).build().perform();			
			element.click();
			threadsleep(2000);
		}catch (StaleElementReferenceException e) {
			try {
				action.moveToElement(element).build().perform();			
				element.click();
				Thread.sleep(2000);
			}catch(Exception e1) {
				return false;
			}
		}catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * This method takes WebElement and gets us the X and Y co-ordinates for same 
	 *  @param element The element whose co-ordinates we need
	 *  @return ArrayList This returns list of integers contains X co-ordinate at index 0
	 *  and Y co-ordinate at index 1
	 */
	public ArrayList<Integer> getElementCoordinates(WebElement element) {

		ArrayList<Integer> coordinates = new ArrayList<Integer>();				
		try {				             
			int XCoord = element.getLocation().getX();
			int YCoord = element.getLocation().getY();     

			coordinates.add(XCoord);
			coordinates.add(YCoord);
			return coordinates;			
		}catch (Exception e) {
			logError("Failed to get coordinates "+e.getMessage());	
			throw e; 
		}
	}	

	/**
	 * This method is used to dynamically retrieve WebElement by Id
	 * @author hingorani_a
	 * @param id The Id to be manipulated
	 * @param replaceWith String to be replaced
	 * @param replaceTo String to be replaced with
	 * @return WebElement This returns WebElement after dynamically retrieval of it using Id
	 */
	public WebElement perform_GetElementById(String id, String replaceWith, String replaceTo){
		WebElement webElement = null;
		String finalId = null;
		try{
			finalId = id.replaceAll(replaceWith, replaceTo);
			webElement = driver.findElement(By.id(finalId));
		}
		catch(Exception e){
			logError("Failed to get element with id - " + finalId + " - "
					+ e.getMessage());
		}
		return webElement;
	}

	/**
	 * This method is used to wait for an element until it is clickable.
	 * @author hingorani_a
	 * @param element WebElement for which we want to wait
	 * @return boolean This returns true when the element becomes clickable
	 */
	public boolean WaitForAnElementToBeClickable(WebElement element)
	{   
		try{
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		}
		catch(Exception e){
			throw e;
		}
	}

	/**
	 * This method is used to switch to mentioned Window Tab
	 * @author hingorani_a
	 * @param tabNo Use Class WINDOW_TAB to set tab number
	 * @return boolean This returns true once tab is switched
	 */
	public boolean switchToTab(String tabNo) {
		try{
			ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());

			if(handles.size() >= 2) {
				switch(tabNo) {
				case WINDOW_TAB.PARENT:
					driver.switchTo().window(handles.get(0));
					logInfo("Navigated to Main Tab");
					return true;
				case WINDOW_TAB.SECOND:
					driver.switchTo().window(handles.get(1));
					logInfo("Navigated to Second Tab");
					return true;
				default:
					logError("The input is invalid");
					return false;
				}
			}
			logError("As " + handles.size() + " number of tabs are identified, cannot switch");
			return false;
		}
		catch(Exception e){
			throw e;
		}
	}

	/**
	 * This method is used to close Current mentioned Tab and switch to Parent Tab
	 * @author hingorani_a
	 * @param currentTabNo Use Class WINDOW_TAB to set current tab number. 
	 * You can directly mention current tab number as well
	 * @return boolean This returns true once tab is switched to Parent after closing mentioned 
	 * Current tab
	 */
	public boolean closeCurrentAndSwitchToTab(String currentTabNo) {
		int currentWindowTab = 0;

		try{
			ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());

			if(handles.size() >= 2) {

				switch(currentTabNo) {
				case WINDOW_TAB.PARENT:
					currentWindowTab = 0;
					logInfo("Value set to Main Tab");
					break;
				case WINDOW_TAB.SECOND:
					currentWindowTab = 1;
					logInfo("Value set to Second Tab");
					break;
				default:
					logError("The input is invalid");
					return false;
				}

				driver.switchTo().window(handles.get(currentWindowTab));
				driver.close();
				driver.switchTo().window(handles.get(0));
				logInfo("Landed/Switched to main tab");
				return true;
			}
			logError("As " + handles.size() + " number of tabs are identified, cannot switch");
			return false;
		}
		catch(Exception e){
			throw e;
		}
	}

	/**
	 * This method is used to verify if a file exists in mentioned folder
	 * @author hingorani_a
	 * @param downloadPath To mention the folder path where we need to find file
	 * @param fileName Name of the file to be found
	 * @return boolean This returns true when file in given folder exists
	 */
	public boolean verifyFileDownloaded (String downloadPath, String fileName) {
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();

		try {
			for (int i = 0; i < dir_contents.length; i++)
				if(dir_contents[i].getName().equals(fileName)) {
					logInfo("Document " + fileName + " download verified");
					return true;
				}

			logError("Could NOT find downloaded Document" + fileName);
			return false;
		}catch(Exception e) {
			logError("Document download not verified - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to set value for any element (mostly dropdown) which is a list
	 * @author hingorani_a
	 * @param driver Current webdriver instance
	 * @param selector The JavaScript selector used to represent the list of elements
	 * @param text The unique innerText of element to represent element
	 * @param index The number of element to be clicked/set
	 * @return boolean This returns boolean true after setting value for the text intended
	 */
	public boolean JSSetValueFromList(WebDriver driver, String selector, String text, int index)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		try
		{
			String setElement = "var list = document.querySelectorAll(\""+selector+"\")\n" + 
					"var myVal = [...list ].filter(e => e.innerText == \""+text+"\")\n" + 
					"myVal["+index+"].click();";
			js.executeScript(setElement);
			return true;

		}
		catch (Exception e)
		{
			logError("Failed to perform JS Set for an element from list - " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to clear element text
	 * @author hingorani_a
	 * @param element WebElement for which we want to clear text
	 * @return boolean This returns true when the element is cleared
	 */
	public boolean clear(WebElement element) {
		try {
			element.clear();
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * This method is used to dynamically retrieve List of WebElement.
	 * @author hingorani_a
	 * @param id The id to get elements
	 * @return WebElement This returns WebElements once located
	 */
	public List<WebElement> perform_GetListOfElementsById(String id){
		return perform_waitUntilListVisible(By.id(id));
	}

	/**
	 * This method is used to dynamically retrieve WebElement.
	 * @author hingorani_a
	 * @param id The id to get element
	 * @return WebElement This returns WebElement once located
	 */
	public WebElement performGetElementById(String id){
		WebElement webElement = null;
		try{
			webElement = driver.findElement(By.id(id));
		}
		catch(Exception e){
			logError("Failed to get element with id - " + id + " - "
					+ e.getMessage());
		}
		return webElement;
	}

	/**
	 * This method is used to dynamically retrieve WebElement.
	 * @author hingorani_a
	 * @param xPath The xpath to get element
	 * @return WebElement This returns WebElement once located
	 */
	public WebElement performGetElementByXPath(String xPath){
		WebElement webElement = null;
		try{
			webElement = driver.findElement(By.xpath(xPath));
		}
		catch(Exception e){
			logError("Failed to get element with xpath - " + xPath + " - "
					+ e.getMessage());
		}
		return webElement;
	}

	/**
	 * This method is used to refresh the current webpage displayed
	 * @author hingorani_a
	 * @return boolean This returns true once page is refreshed
	 */
	public boolean refreshDisplayedPage() {
		try {
			driver.navigate().refresh();
			commonPage = new CommonPage(driver);
			commonPage.Sync();
			return true;
		} catch (Exception e) {
			logError("Failed to refresh page - " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to Clear and Set text to an element
	 * @author hingorani_a
	 * @param element The element to which text is to be set
	 * @param message The value to be set
	 * @return boolean This returns true after setting value to element
	 */
	public boolean clearAndSetText(WebElement element, String message) {
		try {
			element.clear();
			element.sendKeys(message);
			logInfo("Added text - " + message + " to element");
			return true;
		} catch (Exception e) {
			logError("Failed to send text - " + message + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to dynamically retrieve WebElement.
	 * @author hingorani_a
	 * @param className The className to get element
	 * @return WebElement This returns WebElement once located
	 */
	public WebElement perform_GetElementByClassName(String className) {
		WebElement webElement = null;
		try{
			webElement = driver.findElement(By.className(className));
		}
		catch(Exception e){
			logError("Failed to get element with className - " + className + " - "
					+ e.getMessage());
		}
		return webElement;
	}

	/**
	 * This method takes waits for elements until it is retrieved. It loops until StaleElementReferenceException 
	 * is by passed and elements is retrieved
	 * @author hingorani_a 
	 * @param xpath The xpath to retrieve elements which can be Stale
	 * @return List This returns list of WebElements once retrieved
	 */
	public List<WebElement> WaitUntilElementsIsStale(String xpath)
	{   
		boolean staleElement = true; 
		List<WebElement> elements = null;
		while(staleElement){

			try{
				elements = driver.findElements(By.xpath(xpath));
				staleElement = false;
			} catch(StaleElementReferenceException e){
				staleElement = true;
			}
		}
		return elements;
	}

	/**
	 * This method is used to set value to Kendo Dropdown element
	 * made of UL and LI as option values.
	 * @author hingorani_a
	 * @param driver Current webdriver instance
	 * @param selector The JavaScript selector to click on dropdown so that 
	 * dropdown list is opened
	 * @param searchTxt Text to be searched in text input
	 * @param textboxXpath Xpath of the textbox to be set with value
	 * @return boolean This returns boolean true after setting value to Kendo Dropdown
	 */
	public boolean setKendoDropDownValue(WebDriver driver, String selector, String searchTxt, 
			String textboxXpath) {
		try {
			if(!JSClick(driver, selector)) {
				return false;
			}
			logInfo("Clicked to open drop down list");
			WebElement sendText = driver.findElement(By.xpath(textboxXpath));
			sendText.sendKeys(searchTxt);
			actionEnter();
			return true;
		}
		catch(Exception e){
			logError("Failed to set Kendo Dropdown element with - "
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method takes waits for elements until it is retrieved. It loops until StaleElementReferenceException 
	 * is by passed and elements are retrieved
	 * @author hingorani_a 
	 * @param xpath The xpath to retrieve elements which can be Stale
	 * @return WebElement This returns List of elements once retrieved
	 */
	public List<WebElement> WaitUntilElementsAreStale(String xpath)
	{   
		boolean staleElement = true; 
		List<WebElement> element = null;
		while(staleElement){

			try{
				element = driver.findElements(By.xpath(xpath));
				staleElement = false;
			} catch(StaleElementReferenceException e){
				staleElement = true;
			}
		}
		return element;
	}

	/**
	 * This method is used to find whether the WebElement is Enabled or not
	 * @param webElement The WebElement to be verified if it is displayed or not
	 * @return boolean This returns true if the element is displayed
	 */
	public boolean isElementEnabled(WebElement webElement) {
		try {
			webElement.isEnabled();
			return true;
		} catch (ElementNotInteractableException e) {
			logError("Failed to check element's Enabled status"+e.getMessage());
			e.printStackTrace();
			return false;
		} catch (StaleElementReferenceException e) {
			logError("Failed to check element's Enabled status"+e.getMessage());
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			logError("Failed to check element's Enabled status"+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is used to find whether the WebElement is Disabled or not
	 * @param webElement The WebElement to be verified if it is displayed or not
	 * @return boolean This returns true if the element is displayed
	 */
	public boolean isElementDisabled(WebElement webElement) {
		try {
			if(webElement.getAttribute("disabled") != null)
				return true;
			return false;
		} catch (ElementNotInteractableException e) {
			logError("Failed to check element's disabled status"+e.getMessage());
			e.printStackTrace();
			return false;
		} catch (StaleElementReferenceException e) {
			logError("Failed to check element's disabled status"+e.getMessage());
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			logError("Failed to check element's disabled status"+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method waits for element until it is retrieved. It then checks whether the checkbox
	 * is selected or not
	 * @author hingorani_a 
	 * @param element The element to be verified 
	 * @return boolean This returns true if the checkbox element passed as paramter is Selected
	 */
	public boolean verifyCheckboxIsSelected(WebElement element)
	{   
		try {
			WaitforelementToBeClickable(element);
			if(element.isSelected()) {
				logInfo("Checkbox is selected");
				return true;
			}
			else if (!element.isSelected()) {
				logInfo("Checkbox is NOT selected");
			}
		}
		catch (Exception e) {
			logError("Failed to check element is selected or not " + e.getMessage());
		}
		return false;
	}

	/**
	 * 'getValueByAttributeForElement' this method is used to get value of an element on the basis of 
	 * mentioned attribute
	 * @param webElement [WebElement] : The WebElement whose value is needed 
	 * @param attribute [String] : The attribute's value needed for the mentioned element
	 * @return boolean - Returns true if value is retrieved successfully, else false
	 */
	public String getValueByAttributeForElement(WebElement webElement, String attribute) {
		String value = null;
		try {
			value = webElement.getAttribute(attribute);
			logInfo("Retreived element's " + attribute + " value as " + value);
		} catch (Exception e) {
			logError("Failed to get element's " + attribute + " value " + e.getMessage());
		}
		return value;
	}

	/**
	 * This method is used to dynamically retrieve WebElements.
	 * @author hingorani_a
	 * @param xPath The xpath to get elements
	 * @return List of WebElements - This returns WebElements once located
	 */
	public List<WebElement> performGetElementsByXPath(String xPath){
		List<WebElement> webElement = null;
		try{
			webElement = driver.findElements(By.xpath(xPath));
		}
		catch(Exception e){
			logError("Failed to get element with xpath - " + xPath + " - "
					+ e.getMessage());
		}
		return webElement;
	}
	
	public static class WINDOW_TAB {
		public static final String PARENT = "Parent";
		public static final String SECOND = "Second";
	}
}