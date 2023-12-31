package com.project.pageobjects.pTracker;

public class ActionProjectsConstants {
	
	public static final String PROJECTS_ACTIONS_TAB_LNK = "//li[@id='actions_tab']/a/span[contains(text(),'Project Actions')]";
	public static final String DOWNLOAD_BUTTON = "//*[@id='B154483615703776627']/span[contains(text(),'Download')]";
	public static final String SEARCH_TEXT_BOX = "[id='P30_PA_FC_SEACH']";
	public static final String GO_BTN = "//div[@id='actions']//div[contains(@id,'NewProjects')]//button[contains(text(),'GO')]";
	public static final String PROJECT_TABLE_ROWS = "//*[@id='report_table_projects-active-report']/tbody/tr";
	public static final String PROJECT_ACTION_PAGINATION = "//div[@id='report_project-actions-report']//*[@class='t-Report-paginationText']//a";
	public static final String NEW_PROJECT_PAGINATION = "//div[@id='report_projects-active-report']//*[@class='t-Report-paginationText']/a";
}
