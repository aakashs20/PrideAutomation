package com.project.pageobjects.IAutomateDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

public class IAutomateGoogleDemoPage extends TestBase {
	WebDriverWait wait;
	ControlActions controlActions;
	
	public IAutomateGoogleDemoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
		controlActions = new ControlActions(driver);
	}
	
	/**
	 * Elements
	 */
	
	@FindBy(xpath = IAutomateGoogleDemoConstants.GOOGLE_SEARCH_TXT)
	public WebElement GoogleSearchTxt;	
	
	@FindBy(xpath = IAutomateGoogleDemoConstants.GOOGLE_TESTNG_LNK)
	public WebElement GoogleTestngLnk;	
	
	@FindBy(xpath = IAutomateGoogleDemoConstants.TESTNG_CURRENT_LNK)
	public WebElement TestngCurrentLnk;	
	
	/**
	 * @param inputString [String] : search string to be ntered from excel sheet
	 * @return boolean true if action successful else false
	 */
	
	public boolean enterTextToGoogleSearch(String inputString) {
		try {
			controlActions.waitForElementToBeClickable(GoogleSearchTxt);
			controlActions.sendKeys(GoogleSearchTxt, inputString);
			controlActions.actionEnter();
			log4jInfo("Entered text '"+inputString+"' in Google search");
			return true;
		}
		catch(Exception e){
			log4jError("Failed to enter text '"+inputString+"' in Google search" + 
						e.getMessage());
			return false;
		}
	}
	
	public boolean clickTestNGLinkOnGoogle() {
		try {
			controlActions.waitForElementToBeClickable(GoogleTestngLnk);
			controlActions.click(GoogleTestngLnk);
			log4jInfo("Clicked on the first link found");
			return true;
		}
		catch(Exception e){
			log4jError("Failed to open link for TestNg " + e.getMessage());
			return false;
		}
	}
	
	public String TitleOfPage() {
		String title=null;
		try {
			title=controlActions.getTitle();
		
			log4jInfo("Got the title of the page:"+title);
			return title;
		}
		catch(Exception e){
			log4jError("Failed to get title of page" + e.getMessage());
			return title;
		}
	}
}
