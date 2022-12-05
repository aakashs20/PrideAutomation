package com.project.pageobjects.pTracker;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerCoordinatorConstants {

	
	
	//used in addCustomerCoordinator testcase by Prasanna
	
	public static final String FIXED_PRICE = "//*[@id='R505691641167493060_cards']/li[1]"; //
	public static final String SEARCH_PROJECT = "//*[@id='P30_AP_FC_SEACH']";
	public static final String GO_BUTTON = "//*[@id='B604616602516884943'][contains(text(),'GO')]";
	public static final String ACTIVE_PROJECTS_LIST = "//*[@id='active_tab']//span[contains(text(),'Active Projects')]";
	public static final String PROJECT_TEST8877 = "//a[@class='t-Button--link']";
	public static final String CUSTOMER_COORDINATOR = "//a[@aria-controls='R385363655964807528']";
	public static final String ADD_COORDINATOR = "//span[text()='Add Co-Ordinator']";
	public static final String COORDINATOR_NAME ="//input[@id='oj-inputsearch-input-P96_NAME_HIDDEN']";
	public static final String PROJECT_ROLE = "//input[@id='P96_PROJECT_ROLE']";
	public static final String DESIGNATION = "//input[@id='P96_DESIGNATION']";
	public static final String EMAIL_ADDRESS = "//input[@id='P96_EMAIL_ADDRESS']";
	public static final String REMARK = "//textarea[@id='P96_REMARK']";
	public static final String ADD_BUTTON = "//button[@id='B379684541596213911']";
	public static final String ADD_CUSTOMER_COORDINATOR_IFRAME = "//*[contains(@id,'apex_dialog')]/iframe";
	public static final String CANCEL_BUTTON = "//button[@id='B379682200144213909']";
	public static final String INACTIVE_RADIO_BUTTON = "//label[@for='P96_STATUS_1']";
			//"//label[@for='P96_STATUS_0']/following::label[1]";
	public static final String ACTIVE_RADIO_BUTTON = "//label[@for='P96_STATUS_0']";
	public static final String EDIT_CUSTOMER_COORDINATOR_IFRAME_ACTIVE = "//*[contains(@id,'apex_dialog_')]/iframe";
	public static final String EDIT_COORDINATOR_NAME = "//input[@id='oj-inputsearch-input-P96_NAME_HIDDEN']";
	public static final String EDIT_PROJECT_ROLE = "//input[@id='P96_PROJECT_ROLE']";
	public static final String EDIT_DESIGNATION = "//input[@id='P96_DESIGNATION']";
	public static final String EDIT_EMAIL_ADDRESS = "//input[@id='P96_EMAIL_ADDRESS']";
	public static final String EDIT_REMARK = "//textarea[@id='P96_REMARK']";
	public static final String SAVE_CHANGES_BUTTON = "//button[@id='B379684194474213911']";
	public static final String EDIT_CUSTOMER_COORDINATOR_IFRAME_INACTIVE = "//*[contains(@id,'apex_dialog_')]/iframe";
	public static final String ERROR_MESSAGE_COORDINATORNAME = "//a[@class='a-Notification-link'][text()='Please enter valid Coordinator Name.']";
	public static final String ERROR_MESSAGE_PROJECTROLE = "//a[@class='a-Notification-link'][text()='Please enter valid Project Role.']";
	public static final String ERROR_MESSAGE_DESIGNATION = "//a[@class='a-Notification-link'][text()='Please enter Designation.']";
	public static final String ERROR_MESSAGE_EMAILADDRESS = "//a[@class='a-Notification-link'][text()='Please enter Email Address.']";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	
	