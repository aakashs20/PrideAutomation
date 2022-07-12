package com.project.testcases.IAutomateDemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import com.project.pageobjects.IAutomateDemo.IAutomateGoogleDemoPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;
import com.project.utilities.WebTable;


public class IAutomateWebtable extends TestBase {
	WebDriverWait wait;	ControlActions controlActions;
	//builds a new report using the html template 

   
	@BeforeMethod
	public void launchBrowser() throws InterruptedException {

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions = new ControlActions(driver);
		controlActions.getUrl(prop.getProperty("appl_url_web"));
	
	}

 	@Test(priority=1)
		public void webtable() throws Exception {

		List<String> actual=new ArrayList<String>();
  		actual=controlActions.getTablerecordsbyColumn("//table[@id='customers']//tr/td[1]","//table[@id='customers']//tr/td[1]","text");
	    System.out.println("list is:"+actual);
  	}
  	
  	@Test(priority=2)
		public void getrowebtable() throws Exception {

		List<WebTable> actual=new ArrayList<WebTable>();
		WebTable w1=new WebTable();
		w1.setColumnxpath("//table[@id='customers']//tr/td[1]");
		w1.setAction("text");
		actual.add(w1);
		WebTable w2=new WebTable();
		w2.setColumnxpath("//table[@id='customers']//tr/td[2]");
		w2.setAction("text");
		actual.add(w2);
		WebTable w3=new WebTable();
		w3.setColumnxpath("//table[@id='customers']//tr/td[3]");
		w3.setAction("text");
		actual.add(w3);
		ArrayList <HashMap<Integer,String> > coldata=new ArrayList<HashMap<Integer,String>>();
		HashMap<Integer,String> row=controlActions.getTablerecordsbyRow("//table[@id='customers']//td[1]",actual);

  
  
        for (Map.Entry<Integer,String> entry : row.entrySet())
        {  System.out.println("Row = " + entry.getKey() +
                             ", Value = " + entry.getValue());
    
	}
  	}
	@AfterMethod
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

  
}
