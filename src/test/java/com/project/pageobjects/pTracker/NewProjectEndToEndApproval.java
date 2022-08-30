package com.project.pageobjects.pTracker;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

public class NewProjectEndToEndApproval extends TestBase{
	
	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	Operations op ;
	public String uName = "abc";
	public String uPassword = "xyz";
	String projectName;
	String initialProjectState;
	String expectedProjectState;
	String testDataExcelFileName;
	String sheetName = "Automation";
	WebDriver driver;
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
			threadsleep(1000);	
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

	public  void approveRejectByManagerDH(String projectName, String initialProjectState, String approveOrReject) throws Exception {
		threadsleep(5000);
		WebElement newProjectsTab= driver.findElement(By.xpath("//span[text()='New Projects']/parent::a"));
		newProjectsTab.click();
		Thread.sleep(2000);
		JavascriptExecutor executor = (JavascriptExecutor)driver; 
		WebElement selectProjectState = driver.findElement(By.xpath("//h3[text()='"+initialProjectState+"']"));
		executor.executeScript("arguments[0].click();", selectProjectState); 
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='P30_NP_FC_SEARCH']")).sendKeys(projectName.trim()); 
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='B604615927023884937']")).click();
		Thread.sleep(2000);  //*[@id="report_table_New-Project-Request"]/tbody/tr[1]/td[1]
		WebElement selectedProject = driver.findElement(By.xpath("//tr[1]/td/span[contains(@title,'" + projectName +"')]/parent::td/preceding-sibling::td/a")); 
		WebElement ProjectName = driver.findElement(By.xpath("//tr[1]/td/span[contains(@title,'" + projectName +"')]/ancestor::td/span"));
		projectName = ProjectName.getText();
		logInfo("ProjectName is : "+ projectName);
		selectedProject.click();
		threadsleep(2000);
		WebElement approveRejectButton=driver.findElement(By.xpath("//*[@id='"+approveOrReject+"']"));
		op=new Operations(driver); 
		op.moveToElementAction(approveRejectButton);
		threadsleep(2000);
		executor.executeScript("arguments[0].click();",approveRejectButton); Thread.sleep(2000);
		//WebElement remarks=driver.findElement(By.xpath("//*[@id=\"P9_PROJECT_MODE\"]"));
		//*[@id=\"P10_STATUS_UPDATE\"] //remarks.sendKeys(Keys.TAB); //remarks.clear();
		//remarks.click(); //remarks.sendKeys("Rejecting the Project");
		//WebElement updateCommentsButton=driver.findElement(By.xpath("//*[@id=\"B432394606896208783\"]"));
		//executor.executeScript("arguments[0].click();", updateCommentsButton);
		//Thread.sleep(2000);
		if(approveOrReject.equalsIgnoreCase("REJECT"))
		{
			WebElement closeRemarks=driver.findElement(By.xpath("//button[@title='Close']"));
			closeRemarks.click(); 
		}
		threadsleep(9000);
	}

	public void assertApproveRejectByManager(String projectName, String expectedProjectState) throws Exception{
		WebElement newProjectsTab= driver.findElement(By.xpath("//span[text()='New Projects']/parent::a"));
		logInfo("ProjectName is : "+ projectName);
		Thread.sleep(10000);
		newProjectsTab.click();
		threadsleep(2000);		
		List<WebElement> preSelectedFIlter=driver.findElements(By.xpath("//span[@class='a-Icon icon-multi-remove']"));//quick fix to remove default Pending with HR filter
		threadsleep(2000);
		//quick fix to remove default Pending with HR filter
		if(preSelectedFIlter.size()>0 && preSelectedFIlter.get(0).isDisplayed()) {
			driver.findElements(By.xpath("//span[text()='My Request']/parent::li/button")).get(0).click();
		}
		driver.findElement(By.xpath("//*[@id='P30_NP_FC_SEARCH']")).sendKeys(projectName.trim());
		threadsleep(2000);
		driver.findElement(By.xpath("//*[@id='B604615927023884937']")).click();
		threadsleep(2000); 
		WebElement selectedProject=driver.findElement(By.xpath("//span[contains(@title,'"+projectName+"')]/parent::td/parent::tr/td[11]/span[2]"));
		String  actualProjectState = selectedProject.getText();
		logInfo("ActualProjectState is : "+actualProjectState);
		logInfo("ExpectedProjectState is : "+expectedProjectState);
		if(actualProjectState.trim().equalsIgnoreCase(expectedProjectState.trim())){ 
			logInfo("Expected and Actual Project State matched. Both have same value as : " +actualProjectState); 
		}
		else{
			logError("Expected and Actual Project State not matched"); 
		}
		Assert.assertEquals(actualProjectState, expectedProjectState,"Expected and Actual Project State not matched.ExpectedProjectState is :  "+expectedProjectState +" and actualProjectState is : " + actualProjectState);
	}
	
	public void closeBrowser() throws InterruptedException 
	{
		driver.close();
	}
}