package com.project.testcases.pTracker;

import com.project.testbase.TestBase;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.utilities.ControlActions;

// Tarun 
public class TCG_ValidateFiltersForProjectActions extends TestBase {
	WebDriverWait wait;
	PTrackerLoginPage LoginPage;

	NewProjectsPage NewProject;
	Operations op;
	ExcelUtils ExcelUtils;
	ControlActions controlActions;
	private String uName = "admin";
	private String uPassword = "admin";
	private String xpath;
	private static final int DELAY = 20;
	NewProjectsPage newProject;
	XSSFWorkbook wb = null;
	XSSFSheet sh = null;
	String datapoolPath;
	int tcID;
	String tcStatus;

	@BeforeClass
	public void groupInit() throws Exception {
		driver = launchbrowser();

		driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, DELAY);
		controlActions = new ControlActions(driver);
		// wait = new WebDriverWait(driver, 20000);
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		op = new Operations(driver);
		newProject = new NewProjectsPage(driver);
		LoginPage = new PTrackerLoginPage(driver);
		NewProject = new NewProjectsPage(driver);
		ExcelUtils = new ExcelUtils();
		LoginPage.waitForPageLoaded(driver);
		LoginPage.TC_Login(driver, uName, uPassword);
		LoginPage.changeUser(driver, "Thakur, Monika");
	}

//---------------------------------------------------------------------------------------------------------------------------------------

	@Test(priority = 1, groups = { "sanity","UI" }, description = "Validate Filter 'All Actions' on Project Actions Tab")
	public void TC_ValidateProjectFiltersOnProjectActionsTab() throws Exception {

		List<String> strProActFilters = Arrays.asList("Pending Extension", "Pending Closure", "Pending New Project", "Pending Modification");
		int counter = 0;

		op.clickElement(NewProject.ProjectActionTabTxt);
		//// *[@id="actions_tab"]
		wait.until(ExpectedConditions.visibilityOf(NewProject.ProjectActionTabFocused));

		if (NewProject.ProjectActionTabFocused.isDisplayed()) {
			logInfo("Project Actions Tab is focused on P-Tracker page");
			try {
				xpath = "//*[@id='R154473969590676725_cards']/li";
				WebElement actEle1 = driver.findElement(By.xpath(xpath));

				op.clickElement(actEle1);
				threadsleep(1000);

				List<WebElement> proActions = driver.findElements(By.xpath("//*[@id='pa_top_region']/ul/li"));
				logInfo("" +proActions);

				// Get The Table
			   // WebElement HTMLTable = driver.findElement(By.xpath("//*[@id='report_table_project-actions-report']"));
				//System.out.println(HTMLTable);

				// Get The Rows
				List<WebElement> rowEle = driver.findElements(By.xpath("//*[@id='report_table_project-actions-report']/tbody/tr"));
				logInfo("Rows Count=" + rowEle.size());

				for (int i = 0; i < rowEle.size(); i++) {
					List<WebElement> colEle = driver.findElements(By.xpath("//*[@id='report_table_project-actions-report']/tbody/tr/td[@headers='STATUS_RETURN_PA']"));

					for (WebElement ele : colEle) {
						boolean contains = strProActFilters.contains(ele.getText());
						if (contains) {
							threadsleep(1000);

							JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
							// jsExecutor.executeScript("window.scrollBy(0,250)", "");
							jsExecutor.executeScript("arguments[0].style.border='2px solid yellow'", ele);
							counter = counter + 1;
						} else {
							logError("Failed to find the project name: " + ele + " :On Project Actions Tab");
						}
						if (counter == rowEle.size()) {
							logInfo("Testcase is pass");
							tcStatus = "PASS";
							ExcelUtils.logTestResult("TC_VALIDATE_FILTERS_ON_PROJECT_ACTION_TAB_FOR_ALL_ACTION", tcStatus);
						} else {
							logError("Testcase is failed");
							tcStatus = "FAIL";
							ExcelUtils.logTestResult("TC_VALIDATE_FILTERS_ON_PROJECT_ACTION_TAB_FOR_ALL_ACTION", tcStatus);
						}

					}
				}

			}

			catch (Exception e) {
				logError("Failed to Select the element " + xpath + e.getMessage());
				e.printStackTrace();
			}

		} else {
			logError("Failed to Focus Project Actions Tab on P-Tracker page");
		}
	}

//-----------------------------------------------------------------------------------------------------------------------------------------

	@Test(priority = 2, groups = { "sanity","UI" }, description = "Validate Filter 'Pending Extension' on Project Actions Tab")
	public void TC_ValidateProjectFiltersOnProjectActionsTab2() throws Exception {
		threadsleep(2000);

		String strProActFilter2 = "Pending Extension";
		int counter = 0;

		wait.until(ExpectedConditions.visibilityOf(NewProject.ProjectActionTabFocused));

		if (NewProject.ProjectActionTabFocused.isDisplayed()) {
			logInfo("Project Actions Tab is focused on P-Tracker page");
			try {
				xpath = "//*[@id='R154473969590676725_cards']/li[2]";
				WebElement actEle2 = driver.findElement(By.xpath(xpath));

				op.clickElement(actEle2);
				threadsleep(2000);

				List<WebElement> proActions = driver.findElements(By.xpath("//*[@id='pa_top_region']/ul/li"));
				System.out.println(proActions);

				// Get The Table
				//WebElement HTMLTable = driver.findElement(By.xpath("//*[@id='report_table_project-actions-report']"));
				//System.out.println(HTMLTable);

				// Get The Rows
				List<WebElement> rowEle = driver
						.findElements(By.xpath("//*[@id='report_table_project-actions-report']/tbody/tr"));
				System.out.println("Rows Count=" + rowEle.size());

				for (int i = 0; i < rowEle.size(); i++) {
					List<WebElement> colEle = driver.findElements(By.xpath(
							"//*[@id='report_table_project-actions-report']/tbody/tr/td[@headers='STATUS_RETURN_PA']"));

					for (WebElement ele : colEle) {
						boolean contains = strProActFilter2.equals(ele.getText());
						if (contains) {
							threadsleep(1000);

							JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
							// jsExecutor.executeScript("window.scrollBy(0,350)", "");
							jsExecutor.executeScript("arguments[0].style.border='2px solid yellow'", ele);
							counter += 1;
						} else {
							logError("Failed to find the project name: " + ele + " :On Project Actions Tab");
						}
						if (counter == rowEle.size()) {
							logInfo("Testcase is pass");
							tcStatus = "PASS";
							ExcelUtils.logTestResult("TC_VALIDATE_FILTERS_ON_PROJECT_ACTION_TAB_FOR_PENDING_EXTENSION", tcStatus);
						} else {
							logError("Testcase is failed");
							tcStatus = "FAIL";
							ExcelUtils.logTestResult("TC_VALIDATE_FILTERS_ON_PROJECT_ACTION_TAB_FOR_PENDING_EXTENSION", tcStatus);
						}

					}
				}
			}

			catch (Exception e) {
				logError("Failed to Select the element " + xpath + e.getMessage());
				e.printStackTrace();
			}

		} else {
			logError("Failed to Focus Project Actions Tab on P-Tracker page");
		}
	}

//-----------------------------------------------------------------------------------------------------------------------------------------

	@Test(priority = 3, groups = { "sanity","UI" }, description = "Validate Filter 'Pending Closure' on Project Actions Tab")
	public void TC_ValidateProjectFiltersOnProjectActionsTab3() throws Exception {
		threadsleep(2000);

		String strProActFilter3 = "Pending Closure";
		int counter = 0;

		// op.clickElement(NewProject.ProjectActionTabTxt);
		// wait.until(ExpectedConditions.visibilityOf(NewProject.ProjectActionTabFocused));

		if (NewProject.ProjectActionTabFocused.isDisplayed()) {
			logInfo("Project Actions Tab is focused on P-Tracker page");
			try {
				xpath = "//*[@id='R154473969590676725_cards']/li[3]";
				WebElement actEle3 = driver.findElement(By.xpath(xpath));
				logInfo(" ------------------------------------------ ");

				op.clickElement(actEle3);
				threadsleep(2000);

				List<WebElement> proActions = driver.findElements(By.xpath("//*[@id='pa_top_region']/ul/li"));
				System.out.println(proActions);

				// Get The Table
				//WebElement HTMLTable = driver.findElement(By.xpath("//*[@id='report_table_project-actions-report']"));
				//System.out.println(HTMLTable);

				// Get The Rows
				List<WebElement> rowEle = driver
						.findElements(By.xpath("//*[@id='report_table_project-actions-report']/tbody/tr"));
				System.out.println("Rows Count=" + rowEle.size());

				for (int i = 0; i < rowEle.size(); i++) {
					List<WebElement> colEle = driver.findElements(By.xpath(
							"//*[@id='report_table_project-actions-report']/tbody/tr/td[@headers='STATUS_RETURN_PA']"));

					for (WebElement ele : colEle) {
						boolean contains = strProActFilter3.equals(ele.getText());
						if (contains) {
							threadsleep(1000);

							JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
							// jsExecutor.executeScript("window.scrollBy(0,350)", "");
							jsExecutor.executeScript("arguments[0].style.border='2px solid yellow'", ele);
							counter = counter + 1;
						} else {
							logError("Failed to find the project name: " + ele.getText() + " :On Project Actions Tab");
						}
						if (counter == rowEle.size()) {
							logInfo("Testcase is pass");
							tcStatus = "PASS";
							ExcelUtils.logTestResult("TC_VALIDATE_FILTERS_ON_PROJECT_ACTION_TAB_FOR_PENDING_CLOSURE", tcStatus);
						} else {
							logError("Testcase is failed");
							tcStatus = "FAIL";
							ExcelUtils.logTestResult("TC_VALIDATE_FILTERS_ON_PROJECT_ACTION_TAB_FOR_PENDING_CLOSURE", tcStatus);
						}

					}
				}

			}

			catch (Exception e) {
				log4jError("Failed to Select the element " + xpath + e.getMessage());
				e.printStackTrace();
			}

		} else {
			logError("Failed to Focus Project Actions Tab on P-Tracker page");
		}
	}

//---------------------------------------------------------------------------------------------------------------------------------------------

	@Test(priority = 4, groups = { "sanity","UI" }, description = "Validate Filter 'Pending New Project' on Project Actions Tab")
	public void TC_ValidateProjectFiltersOnProjectActionsTab4() throws Exception {
		threadsleep(2000);

		String strProActFilter4 = "Pending New Project";
		int counter = 0;

		// op.clickElement(NewProject.ProjectActionTabTxt);
		// wait.until(ExpectedConditions.visibilityOf(NewProject.ProjectActionTabFocused));

		if (NewProject.ProjectActionTabFocused.isDisplayed()) {
			logInfo("Project Actions Tab is focused on P-Tracker page");
			try {
				xpath = "//*[@id='R154473969590676725_cards']/li[4]";
				WebElement actEle4 = driver.findElement(By.xpath(xpath));
				logInfo(" ------------------------------------------ ");

				op.clickElement(actEle4);
				threadsleep(2000);

				List<WebElement> proActions = driver.findElements(By.xpath("//*[@id='pa_top_region']/ul/li"));
				System.out.println(proActions);

				// Get The Table
				//WebElement HTMLTable = driver.findElement(By.xpath("//*[@id='report_table_project-actions-report']"));
				//System.out.println(HTMLTable);

				// Get The Rows
				List<WebElement> rowEle = driver
						.findElements(By.xpath("//*[@id='report_table_project-actions-report']/tbody/tr"));
				System.out.println("Rows Count=" + rowEle.size());

				for (int i = 0; i < rowEle.size(); i++) {
					List<WebElement> colEle = driver.findElements(By.xpath(
							"//*[@id='report_table_project-actions-report']/tbody/tr/td[@headers='STATUS_RETURN_PA']"));

					for (WebElement ele : colEle) {
						boolean contains = strProActFilter4.equals(ele.getText());
						if (contains) {
							threadsleep(1000);

							JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
							// jsExecutor.executeScript("window.scrollBy(0,350)", "");
							jsExecutor.executeScript("arguments[0].style.border='2px solid yellow'", ele);
							counter = counter + 1;
						} else {
							logError("Failed to find the project name: " + ele.getText() + " :On Project Actions Tab");
						}
						if (counter == rowEle.size()) {
							logInfo("Testcase is pass");
							tcStatus = "PASS";
							ExcelUtils.logTestResult("TC_VALIDATE_FILTERS_ON_PROJECT_ACTION_TAB_FOR_PENDING_NEW_PROJECTS", tcStatus);
						} else {
							logError("Testcase is failed");
							tcStatus = "FAIL";
							ExcelUtils.logTestResult("TC_VALIDATE_FILTERS_ON_PROJECT_ACTION_TAB_FOR_PENDING_NEW_PROJECTS", tcStatus);
						}

					}
				}

			}

			catch (Exception e) {
				log4jError("Failed to Select the element " + xpath + e.getMessage());
				e.printStackTrace();
			}

		} else {
			logError("Failed to Focus Project Actions Tab on P-Tracker page");
		}
	}

//-----------------------------------------------------------------------------------------------------------------------------------------	

	@Test(priority = 5, groups = { "sanity","UI" }, description = "Validate Filter 'Pending Modification' on Project Actions Tab")
	public void TC_ValidateProjectFiltersOnProjectActionsTab5() throws Exception {
		threadsleep(2000);

		String strProActFilter5 = "Pending Modification";
		int counter = 0;

		/*
		 * op.clickElement(NewProject.ProjectActionTabTxt);
		 * wait.until(ExpectedConditions.visibilityOf(NewProject.ProjectActionTabFocused
		 * ));
		 */

		if (NewProject.ProjectActionTabFocused.isDisplayed()) {
			logInfo("Project Actions Tab is focused on P-Tracker page");
			try {
				xpath = "//*[@id='R154473969590676725_cards']/li[5]";
				WebElement actEle5 = driver.findElement(By.xpath(xpath));
				logInfo(" ------------------------------------------ ");

				op.clickElement(actEle5);
				threadsleep(2000);

				List<WebElement> proActions = driver.findElements(By.xpath("//*[@id='pa_top_region']/ul/li"));
				System.out.println(proActions);

				// Get The Table
				//WebElement HTMLTable = driver.findElement(By.xpath("//*[@id='report_table_project-actions-report']"));
				//System.out.println(HTMLTable);

				// Get The Rows
				List<WebElement> rowEle = driver
						.findElements(By.xpath("//*[@id='report_table_project-actions-report']/tbody/tr"));
				System.out.println("Rows Count=" + rowEle.size());

				for (int i = 0; i < rowEle.size(); i++) {
					List<WebElement> colEle = driver.findElements(By.xpath(
							"//*[@id='report_table_project-actions-report']/tbody/tr/td[@headers='STATUS_RETURN_PA']"));

					for (WebElement ele : colEle) {
						boolean contains = strProActFilter5.equals(ele.getText());
						if (contains) {
							threadsleep(1000);

							JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
							jsExecutor.executeScript("arguments[0].style.border='2px solid yellow'", ele);
							counter = counter + 1;
						} else {
							logError("Failed to find the project name: " + ele.getText() + " :On Project Actions Tab");
						}
						if (counter == rowEle.size()) {
							logInfo("Testcase is pass");
							tcStatus = "PASS";
							ExcelUtils.logTestResult("TC_VALIDATE_FILTERS_ON_PROJECT_ACTION_TAB_FOR_PENDING_MODIFICATIONS",tcStatus);
						} else {
							logError("Testcase is failed");
							tcStatus = "FAIL";
							ExcelUtils.logTestResult("TC_VALIDATE_FILTERS_ON_PROJECT_ACTION_TAB_FOR_PENDING_MODIFICATIONS",tcStatus);
						}
					}

				}
			}
			catch (Exception e) {
				log4jError("Failed to Select the element " + xpath + e.getMessage());
				e.printStackTrace();
			}

		} else {
			logError("Failed to Focus Project Actions Tab on P-Tracker page");
		}
	}

	@AfterClass
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
