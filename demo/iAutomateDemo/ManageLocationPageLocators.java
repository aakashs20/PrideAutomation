package com.project.safetychain.webapp.pageobjects;

public class ManageLocationPageLocators {

	public static final String SELECT_LOCATION_CATEGORY_TRE = "//span[span[@class='scs-category-text ng-binding']]/span[text()='LOCATION_CATEGORY_VALUE']";
	public static final String ADD_NEW_LOCATION_TRE = "//span[span[@class='scs-category-text ng-binding']]/span[text()='LOCATION_CATEGORY_VALUE']/following-sibling::i[@class='fa fa-plus-square ng-scope']";
	public static final String SET_LOCATION_INSTANCE_TXT = "//*[@id='{{settings.treeId}}_tv_active']//input";
	public static final String SAVE_LOCATION_BTN = "//*[@id='location-detail-save-btn']";

	//public static final String ALL_FIELDS_DBL = "//li[span[label[text()!='Name']]/span[@class='location-details-required']]/span[2]";
	//public static final String OPEN_SELECTONE_DDL = "//li[span[label[text()!='Name']]/span[@class='location-details-required']]//span[@role='button'][2]";

	public static final String SET_LOCATION_NAME_TXT = "//*[@id='scs-location-details']/ul/li[span[label[text()='Name']]]//input";
	public static final String ALL_FIELDS_DBL = "//*[@id='scs-location-details']/ul/li[span[label[text()!='Name']]]/span[2]";
	public static final String OPEN_SELECTONE_DDL = "//li[span[label[text()!='Name']]]//span[@role='button'][2]";
	public static final String FIELD_INPUTS_TXT = "//*[@id='scs-location-details']/ul/li[COUNT]/span[2]//input[1]";
	public static final String ALL_FIELD_INPUTS_TXT = "//*[@id='scs-location-details']//li//input";
	public static final String MODIFIED_BY_LBL = "//*[@id='scs-location-details']//li[last()]//label[text()='Modified By']";
	
	public static final String USERS_TAB = "//*[@id='location-tabstrip']/ul/li[last()]/span[2]";
	public static final String USER_SEARCH_TXT = "//*[@id='scs-manage-locations']//input[@placeholder='Search']";
	public static final String USER_CHK = "//*[@id='scs-loc-Users-grid']//tr[td[2][text()='USERNAME']]//input";
	public static final String AVAILABLE_LOCATION = "//span[@class='scs-treenode-text ng-binding'][text()='LOCATION_NAME']";
	
	public static final String RESOURCE_SEARCH_TXT = "//*[@id='scs-manage-locations']//input[@placeholder='Search']";
	public static final String SELECT_RESOURCE_CATEGORY = "//div[@class='scs-list-view-name-container ng-binding' and contains(normalize-space(.),'RESOURCE_CATEGORY')]";
	public static final String SELECT_RESOURCE_INSTANCE = "//*[@id='scs-manage-locations-restab-RESOURCE_CATEGORY-grid']//tr[td[2][contains(text(),'RESOURCE_INSTANCE')]]//input";
	
	public static final String RESOURCE_TAB_LBL = "//*[@id='location-tabstrip']//li/span[normalize-space(.)='Resources']";
	public static final String RESOURCE_TYPE_LBL = "//div[@class='scs-list-view-content-container']//div/div[normalize-space(.)='RESOURCE_CATEGORY'][1]";

	public static final String NAVIGATION_TABS = "//*[@id='location-tabstrip']//li[contains(@class,'ng-binding ng-scope')]";
	
	public static final String CUSTOMERS_TAB = "//*[@id='d8d13144-2941-4191-8200-3090dc9f6709']";
	public static final String CUSTOMERS_CHK = "(//*[@id='scs-manage-locations-restab-Customers-grid']/div[@class='k-grid-content k-auto-scrollable']//input)[1]";
			
	public static final String EQUIPMENT_TAB = "//*[@id='b8750de4-9026-481b-a3c1-dd69abe017d5']";
	public static final String EQUIPMENT_CHK = "(//*[@id='scs-manage-locations-restab-Equipment-grid']/div[@class='k-grid-content k-auto-scrollable']//input)[1]";
	
	public static final String ITEMS_TAB = "//*[@id='46791ac9-6840-4854-a452-3bb755183670']";
	public static final String ITEMS_CHK = "(//*[@id='scs-manage-locations-restab-Items-grid']/div[@class='k-grid-content k-auto-scrollable']//input)[1]";
	
	public static final String SUPPLIERS_TAB = "//*[@id='0b91c344-cda7-4133-a852-3c1cc2d6c1b0']";
	public static final String SUPPLIERS_CHK = "(//*[@id='scs-manage-locations-restab-Suppliers-grid']/div[@class='k-grid-content k-auto-scrollable']//input)[1]";
	
	public static final String FIRST_CUSTOMERS_CHK1 = "(//*[@id='scs-manage-locations-restab-Customers-grid']//td[@role='gridcell'][2])[1]";
	public static final String FIRST_EQUIPMENT_CHK1 = "(//*[@id='scs-manage-locations-restab-Equipment-grid']//td[@role='gridcell'][2])[1]";
	public static final String FIRST_ITEMS_CHK1 = "(//*[@id='scs-manage-locations-restab-Items-grid']//td[@role='gridcell'][2])[1]";
	public static final String FIRST_SUPPLIERS_CHK1 = "(//*[@id='scs-manage-locations-restab-Suppliers-grid']//td[@role='gridcell'][2])[1]";
	public static final String FIND_LOCATION = "//ul[contains(@class,'treeview-line')]//span[contains(text(), 'LOCATION_CATEGORY')]//ancestor::li[@role='treeitem']//span[contains(@class,'i-expand')]";
	public static final String FIND_LOCATION_INSTANCE = "//ul[contains(@class,'k-group')]//span[text()='LOCATION_INSTANCE']";
	
	public static final String CHECKBOX = "//*[@type='checkbox']";
	public static final String EQUIPMENT_CHK1 = "//*[@id='scs-manage-locations-resources-grid']//td[@role='gridcell'][2]";
	public static final String HEADING = "//*[@data-field='Name']";
	public static final String ALL_ITEMS_RESOURCE_HEADER = "//*[@data-field='Name'][@data-title='All Items']";
	
	public static final String COPY_BTN = "//*[@id='scs-manage-locations']//i[@ng-click='showCopyLocationPopup();']";
	public static final String COPY_BTN1 = "//*[@id='bottomButtonContainer']//button[2]";
	public static final String COPY_LOCATION_TXT = "//*[@id='scs-popup']//div[text()='Copy Location']";
	public static final String SELECT_ALL = "//div[@id='copyLocationGridContainer']//input[@id='scs-grid-select-all']";
	public static final String COPY_CUSTOMERS = "(//*[@ng-repeat='resourceType in resourceTypeList']/td)[1]";
	public static final String COPY_EQUIPMENT = "(//*[@ng-repeat='resourceType in resourceTypeList']/td)[2]";
	public static final String COPY_ITEMS ="(//*[@ng-repeat='resourceType in resourceTypeList']/td)[3]";
	public static final String COPY_SUPPLIERS = "(//*[@ng-repeat='resourceType in resourceTypeList']/td)[4]";
	public static final String USERS_CHK = "(//*[@id='scs-loc-Users-grid']//td[@role='gridcell']/input)[1]";
	
	public static final String SELECTED_LOC_INSTANCE = "//li[@aria-selected='true']//div[@data-item-role]/span";
	public static final String ALL_RESOURCE_TYPES_CHK = "//*[@class='scs-grid-row-checkbox']";
	public static final String RESOURCES_TAB = "//*[@id='location-tabstrip']/ul/li[@data-hash='Resources']/span[2]";
	public static final String DUPLICATE_LOCATION_POPUP_OK_BTN ="//button[@class='scs-ok-button-popup scs-button scs-blue-button ui-button ui-corner-all ui-widget']";
	public static final String DUPLICATE_LOCATION_CANCEL_BTN = "//input[@id='location-detail-cancel-btn']";
	
	public static final String COPY_LOCATION_BTN = "//i[@class='fa fa-files-o tree-operation-btn scs-ml-copy-btn ng-scope']";
				
	
}
