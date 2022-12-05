package com.project.pageobjects.pTracker;

public class PTrackerLoginConstants {
	
	public static final String role = "";
	public static final String USERNAME_TXT = "//*[@id='P9999_USERNAME']";
	public static final String PASSWORD_TXT = "//*[@id='P9999_PASSWORD']";
	public static final String LOGIN_BTN = "//*[@id='B130792125418763438']";
	public static final String DASHBOARD_ICON = "//*[@id='130784953437763361']";
	public static final String NAVIGATION_BAR_ICON = "//span[@class ='t-Icon fa fa-user']/parent::a[contains(@class,'t-Button--navBar')]";
	//public static final String NAVIGATION_BAR_ICON = "//*[@id='130784953437763361']/li[1]";
	public static final String LOGIN_TITLE_TXT = "//*[@id='R364464773624473992_heading']";
	public static final String PERSON_TXT = "//*[@id='P5_PERSON_ID']";
	public static final String SUBMIT_BTN = "//*[@id='B226534777801109334']";
	public static final String CANCEL_BTN = "//*[@id='B224965660767892127']";
	public static final String PERSON_ID_TXT = "//*[@id='PopupLov_5_P5_PERSON_ID_dlg']/div[1]/input";
	public static final String PERSON_ID_LIST = "//*[contains(@class, 'popup-lov-highlight')]";
	public static final String ALERT_TXT = "//*[@class='alertHeading' and contains(text(),'Alerts')]"; //span[contains(text(),'Home')]"
	public static final String PTRACK_ICON = "//span[@class='fa a-ptrack']";
	public static final String PTRACK_LABEL = "//*[@id='t_TreeNav_3']//a[contains(text(),'P-Track')]";
	public static final String SELECT_ROLE_LIST = "//*[@id='P8_SEL_ROLE']";
	public static final String SELECT_ROLE_BTN = "//*[@id='P8_SEL_ROLE_lov_btn']";
	//public static final String ROLE_LIST = "//*[@id='PopupLov_8_P8_SEL_ROLE_dlg']/div[2]/div/div[3]/ul/li[contains(text(),'Release 10 - Project Management')]";
	public static final String ROLE_LIST = "//*[@id='PopupLov_8_P8_SEL_ROLE_dlg']//li[contains(text(),'Release 12 - Project Management')]";
	public static final String ROLE_LIST_FR = "//*[@id='PopupLov_8_P8_SEL_ROLE_dlg']//li[contains(text(),'Finance')]";
	public static final String ROLE_LIST_ON_BEHALF = "//*[@id='PopupLov_8_P8_SEL_ROLE_dlg']//li[contains(text(),'On Behalf Of')and @tabindex='-1']";
	public static final String ROLE_LIST2 = "//*[@id='PopupLov_8_P8_SEL_ROLE_dlg']//li[contains(text(),'PMODesk')][1]";
	public static final String CANCEL_ROLE_BTN = "//*[@id='B77297466900893843']";
	public static final String PROCEED_BTN = "//*[@id='B77297519692893844']";
	public static final String ACTIVE_TAB_TXT = "//*[@id='active_tab']//span[contains(text(),'Active Projects')]";
	public static final String HOME_BUTTON = "//span[text() ='Home']/parent::a";
	
	public static final String ADVANCE_BTN = "//*[@id='details-button' and contains(text(),'Advanced')]";
	public static final String PROCEED_TO_BTN = "//*[@id='proceed-link' and contains(text(),'Proceed to')]";
	
	public static final String CALL_FOR_ACTION_BTN = "//*[@id='Btncfa_click']"; 
	

	
	
	
	
	
	
	
	
	//*[@id="PopupLov_5_P5_PERSON_ID_dlg"]/div[2]/div/div[3]/ul/li/span
	//*[contains(@class, 'popup-lov-highlight') and contains(text(),'Mahajan, Milind')]

}
