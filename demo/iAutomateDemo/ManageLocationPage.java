package com.project.safetychain.webapp.pageobjects;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.utilities.CommonMethods;

public class ManageLocationPage extends CommonPage{ 
	CommonMethods commonMethods;
	public ManageLocationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
	}


	@FindBy(xpath = ManageLocationPageLocators.SELECT_LOCATION_CATEGORY_TRE)
	public WebElement SelectCategoryTre;
	@FindBy(xpath = ManageLocationPageLocators.ADD_NEW_LOCATION_TRE)
	public WebElement AddLocationTre;
	@FindBy(xpath = ManageLocationPageLocators.SET_LOCATION_INSTANCE_TXT)
	public WebElement SetLocationInstanceTxt;
	@FindBy(xpath = ManageLocationPageLocators.SET_LOCATION_NAME_TXT)
	public WebElement SetLocationNameTxt;

	@FindBy(xpath = ManageLocationPageLocators.FIELD_INPUTS_TXT)
	public WebElement FieldInputsTxt;
	@FindBy(xpath = ManageLocationPageLocators.ALL_FIELD_INPUTS_TXT)
	public List<WebElement> AllFieldInputsTxt;
	@FindBy(xpath = ManageLocationPageLocators.ALL_FIELDS_DBL)
	List<WebElement> AllFieldsDbl;
	@FindBy(xpath = ManageLocationPageLocators.OPEN_SELECTONE_DDL)
	List<WebElement> OpenSelectOneDdl;

	@FindBy(xpath = ManageLocationPageLocators.SAVE_LOCATION_BTN)
	public WebElement SaveLocationBtn;
	@FindBy(xpath = ManageLocationPageLocators.MODIFIED_BY_LBL)
	public WebElement ModifiedByLbl;

	@FindBy(xpath = ManageLocationPageLocators.USERS_TAB)
	public WebElement UsersTab;

	@FindBy(xpath = ManageLocationPageLocators.USER_SEARCH_TXT)
	public WebElement UserSearchTxt;

	@FindBy(xpath = ManageLocationPageLocators.RESOURCE_SEARCH_TXT)
	public WebElement ResourceSearchTxt;

	@FindBy(xpath = ManageLocationPageLocators.NAVIGATION_TABS)
	public List<WebElement> NavigationTabs;

	@FindBy(xpath = ManageLocationPageLocators.CUSTOMERS_TAB)
	public WebElement CustomersTab;

	@FindBy(xpath = ManageLocationPageLocators.EQUIPMENT_TAB)
	public WebElement EquipmentTab;

	@FindBy(xpath = ManageLocationPageLocators.ITEMS_TAB)
	public WebElement ItemsTab;

	@FindBy(xpath = ManageLocationPageLocators.SUPPLIERS_TAB)
	public WebElement SuppliersTab;

	@FindBy(xpath = ManageLocationPageLocators.CUSTOMERS_CHK)
	public WebElement CustomersChk;

	@FindBy(xpath = ManageLocationPageLocators.EQUIPMENT_CHK)
	public WebElement EquipmentChk;

	@FindBy(xpath = ManageLocationPageLocators.ITEMS_CHK)
	public WebElement ItemsChk;

	@FindBy(xpath = ManageLocationPageLocators.SUPPLIERS_CHK)
	public WebElement SuppliersChk;

	@FindBy(xpath = ManageLocationPageLocators.USERS_CHK)
	public WebElement UsersChk;

	@FindBy(xpath = ManageLocationPageLocators.FIRST_CUSTOMERS_CHK1)
	public WebElement FirstCustomersChk1;

	@FindBy(xpath = ManageLocationPageLocators.FIRST_EQUIPMENT_CHK1)
	public WebElement FirstEquipmentChk1;

	@FindBy(xpath = ManageLocationPageLocators.FIRST_ITEMS_CHK1)
	public WebElement FirstItemsChk1;

	@FindBy(xpath = ManageLocationPageLocators.FIRST_SUPPLIERS_CHK1)
	public WebElement FirstSuppliersChk1;

	@FindBy(xpath = ManageLocationPageLocators.CHECKBOX)
	public List<WebElement> Checkbox;

	@FindBy(xpath = ManageLocationPageLocators.EQUIPMENT_CHK1)
	public List<WebElement> EquipmentChk1;

	@FindBy(xpath = ManageLocationPageLocators.HEADING)
	public WebElement Heading;

	@FindBy(xpath = ManageLocationPageLocators.ALL_ITEMS_RESOURCE_HEADER)
	public WebElement AllItemsResourceHeader;

	@FindBy(xpath = ManageLocationPageLocators.COPY_BTN)
	public WebElement CopyBtn;

	@FindBy(xpath = ManageLocationPageLocators.COPY_BTN1)
	public WebElement CopyBtn1;

	@FindBy(xpath = ManageLocationPageLocators.COPY_LOCATION_TXT)
	public WebElement CopyLocationTxt;

	@FindBy(xpath = ManageLocationPageLocators.SELECT_ALL)
	public WebElement SelectALL;

	@FindBy(xpath = ManageLocationPageLocators.COPY_CUSTOMERS)
	public WebElement CopyCustomers
	;
	@FindBy(xpath = ManageLocationPageLocators.COPY_EQUIPMENT)
	public WebElement CopyEquipment;

	@FindBy(xpath = ManageLocationPageLocators.COPY_ITEMS)
	public WebElement CopyItems;

	@FindBy(xpath = ManageLocationPageLocators.COPY_SUPPLIERS)
	public WebElement CopySuppliers;

	@FindBy(xpath = ManageLocationPageLocators.ALL_RESOURCE_TYPES_CHK)
	public List<WebElement> AllResourceTypesChk;

	@FindBy(xpath = ManageLocationPageLocators.RESOURCES_TAB)
	public WebElement ResourcesTab;
	
	@FindBy(xpath = ManageLocationPageLocators.DUPLICATE_LOCATION_POPUP_OK_BTN)
	public WebElement DuplicateLocationPopUpOkBtn;
	
	
	@FindBy(xpath = ManageLocationPageLocators.DUPLICATE_LOCATION_CANCEL_BTN)
	public WebElement DuplicateLocationCancelBtn;


	/** "addLocationInstance" method is used to add location instance in the location tree
	 * @author pashine_a
	 * @param String - 'categoryName'
	 * @return boolean status
	 * IF the location instance input is shown THEN true ELSE false
	 */
	public boolean addLocationInstance(String categoryName) {
		try {
			controlActions.refreshPage();
			//Sync();
			WebElement NewLocationCategory = controlActions.perform_GetElementByXPath(ManageLocationPageLocators.SELECT_LOCATION_CATEGORY_TRE, "LOCATION_CATEGORY_VALUE", categoryName);
			controlActions.doubleClickElement(NewLocationCategory);
			WebElement NewLocationCategoryPlus = controlActions.perform_GetElementByXPath(ManageLocationPageLocators.ADD_NEW_LOCATION_TRE, "LOCATION_CATEGORY_VALUE", categoryName);
			controlActions.perform_ClickWithJavaScriptExecutor(NewLocationCategoryPlus);
			//			String NewLocationCategoryPlus = controlActions.perform_GetDynamicXPath(ManageLocationPageLocators.ADD_NEW_LOCATION_TRE, "LOCATION_CATEGORY_VALUE", categoryName);
			//			controlActions.clickElement(driver, NewLocationCategoryPlus);
			logInfo("Extracted location instance");
			return true;
		}catch(Exception e) {
			logError("Error while adding new location instance in location category tree"+e.getMessage());
			return false;
		}

	}
	/** "setFields" method is used to set the value in fields of the location instance. For 'Select One' field, chooses first option
	 * @author pashine_a
	 * @param int - 'number' for Numeric value
	 * @param String - 'text' for Single Line Text value
	 * @param String - 'locationInstanceName'
	 * @return boolean status
	 * IF all the values have been set THEN true ELSE false
	 */	
	public boolean setFields(int number,String text, String locationInstanceName) {
		String fieldType;
		WebElement	input;
		//input = driver.findElement(By.xpath("//*[@id='scs-location-details']/ul/li["+count+"]/span[2]//input[1]"));
		int count=1,selectOneCount=0;
		try {
			if(AllFieldsDbl.isEmpty()) {
				controlActions.sendKeys(SetLocationNameTxt, locationInstanceName);
				logInfo("There is only Name field. There are no other fields for the location");
			}else {
				controlActions.sendKeys(SetLocationNameTxt, locationInstanceName);
				for(int i=0;i<AllFieldsDbl.size();i++) {
					count++;
					input = controlActions.perform_GetElementByXPath(ManageLocationPageLocators.FIELD_INPUTS_TXT, "COUNT", Integer.toString(count));
					fieldType = AllFieldsDbl.get(i).getAttribute("ng-if");
					if(fieldType.equals("data.Type == 'Textbox'")) {
						controlActions.sendKeys(input, text);
					}
					if(fieldType.equals("data.Type == 'Numeric'")) {
						controlActions.sendKeys(input, Integer.toString(number));
					}
					if(fieldType.equals("data.Type == 'SingleSelect'")) {
						controlActions.clickElement(OpenSelectOneDdl.get(selectOneCount));
						//input.sendKeys(Keys.DOWN);
						//input.sendKeys(Keys.ENTER);
						controlActions.sendKey(input, Keys.DOWN);
						controlActions.sendKey(input, Keys.ENTER);
						selectOneCount++;
					}	
				}
			}
			logInfo("VALUES in all the field for location instance has been SET");
			return true;
		}catch(Exception e) {
			logError("ERROR while setting field values -"+e.getMessage());
			return false;
		}
	}

	/** "createLocation" method is used to create a location
	 * @author pashine_a
	 * @param String - 'categoryName'
	 * @param String -'locationName'
	 * @return boolean status
	 * IF the location is created THEN true ELSE false
	 */
	public boolean createLocation(String categoryName, String locationName) {
		ResourceDesignerPage rdp;
		try {
			rdp = clickResourceDesignerMenu();
			if(rdp.error) {
				return false;
			}
			if(!rdp.createLocationCategory(categoryName)) {
				return false;
			}
			if(clickLocationsMenu().error) {
				return false;
			} 
			if(!addLocationInstance(categoryName)){
				return false;
			}
			if(!setFields(1234,"Automation Test", locationName)) {
				return false;
			}
			Sync();
			controlActions.clickElement(SaveLocationBtn);
			if(!verifyCreatedLocation()) {
				return false;
			}
			logInfo("Successfully created the location instance in location category tree");
			return true;
		}catch(Exception e) {
			logError("Failed to create the location instance in location category tree - "+e.getMessage());
			return false;
		}
	}

	/** "verifyCreatedLocation" method is used to verify that the location instance is created
	 * @author pashine_a
	 * @param null
	 * @return boolean status
	 * IF Added locations is verified THEN true ELSE false
	 */	
	public boolean verifyCreatedLocation() {
		try {
			Sync();
			if(!controlActions.isElementDisplayedOnPage(ModifiedByLbl)) {
				logError("NOT VERIFIED created location");
				return false;
			}
			logInfo("VERIFIED created location");
			return true;
		}catch(Exception e) {
			logError("FAILED to VERIFY created location - "+e.getMessage());
			return false;
		}
	}
	/** "linkUser" method is used to link the user with the locations
	 * @author pashine_a
	 * @param String - username
	 * @return boolean status
	 * IF user is linked THEN true ELSE false
	 */	
	public boolean linkUser(String username) {
		String userStatus = null;
		try {
			controlActions.doubleClickElement(UsersTab);
			controlActions.sendKeys(UserSearchTxt,username);
			controlActions.sendKey(UserSearchTxt,Keys.ENTER);
			threadsleep(4000);
			WebElement userNameChk = controlActions.perform_GetElementByXPath(ManageLocationPageLocators.USER_CHK, "USERNAME",username);
			userStatus = userNameChk.getAttribute("disabled");
			if(userStatus==null) {
				controlActions.clickElement(userNameChk);
				controlActions.clickElement(SaveLocationBtn);
				logInfo("User is selected");
			}else {
				logInfo("User has access of the location");
			}

			return true;
		}catch(Exception e) {
			logError("FAILED to select user - "+e.getMessage());
			return false;
		}
	}

	/** "createLocationLinkUser" method is used to create a location & link the user with the location
	 * @author pashine_a
	 * @param String - locationCategoryValue
	 * @param String - locationInstanceValue
	 * @param String - username
	 * @return boolean status
	 * IF location is created & linked with user THEN true ELSE false
	 */	
	public boolean createLocationLinkUser(String locationCategoryValue,String locationInstanceValue,String username) {
		try {
			if(!createLocation(locationCategoryValue, locationInstanceValue)) {
				return false;
			}
			if(!linkUser(username)) {
				return false;
			}
			logInfo("Location - '"+locationInstanceValue+"' is linked with - '"+username+"'");
			logInfo("Flow -> 'Location Creation - User Linking' COMPLETED");
			return true;
		}catch(Exception e) {
			logError("ERROR in flow -> 'Location Creation - User Linking' - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to create location instances for a location category
	 * @author hingorani_a
	 * @param lip An object of Class LocationInstanceParams, in order to set
	 * different fields for location category creation.
	 * @return boolean This returns boolean true after creation location instances
	 */
	public boolean createLocation(LocationInstanceParams lip) {
		try {
			for (Map.Entry<String, Boolean> entry : lip.InstanceName.entrySet()) {
				String instanceName = entry.getKey();
				if(!addLocationInstance(lip.CategoryName)){
					return false;
				}
				if(!setFields(instanceName, lip.NumberFieldValue, lip.TextFieldValue)) {
					return false;
				}

				controlActions.clickElement(SaveLocationBtn);
				Sync();
				if(!verifyCreatedLocation()) {
					return false;
				}
				logInfo("Location instance - " + instanceName + " created successfully");
			}

			logInfo("Successfully created the location instance in location category tree");
			return true;
		}
		catch(Exception e) {
			logError("Failed to create the location instance in location category tree - "
					+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to set fields when creating a location instance
	 * @author hingorani_a
	 * @param locationInstanceName Name of Location instance
	 * @param number The value you would like to set for Number fields
	 * @param text The value you would like to set for Text fields 
	 * @return boolean This returns true if values are set for location instance
	 */
	public boolean setFields(String locationInstanceName, int number, String text) {

		try {
			SetLocationNameTxt.clear();
			controlActions.sendKeys(SetLocationNameTxt, locationInstanceName);

			if(AllFieldInputsTxt.isEmpty()) {
				logInfo("There is only Name field. There are no other fields for the location");
			}else {
				for(int i = 1; i < AllFieldInputsTxt.size(); i++) {
					if(AllFieldInputsTxt.get(i).isDisplayed()) {

						if(AllFieldInputsTxt.get(i).getAttribute("role") == null) {
							if(AllFieldInputsTxt.get(i).getAttribute("class").contains("ng-empty")) {
								controlActions.sendKeys(AllFieldInputsTxt.get(i), text);
								logInfo("Resource text field set as - " + text);
							}
						}
						else if(AllFieldInputsTxt.get(i).getAttribute("role").equals("spinbutton")) {
							if(AllFieldInputsTxt.get(i).getAttribute("aria-valuenow") == null) {
								controlActions.sendKeys(AllFieldInputsTxt.get(i), Integer.toString(number));
								logInfo("Resource numeric field set as - " + number);
								i++;
							}
						}
						else if(AllFieldInputsTxt.get(i).getAttribute("role").equals("combobox")) {
							controlActions.clickElement(AllFieldInputsTxt.get(i));
							controlActions.actionDown(); 
							logInfo("Resource combobox value is set");		
						}
						else {
							logInfo("The value of role tag for input element is - " + AllFieldInputsTxt.get(i).getAttribute("role"));
						}

						Sync();
					}
				}
			}
			logInfo("Values in all the field for location instance has been set");
			return true;
		}
		catch(Exception e) {
			logError("Failed while setting field values -"+e.getMessage());
			return false;
		}
	}
	/** "createLocationInstanceLinkUser" method is used to create a location & link the user with the location
	 * @author pashine_a
	 * @param String - locationCategoryValue
	 * @param String - locationInstanceValue
	 * @param String - userName
	 * @return boolean status
	 * IF location is created & linked with user THEN true ELSE false
	 */	
	public boolean createLocationInstanceLinkUser(String locationCategoryValue,String locationInstanceValue,String userName) {
		try {
			if(clickLocationsMenu().error) {
				return false;
			} 
			if(!addLocationInstance(locationCategoryValue)){
				return false;
			}
			if(!setFields(1234,"Automation Test", locationInstanceValue)) {
				return false;
			}
			Sync();
			controlActions.clickElement(SaveLocationBtn);
			if(!verifyCreatedLocation()) {
				return false;
			}
			if(!linkUser(userName)) {
				return false;
			}
			logInfo("Location - '"+locationInstanceValue+"' is linked with - '"+userName+"'");
			logInfo("Flow -> 'Location Creation - User Linking' COMPLETED");
			return true;
		}catch(Exception e) {
			logError("ERROR in flow -> 'Location Creation - User Linking' - "+e.getMessage());
			return false;
		}
	}

	/** "createLocationInstance" method is used to create a location
	 * @author choubey_a
	 * @param String - locationCategoryValue
	 * @param String - locationInstanceValue
	 * @return boolean status
	 * IF location is created THEN true ELSE false
	 */		
	public boolean createLocationInstance(String locationCategoryValue,String locationInstanceValue) {
		try {
			if(clickLocationsMenu().error) {
				return false;
			} 
			if(!addLocationInstance(locationCategoryValue)){
				return false;
			}
			if(!setFields(1234,"Automation Test", locationInstanceValue)) {
				return false;
			}
			Sync();
			controlActions.clickElement(SaveLocationBtn);
			if(!verifyCreatedLocation()) {
				return false;
			}
			logInfo("Flow -> 'Location Creation - User Linking' COMPLETED");
			return true;
		}catch(Exception e) {
			logError("ERROR in flow -> 'Location Creation' - "+e.getMessage());
			return false;
		}
	}


	/** This method is used to verify the location instance presence
	 * @author pashine_a
	 * @param locationCategory
	 * @@param locationValue
	 * @return boolean status
	 * IF location is available verified THEN true ELSE false
	 */	
	public boolean verifyAvailableLocation(String locationCategory, String locationValue) {
		try {
			WebElement locationCategorySelection = controlActions.perform_GetElementByXPath(ManageLocationPageLocators.SELECT_LOCATION_CATEGORY_TRE, "LOCATION_CATEGORY_VALUE", locationCategory);
			controlActions.doubleClickElement(locationCategorySelection);
			Sync();
			WebElement LocationAvailability = controlActions.perform_GetElementByXPath(ManageLocationPageLocators.AVAILABLE_LOCATION, "LOCATION_NAME", locationValue);

			if(!controlActions.isElementDisplayedOnPage(LocationAvailability)) {
				logError("Location is not present");
				return false;
			}
			logInfo("Location is visible");
			return true;
		}catch(Exception e) {
			logError("Failed to verify location's presence- "+e.getMessage());
			return false;
		}
	}
	/** "linkResourceToLocation" method is used to link resource(s) with location
	 * @author pashine_a
	 * @param LocationInstanceParams - locationInst
	 * @return boolean status
	 * IF resource is created & linked with location THEN true ELSE false
	 */	
	public boolean linkResourceToLocation(HashMap<String, List<String>> resourceList) {
		String categoryType;
		List<String> resourceInstances;
		WebElement selectResourceCategory;
		try {
			controlActions.clickElement(ResourcesTab);
			for (Map.Entry<String, List<String>> entry : resourceList.entrySet()) {
				categoryType = entry.getKey();
				resourceInstances = entry.getValue();
				selectResourceCategory = controlActions.perform_GetElementByXPath(ManageLocationPageLocators.SELECT_RESOURCE_CATEGORY, "RESOURCE_CATEGORY",categoryType);
				controlActions.clickElement(selectResourceCategory);
				Sync();
				if(!linkResource(categoryType, resourceInstances)) {
					logError("Failed to select resource(s) for '"+categoryType+"' category");
					return false;
				}
			}
			logInfo("Location-Resource Linking is successfull");
			return true;
		}catch(Exception e) {
			logError("Error in Location-Resource Linking - "+e.getMessage());
			return false;
		}
	}

	/** "linkResource" method is used to link the resource(s)
	 * @author pashine_a
	 * @param String - categoryType
	 * @param List<String> - resourceInstances
	 * @return boolean status
	 * IF user is linked THEN true ELSE false
	 */	
	public boolean linkResource(String categoryType, List<String> resourceInstances) {
		String initialXPath;
		controlActions.clickElement(ResourcesTab);
		Sync();
		String resourceType = controlActions.perform_GetDynamicXPath(ManageLocationPageLocators.RESOURCE_TYPE_LBL, "RESOURCE_CATEGORY", categoryType);
		WebElement ResourceTypeElement = controlActions.perform_GetElementByXPath(resourceType);
		controlActions.clickElement(ResourceTypeElement);
		Sync();
		threadsleep(2000);

		try {
			for(int i=0;i<resourceInstances.size();i++) {
				controlActions.sendKeys(ResourceSearchTxt,resourceInstances.get(i));
				controlActions.sendKey(ResourceSearchTxt,Keys.ENTER);
				Sync();
				threadsleep(2000);
				initialXPath = controlActions.perform_GetDynamicXPath(ManageLocationPageLocators.SELECT_RESOURCE_INSTANCE, "RESOURCE_CATEGORY", categoryType);
				WebElement resourceNameChk = controlActions.perform_GetElementByXPath(initialXPath, "RESOURCE_INSTANCE",resourceInstances.get(i));
				controlActions.clickElement(resourceNameChk);
			}
			controlActions.clickElement(SaveLocationBtn);
			logInfo("Resource is selected");
			return true;
		}catch(Exception e) {
			logError("FAILED to select resource - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to create location instances for a location category
	 * @author dahale_p
	 * @param lip An object of Class LocationInstanceParams, in order to set
	 * different fields for location category creation.
	 * @return boolean This returns boolean true after creation location instances
	 */
	public boolean createLocationlinkresource( String locationCategoryValue,String locationInstanceValue,String username,HashMap<String, List<String>> resourceList) {
		try {

			if(!createLocationInstanceLinkUser( locationCategoryValue,locationInstanceValue , username)){
				return false;
			}


			if(!linkResourceToLocation(resourceList)) {
				return false;
			}




			logInfo("Successfully created the location instance in location category tree");
			return true;
		}
		catch(Exception e) {
			logError("Failed to create the location instance in location category tree - "
					+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to select resources tab
	 * @author jadhav_akan
	 * @param tabName : Name of the tab to be select
	 * @return boolean This returns true if mentioned resource category tab is selected
	 */
	public boolean selectTab(String tabName) {
		WebElement tab = null;
		try {
			controlActions.click(ResourcesTab);
			switch (tabName) {
			case SelectTAB.CUSTOMERS : tab = CustomersTab;break;
			case SelectTAB.EQUIPMENT : tab = EquipmentTab;break;
			case SelectTAB.ITEMS 	 : tab = ItemsTab;break;
			case SelectTAB.SUPPLIERS : tab = SuppliersTab;break;
			case SelectTAB.USERS     : tab = UsersTab;break;
			}
			controlActions.click(tab);
			logInfo("Selected resource category tab -" + tabName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to switch to category tab: " + tabName
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to check first checkbox of Customer tab/Equipment tab/Items tab/Suppliers tab
	 * @author jadhav_akan
	 * @param resourceCategory : Name of resource category that needs to be selected
	 * @return boolean This returns true if Selects resource category tab and checked first resource
	 */
	public boolean selectFirstCheckboxForResourceCategory(String resourceCategory) {
		WebElement firstChkbox = null;

		try {
			switch(resourceCategory) {
			case SelectTAB.CUSTOMERS : firstChkbox = CustomersChk;
			break;

			case SelectTAB.EQUIPMENT : firstChkbox = EquipmentChk;
			break;

			case SelectTAB.ITEMS     : firstChkbox = ItemsChk;
			break;

			case SelectTAB.SUPPLIERS : firstChkbox = SuppliersChk;
			firstChkbox.getText();
			break;
			}
			controlActions.click(firstChkbox);
			logInfo("Selected resource category tab - " + resourceCategory + " and checked first resource");
			return true;

		}catch(Exception e) {
			logError("Failed to click on First Category " + e.getMessage());
			return false;
		}
	}
	/**
	 * This method is used to click on Save button
	 * @author jadhav_akan
	 * @return boolean This returns true if Clicked on save location button
	 */
	public boolean clickOnSave() {
		try {
			controlActions.clickElement(SaveLocationBtn);
			logInfo("Clicked on save");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Save "+ e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to get name of the first resource 
	 * @author jadhav_akan
	 * @param resourceTab : Name of resource category to be selected
	 * @return String This returns value if gets first resource name
	 */
	public String getFirstResourceName(String resourceTab) {
		WebElement firstResourceElement = null;
		try {
			switch(resourceTab) {
			case SelectResource.CUSTOMERS : firstResourceElement = FirstCustomersChk1;
			break;

			case SelectResource.EQUIPMENT : firstResourceElement = FirstEquipmentChk1;
			break;


			case SelectResource.ITEMS     : firstResourceElement = FirstItemsChk1;
			break;

			case SelectResource.SUPPLIERS : firstResourceElement = FirstSuppliersChk1;
			break;
			}
			logInfo("Succesfully get Resource Name "+firstResourceElement.getText());
			return firstResourceElement.getText();
		}
		catch(Exception e) {
			logError("Failed to get Resource Name "+ e.getMessage());
			return null;
		}
	}
	/**
	 * This method is used to scroll and find Location Category and Location Instance
	 * @author jadhav_akan
	 * @param locationCategory : Name of category that needs to be selected
	 * @param locationInstance : Name of Instance that needs to be selected
	 * @return boolean This returns true if find and click Location Category and Location Instance
	 */
	public boolean scrollAndFindLocationInstance(String locationCategory,String locationInstance) {
		try {

			WebElement FindLocationCat = controlActions.perform_GetElementByXPath(ManageLocationPageLocators.FIND_LOCATION, "LOCATION_CATEGORY", locationCategory);
			controlActions.perform_scrollToElement_ByElement(FindLocationCat);
			controlActions.clickOnElement(FindLocationCat);
			Sync();

			WebElement FindLocationInst = controlActions.perform_GetElementByXPath(ManageLocationPageLocators.FIND_LOCATION_INSTANCE, "LOCATION_INSTANCE", locationInstance);
			controlActions.clickOnElement(FindLocationInst);
			Sync();

			logInfo("Succesfully found and clicked location instance");
			return true;	
		}
		catch(Exception e) {
			logError("Failed to find and click location instance " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to check checkbox of Customer tab/Equipment tab/Items tab/Suppliers tab resources are unselected or not
	 * @author jadhav_akan
	 * @return boolean This returns true when checkbox i.e resources are not selected
	 */
	public boolean checkListOfCheckboxAreUnselected() {
		List<WebElement> allCheckBox1 = Checkbox;
		try {
			for(int i=0;i<allCheckBox1.size();i++){
				if(allCheckBox1.get(i).isSelected()){
					return false;
				}
			}

			logInfo("Checked Resources are UNSELECTED");
			return true;
		}
		catch(Exception e) {
			logError("Failed to check resources are selected or not "+ e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to verify resources of Customer tab/Equipment tab/Items tab/Suppliers tab are not selected
	 * @author jadhav_akan
	 * @param resourceCategory : Name of resource category that needs to be selected
	 * @return boolean This returns true if resources are not selected
	 */
	public boolean resourceCategoryAreNotSelected(String resourceCategory) {

		try {
			switch(resourceCategory) {
			case SelectTAB.CUSTOMERS : 
				checkListOfCheckboxAreUnselected();
				break;

			case SelectTAB.EQUIPMENT : 
				checkListOfCheckboxAreUnselected();
				break;

			case SelectTAB.ITEMS     : 
				checkListOfCheckboxAreUnselected();
				break;

			case SelectTAB.SUPPLIERS : 
				checkListOfCheckboxAreUnselected();
				break;
			}
			Sync();
			logInfo("Selected resource category tab - " + resourceCategory + " are UNCHECKED");
			return true;

		}catch(Exception e) {
			logError("Failed to click on resource category tab and resources are unselected " + e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to check if Resources of Equipment is not displayed
	 * @author jadhav_akan
	 * @return boolean This returns true if resources are not displaying.
	 */
	public boolean chkEquipmentResourceIsNotDisplayed() {
		try {
			if(EquipmentChk1.isEmpty()) {
				logInfo("Nothing is Displayed");
			}
			logInfo("Verified Resources are not Displayed " );
		} catch (Exception e) {
			logError("Failed, Resources are displaying  " + e.getMessage());
			return false;

		}
		Sync();
		return true;
	}

	/**
	 * This method is used to search resources of Customer tab/Equipment tab/Items tab/Suppliers tab.
	 * @author jadhav_akan
	 * @param resourceInstName : Name of resource that needs to be search
	 * @return boolean This returns true if resource is searched successfully
	 */
	public boolean searchResource(String resourceInstName) {

		try {
			controlActions.sendKeys(UserSearchTxt,resourceInstName);
			controlActions.sendKey(UserSearchTxt,Keys.ENTER);
			Sync();
			logInfo("Succesfully searched resource");
			return true;
		}catch(Exception e) {
			logError("FAILED to search resource  - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to get the text of header
	 * @author jadhav_akan
	 * @return boolean This returns true if expected heading matches with actual heading.
	 */
	public boolean getTextOfHeading() {
		try {
			String expectedHeadingText = "All Items";

			WebElement headingText = controlActions.perform_GetElementByXPath(ManageLocationPageLocators.HEADING);
			controlActions.perform_waitUntilVisibility(headingText);
			String heading = headingText.getText();
			if(expectedHeadingText.equalsIgnoreCase(heading)) {
				logInfo("The expected heading is same as actual heading "+  heading);
				return true;
			}
			logInfo("The expected heading doesn't match the actual heading " + heading);
			return false;
		}
		catch(Exception e) {
			logError("Failed to get text of heading "+ e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to search the user when click on users tab
	 * @author jadhav_akan
	 * @param userToSearch : Name of user that needs to be search
	 * @return boolean This returns true if user is searched successfully.
	 */
	public boolean searchUser(String userToSearch) {

		try {
			controlActions.sendKeys(UserSearchTxt,userToSearch);
			controlActions.sendKey(UserSearchTxt,Keys.ENTER);
			Sync();
			logInfo("Succesfully searched User");
			return true;
		}catch(Exception e) {
			logError("FAILED to search User  - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to check checkbox for searched user is enable or disable
	 * @author jadhav_akan
	 * @param username : Name of user that needs to be search
	 * @return boolean Status whether checkbox for searched user is enable or disable. 
	 */
	public boolean usercheckboxIsDisableOrEnable(String username) {
		String userStatus = null;
		try {
			WebElement userNameChk = controlActions.perform_GetElementByXPath(ManageLocationPageLocators.USER_CHK, "USERNAME",username);
			userStatus = userNameChk.getAttribute("disabled");
			if(userStatus==null) {
				logInfo("Succesfully Checked User is Enabled");
			}else {
				logInfo("Succesfully Checked User is Disabled");
			}
			return true;
		}catch(Exception e) {
			logError("FAILED to check User is Disable or not - "+e.getMessage());
			return false;
		}
	}
	/**
	 * This method is used to check copy Icon is displaying or not
	 * @author jadhav_akan
	 * @return boolean This returns true if Icon is displayed
	 */
	public boolean copyIsDisplayed() {
		try {
			if(controlActions.isElementDisplayedOnPage(CopyBtn)) {
				String copy = CopyBtn.getText();
				logInfo("VERIFIED copy Icon is displayed " + copy);	
			}else {

				logInfo("VERIFIED copy Icon is Not displayed" );
			}
			return true;
		}
		catch(Exception e) {
			logError("Failed to verify Copy Button is displaying or not  "+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click on copy Icon
	 * @author jadhav_akan
	 * @return boolean This returns true if Successfully clicked on copy Icon 
	 */
	public boolean clickOnCopyIcon() {
		try {
			controlActions.clickElement(CopyBtn);
			logInfo("Clicked on Copy Icon ");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Copy Icon"+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click on copy Button of Copy Location PopUp
	 * @author jadhav_akan
	 * @return boolean This returns true if Successfully clicked on copy Button 
	 */
	public boolean clkCopyButton() {
		try {
			controlActions.clickElement(CopyBtn1);
			logInfo("Clicked on Copy Button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Copy Button"+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to check copy Button of Copy Location PopUp is Enable or Disable
	 * @author jadhav_akan
	 * @return boolean This returns true if copy Button is Disable
	 */
	public boolean copyButtonIsEnabledOrDisabled() {
		String copyStatus = null;
		try {
			copyStatus = CopyBtn1.getAttribute("disabled");
			System.out.println(copyStatus);
			if(copyStatus==null) {
				logInfo("Succesfully Checked Copy Button is Enabled");
			}else {
				logInfo("Succesfully Checked Copy Button is Disabled");
			}
			return true;
		}
		catch(Exception e) {
			logError("Failed to check Copy Copy button is Enabled or Disabled "+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click Select All CheckBox of Copy Location PopUp
	 * @author jadhav_akan
	 * @return boolean This returns true if Clicked on Select All
	 */
	public boolean clickOnSelectAll() {
		try {
			controlActions.clickElement(SelectALL);
			logInfo("Clicked on Select All");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Select All "+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to check Resources checkBox of Customers/Equipment/Items/Suppliers are Selected or not
	 * @author jadhav_akan
	 * @return boolean This returns true if checkBox of Resources are Selected
	 */
	public boolean checkListOfCheckboxAreSelectedOrNot() {
		List<WebElement> allCheckBox1 = Checkbox;
		try {
			for(int i=0;i<allCheckBox1.size();i++){
				if(allCheckBox1.get(i).isSelected()){
				}
			}
			logInfo("Checked Resources are SELECTED");
			return true;
		}
		catch(Exception e) {
			logError("Failed to check All resources are selected or not "+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to check user Checked or Unchecked in Users Tab
	 * @author jadhav_akan
	 * @return boolean This returns true if User is Checked i.e selected
	 */
	public boolean userIsCheckedOrUnchecked() {
		try {
			if(UsersChk.isSelected()) {
				logInfo("User is Checked ");	
			}else {

				logInfo("User is Unchecked" );
			}
			return true;
		}
		catch(Exception e) {
			logError("Failed to verify user is Checked or Unchecked  "+ e.getMessage());
			return false;
		}
	}

	/**
	 * @author ahmed_tw
	 * @param locCatName
	 * @param locInstName
	 * @return
	 */
	public boolean copyLocInstanceForAllResources(String locCatName, String locInstName) {
		String copyInstanceName = locInstName+" 1";
		try {

			scrollAndFindLocationInstance(locCatName,locInstName);

			controlActions.clickElement(CopyBtn);
			logInfo("Clicked on Copy Icon");

			controlActions.isElementDisplay(CopyLocationTxt);
			logInfo("Verifeid Copy Location Pop up Heder");

			clickOnSelectAll();
			logInfo("Clicked on Select All");

			controlActions.clickElement(CopyBtn1);
			logInfo("Clicked on Copy Button");

			WebElement findLocationInst = controlActions.perform_GetElementByXPath(ManageLocationPageLocators.FIND_LOCATION_INSTANCE, "LOCATION_INSTANCE", locInstName);
			if(findLocationInst.isDisplayed()) {
				logInfo("Verified Loc Instance is Copied Successfully" + copyInstanceName);
				return true;
			}
			logInfo("Could Not copy Instance" + locInstName);
			return false;
		}
		catch(Exception e) {
			logError("Failed to click on Copy "+ e.getMessage());
			return false;
		}
	}

	/**This method is used to get the name of currently selected location instance
	 * @author ahmed_tw
	 * @return [String] : Name of currently selected Location Instance.
	 */
	public String getSelectedLocInstName() {
		try {
			WebElement selectedInstance = controlActions.perform_GetElementByXPath(ManageLocationPageLocators.SELECTED_LOC_INSTANCE);
			logInfo("Selected Location Instance  is " + selectedInstance.getText());
			return selectedInstance.getText();
		} catch (Exception e) {
			logError("Could not get name of Selected location instance" + e.getMessage());
			return null;
		}
	}

	/**
	 * This method is used to check All Resource types from the list are selected after clicking on Select All in Copy Location PopUp
	 * @author jadhav_akan
	 * @return boolean This returns true if All Resource Types from the list are SELECTED.
	 */
	public boolean checkListOfAllResourceTypesSelected() {
		List<WebElement> allTypesCheckBox = AllResourceTypesChk;
		try {
			for(int i=0;i<allTypesCheckBox.size();i++){
				if(allTypesCheckBox.get(i).isSelected()){
					logInfo("Checked All Resource Types are SELECTED");
				}
			}
			return true;
		}
		catch(Exception e) {
			logError("Failed to check All resources are selected or not "+ e.getMessage());
			return false;
		}
	}
	/**
	 * This method is used to unlink/unCheck resource from location
	 * @author jadhav_akan
	 * @param locationCategory : Location Category value
	 * @param locationInstance : Location Instance value
	 * @param tabName : Name of Resource Tab that needs to be select
	 * @param resourceInstName : Name of resource that needs to be search and unlink
	 * @param resourceCategory : Resource category value such as SelectTAB.CUSTOMERS/SelectTAB.EQUIPMENT...
	 * @return boolean Status returns true if Resource is unlinked Successfully else false. 
	 */
	public boolean unlinkResourceFromLocation(String locationCategory,String locationInstance,String tabName,String resourceInstName,String resourceCategory) {
		try {
			if(!scrollAndFindLocationInstance(locationCategory,locationInstance))
				return false;

			if(!selectTab(tabName))
				return false;

			if(!searchResource(resourceInstName))
				return false;

			if(!selectFirstCheckboxForResourceCategory(resourceCategory))
				return false;

			if(!clickOnSave())
				return false;

			logInfo("Successfully Unchecked/Unlink resource - "+resourceInstName+" from location - "+locationInstance);
			return true;
		}
		catch(Exception e) {
			logError("Failed to Uncheck/Unlink resource - "+resourceInstName+" from location - "+locationInstance+ " "+ e.getMessage());
			return false;
		}
	}


	/** "locationLinkUser" method is used to link/unlink the user with the provided location
	 * @author pashine_a
	 * @param String - locationCategory
	 * @param String - locationInstance
	 * @param String - username
	 * @return boolean status
	 * IF user is linked THEN true ELSE false
	 */	
	public boolean locationLinkUser(String locationCategory,String locationInstance, String username) {
		String userStatus = null;
		try {
			if(!scrollAndFindLocationInstance(locationCategory,locationInstance)) {
				return false;
			}
			controlActions.doubleClickElement(UsersTab);
			Sync();
			controlActions.sendKeys(UserSearchTxt,username);
			controlActions.sendKey(UserSearchTxt,Keys.ENTER);
			Sync();
			threadsleep(4000);
			WebElement userNameChk = controlActions.perform_GetElementByXPath(ManageLocationPageLocators.USER_CHK, "USERNAME",username);
			userStatus = userNameChk.getAttribute("disabled");
			if(userStatus==null) {
				controlActions.clickElement(userNameChk);
				controlActions.clickElement(SaveLocationBtn);
				logInfo("User is selected/unselected");
			}else {
				logInfo("User has access of the ALL location");
			}
			return true;
		}catch(Exception e) {
			logError("FAILED to select/unselect the user - "+e.getMessage());
			return false;
		}
	}
	
	/** "createLocation" method is used to create a location
	 * @author dahale_p
	 * @param String - 'categoryName'
	 * @param String -'locationName'
	 * @return boolean status
	 * IF the Duplicate location popup is occurred THEN true ELSE false
	 */
	public boolean createDuplicateLocation(String categoryName, String locationName) {
		//ResourceDesignerPage rdp;
		try {
			
			if(clickLocationsMenu().error) {
				return false;
			} 
			if(!addLocationInstance(categoryName)){
				return false;
			}
			if(!setFields(1234,"Automation Test", locationName)) {
				return false;
			}
			Sync();
			controlActions.clickElement(SaveLocationBtn);
			if(!verifyCreatedLocation()) {
				return false;
			}
			
			if(!clickRoleDuplicatePopUpButton()){
				return false;
				}
				
			if(!clickDuplicateRoleCancelButton()){
				return false;
				}
				
				Sync();
			logInfo("Throws Validation popup for Duplicate Location and dupicate location not created ");
			return true;
		}catch(Exception e) {
			logError("Failed to Throws Validation popup for Duplicate Location and dupicate location get created - "+e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to click OK BUTTON while in Workgroup module
	 * @author dahale_p
	 * @return True after clicking OK Button, else false
	 */
	public boolean clickRoleDuplicatePopUpButton() {
		try {	
			
			Sync();
			controlActions.WaitforelementToBeClickable(DuplicateLocationPopUpOkBtn);
			Sync();
			controlActions.clickOnElement(DuplicateLocationPopUpOkBtn);
			Sync();
			logInfo("Clicked on OK Button in popup of WG menu");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click OK Button in popup of WG menu - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to click on Cancel button while in Workgroup module
	 * @author dahale_p
	 * @return True after clicking on Cancel button of Workgroup module, else false
	 */
	public boolean clickDuplicateRoleCancelButton() {
		try {	
			Sync();
			controlActions.WaitforelementToBeClickable(DuplicateLocationCancelBtn);
			Sync();
			controlActions.clickOnElement(DuplicateLocationCancelBtn);
			Sync();
			logInfo("Clicked on Cancel Button of WG menu");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click  Cancel Button of WG menu - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to select actual ROLE .
	 * @author dahale_p
	 * @param roleName
	 * @return boolean This returns true if required LOCATION get clicked.
	 */
	public boolean selectAndCopyLocation(String locationName) { 
		try {
			
//			WebElement selectLocation = controlActions.perform_GetElementByXPath(ManageLocationPageLocators.SELECT_ROLE, 
//					"LOCATION",locationName);
//			controlActions.WaitforelementToBeClickable(selectRole);
//			//			Sync();
			//			controlActions.clickOnElement(selectRoleName);
//			selectRole.click();
			Sync();
			logInfo("Clicked on Role panel for role");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Role  "
					+ e.getMessage());
			return false;
		}		
	}
	

	public static class SelectTAB{
		public static final String CUSTOMERS = "Customers";
		public static final String EQUIPMENT = "Equipment";
		public static final String ITEMS = "Items";
		public static final String SUPPLIERS = "Suppliers";
		public static final String USERS = "Users";

	}

	public static class SelectResource{
		public static final String CUSTOMERS = "Customers";
		public static final String EQUIPMENT = "Equipment";
		public static final String ITEMS = "Items";
		public static final String SUPPLIERS = "Suppliers";


	}

	public static class LocationInstanceParams{
		public String CategoryName;
		public HashMap<String,Boolean> InstanceName; 
		public String TextFieldValue;
		public int NumberFieldValue;
		public LinkedHashMap<String, List<String>> Resources;
	}


}
