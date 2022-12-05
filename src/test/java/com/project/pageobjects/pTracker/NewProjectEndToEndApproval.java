package com.project.pageobjects.pTracker;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

import groovyjarjarantlr4.v4.parse.ANTLRParser.option_return;

public class NewProjectEndToEndApproval extends TestBase{

	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	PL_ActiveProjectsPage activeProject;
	ControlActions controlActions;
	Operations op ;
	public String uName = "admin";
	public String uPassword = "admin";
	String projectName;
	String initialProjectState;
	String expectedProjectState;
	String testDataExcelFileName;
	String sheetName = "Automation";
	WebDriver driver;
	Random random ;
	// Prepare the path of excel file
	String workspace = System.getProperty("user.dir");
	//private HashMap<String, String> rowData;

	public boolean groupInit() 
	{
		try 
		{
			driver = launchbrowser();
			controlActions = new ControlActions(driver);
			controlActions.getUrl(prop.getProperty("appl_url_dev"));
			loginPage =  new PTrackerLoginPage(driver);
			newProject = new NewProjectsPage(driver);
			activeProject = new PL_ActiveProjectsPage(driver);
			random = new Random();
			loginPage.waitForPageLoaded(driver);
			return loginPage.login(driver,uName, uPassword);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}

	public void changeUser(String approver)
	{
		try 
		{
			threadsleep(2000);	
			loginPage.userChange(driver, approver);
		}
		catch (Exception e) 
		{
			logError(e.toString());
		}
	}

	public boolean openPride()
	{
		try {
			threadsleep(2000);
			boolean ispTrackPageOpend = loginPage.goToPTrack();
			IsTrue(ispTrackPageOpend, "P-Tracker Home page Successfully opend", "Failed to open P-Tracker Home page");
			return ispTrackPageOpend;
		}
		catch (Exception e) {
			logError(e.toString());
			return false;
		}
	}

	public boolean openPride(String role)
	{
		try {
			threadsleep(2000);
			loginPage.goToPTrack(role);
			Boolean isRoleSelected =loginPage.searchPTrackProjects(driver, role.trim());
			IsTrue(isRoleSelected, "P-Tracker Home page Successfully opend", "Failed to open P-Tracker Home page");
			return isRoleSelected;
		}
		catch (Exception e) {
			logError(e.toString());
			return false;
		}
	}


		/*
		 * public HashMap<String, String> changeUser(String datapoolPath,String
		 * sheetName,int headerRow, int tcRow ) { try { //first row is 0 while reading
		 * excel,so 1 is subtracted tcRow=tcRow-1; //header row just above testcase data
		 * headerRow=tcRow-1; rowData = ExcelUtils.getTestDataXls (datapoolPath,
		 * sheetName, headerRow, tcRow); String approver =
		 * rowData.get("ApprovingManager");
		 * logInfo("Employee Select from Change User Screen: "+approver);
		 * loginPage.changeUser(driver, approver); logInfo("Changed User"); } catch
		 * (Exception e) { logError(e.toString()); } return rowData; }
		 */
		public void approveRejectByManagerDH(String projectName, String initialProjectState, String approveOrReject,String approverRole) throws Exception{
				op=new Operations(driver); 
				threadsleep(5000);
				//WebElement newProjectsTab= driver.findElement(By.xpath("//span[text()='New Projects']/parent::a"));
				op.waitForAnElementToBeClickable(newProject.NewProjectLink);
				newProject.NewProjectLink.click();
				threadsleep(2000);
				JavascriptExecutor executor = (JavascriptExecutor)driver; 
				WebElement selectProjectState = driver.findElement(By.xpath("//h3[text()='"+initialProjectState+"']"));
				executor.executeScript("arguments[0].click();", selectProjectState); 
				threadsleep(2000);
				newProject.SearchProjectTxt.sendKeys(projectName.trim());
				threadsleep(2000);
				newProject.GoBtn.click();
				threadsleep(2000);  //*[@id="report_table_New-Project-Request"]/tbody/tr[1]/td[1]
				if(driver.findElement(By.xpath("//tr[1]/td/span[contains(@title,'" + projectName +"')]/parent::td/preceding-sibling::td/a"))==null)
				{
					logInfo(projectName + " is not found in Search Result");
					 
				}
				WebElement selectedProject = driver.findElement(By.xpath("//tr[1]/td/span[contains(@title,'" + projectName +"')]/parent::td/preceding-sibling::td/a"));
				WebElement ProjectName = driver.findElement(By.xpath("//tr[1]/td/span[contains(@title,'" + projectName +"')]/ancestor::td/span"));
				projectName = ProjectName.getText();
				logInfo("ProjectName is : "+ projectName);
				selectedProject.click();
				threadsleep(2000);
		
				if(approverRole.equalsIgnoreCase("PMO") && approveOrReject.equalsIgnoreCase("APPROVE")) {
					op.perform_scrollToElement_ByElement(newProject.parentProjectTextBox);
					newProject.parentProjectTextBox.sendKeys("TEST0001");
					String projectnum ="TEST" + String.format("%04d", random.nextInt(10000));; 
					newProject.projectNumber.sendKeys(projectnum);
				}
		
				WebElement approveRejectButton=driver.findElement(By.xpath("//*[@id='"+approveOrReject.toLowerCase()+"' or @id='"+approveOrReject+"' ]"));
				op.moveToElementAction(approveRejectButton);
				threadsleep(2000);
				executor.executeScript("arguments[0].click();",approveRejectButton);
				threadsleep(2000);
				if(approveOrReject.equalsIgnoreCase("REJECT"))
				{
					WebElement remarksModalFrame = driver.findElement(By.xpath("//iframe[@title='Remark']"));
					driver.switchTo().frame(remarksModalFrame);
					WebElement remarksTextBox = driver.findElement(By.xpath("//textarea[@name='P10_STATUS_UPDATE']"));
					remarksTextBox.click();
					remarksTextBox.sendKeys("Automation Test - Project Reject");
					WebElement addUpdateButton = driver.findElement(By.xpath("//button[contains(text(),'Update')]"));
					addUpdateButton.click();		
					driver.switchTo().defaultContent();
				}
				threadsleep(9000);
				if(approverRole.equalsIgnoreCase("FR") && approveOrReject.equalsIgnoreCase("APPROVE")) {
					WebElement yesButtononAlert=driver.findElement(By.xpath("//button[text() ='Yes']"));
					yesButtononAlert.click();
				}
	   }

	public boolean assertApproveRejectByManager(String projectName, String expectedProjectState ,String approverRole) throws Exception{
		//WebElement newProjectsTab= driver.findElement(By.xpath("//span[text()='New Projects']/parent::a"));
		logInfo("ProjectName is : "+ projectName);
		threadsleep(2000);	

		if(approverRole.equalsIgnoreCase("FR") && expectedProjectState.equalsIgnoreCase("ACTIVE")) {
			logInfo("As " + approverRole + " asserting for Active Project");
			newProject.ActiveProjectTabLink.click();
			threadsleep(2000);		
			List<WebElement> preSelectedFIlter=driver.findElements(By.xpath("//span[@class='a-Icon icon-multi-remove']"));//quick fix to remove default Pending with HR filter
			threadsleep(2000);
			//quick fix to remove default Pending with HR filter
			if(preSelectedFIlter.size()>0 && preSelectedFIlter.get(0).isDisplayed()) {
				driver.findElements(By.xpath("//span[text()='My Request']/parent::li/button")).get(0).click();
			}
			logInfo("Searching for the Project: "+ projectName);
			activeProject.SearchProjectTxt.sendKeys(projectName.trim());
			threadsleep(2000);
			activeProject.GoBtn.click();

		}else {
			logInfo("As " + approverRole + " asserting for Active Project");
			newProject.NewProjectLink.click();
			threadsleep(2000);		
			List<WebElement> preSelectedFIlter=driver.findElements(By.xpath("//span[@class='a-Icon icon-multi-remove']"));//quick fix to remove default Pending with HR filter
			threadsleep(2000);
			//quick fix to remove default Pending with HR filter
			if(preSelectedFIlter.size()>0 && preSelectedFIlter.get(0).isDisplayed()) {
				driver.findElements(By.xpath("//span[text()='My Request']/parent::li/button")).get(0).click();
			}
			logInfo("Searching for the Project: "+ projectName);
			newProject.SearchProjectTxt.sendKeys(projectName.trim());
			threadsleep(1000);
			newProject.GoBtn.click();	
		}
		threadsleep(1000); 
		WebElement selectedProject=driver.findElement(By.xpath("//span[contains(@title,'"+projectName+"')]/parent::td/parent::tr//span[2]"));
		String  actualProjectState = selectedProject.getText();
		logInfo("ActualProjectState is : "+actualProjectState);
		logInfo("ExpectedProjectState is : "+expectedProjectState);
		boolean isProjectStatusMatching = actualProjectState.trim().equalsIgnoreCase(expectedProjectState.trim());
		if(isProjectStatusMatching){ 
			//logInfo("Expected and Actual Project State matched. Both have same value as : " +actualProjectState); 
			logInfo("Expected and Actual Project State are matched. ExpectedProjectState is :  "+expectedProjectState +" and actualProjectState is : \" + actualProjectState");
		}
		else{
			//logError("Expected and Actual Project State not matched"); 
			logInfo("Expected and Actual Project State not matched. ExpectedProjectState is :  "+expectedProjectState +" and actualProjectState is : " + actualProjectState);
		}
		//boolean isProjectStatusMatching = actualProjectState.equalsIgnoreCase(expectedProjectState);
		//IsTrue(isProjectStatusMatching,"Expected and Actual Project State are matched.ExpectedProjectState is :  "+expectedProjectState +" and actualProjectState is : \" + actualProjectState","Expected and Actual Project State not matched.ExpectedProjectState is :  "+expectedProjectState +" and actualProjectState is : " + actualProjectState);
		return isProjectStatusMatching;
	}

	public void clickOnHomeButton()
	{
		try {
			threadsleep(2000);
			loginPage.homeNavBtn.click();
		}
		catch (Exception e) {
			logError(e.toString());
		}
	}
     
	public void waitForHomeNavBtnToBeClickable()
	{
		op.waitForAnElementToBeClickable(loginPage.homeNavBtn);
	}

	public void closeBrowser() throws InterruptedException 
	{
		driver.close();
	}
}