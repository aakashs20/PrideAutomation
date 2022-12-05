package com.project.testcases.IAutomateDemo;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.project.pageobjects.IAutomateDemo.IAutomateGoogleDemoPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

import com.project.utilities.ExcelReader;

public class IAutomateGoogleDemoTest extends TestBase {
	WebDriverWait wait;
	IAutomateGoogleDemoPage iAutomateDemoPage;
	ControlActions controlActions;

	@BeforeMethod
	public void launchBrowser() throws InterruptedException {

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions = new ControlActions(driver);
		controlActions.getUrl(prop.getProperty("appl_url_qa"));
		iAutomateDemoPage = new IAutomateGoogleDemoPage(driver);
	}

	@Test(dataProvider="dataexcel")
	public void TestCase_Search(String searchword,String expectedTitle) throws Exception {

		boolean setSearchText = iAutomateDemoPage.enterTextToGoogleSearch(searchword);
		//TestValidation.IsTrue(boolean,"Pas", "Fail");
		Assert.assertTrue(setSearchText, "SUCCESSFULLY added text to Google search as '"+searchword+"'");

		boolean clickLink = iAutomateDemoPage.clickTestNGLinkOnGoogle();
		Assert.assertTrue(clickLink, "SUCCESSFULLY clicked on "+searchword+" link/url");
		
		String title=iAutomateDemoPage.TitleOfPage();
		Assert.assertEquals(title,expectedTitle);
	}
	
	
	@DataProvider
	public Object[][] dataexcel() throws IOException
	{
		Object[][] exceldata = null;
		try
		{
		ExcelReader e=new ExcelReader();
		String excel=prop.getProperty("excel_file_path");
		System.out.println(excel);
	    exceldata=e.readDataFromExcel(System.getProperty("user.dir")+File.separator+"test-data-files"+File.separator+"UI-TestData"+File.separator+excel,"Sheet1");
	    //exceldata=e.readDataFromExcel(excel,"Sheet1");
		return exceldata;
		}
		catch (Exception e)
		{
			log4jError("Error while reading from excel check the excel path or sheet name: " + e.toString());
			return exceldata;
		}
	}

	@AfterMethod
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
