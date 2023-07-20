package com.project.pageobjects.pTracker;

import org.openqa.selenium.StaleElementReferenceException;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;

import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.pageobjects.pTracker.PL_ActiveProjectConstant;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;


public class PL_ActiveProjectsPage extends TestBase{
	
	WebDriverWait wait;
	ControlActions controlActions;
	WebDriver driver1 ;
	Actions actions;
	
	public PL_ActiveProjectsPage(WebDriver driver) {
		driver1 = driver ;
		actions = new Actions(driver1);
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 10);
		controlActions = new ControlActions(driver);
	}
	
	@FindBy(xpath = ActiveProjectsConstants.DOWNLOAD_BUTTON)
	public WebElement DownloadBtn;
	
	@FindBy(css = ActiveProjectsConstants.SEARCH_TEXT_BOX)
	public WebElement SearchProjectTxt;
	
	@FindBy(xpath = ActiveProjectsConstants.GO_BTN)
	public WebElement GoBtn;
	
	@FindBy(xpath = ActiveProjectsConstants.PROJECT_TABLE_ROWS)
	public List<WebElement> ProjectTableRows;
	
	@FindBy(xpath = ActiveProjectsConstants.NEW_PROJECT_PAGINATION)
	public List<WebElement> NewProjectPagination;
	
	@FindBy(xpath = PL_ActiveProjectConstant.ACTIVE_FILTER_HEADERS)
	public List<WebElement> ActiveFilterHeader ;
	
	@FindBy(xpath = PL_ActiveProjectConstant.ACTIVE_FILTER_BUTTON)
	public WebElement ActiveFilterButton ;
	
	@FindBy(xpath = PL_ActiveProjectConstant.ACTIVE_PROJECTS_TAB_LNK)
	public WebElement ActiveProjectTabLink;
	
	@FindBy(xpath = PL_ActiveProjectConstant.ACTIVE_FILTER_CHECKBOXS)
	public List<WebElement> ActiveFilterCheckbox ;
	
	@FindBy(xpath = PL_ActiveProjectConstant.APPLIED_FILTERS)
	public List<WebElement> AppliedFilters; 
	
	@FindBy(xpath = PL_ActiveProjectConstant.APPLIED_FIXEDPRICE_ROW)
	public List<WebElement> AppliedFixedPriceFilterRow; 
	
	@FindBy(xpath = PL_ActiveProjectConstant.ACTIVE_PROJECTTYPE_FILTERS)
	public List<WebElement> AppliedProjectTypeFilters; 
	
	@FindBy(xpath = PL_ActiveProjectConstant.ACTIVE_FILTERS_HEADERS_ROW)
	public List<WebElement> ActiveProjectFilterHeadersRow; 
	
	@FindBy(xpath = PL_ActiveProjectConstant.APPLIED_CUSTOMERTYPE_FILTER_COL)
	public List<WebElement> AppliedCustomerTypeFilterCol; 
	
	@FindBy(xpath = PL_ActiveProjectConstant.ACTIVE_FILTERS_DATA)
	public List<WebElement> ActiveFilterData; 
	
	@FindBy(xpath = PL_ActiveProjectConstant.ACTIVE_NO_DATA)
	public WebElement ActiveNoData;
	
	@FindBy(xpath = PL_ActiveProjectConstant.ACTIVE_PROJ_PAGINATION)
	public List<WebElement> ActiveProjectPagination;
	
	@FindBy(xpath = PL_ActiveProjectConstant.ACTIVE_PROJECT_TYPE_SELECTOR)
	public List<WebElement> ActiveProjectSelector;
	
	@FindBy(xpath = PL_ActiveProjectConstant.ACTIVE_PROJECT_TABLE_PROJECT_TYPE)
	public List<WebElement> ActiveProjectTableType;
	//Ajay
	@FindBy(xpath = PL_ActiveProjectConstant.ACTIVE_PROJECT_NUMBER)
	public WebElement ActiveProjectNumber;
	
	@FindBy(xpath = PL_ActiveProjectConstant.NEWPROJECT_MENU_MOUSE_HOVER)
	public WebElement ActiveProMouseHover;
	
	@FindBy(xpath = PL_ActiveProjectConstant.INVOICE_BUTTON)
	public WebElement InvoiceButton;
	
	@FindBy(xpath = PL_ActiveProjectConstant.RAISE_INVOICE)
	public WebElement RaiseInvoiceButton;
	
	@FindBy(xpath = PL_ActiveProjectConstant.APEX_RDX_CONTAINER_UL)
	public WebElement Apex_RDX_CONTAINER_UL;
	
	@FindBy(xpath = PL_ActiveProjectConstant.FRAME_01)
	public WebElement Frame_01; 
	
	@FindBy(xpath = PL_ActiveProjectConstant.PAYMENT_MILESTONE)
	public WebElement Payment_Milestone;
	
	@FindBy(xpath = PL_ActiveProjectConstant.SELECT_PAYMENT_MILESTONE)
	public WebElement Select_Payment_Milestone;
	
	@FindBy(xpath = PL_ActiveProjectConstant.REQUEST_RAISE_INVOICE_BUTTON)
	public WebElement Request_RaiseInvoice_Button;
	
	@FindBy(xpath = PL_ActiveProjectConstant.PAYMENT_MILESTONE_EROOR_TEXT)
	public WebElement Payment_Milestone_Error_Text;
	
	@FindBy(xpath = PL_ActiveProjectConstant.CANCEL_BUTTON)
	public WebElement Cancel_Button;
	
	@FindBy(xpath = PL_ActiveProjectConstant.PAYMENT_MILESTONE_DRP_VALUES)
	public WebElement Payment_Milestone_Drp_Values;
	
	@FindBy(xpath = PL_ActiveProjectConstant.PTRACK_PAYMENT_MILESTONE)
	public List<WebElement> Ptrack_Payment_Milestone;
	
	@FindBy(css = PL_ActiveProjectConstant.PTRACK_INPUT_BOX)
	public List<WebElement> Ptrack_Input_Box;
	
	
	
	
	

	 
	
	
	
}
