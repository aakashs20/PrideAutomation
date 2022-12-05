package com.project.pageobjects.pTracker;

//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;

//import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.List;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;

import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;


public class PL_ClosedProjectsPage extends TestBase{
	
	WebDriverWait wait;
	ControlActions controlActions;
	
	public PL_ClosedProjectsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 10);
		controlActions = new ControlActions(driver);
	}
	
	@FindBy(xpath = ClosedProjectsConstants.DOWNLOAD_BUTTON)
	public WebElement DownloadBtn;
	
	@FindBy(css = ClosedProjectsConstants.SEARCH_TEXT_BOX)
	public WebElement SearchProjectTxt;
	
	@FindBy(xpath = ClosedProjectsConstants.GO_BTN)
	public WebElement GoBtn;
	
	@FindBy(xpath = ClosedProjectsConstants.PROJECT_TABLE_ROWS)
	public List<WebElement> ProjectTableRows;
	
	@FindBy(xpath = ClosedProjectsConstants.CLOSED_PROJECT_PAGINATION)
	public List<WebElement> ClosedProjectPagination;
	
	

	@FindBy(css = ClosedProjectsConstants.CLOSED_PROJECT_FILTER_BTN)
	public WebElement closedProjectFilter;
	
	@FindBy(xpath = ClosedProjectsConstants.CLOSED_PROJECT_FILTER_BOX)
	public WebElement closedProjectFilterBox;
	
	@FindBy(xpath = ClosedProjectsConstants.CLOSED_PROJECT_FILTER_HEADER)
	public WebElement closedProjectFilterHeader;
	
	@FindBy(xpath = ClosedProjectsConstants.CLOSED_PROJECT_FILTER_CLEAR_BUTTON)
	public WebElement closedProjectFilterClearButton;
	
	@FindBy(xpath = ClosedProjectsConstants.CLOSED_PROJECT_FILTER_MENU_CHECKBOX)
	public List<WebElement> closedProjectFilterMenuCheckBox;
	
	@FindBy(xpath = ClosedProjectsConstants.CLOSED_PROJECT_APPLIED_FILTER)
	public List<WebElement> closedProjectAppliedFilters;
	
	@FindBy(xpath=ClosedProjectsConstants.CLOSED_PROJECT_TYPE_SELECTOR)
	public List<WebElement> closedProjectTypeSelector;
	
	@FindBy(xpath=ClosedProjectsConstants.CLOSED_PROJECT_TABLE_PROJECT_TYPE)
	public List<WebElement> closedProjectTableProjectType;
	
	@FindBy(xpath = ClosedProjectsConstants.CLOSED_PROJECT_PAGINATION)
	public WebElement closedProjectPagination;
	
}
