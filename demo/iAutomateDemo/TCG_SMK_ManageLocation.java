package com.project.safetychain.webapp.testcases;

import java.util.Arrays;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_SMK_ManageLocation extends TestBase{
	ControlActions controlActions;
	CommonPage mainPage;
	LoginPage lgnPge;
	ResourceDesignerPage resourceDesigner;
	ManageLocationPage locations;
	ManageResourcePage resources;

	//Data
	public static String locationCategoryValue;
	public static String equipmentCategoryValue;

	public static String locationInstanceValue;
	public static String equipmentInstanceValue;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {

		locationCategoryValue	= CommonMethods.dynamicText("Loc_Cat");
		locationInstanceValue	= CommonMethods.dynamicText("Loc_Inst");
		equipmentCategoryValue	= CommonMethods.dynamicText("Eqpmnt_Cat");
		equipmentInstanceValue	= CommonMethods.dynamicText("Eqpmnt_Inst");

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		mainPage = new CommonPage(driver);
		lgnPge = new LoginPage(driver);
		locations = new ManageLocationPage(driver);
		resources= new ManageResourcePage(driver);
		resourceDesigner = new ResourceDesignerPage(driver);

		HomePage hp = lgnPge.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		ResourceDesignerPage rdp = mainPage.clickResourceDesignerMenu();
		if(rdp.error) {
			TCGFailureMsg = "Could Not Open Resource Designer";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}	

		boolean creationOfLocationCategory = rdp.createLocationCategory(locationCategoryValue);
		if(!creationOfLocationCategory) {
			TCGFailureMsg = "Fail to create location category";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}	

		boolean creationOfSupplierCategory = resourceDesigner.createEquipmentCategory(equipmentCategoryValue);
		if(!creationOfSupplierCategory) {
			TCGFailureMsg = "Fail to create Equipment category";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		ManageResourcePage mrp = mainPage.clickResourcesMenu();

		if(mrp.error) {
			TCGFailureMsg = "Could NOT Open Manage Resource";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		boolean addNewEquipmetResource = resources.addEquipmentResourceInstance(equipmentCategoryValue);	
		boolean settingFieldValues = mrp.setFields(1234,"Automation Test", equipmentInstanceValue);
		boolean saveResource = mrp.saveResource();

		if(!addNewEquipmetResource && settingFieldValues && saveResource) {
			TCGFailureMsg = "FAILED to ADD equipment instance";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		boolean modifiedByDisplayed = resources.verifyCreatedResource();
		TestValidation.IsTrue(modifiedByDisplayed,
				"VERIFIED saved equipment instance in equipment category tree", 
				"FAILED to SAVE created equipment instance in equipment category tree");

	}

	//author - Abhishek
	@Test(description="Creation of location")
	public void TestCase_32566() {

		ManageLocationPage mlp = mainPage.clickLocationsMenu();
		TestValidation.Equals(mlp.error,false,"Opened Manage Location","Could Not Open Manage Location"); 

		boolean addNewLocation = locations.addLocationInstance(locationCategoryValue);
		TestValidation.IsTrue(addNewLocation,
				"Location instance ADDED in location category tree ", 
				"FAILED to ADD location instance in location category tree");

		boolean settingFieldValues = locations.setFields(1234,"Automation Test", locationInstanceValue);
		TestValidation.IsTrue(settingFieldValues,
				"VALUE have been set in fields", 
				"FAILED to set value in fields");

		boolean saveResource = locations.clickOnSave();
		TestValidation.IsTrue(saveResource,
				"SAVED location details", 
				"FAILED to save location details");

		boolean modifiedByDisplayed = locations.verifyCreatedLocation();
		TestValidation.IsTrue(modifiedByDisplayed,
				"VERIFIED saved location instance in location category tree", 
				"FAILED to SAVE created location instance in location category tree");

		boolean linkResource = locations.linkResource("Equipment", Arrays.asList(equipmentInstanceValue));
		TestValidation.IsTrue(linkResource,
				"LINKED resource with location", 
				"FAILED to link resource with location");

	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
