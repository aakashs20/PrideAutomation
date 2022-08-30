package com.project.pageobjects.pTracker;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;
import org.testng.Assert;


public class CommonPages extends TestBase {
	
	WebDriverWait wait;
	ControlActions controlActions;
	Operations op;
	private static final int DELAY = 2000;
	
	public CommonPages(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, DELAY);
		controlActions = new ControlActions(driver);
		op=new Operations(driver);
		driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);
	}
	
	// ACTIVE PROJECT TAB OBJECTS 
	@FindBy(xpath = CommonPagesConstants.ACTIVE_PROJECT_SEARCH_TXT)
	public WebElement ActiveProjectSearchTxt;
	
	@FindBy(xpath = CommonPagesConstants.ACTIVE_PROJECT_GO_BTN)
	public WebElement ActiveProjectGoBtn;
	
	@FindBy(xpath = CommonPagesConstants.ACTIVE_PROJECT_FILTER_BTN)
	public WebElement ActiveProjectFilterBtn;
	
	@FindBy(xpath = CommonPagesConstants.ACTIVE_PROJECT_DOWNLOAD_BTN)
	public WebElement ActiveProjectDownloadBtn;
	
	@FindBy(xpath = CommonPagesConstants.ACTIVE_PROJECT_NEXTSET_BTN)
	public WebElement ActiveProjectNextsetBtn;
	
	@FindBy(xpath = CommonPagesConstants.MANAGER_DETAILS_TAB)
	public WebElement ManagerDetailsTab;
	
	@FindBy(xpath = CommonPagesConstants.ADD_SUB_MANAGER_BTN)
	public WebElement AddSubManagerBtn;
	
	@FindBy(xpath = CommonPagesConstants.SUB_MANAGER_TYPE)
	public WebElement SubManagerType;
	
	//Ganesh//
	
	@FindBy(xpath = CommonPagesConstants.SELECT_SUB_MANAGER)
	public WebElement SelectSubManager;
	
	@FindBy(xpath = CommonPagesConstants.SELECT_SUB_MANAGER_SDATE)
	public WebElement SelectSubManagerSDate;
	
	@FindBy(xpath = CommonPagesConstants.SUB_MANAGER_ADD)
	public WebElement SubManagerAdd;
	
	@FindBy(xpath = CommonPagesConstants.SUB_MANAGER_NAME_SEARCH)
	public WebElement SubManagerNameSearch;
	
	@FindBy(xpath = CommonPagesConstants.SUB_MANAGER_SELECT)
	public WebElement SubManagerSelect;
	
	@FindBy(xpath = CommonPagesConstants.VERF_SUB_MANAGER_NAME)
	public WebElement VerifySubManagerName;
	
	@FindBy(xpath = CommonPagesConstants.VERF_SUB_MANAGER_ROLE)
	public WebElement VerifySubManagerRole;
	
	@FindBy(xpath = CommonPagesConstants.VERF_SUB_MANAGER_DATE)
	public WebElement VerifySubManagerDate;
	
	@FindBy(xpath = CommonPagesConstants.SPINEER)
	public List <WebElement> spinner;
	
	@FindBy(xpath = CommonPagesConstants.SUB_MANAGER_CANCEL)
	public WebElement cancelSubManager;
	
	@FindBy(xpath = CommonPagesConstants.VERF_PROJECT_MANAGER_ROLE)
	public WebElement VerifyprojectManagerRole;
	
	@FindBy(xpath = CommonPagesConstants.PAYMENT_MILESTONE_DETAILS_TAB)
	public WebElement paymentMilestoneDetailsTab;
	
	@FindBy(xpath = CommonPagesConstants.ADD_PAYMENT_MILESTONE_BTN)
	public WebElement addPaymentMilestone;
	
	@FindBy(xpath = CommonPagesConstants.MILESTONE_NAME)
	public WebElement paymentMilestoneName;
	
	@FindBy(xpath = CommonPagesConstants.MILESTONE_START_DATE)
	public WebElement paymentMilestoneStartDate;
	
	@FindBy(xpath = CommonPagesConstants.MILESTONE_END_DATE)
	public WebElement paymentMilestoneEndtDate;
	
	@FindBy(xpath = CommonPagesConstants.MILESTONE_AMOUNT)
	public WebElement paymentMilestoneAmount;
	
	@FindBy(xpath = CommonPagesConstants.MILESTONE_ADDBT)
	public WebElement paymentMilestoneAddBtn;
	
	@FindBy(xpath = CommonPagesConstants.MILESTONE_NAME_VERIFY)
	public WebElement paymentMilestoneNameVerify;
	
	@FindBy(xpath = CommonPagesConstants.MILESTONE_STARTDATE_VERIFY)
	public WebElement paymentMilestoneStartDateVerify;
	
	@FindBy(xpath = CommonPagesConstants.MILESTONE_ENDDATE_VERIFY)
	public WebElement paymentMilestoneEndDateVerify;
	
	@FindBy(xpath = CommonPagesConstants.MILESTONE_AMOUNT_VERIFY)
	public WebElement paymentMilestoneAmmountVerify;

	@FindBy(xpath = CommonPagesConstants.MILESTONE_MIDIFY_CLICK)
	public WebElement paymentMilestoneModifyClick;

	@FindBy(xpath = CommonPagesConstants.MILESTONE_MIDIFY_SAVE)
	public WebElement paymentMilestoneModifySave;
	//Ganesh2//
	
	//Nikita//
	
		@FindBy(xpath = CommonPagesConstants.RELEASE_RESOURCE_REQUEST_TAB)
		public WebElement ReleaseResourceRequesTab;
		
		@FindBy(xpath = CommonPagesConstants.SELECT_RELEASE_RESOURCE_EDATE)
		public WebElement SelectReleaseResourceEdate;
		
		@FindBy(xpath = CommonPagesConstants.SELECT_ADD_EMPLYOEE)
		public WebElement SelectAddEmplyoee;
		
		@FindBy(xpath = CommonPagesConstants.ADD_EMPLYOEE_NAME_SEARCH)
		public WebElement AddEmplyoeeNameSearch;
		
		@FindBy(xpath = CommonPagesConstants.ADD_EMPLYOEE_SELECT)
		public WebElement AddEmplyoeeSelect;
		
		@FindBy(xpath = CommonPagesConstants.REALLOCATE_RESOURCE_SUBMIT_BTN)
		public WebElement ReallocateResourceSubmitBtn;
		
		@FindBy(xpath = CommonPagesConstants.REALLOCATE_ROLE_TYPE)
		public WebElement ReallocateRoleType;
		
		@FindBy(xpath = CommonPagesConstants.VERIFY_REALLOCATION)
		public WebElement VerifyReallocation;
		
		@FindBy(xpath = CommonPagesConstants.END_DATE_PICKER)
		public WebElement EndDatePicker;
		
	//Nikita//
	
	public String[][] Search(WebDriver driver, String tableXpath, String projectName)
	{
		String[][] arrTabledata ;
		ActiveProjectSearchTxt.clear();
		ActiveProjectSearchTxt.sendKeys("");
		ActiveProjectSearchTxt.sendKeys(projectName);
		ActiveProjectGoBtn.click();
		threadsleep(1000);        
		arrTabledata = getWebTableData(driver, tableXpath);
		return arrTabledata;
	}
	
	/**
	 * This method returns all the data from webtable in 2d array
	 * Considering table is created with tr and td tags
	 * Can change the row and cell tags for other table tags i.e. mat-row & mat-cell
	 *
	 * @param driver  WebDriver driver
	 * @param tableXpath WebTable xpath where data needs to be fetched from
	 * @return Returns 2d array with table data
	 */
	public String[][] getWebTableData(WebDriver driver, String tableXpath) {
		String tempCellText;
		WebElement table = driver.findElement(By.xpath(tableXpath));
		List <WebElement> rowsList = table.findElements(By.tagName("tr"));
		int numRows = rowsList.size();
		int numCols = rowsList.get(0).findElements(By.tagName("td")).size();

		 logInfo("Total number of rows= " + numRows);
		 logInfo("Total number of cols=" + numCols);

		String[][] arrTabledata = new String[numRows][numCols];
		List < WebElement > columnsList = null;

		for (int i = 0; i < numRows; i++) {
			System.out.println();
			columnsList = rowsList.get(i).findElements(By.tagName("td"));
			for (int j = 0; j < numCols; j++) {
				 logInfo(columnsList.get(j).getText().toString() + ",");
				tempCellText = columnsList.get(j).getText();
				arrTabledata[i][j] = tempCellText.toString();
			}
			 logInfo("Next Row");
		}
		return arrTabledata;
	}
	
	/**
	 * Wait for assync content in a determined period
	 * //from   www.j  a v a 2 s .c o  m
	 * @param driver Selenium web driver.
	 * @param by Selenium By expression.
	 * @param timeout Selenium time out.
	 * @return a WebElement asynchronously loaded.
	 * @throws NoSuchElementException
	 */
	public WebElement waitForAssyncContent(WebDriver driver, By by, Long timeout) throws NoSuchElementException {
	    long end = System.currentTimeMillis() + (timeout);
	    WebElement renderedWebElement = null;

	    while (System.currentTimeMillis() < end) {
	        try {
	            renderedWebElement = driver.findElement(by);
	        } catch (NoSuchElementException nsee) {
	        	logError("ERROR: "+ nsee.getMessage());
	        }

	        if (renderedWebElement != null && renderedWebElement.isEnabled() && renderedWebElement.isDisplayed()) {
	            return renderedWebElement;
	        }

	        try {
	            Thread.sleep(1000);
	        } catch (InterruptedException ie) {
	        	 logError("ERROR: "+ ie.getMessage());
	        }
	    }

	    if (renderedWebElement == null) {
	        throw new NoSuchElementException("Could not locate assync content");
	    }

	    try {
	        if (renderedWebElement.isDisplayed()) {
	            throw new NoSuchElementException("Element is not being displayed");
	        }
	    } catch (Throwable t) {
	    	 logError("ERROR: "+ t.getMessage());
	    }

	    return renderedWebElement;
	}
	
//Ganesh//
	
	public boolean addSubManager(WebDriver driver, String employeeName, int tcRowNum) throws Exception {
		try {
			
			op.switchToiFrameByXpath(driver,CommonPagesConstants.IFRAME);
			wait.until(ExpectedConditions.visibilityOf(SubManagerType));
			Boolean AddSubManagerTit = SubManagerType.isDisplayed();
			IsTrue(AddSubManagerTit, "Frame swiched to Add Sub Manager", "Frame not swiched to Add Sub Manager");
			threadsleep(1000);
			SelectSubManager.sendKeys(employeeName);
			op.switchToDefaultContent(driver);
			SubManagerSelect.click();
			op.switchToiFrameByXpath(driver,CommonPagesConstants.IFRAME);
			wait.until(ExpectedConditions.visibilityOf(SubManagerType));
			SelectSubManagerSDate.click();
			CommonPages cp1 = new CommonPages(driver);
			cp1.addStartDate(driver,tcRowNum);
			wait.until(ExpectedConditions.visibilityOf(SubManagerAdd));
			SubManagerAdd.click();
			waitTillSpinnerDisable();
			logInfo("SubManager added");
			return true;
		} catch (NoSuchElementException nsee) {
			logError("ERROR:Submanager Not added ");
			return false;
		}

	}

	public boolean cancelSubManager(WebDriver driver, String employeeName,int tcRowNum) throws Exception {

		try {
			op.switchToiFrameByXpath(driver,CommonPagesConstants.IFRAME);
			wait.until(ExpectedConditions.visibilityOf(SubManagerType));
			Boolean AddSubManagerTit = SubManagerType.isDisplayed();
			Assert.assertTrue(AddSubManagerTit, "Frame not swiched to Add Sub Manager");
			IsTrue(AddSubManagerTit, "Frame swiched to Add Sub Manager", "Frame not swiched to Add Sub Manager");
			threadsleep(1000);
			SelectSubManager.sendKeys(employeeName);
			op.switchToDefaultContent(driver);
			SubManagerSelect.click();
			op.switchToiFrameByXpath(driver,CommonPagesConstants.IFRAME);
			wait.until(ExpectedConditions.visibilityOf(SubManagerType));
			SelectSubManagerSDate.click();
			CommonPages cp1 = new CommonPages(driver);
			cp1.addStartDate(driver,tcRowNum);
			wait.until(ExpectedConditions.visibilityOf(SubManagerAdd));
			cancelSubManager.click();
			logInfo("SubManager Cancel");
			return true;

		} catch (NoSuchElementException nsee) {
			logError("ERROR:Submanager Not canceled ");
			return false;
		}

	}

	private void addStartDate(WebDriver driver, int tcRowNum) throws Exception {
		
		String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace + "\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
		String sheetName = "Automation";
		int header = 0; // Excel first row is 0
		int tc = tcRowNum-1;
		HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tc);
		Operations op = new Operations(driver);
		op.selectDropdown(driver, "//*[@class='ui-datepicker-month']", "Aug");
		op.selectDropdown(driver, "//*[@class='ui-datepicker-year']", "2022");
		op.selectFromList(driver, "//*[@id='ui-datepicker-div']/table/tbody/tr/td/a", rowData.get("StartDate"),"Start Day");
 	}

	public String subMangerRoleVerify() {
		String subMangervf = VerifySubManagerRole.getText();
		return subMangervf; 
	}

	public String projectManagerRoleVerify() {
		String subMangervf = VerifyprojectManagerRole.getText();
		return subMangervf;
	}

	public String subMangerNameVerify() {
		String subMangerNamevf = VerifySubManagerName.getText();
		return subMangerNamevf;
	}

	public String subMangerDateVerify() {
		String subMangerDatevf = VerifySubManagerDate.getText();
		return subMangerDatevf;
	}
	
	public boolean subMangerRoleDisplay() {
		boolean subMangerDisplay = VerifySubManagerRole.isDisplayed();
		return subMangerDisplay;
	}

	public boolean projectManagerRoleDisplay() {
		boolean subMangerDisplay = VerifyprojectManagerRole.isDisplayed();
		return subMangerDisplay;
	}

	public boolean subMangerNameDisplay() {
		boolean subMangerNameDisplay = VerifySubManagerName.isDisplayed();
		return subMangerNameDisplay;
	}

	public boolean subMangerDateDisplay() {
		boolean subMangerDateDisplay = VerifySubManagerDate.isDisplayed();
		return subMangerDateDisplay;
	}

	public void waitTillSpinnerDisable() throws InterruptedException {
		int count = 0;
		while (spinner.size() != 0 && count < 90) {
			Thread.sleep(5000);
			count++;
		}

	}

	public void waitSwitch() {
		logInfo("SubManager waitswitch start");
		wait.until(ExpectedConditions.visibilityOf(VerifySubManagerRole));

	}

	//Ganesh2//
	public boolean addPaymentMilestone(WebDriver driver, String milestoneName, String amount, int tcRowNum) throws Exception {
		try {
			op.switchToiFrameByXpath(driver,CommonPagesConstants.IFRAME);
			paymentMilestoneName.sendKeys(milestoneName);
			paymentMilestoneStartDate.click();
			addStartDate(driver,tcRowNum );
			paymentMilestoneEndtDate.click();
			addEndtDate(driver,tcRowNum);
			paymentMilestoneAmount.sendKeys(amount);
			paymentMilestoneAddBtn.click();
			threadsleep(4000);
			op.switchToDefaultContent(driver);
			threadsleep(4000);
			wait.until(ExpectedConditions.visibilityOf(paymentMilestoneNameVerify));
			logInfo("payment Milestone added successfully");
			return true;
		} catch (NoSuchElementException nsee) {
			logError("payment Milestone not added.");
			return false;
		}
	}
		
		    public boolean modifyPaymentMilestone(WebDriver driver, String amountModify, int tcRowNum) throws Exception {
				try {
					paymentMilestoneModifyClick.click();
					threadsleep(4000);
					op.switchToiFrameByXpath(driver,CommonPagesConstants.IFRAME);
					paymentMilestoneStartDate.click();
					addStartDate(driver,tcRowNum );
					threadsleep(4000);
					paymentMilestoneEndtDate.click();
					addEndtDate(driver,tcRowNum);
					paymentMilestoneAmount.clear();
					paymentMilestoneAmount.sendKeys(amountModify);
					paymentMilestoneModifySave.click();
					threadsleep(4000);
					op.switchToDefaultContent(driver);
					threadsleep(4000);
					wait.until(ExpectedConditions.visibilityOf(paymentMilestoneNameVerify));
					logInfo("payment Milestone saved successfully");
					return true;
				} catch (NoSuchElementException nsee) {
					logError("payment Milestone not saved.");
					return false;
				}
			}
	    
		
		private void addEndtDate(WebDriver driver, int tcRowNum) throws Exception {
			
			String workspace = System.getProperty("user.dir");
			String datapoolPath = workspace + "\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
			String sheetName = "Automation";
			int header = 0; // Excel first row is 0
			int tc = tcRowNum-1;
			HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tc);
			Operations op = new Operations(driver);
			op.selectDropdown(driver, "//*[@class='ui-datepicker-month']", "Aug");
			op.selectDropdown(driver, "//*[@class='ui-datepicker-year']", "2022");
			op.selectFromList(driver, "//*[@id='ui-datepicker-div']/table/tbody/tr/td/a", rowData.get("EndDate"),"End Day");
	 	}
		
		
		public String paymentMilestoneNameVerify() {

			String paymentMilestoneNamevf = paymentMilestoneNameVerify.getText();
			return paymentMilestoneNamevf;
		}
		public boolean paymentMilestoneNameDisplay() {
			
			boolean paymentMilestoneNameDisplay = paymentMilestoneNameVerify.isDisplayed();
			return paymentMilestoneNameDisplay;
		}
		
		public String paymentMilestoneStartDateVerify() {
			String paymentMilestoneStartDatevf = paymentMilestoneStartDateVerify.getText();
			return paymentMilestoneStartDatevf;
		}
		public boolean paymentMilestoneStartDateDisplay() {
			
			boolean paymentMilestoneStartDateDisplay = paymentMilestoneStartDateVerify.isDisplayed();
			return paymentMilestoneStartDateDisplay;
		}
		public String paymentMilestoneEndDateVerify() {
			String paymentMilestoneEndDatevf = paymentMilestoneEndDateVerify.getText();
			return paymentMilestoneEndDatevf;
		}
		public boolean paymentMilestoneEndDateDisplay() {
			
			boolean paymentMilestoneEndDateDisplay = paymentMilestoneEndDateVerify.isDisplayed();
			return paymentMilestoneEndDateDisplay;
		}
		
		public String paymentMilestoneAmountVerify() {
			String paymentMilestoneAmountvf = paymentMilestoneAmmountVerify.getText();
			return paymentMilestoneAmountvf;
		}
		
		public boolean paymentMilestoneAmountDisplay() {
			
			boolean paymentMilestoneAmountDisplay = paymentMilestoneAmmountVerify.isDisplayed();
			return paymentMilestoneAmountDisplay;
		}
		

		public  boolean ReallocateResource(WebDriver driver, String endDate, String emplyoeeName) throws Exception 
		{
			 
			    ReleaseResourceRequesTab.click();
			    driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='apex_dialog_1']/iframe"))); 
			    
			    SelectReleaseResourceEdate.click();
//			    CommonPages cp1 = new CommonPages(driver);
//				cp1.addEndDate(driver);
				driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[2]/td[3]")).click();
				threadsleep(15000);
				AddEmplyoeeNameSearch.sendKeys("");
				AddEmplyoeeNameSearch.sendKeys(emplyoeeName);
				driver.switchTo().defaultContent();
				threadsleep(15000);
				AddEmplyoeeSelect.click();
				threadsleep(1500);
				driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='apex_dialog_1']/iframe")));
				wait.until(ExpectedConditions.visibilityOf(SelectReleaseResourceEdate));
				ReallocateResourceSubmitBtn.click();		
				driver.switchTo().defaultContent();
				boolean resourceName = VerifyReallocation.isDisplayed();
				return resourceName;
		}



	
	

}
