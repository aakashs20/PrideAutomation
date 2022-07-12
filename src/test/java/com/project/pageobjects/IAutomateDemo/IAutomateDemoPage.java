package com.project.pageobjects.IAutomateDemo;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.testbase.TestBase;


public class IAutomateDemoPage extends TestBase{
	
	WebDriverWait wait;
	
	public IAutomateDemoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);

	}
	
	//For Selinium Easy
	
	@FindBy(xpath = IAutomateDemoConstants.POP_UP)
	public WebElement PopUp;
	
		
	@FindBy(xpath = IAutomateDemoConstants.INPUT_FORMS)
	public WebElement InputForms;
	
	@FindBy(xpath = IAutomateDemoConstants.SIMPLE_FORMS_DEMO)
	public WebElement SimpleFormsDemo;
	
	@FindBy(xpath = IAutomateDemoConstants.ENTER_MSG)
	public WebElement EnterMsg;
	
	@FindBy(xpath = IAutomateDemoConstants.SHOW_MSG_BTN)
	public WebElement ShowMsgBtn;
	
	@FindBy(xpath = IAutomateDemoConstants.VALUE1)
	public WebElement Value1;
	
	@FindBy(xpath = IAutomateDemoConstants.VALUE2)
	public WebElement Value2;
	
	@FindBy(xpath = IAutomateDemoConstants.GET_TOTAL_BTN)
	public WebElement GetTotalbtn;
	
	@FindBy(xpath = IAutomateDemoConstants.LIST_BOX)
	public WebElement ListBox;
	
	@FindBy(xpath = IAutomateDemoConstants.DATA_LIST_FILTER)
	public WebElement DataListFilter;
	
	@FindBy(xpath = IAutomateDemoConstants.SEARCH_ATTENDEES)
	public WebElement SearchAttendees;	
	
}
