package com.project.pageobjects.pTracker;

import java.util.Arrays;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
import java.util.List;
//import java.util.Map;
import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
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
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;


public class PTrackerLoginPage extends TestBase{
	
	WebDriverWait wait;
	ControlActions controlActions;
	
	public PTrackerLoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 9000);
		controlActions = new ControlActions(driver);
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
	
	@FindBy(xpath = PTrackerLoginConstants.CANCEL_ROLE_BTN)
	public WebElement CancelRoleBtn;
	
	@FindBy(xpath = PTrackerLoginConstants.PROCEED_BTN)
	public WebElement ProceedBtn;
	
	@FindBy(xpath = PTrackerLoginConstants.ACTIVE_TAB_TXT)
	public WebElement ActiveTabTxt;
	
	public boolean waitUntilElementPresent(WebElement webElement) {
		  try {
		    wait.until(ExpectedConditions.visibilityOfAllElements(webElement));
		    logInfo("Element Successfully loaded on the page");
		    log4jInfo("Element Successfully loaded on the page");
		    return true;
		  } catch (Exception e) {
			  logError("Failed to load the element on the page"+ e);
			  log4jError("Failed to load the element on the page" + e.getMessage());
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
                	log4jInfo("Page Loaded Successfully completed.");
                	logInfo("Page Loaded Successfully completed");
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
        	  	  logError("Timeout waiting for Page Load Request to complete");
        	      log4jError("Timeout waiting for Page Load Request to complete" + e.getMessage());
                  //Assert.assertFalse(true, "Timeout waiting for Page Load Request to complete.");
                  return false;
          }
     } 


	/**
	 * This method is used to get the page Title  
	 * @author Ketan Tank
	 * @return String Title
	 */	
	public String TitleOfPage() {
		String title=null;
		try {
			title=controlActions.getTitle();
			logInfo("Success, got the title of the page: "+title);
			log4jInfo("Success, got the title of the page: "+title);
			return title;
		}
		catch(Exception e){
			logError("Failed to get title of page" + e.getMessage());
			log4jError("Failed to get title of page" + e.getMessage());
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
			controlActions.WaitforelementToBeClickable(usernameTxt);
			controlActions.sendKeys(usernameTxt, uName);
			//controlActions.actionEnter();
			logInfo("Entered text '"+uName+"' in user name Field");
			log4jInfo("Entered text '"+uName+"' in user name Field");
			return true;
		}
		catch(Exception e){
			logError("Failed to enter text '"+uName+"' in user name Field" + e.getMessage());
			log4jError("Failed to enter text '"+uName+"' in user name Field" + e.getMessage());
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
			controlActions.WaitforelementToBeClickable(passwordTxt);
			controlActions.sendKeys(passwordTxt, uPassword);
			//controlActions.actionEnter();
			logInfo("Entered text '"+ uPassword +"' in password Field");
			log4jInfo("Entered text '"+ uPassword +"' in password Field");
			return true;
		}
		catch(Exception e){
			logError("Failed to enter text '"+ uPassword +"' in password Field" + e.getMessage());
			log4jError("Failed to enter text '"+ uPassword +"' in password Field" + e.getMessage());
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
			controlActions.clickElement(LoginBtn);
			logInfo("Successfully clicked on Login Button");
			log4jInfo("Successfully clicked on Login Button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Login Button "+ e.getMessage());
			log4jError("Failed to click on Login Button "+ e.getMessage());
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
			log4jInfo("Dashboard Icon is Visible On Page");
			return DashboardIcon.isDisplayed();
		} catch(Exception e) {
			logError("Dashboard Icon Not is Visible On Page "+ e.getMessage());
			log4jError("Dashboard Icon Not is Visible On Page "+ e.getMessage());
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
			controlActions.clickElement(NavigationBarIcon);
			String actualString = LoginTitleTxt.getText(); 
			if(actualString.contains("Change User"))
			{
				logInfo("Successfully clicked on Navigation Bar");
				log4jInfo("Successfully clicked on Navigation Bar");
				return true;
			}
			else
			{
				logError("Failed to click on Navigation Bar ");
				log4jError("Failed to click on Navigation Bar ");
				return false;
			}
		}
		catch(Exception e) {
			logError("Failed to click on Navigation Bar "+ e.getMessage());
			log4jError("Failed to click on Navigation Bar "+ e.getMessage());
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
			log4jInfo("Successfully clicked on Cancel Button");
			return true; 
		}
		catch(Exception e) {
			logError("Failed to click on Cancel Button "+ e.getMessage());
			log4jError("Failed to click on Cancel Button "+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to click on Submit In Button
	 * @author Ketan Tank
	 * @return boolean This returns true if Successfully clicked on Submit Button 
	 */
	public boolean clkSubmitButton() {
	try {
			controlActions.clickElement(SubmitBtn);
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			int DEFAULT_TIME_OUT_MILLISEC = 60000;
			//int count = (int)stopWatch.getTotalTimeMillis();
			int count = Long.valueOf(stopWatch.getTotalTimeMillis()).intValue();
			if(count > DEFAULT_TIME_OUT_MILLISEC)
			{
				controlActions.clickElement(SubmitBtn);
				logInfo("****** TIMER Count is : " + count);
				throw new Exception("Page did not finish initializing after "+ stopWatch.getTotalTimeSeconds() +" seconds.");
			}
			waitUntilElementPresent(AlertTxt);
			stopWatch.stop();
			logInfo("****** TIMER IS : " + stopWatch.getTotalTimeMillis());
			wait.until(ExpectedConditions.visibilityOf(AlertTxt));
			logInfo("Change User Successfully Done");
			log4jInfo("Change User Successfully Done");
			return true;
		}
		catch(Exception e) {
			logError("Failed to Change User "+ e.getMessage());
			log4jError("Failed to Change User "+ e.getMessage());
			return false;
		}
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
			log4jInfo("Entered text '"+eName+"' in user name Field");
			return true;
		}
		catch(Exception e){
			logError("Failed to enter text '"+eName+"' in user name Field" + e.getMessage());
			log4jError("Failed to enter text '"+eName+"' in user name Field" + e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to navigate to Ptrack Page   
	 * @author Ketan Tank
	 * @return boolean true if action successful else false
	 */
	public boolean goToPTrack() {
		try {
			controlActions.click(PtrackIcon);
			wait.until(ExpectedConditions.visibilityOf(SelectRoleList));
			if(controlActions.isElementDisplayedOnPage(SelectRoleList))
			{
				log4jInfo("Select Role List is Displayed on the page");
				controlActions.click(SelectRoleList);
				controlActions.click(RoleList);
				controlActions.click(ProceedBtn);
				wait.until(ExpectedConditions.visibilityOf(ActiveTabTxt));
				if(controlActions.isElementDisplayedOnPage(ActiveTabTxt))
				{
					logInfo("Successfully opend P-Tracker page");
					log4jInfo("Successfully opend P-Tracker page");
					return true;
				}
				else
				{
					logError("Failed to navigate to P-Tracker page");
					log4jError("Failed to navigate to P-Tracker page");
					return false;
				}
			}
			else
			{
				logError("Failed to load Select Role List  on the page");
				log4jError("Failed to load Select Role List  on the page");
			}
			
			log4jInfo("Ptrack page opened successfully");
			logInfo("Ptrack page opened successfully");
			return true;
		}
		catch(Exception e){
			logError("Failed to navigate to PTrack Page" + e.getMessage());
			log4jError("Failed to navigate to PTrack Page" + e.getMessage());
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
	
	public void TC_Login(String uName,String uPassword) throws Exception {
		String expectedTitle = "PRIDE - Sign In";
		String title = TitleOfPage();
		Assert.assertEquals(title, expectedTitle);
		boolean isUserNameSet = enterTextToUserName(uName);
		Assert.assertTrue(isUserNameSet, "Failed to add text to Google search as '" + uName + "'");
		boolean isPasswordSet = enterTextToPassword(uPassword);
		Assert.assertTrue(isPasswordSet, "Failed to add text to Google search as '" + uPassword + "'");
		boolean isLoginButtonClicked = clkLoginButton();
		Assert.assertTrue(isLoginButtonClicked, "Failed to clicked on Login Button.");
		boolean isDashboardIconVisibleOnPage = isDashboardIconVisibleOnPage();
		Assert.assertTrue(isDashboardIconVisibleOnPage, "Login Failed and Dashboard ICON not visible.");
	}
	
	public void TC_ChangeUser() throws Exception {
		boolean isclkNavigationBarIcon = clkNavigationBarIcon();
		Assert.assertTrue(isclkNavigationBarIcon, "Failed to click Navigation Icon.");
		boolean isCancelButtonClicked = clkCancelButton();
		Assert.assertTrue(isCancelButtonClicked, "Failed to click Cancel Button.");
		isclkNavigationBarIcon = clkNavigationBarIcon();
		Assert.assertTrue(isclkNavigationBarIcon, "Failed to click Navigation Icon.");
		String eName = "Mahajan, Milind";
		boolean isEmployeeSelected = selectEmployee(eName);
		Assert.assertTrue(isEmployeeSelected, "Failed to add text to slect Employee search as '" + eName + "'");
		boolean isSubmitButtonClicked = clkSubmitButton();
		//driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
		Assert.assertTrue(isSubmitButtonClicked, "Failed to click Submit Button on login page");
		threadsleep(2000);
		boolean ispTrackPageOpend = goToPTrack();
		Assert.assertTrue(ispTrackPageOpend, "Failed to open P-Tracker Home page");
	}
	
	public void TC_OpenPTrackPage() throws Exception {
		boolean ispTrackPageOpend = goToPTrack();
		Assert.assertTrue(ispTrackPageOpend, "Failed to open P-Tracker Home page");
	}

}
