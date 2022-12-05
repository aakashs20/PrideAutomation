package com.project.pageobjects.pTracker;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.springframework.util.StopWatch;

import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;


public class PTrackerLoginPage extends TestBase{
	
	WebDriverWait wait;
	ControlActions controlActions;
	Operations op ;
	private static final int DELAY = 20;
	private HashMap<String, String> rowData;
	String isAlertOn = "TRUE";
	String expectedRole;
	
	public PTrackerLoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, DELAY);
		controlActions = new ControlActions(driver);
		op = new Operations(driver);
		driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);
	}
	
	@FindBy(xpath = PTrackerLoginConstants.USERNAME_TXT)
	public WebElement usernameTxt;

	@FindBy(xpath = PTrackerLoginConstants.PASSWORD_TXT)
	public WebElement passwordTxt;
	
	@FindBy(xpath = PTrackerLoginConstants.LOGIN_BTN)
	public WebElement LoginBtn;
	
	@FindBy(xpath = PTrackerLoginConstants.DASHBOARD_ICON)
	public WebElement DashboardIcon;
	
	@FindBy(xpath = PTrackerLoginConstants.NAVIGATION_BAR_ICON)
	public WebElement NavigationBarIcon;
	
	@FindBy(xpath = PTrackerLoginConstants.LOGIN_TITLE_TXT)
	public WebElement LoginTitleTxt;
	
	@FindBy(xpath = PTrackerLoginConstants.SUBMIT_BTN)
	public WebElement SubmitBtn;
	
	@FindBy(xpath = PTrackerLoginConstants.CANCEL_BTN)
	public WebElement CancelBtn;
	
	@FindBy(xpath = PTrackerLoginConstants.PERSON_TXT)
	public WebElement PersonTxt;
	
	@FindBy(xpath = PTrackerLoginConstants.PERSON_ID_TXT)
	public WebElement PersonIDTXT;
	
	@FindBy(xpath = PTrackerLoginConstants.PERSON_ID_LIST)
	public WebElement PersonIdList;
	
	@FindBy(xpath = PTrackerLoginConstants.ALERT_TXT)
	public WebElement AlertTxt;
	
	@FindBy(xpath = PTrackerLoginConstants.PTRACK_ICON)
	public WebElement PtrackIcon;
	
	@FindBy(xpath = PTrackerLoginConstants.PTRACK_LABEL)
	public WebElement PtrackLabel;
	
	@FindBy(xpath = PTrackerLoginConstants.SELECT_ROLE_LIST)
	public WebElement SelectRoleList;
	
	@FindBy(xpath = PTrackerLoginConstants.SELECT_ROLE_BTN)
	public WebElement SelectRoleBtn;
	
	@FindBy(xpath = PTrackerLoginConstants.ROLE_LIST)
	public WebElement RoleList;
	
	@FindBy(xpath = PTrackerLoginConstants.ROLE_LIST_FR)
	public WebElement RoleListFR;
	
	@FindBy(xpath = PTrackerLoginConstants.ROLE_LIST_ON_BEHALF)
	public WebElement RoleListOnBehalf;

	@FindBy(xpath = PTrackerLoginConstants.ROLE_LIST2)
	public WebElement RoleList2;

	@FindBy(xpath = PTrackerLoginConstants.CANCEL_ROLE_BTN)
	public WebElement CancelRoleBtn;
	
	@FindBy(xpath = PTrackerLoginConstants.PROCEED_BTN)
	public WebElement ProceedBtn;
	
	@FindBy(xpath = PTrackerLoginConstants.ACTIVE_TAB_TXT)
	public WebElement ActiveTabTxt;
	
	@FindBy(xpath = PTrackerLoginConstants.CALL_FOR_ACTION_BTN)
	public WebElement callForActionBtn;

	@FindBy(xpath = PTrackerLoginConstants.HOME_BUTTON)
	public WebElement homeNavBtn;
	
	@FindBy(xpath = PTrackerLoginConstants.ADVANCE_BTN)
	public WebElement advanceBtn;
	
	@FindBy(xpath = PTrackerLoginConstants.PROCEED_TO_BTN)
	public WebElement ProceedToBtn;

	public boolean waitUntilElementPresent(WebElement webElement) {
		threadsleep(5000);
		  try {
		    wait.until(ExpectedConditions.visibilityOfAllElements(webElement));
		    logInfo("Element Successfully loaded on the page");
		    return true;
		  } catch (Exception e) {
			  logError("Failed to load the element on the page"+ e.getMessage());
		    return false;
		  }
		}
	
	/**
	 * This method is used to check completely loaded or not  
	 * @author Ketan Tank
	 * @return Void
	 */
	
public boolean waitForPageLoaded(WebDriver driver) {
         ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                String readyState = String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"));
                boolean complete = readyState.equalsIgnoreCase("complete");
               // return complete;
                if (complete) {
                  	logInfo("Page Loaded Successfully completed: " + complete );
                	return complete;
                }
                return complete;
           }
          };
          WebDriverWait wait = new WebDriverWait(driver,180);
          try {
                  wait.until(expectation);
                  return true;
          } catch(Throwable e) {
        	  	  logError("Timeout waiting for Page Load Request to complete"+ e.getMessage());
                  //Assert.assertFalse(true, "Timeout waiting for Page Load Request to complete.");
                  return false;
          }
     } 

		public void handleSSLError(WebDriver driver) 
		{
			try {
			logInfo("Executing on UAT Environment ?" + prop.getProperty("executeOnUAT").trim().equalsIgnoreCase("true"));
			op.clickOnElement(advanceBtn);
			threadsleep(2000);
			op.clickOnElement(ProceedToBtn);
			threadsleep(2000);
			
//			WebElement advanced = driver.findElement(By.xpath("//*[@id='details-button' and contains(text(),'Advanced')]"));
//			op.clickElement(advanced,driver);
//			threadsleep(2000);
//			WebElement browser = driver.findElement(By.xpath("//*[@id='proceed-link' and contains(text(),'Proceed to')]"));
//			op.clickElement(browser,driver);
//			threadsleep(2000);
		}
		catch(Exception e){
			logError("Failed to handel SSL Error page" + e.getMessage());
			}
		}

	/**
	 * This method is used to get the page Title  
	 * @author Ketan Tank
	 * @return String Title
	 */	
	public String TitleOfPage(WebDriver driver) { //*[@id='details-button' and contains(text(),'Advanced')]
		String title=null;
		try {
			
			title=driver.getTitle();
			logInfo("Success, got the title of the page: "+title);
			return title;
		}
		catch(Exception e){
			logError("Failed to get title of page" + e.getMessage());
			return title;
		}
	}
	
	
	/**
	 * This method is used to enter User Name  
	 * @param inputString [String] 
	 * @author Ketan Tank
	 * @return boolean true if action successful else false
	 */
	public boolean enterTextToUserName(String uName) {
		try {
			isElementDisplayed(usernameTxt);
			controlActions.waitForElementToBeClickable(usernameTxt);
			controlActions.sendKeys(usernameTxt, uName);
			//controlActions.actionEnter();
			logInfo("Entered text '"+uName+"' in user name Field");
			return true;
		}
		catch(Exception e){
			logError("Failed to enter text '"+uName+"' in user name Field" + e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to enter password  
	 * @param inputString [String] 
	 * @author Ketan Tank
	 * @return boolean true if action successful else false
	 */
	public boolean enterTextToPassword(String uPassword) {
		try {
			controlActions.waitForElementToBeClickable(passwordTxt);
			controlActions.sendKeys(passwordTxt, uPassword);
			//controlActions.actionEnter();
			logInfo("Entered text '"+ uPassword +"' in password Field");
			return true;
		}
		catch(Exception e){
			logError("Failed to enter text '"+ uPassword +"' in password Field" + e.getMessage());
			return false;
		}
	}
	

	/**
	 * This method is used to click on Log In Button
	 * @author Ketan Tank
	 * @return boolean This returns true if Successfully clicked on copy Button 
	 */
	public boolean clkLoginButton() {
		try {
			controlActions.waitForAnElementToBeClickable(LoginBtn);
			controlActions.clickElement(LoginBtn);
			logInfo("Successfully clicked on Login Button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Login Button "+ e.getMessage());
			return false;
		}
	}
	
	
	/**
	 * This method is used to wait for few seconds before verifying whether element is displayed
	 * @author - Ketan Tank
	 * @param element The element to be verified if it is displayed or not
	 * @return This return true is the element is displayed
	 */
	public boolean isDashboardIconVisibleOnPage() {
		try {		
			//commonPage = new CommonPage(driver);
			//commonPage.Sync();
			threadsleep(2000);
			logInfo("Dashboard Icon is Visible On Page");
			return DashboardIcon.isDisplayed();
		} catch(Exception e) {
			logError("Dashboard Icon Not is Visible On Page "+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to click on Log In Button
	 * @author Ketan Tank
	 * @return boolean This returns true if Successfully clicked on copy Button 
	 */
	public boolean clkNavigationBarIcon() {
		try {
			threadsleep(2000);
			op.waitForAnElementToBeClickable(NavigationBarIcon);
			op.clickElement(NavigationBarIcon);
			//threadsleep(5000);
			//op.clickElement(NavigationBarIcon);
			op.waitUntilElementPresent(LoginTitleTxt);
			String actualString = LoginTitleTxt.getText(); 
			if(actualString.contains("Change User"))
			{
				logInfo("Successfully clicked on Navigation Bar");
				return true;
			}
			else
			{
				logError("Failed to click on Navigation Bar ");
				return false;
			}
		}
		catch(Exception e) {
			logError("Failed to click on Navigation Bar "+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to click on Cancel In Button
	 * @author Ketan Tank
	 * @return boolean This returns true if Successfully clicked on Cancel Button 
	 */
	public boolean clkCancelButton() {
		try {
			controlActions.clickElement(CancelBtn);
			logInfo("Successfully clicked on Cancel Button");
			return true; 
		}
		catch(Exception e) {
			logError("Failed to click on Cancel Button "+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to click on Submit In Button
	 * @author Ketan Tank
	 * @return boolean This returns true if Successfully clicked on Submit Button 
	 */
	public boolean clkSubmitButton(WebDriver driver) {
	try {
			controlActions.clickElement(SubmitBtn);
			logInfo("Title Of Page is : " + TitleOfPage(driver));
			waitUntilElementPresent(AlertTxt);
			wait.until(ExpectedConditions.visibilityOf(AlertTxt));
			logInfo("Change User Successfully Done");
			return true;
		}
		catch(Exception e) {
			logError("Failed to Change User "+ e.getMessage());
			return false;
		}
	}
	
	public boolean clkSubmitButtonNew(WebDriver driver, String eName) {
	    boolean isChangeUser = true;
	try {
			WebElement aa ;
//			String isAlertPresent = prop.getProperty("isAlertOn").trim().toLowerCase();
//			logInfo("***Alert is configured as : " + isAlertPresent);
			op.clickElement(SubmitBtn, driver);
			//SubmitBtn.click(); 
			threadsleep(5000);	
			aa = driver.findElement(By.xpath("//span[contains(text(),'"+eName+"')]"));
			op.waitUntilElementIsClickable(aa);
			boolean isUserNamePresent = op.isElementDisplayedOnPage(aa);
			if(isUserNamePresent)
			{
				logInfo("Successfully clicked on Submit Button and User Name Displayed on page after Change User is?   "+ isUserNamePresent);
			}
			logInfo("Is User Name is displayed on the page after Change User?   "+ isUserNamePresent);
			for(int count=1; count<=5; count++)
			{
				logInfo("Change User Retry Count is : " + count);
				threadsleep(2000);	
				if(!op.checkElementPresent(aa))
				{
					op.clickElement(SubmitBtn, driver);
					//SubmitBtn.click();
				}
				else
				{
					break;
				}
			}
			aa = driver.findElement(By.xpath("//span[contains(text(),'"+eName+"')]"));
			isUserNamePresent = op.isElementDisplayedOnPage(aa);
			if(isUserNamePresent)
			{
	 			waitUntilElementPresent(aa);
				wait.until(ExpectedConditions.visibilityOf(aa));
				if(op.checkElementPresent(aa))
				{
					logInfo("Change User Successfully Done");
					isChangeUser = true;
				}
				else
				{
					logError("Failed to Change User");
					isChangeUser = false;
			    }
			}
		}
		catch (Exception e) {
			logError("Failed to Change User");
			isChangeUser = false;
		}
	return isChangeUser;
	}
	
	/**
	 * This method is used to enter text in select employ field   
	 * @param inputString [String] 
	 * @author Ketan Tank
	 * @return boolean true if action successful else false
	 */
	public boolean selectEmployee(String eName) {
		try {
			controlActions.click(PersonTxt);
			controlActions.click(PersonIDTXT);
			controlActions.sendKeys(PersonIDTXT, eName);
			controlActions.click(PersonIdList);
			logInfo("Entered text '"+eName+"' in user name Field");
			return true;
		}
		catch(Exception e){
			logError("Failed to enter text '"+eName+"' in user name Field" + e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to navigate to Ptrack Page   
	 * @author Ketan Tank
	 * @return boolean true if action successful else false
	 */
	public boolean goToPTrack() {
		logInfo("Navigating to P-Track Landing page");
		try {
			op.clickElement(PtrackIcon);
			prop.getProperty("executeOnUAT");
			logInfo("Executing on UAT Environment ?" + prop.getProperty("executeOnUAT").trim().equalsIgnoreCase("true"));
			if(!prop.getProperty("executeOnUAT").trim().equalsIgnoreCase("true"))
			{
				//controlActions.click(PtrackIcon);
				wait.until(ExpectedConditions.visibilityOf(SelectRoleList));
				if(controlActions.isElementDisplayedOnPage(SelectRoleList))
				{
					logInfo("Select Role List is Displayed on the page");
					controlActions.click(SelectRoleList);
					controlActions.click(RoleList);
					controlActions.click(ProceedBtn);
					int count =0;
					logInfo("Active Tab is Visible? : " + op.isElementDisplay(ActiveTabTxt));
					while(!op.waitUntilElementIsClickable(ActiveTabTxt))
					{
						logInfo("Select Role Retry count is: " +count);
						controlActions.click(ProceedBtn);
						count = count+1;
						threadsleep(5000);
					}
				  }
					//wait.until(ExpectedConditions.visibilityOf(ActiveTabTxt));
					if(controlActions.isElementDisplayedOnPage(ActiveTabTxt))
					{
						logInfo("Successfully opend P-Tracker page");
						return true;
					}
					else
					{
						logError("Failed to navigate to P-Tracker page");
						return false;
					}
			}
			else
			{
				logError("Skipped Selecting User from Select Role List Page");
				if(controlActions.isElementDisplayedOnPage(ActiveTabTxt))
				{
					logInfo("Successfully opend P-Tracker page");
					return true;
				}
				else
				{
					return false;
				}
			}
			//logInfo("Ptrack page opened successfully");
		}
		catch(Exception e){
			logError("Failed to navigate to PTrack Page" + e.getMessage());
			return false;
		}
	}
	
	
	/**
	 * This method is used to navigate to Ptrack Page   
	 * @author Ketan Tank
	 * @return boolean true if action successful else false
	 */
	public boolean selectRole(String role) {
		try {
			controlActions.click(PtrackIcon);
			wait.until(ExpectedConditions.visibilityOf(SelectRoleList));
			if(controlActions.isElementDisplayedOnPage(SelectRoleList))
			{
				logInfo("Select Role List is Displayed on the page");
				controlActions.click(SelectRoleList);
				//controlActions.click(RoleList);
				//controlActions.click(RoleListFR);
				controlActions.click(RoleListOnBehalf);
				
				controlActions.click(ProceedBtn);
				int count =0;
				logInfo("Active Tab is Visible? : " + op.isElementDisplay(ActiveTabTxt));
				while(!op.waitUntilElementIsClickable(ActiveTabTxt))
				{
					logInfo("Select Role Retry count is: " +count);
					controlActions.click(ProceedBtn);
					count = count+1;
					threadsleep(5000);
				}
				//wait.until(ExpectedConditions.visibilityOf(ActiveTabTxt));
				if(controlActions.isElementDisplayedOnPage(ActiveTabTxt))
				{
					logInfo("Successfully opend P-Tracker page");
					return true;
				}
				else
				{
					logError("Failed to navigate to P-Tracker page");
					return false;
				}
			}
			else
			{
				logError("Failed to load Select Role List  on the page");
			}
			logInfo("Ptrack page opened successfully");
			return true;
		}
		catch(Exception e){
			logError("Failed to navigate to PTrack Page" + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to navigate to Ptrack Page   
	 * @author Vasanthavasan M
	 * @return boolean true if action successful else false
	 */
	public void goToPTrack(String role) {
		try {
			wait.until(ExpectedConditions.visibilityOf(PtrackIcon));
			controlActions.click(PtrackIcon);
			wait.until(ExpectedConditions.visibilityOf(SelectRoleList));
			logInfo("Successfully naviagte to Ptrack Icon");
			// true;
		}
		catch(Exception e){
			logError("Failed to navigate to PTrack Page" + e.getMessage());
			//return false;
		}
	}
	
public boolean searchPTrackProjects(WebDriver driver,String role) throws InterruptedException {
		//op.waitForAnElementToBeClickable(SelectRoleList);
	try {
		//op.clickElement(SelectRoleList,driver);	 
		    SelectRoleList.click();
			logInfo("Select Roles Drop Drown List is Clicked");
		} catch (Exception e) {
			logError("Failed to Click Select Roles Drop Drown List");
		}
		
		try {
			WebElement findElement = driver.findElement(By.xpath("//ul[@role='listbox']"));
			List<WebElement> findElements = findElement.findElements(By.tagName("li"));
			for (WebElement webElement : findElements) {
				//Verifying that we provided Role is whether present in or not.
				if(webElement.getText().equals(role)){
					expectedRole=webElement.getText();
					System.out.println("Found "+expectedRole);
				}
				logInfo("Available Role for This User are :"+webElement.getText());
			}
			logInfo("All the elements in Role list is Are Displayed");
		} catch (Exception e) {
			logError("Could Not found Any Role");
		}
		logInfo(expectedRole);
		
		
		//Function used to select our specify Role
		try {
			driver.findElement(By.xpath("//*[text()='"+expectedRole+"']")).click();
			logInfo("Successfully Got specified Role "+expectedRole);
		}catch(Exception e) {
			logError("Not Sccussfully Got Specified Role"+expectedRole);
		}

		//Clicking Submit 
		try {
			//driver.findElement(By.xpath("//*[@id='B77297519692893844']")).click();
			controlActions.click(ProceedBtn);
			op.waitUntilElementIsClickable(ActiveTabTxt);
			logInfo("Successfully Clicked Submit button");
			return true;
		} catch (Exception e) {
			logError("not Successfully Clicked Submit button");
			return false;
		}
		
	}
	
	
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
	
	public void changeUser(String approver)
	{
		try 
		{
			threadsleep(2000);	
			userChange(driver, approver);
		}
		catch (Exception e) 
		{
			logError(e.toString());
		}
	}

	public boolean openPride()
	{
		try {
			threadsleep(2000);
			boolean ispTrackPageOpend = goToPTrack();
			IsTrue(ispTrackPageOpend, "P-Tracker Home page Successfully opend", "Failed to open P-Tracker Home page");
			return ispTrackPageOpend;
		}
		catch (Exception e) {
			logError(e.toString());
			return false;
		}
	}

	public boolean openPride(String role)
	{
		try {
			threadsleep(2000);
			goToPTrack(role);
			Boolean isRoleSelected = searchPTrackProjects(driver, role.trim());
			IsTrue(isRoleSelected, "P-Tracker Home page Successfully opend", "Failed to open P-Tracker Home page");
			return isRoleSelected;
		}
		catch (Exception e) {
			logError(e.toString());
			return false;
		}
	}
	
	public boolean login(WebDriver driver,String uName,String uPassword) throws Exception {
		try
		{
			String expectedTitle = "PRIDE - Sign In";
			String title = TitleOfPage(driver);
			Assert.assertEquals(title, expectedTitle);
			threadsleep(1000);
			enterTextToUserName(uName);
			threadsleep(1000);
			enterTextToPassword(uPassword);
			threadsleep(1000);
			clkLoginButton();
			//boolean isDashboardIconVisibleOnPage = isDashboardIconVisibleOnPage();
			controlActions.waitForAnElementToBeClickable(DashboardIcon);
			boolean isDashboardIconVisibleOnPage = waitUntilElementPresent(DashboardIcon);
			IsTrue(isDashboardIconVisibleOnPage, "Login Success and Dashboard ICON is visible", "Login Failed and Dashboard ICON not visible");
			return isDashboardIconVisibleOnPage;
		}
		catch(Throwable e) {
     	  	  logError("Login Failed "+ e.getMessage());
			return false;
		}
	}
	
	public void TC_Login(WebDriver driver,String uName,String uPassword) throws Exception {
		String expectedTitle = "PRIDE - Sign In";
		prop.getProperty("executeOnUAT");
		if(prop.getProperty("executeOnUAT").trim().equalsIgnoreCase("true"))
		{
			handleSSLError(driver);
//			logInfo("Executing on UAT Environment ?" + prop.getProperty("executeOnUAT").trim().equalsIgnoreCase("true"));
//			WebElement advanced = driver.findElement(By.xpath("//*[@id='details-button' and contains(text(),'Advanced')]"));
//			op.clickElement(advanced,driver);
//			threadsleep(2000);
//			WebElement browser = driver.findElement(By.xpath("//*[@id='proceed-link' and contains(text(),'Proceed to')]"));
//			op.clickElement(browser,driver);
//			threadsleep(2000);
		}
		String title = TitleOfPage(driver);
		Equals(title, expectedTitle,"Title of the page Matched Successfully","Fail to Match the Title of the page");
		boolean isUserNameSet = enterTextToUserName(uName);
		Assert.assertTrue(isUserNameSet, "Failed to add text to Google search as '" + uName + "'");
		boolean isPasswordSet = enterTextToPassword(uPassword);
		Assert.assertTrue(isPasswordSet, "Failed to add text to Google search as '" + uPassword + "'");
		boolean isLoginButtonClicked = clkLoginButton();
		Assert.assertTrue(isLoginButtonClicked, "Failed to clicked on Login Button.");
		//boolean isDashboardIconVisibleOnPage = isDashboardIconVisibleOnPage();
		controlActions.waitForAnElementToBeClickable(DashboardIcon);
		boolean isDashboardIconVisibleOnPage = waitUntilElementPresent(DashboardIcon);
		Assert.assertTrue(isDashboardIconVisibleOnPage, "Login Failed and Dashboard ICON not visible.");
	}
	
	
	public void TC_PTrack_ChangeUser(WebDriver driver,String eName) throws Exception {
		boolean isclkNavigationBarIcon = clkNavigationBarIcon();
		Assert.assertTrue(isclkNavigationBarIcon, "Failed to click Navigation Icon.");
		boolean isCancelButtonClicked = clkCancelButton();
		Assert.assertTrue(isCancelButtonClicked, "Failed to click Cancel Button.");
		isclkNavigationBarIcon = clkNavigationBarIcon();
		Assert.assertTrue(isclkNavigationBarIcon, "Failed to click Navigation Icon.");
		//String eName = "Mahajan, Milind";
		boolean isEmployeeSelected = selectEmployee(eName);
		Assert.assertTrue(isEmployeeSelected, "Failed to add text to select Employee search as '" + eName + "'");
		boolean isSubmitButtonClicked = clkSubmitButtonNew(driver, eName);
		//driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
		Assert.assertTrue(isSubmitButtonClicked, "Failed to click Submit Button on login page");
		threadsleep(2000);
//		boolean ispTrackPageOpend = goToPTrack();
//		Assert.assertTrue(ispTrackPageOpend, "Failed to open P-Tracker Home page");
	}


	public void TC_ChangeUser(WebDriver driver,String eName) throws Exception {
		try
		{
			boolean isclkNavigationBarIcon = clkNavigationBarIcon();
			Assert.assertTrue(isclkNavigationBarIcon, "Failed to click Navigation Icon.");
			//boolean isCancelButtonClicked = clkCancelButton();
		    //Assert.assertTrue(isCancelButtonClicked, "Failed to click Cancel Button.");
			//isclkNavigationBarIcon = clkNavigationBarIcon();
			//Assert.assertTrue(isclkNavigationBarIcon, "Failed to click Navigation Icon.");
			//String eName = "Mahajan, Milind";
			boolean isEmployeeSelected = selectEmployee(eName);
			Assert.assertTrue(isEmployeeSelected, "Failed to add text to select Employee search as '" + eName + "'");
			boolean isSubmitButtonClicked = clkSubmitButtonNew(driver,eName);
			WebElement userNameButton = driver.findElement(By.xpath("//span[contains(text(),'"+eName+"')]"));
			op.waitForAnElementToBeClickable(userNameButton);
			boolean isUserNamePresent = op.isElementDisplayedOnPage(userNameButton);
			logInfo("Is User Name Displayed on page after Change User?   "+ isUserNamePresent);
			//driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
			Assert.assertTrue(isSubmitButtonClicked, "Failed to click Submit Button on login page");
			threadsleep(1000);
			boolean ispTrackPageOpend = goToPTrack();
			Assert.assertTrue(ispTrackPageOpend, "Failed to open P-Tracker Home page");
		}
		catch(Throwable e) {
   	  	  logError("Error in Change User "+ e.getMessage());
		}
	}
	
	public void changeUser(WebDriver driver, String approver) throws Exception {
		boolean isclkNavigationBarIcon = clkNavigationBarIcon();
		Assert.assertTrue(isclkNavigationBarIcon, "Failed to click Navigation Icon.");
		boolean isCancelButtonClicked = clkCancelButton();
		Assert.assertTrue(isCancelButtonClicked, "Failed to click Cancel Button.");
		isclkNavigationBarIcon = clkNavigationBarIcon();
		Assert.assertTrue(isclkNavigationBarIcon, "Failed to click Navigation Icon.");
		String eName = approver; //"Mahajan, Milind";
		boolean isEmployeeSelected = selectEmployee(eName);
		Assert.assertTrue(isEmployeeSelected, "Failed to add text to select Employee search as '" + eName + "'");
		boolean isSubmitButtonClicked = clkSubmitButtonNew(driver,eName);
		//driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
		Assert.assertTrue(isSubmitButtonClicked, "Failed to click Submit Button on login page");
		threadsleep(2000);
		boolean ispTrackPageOpend = goToPTrack();
		Assert.assertTrue(ispTrackPageOpend, "Failed to open P-Tracker Home page");
	}
	
	public boolean userChange(WebDriver driver,String approver) throws Exception {
		logInfo("Employee Select from Change User Screen: "+approver);
		try
		{
			boolean isclkNavigationBarIcon = clkNavigationBarIcon();
			IsTrue(isclkNavigationBarIcon, "Navigation Icon Clicked Successfully", "Failed to click Navigation Icon.");
//			boolean isCancelButtonClicked = clkCancelButton();
//			Assert.assertTrue(isCancelButtonClicked, "Failed to click Cancel Button.");
			isclkNavigationBarIcon = clkNavigationBarIcon();
			IsTrue(isclkNavigationBarIcon, "Navigation Icon Clicked Successfully", "Failed to click Navigation Icon.");
			String eName = approver; //"Mahajan, Milind";
			boolean isEmployeeSelected = selectEmployee(eName);
			Assert.assertTrue(isEmployeeSelected, "Failed to add text to select Employee search as '" + eName + "'");
			boolean isSubmitButtonClicked = clkSubmitButtonNew(driver,eName);
			//driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
			Assert.assertTrue(isSubmitButtonClicked, "Failed to click Submit Button on login page");
			threadsleep(2000);
			WebElement userNameButton = driver.findElement(By.xpath("//span[contains(text(),'"+eName+"')]"));
			op.waitForAnElementToBeClickable(userNameButton);
			boolean ischangeUserSuccess = op.isElementDisplayedOnPage(userNameButton);
			logInfo("Is User Name Displayed on page after Change User?   "+ ischangeUserSuccess);
			
			//boolean ischangeUserSuccess = controlActions.waitForAnElementToBeClickable(callForActionBtn)
			IsTrue(ischangeUserSuccess, "Change User performed Successfully", "Failed to perfrom Change User");
			//boolean isDashboardIconVisibleOnPage = waitUntilElementPresent(DashboardIcon);
			//boolean ispTrackPageOpend = goToPTrack();
		    //Assert.assertTrue(ispTrackPageOpend, "Failed to open P-Tracker Home page");
			return ischangeUserSuccess;
		}
		catch(Throwable e) {
     	  	  logError("Timeout waiting for Page Load Request to complete "+ e.getMessage());
     	  	 return false;
		}
	}
	
	public void TC_OpenPTrackPage() throws Exception {
		boolean ispTrackPageOpend = goToPTrack();
		Assert.assertTrue(ispTrackPageOpend, "Failed to open P-Tracker Home page");
	}
	
	public boolean waitForPageLoad() {

        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
           public Boolean apply(WebDriver driver) {
               String readyState = String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"));
               boolean complete = readyState.equalsIgnoreCase("complete");
              // return complete;
               if (complete) {
                 	logInfo("Page Loaded Successfully completed: " + complete );
               	return complete;
               }
               return complete;
          }
         };
         WebDriverWait wait = new WebDriverWait(driver,180);
         try {
                 wait.until(expectation);
                 return true;
         } catch(Throwable e) {
       	  	  logError("Timeout waiting for Page Load Request to complete"+ e.getMessage());
                 //Assert.assertFalse(true, "Timeout waiting for Page Load Request to complete.");
                 return false;
         }
    } 

}
