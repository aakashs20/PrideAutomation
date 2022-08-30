package com.project.pageobjects.pTracker;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

public class ActionProjectsPage extends TestBase{
	
	WebDriverWait wait;
	ControlActions controlActions;
	
	public ActionProjectsPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 10);
		controlActions = new ControlActions(driver);
	}
	
	@FindBy(xpath = ActionProjectsConstants.PROJECTS_ACTIONS_TAB_LNK)
	public WebElement ProjectActionsLink;
	
	@FindBy(xpath = ActionProjectsConstants.DOWNLOAD_BUTTON)
	public WebElement DownloadBtn;
	
	@FindBy(css = ActionProjectsConstants.SEARCH_TEXT_BOX)
	public WebElement SearchProjectTxt;
	
	@FindBy(css = ActionProjectsConstants.GO_BTN)
	public WebElement GoBtn;
	
	@FindBy(xpath = ActionProjectsConstants.PROJECT_TABLE_ROWS)
	public List<WebElement> ProjectTableRows;
	
}
