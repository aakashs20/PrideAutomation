package com.project.pTracker.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

//import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

public class Operations extends ControlActions {
	
	WebDriverWait wait;
	JavascriptExecutor jsExecutor;

	public Operations(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, 20000);
		jsExecutor = (JavascriptExecutor) driver;
}
	
	public void sleepInMiliSeconds(int duration)
	{
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			logWarn("[DriverBotWarn]warning got InterruptedException exception(sleepInSeconds)."+ e.getMessage());
			//e.printStackTrace();
		}
	}

	
	/**
	 * scrollPageTo
	 * @param element
	 * @param driver
	 */
	public void scrollPageTo(WebElement element, WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String bottom = jse.executeScript("return arguments[0].getBoundingClientRect().bottom;", element).toString();
		jse.executeScript("window.scrollTo(0," + bottom + ")", element);
	}

	public boolean waitUntilElementPresent(WebElement webElement) {
		try {
			wait.until(ExpectedConditions.visibilityOfAllElements(webElement));
			logInfo("Element Successfully loaded on the page");
			return true;
		} catch (Exception e) {
			logError("Failed to load the element on the page" + e.getMessage());
			return false;
		}
	}

	/**
	 * This method will click on Element with the given XPath
	 * @param driver
	 * @param xpathLocator
	 */
	public void clickElement(WebDriver driver, String xpathLocator) {
		try {
			driver.findElement(By.xpath(xpathLocator)).click();
			log4jInfo("Clicked on the element " + xpathLocator);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will click on Element using element
	 * @param element
	 */
	public void clickElement(WebElement element) {
		try {
			element.click();
			log4jInfo("Clicked on the element " + element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will click on Element 
	 * @param element
	 * @param driver
	 */
	public void clickElement(WebElement element, WebDriver driver) {
		try {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].style.border='2px solid yellow'", element);
			element.click();
			log4jInfo("Clicked on the element " + element);
			waitImplicitely(driver, 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * javascriptclick
	 * This method will click on web element
	 * @param driver
	 * @param webElement
	 */
	public void javascriptclick(WebDriver driver, WebElement webElement) {
		// WebElement webElement = driver.findElement(By.xpath(element));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", webElement);
		System.out.println("javascriptclick" + " " + webElement);
	}
	
	/**
	 * mouseHoverJScript
	 * This method will perform mouseHover using Java Script
	 * @param driver
	 * @param xpathLocator
	 */
	public void mouseHoverJScript(WebDriver driver, String xpathLocator) {

		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
		WebElement HoverElement = driver.findElement(By.xpath(xpathLocator));
		((JavascriptExecutor) driver).executeScript(mouseOverScript, HoverElement);
	}

	/**
	 * clickLink
	 * This method will click on Link or Button with the given XPath
	 * @param driver
	 * @param xpathLocator
	 */
	public void clickLink(WebDriver driver, String xpathLocator) {
		try {
			driver.findElement(By.xpath(xpathLocator)).click();
			log4jInfo("Clicked on the link for " + xpathLocator);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * clickRadiobutton
	 * This method will click on Radio-button with the given XPath
	 * @param driver
	 * @param xpathLocator
	 */
	public void clickRadiobutton(WebDriver driver, String xpathLocator) {
		try {
			driver.findElement(By.xpath(xpathLocator)).click();
			log4jInfo("Clicked on the radiobutton for " + xpathLocator);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * clickCheckbox
	 * This method will click on checkbox with the given XPath
	 * @param driver
	 * @param xpathLocator
	 */
	public void clickCheckbox(WebDriver driver, String xpathLocator) {
		try {
			driver.findElement(By.xpath(xpathLocator)).click();
			log4jInfo("Clicked on the checkbox for " + xpathLocator);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * setElementValueByActions
	 * @param driver
	 * @param element
	 * @param text
	 * @param text
	 */
	public String setManagerDetailsText(WebDriver driver, WebElement element, String text, String str) {
		String fieldText = null;
		WebElement managerDetails = null;

		/**
		 * Validate whether the TextBox is visible and enabled
		 */
		Boolean visibleState = element.isDisplayed();
		System.out.println("The Textbox visibility is: " + visibleState);

		if (visibleState) {
			Boolean status = element.isEnabled();
			System.out.println("The status of Textbox is: " + status);

			// If TextBox is enabled set a value to it
			if (status) {
				// JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				// jsExecutor.executeScript("arguments[0].style.border='2px solid yellow'",
				// element);
				element.click();
				element.sendKeys(text);
				element.click();
				// element.sendKeys(Keys.ENTER);
				threadsleep(60);
				switch (str) {
				// Case Statements
				case "PROJECT_SPONSOR":
					managerDetails = driver.findElement(By.xpath(
								"//*[@id='PopupLov_2_P2_PROJECT_SPONSOR_dlg']//span[contains(text(),'Ved, Bhavesh')]"));
					fieldText = managerDetails.getText();
					managerDetails.click();
					break;
				case "SALES_PERSON":
					managerDetails = driver.findElement(By.xpath(
							"//*[@id='PopupLov_2_P2_SALESPERSON_ID_dlg']//span[contains(text(),'Thakur, Monika')]"));
					fieldText = managerDetails.getText();
					managerDetails.click();
					break;
				case "ACCOUNT_MANAGER":
					managerDetails = driver.findElement(By.xpath(
							"//*[@id='PopupLov_2_P2_ACCOUNT_MANAGER_ID_dlg']//span[contains(text(),'Subramanyam, Ram')]"));
					fieldText = managerDetails.getText();
					managerDetails.click();
					break;
				case "DELIVERY_HEAD":
					managerDetails = driver.findElement(By.xpath(
							"//*[@id='PopupLov_2_P2_DELIVERY_HEAD_ID_dlg']//span[contains(text(),'Mahajan, Milind')]"));
					fieldText = managerDetails.getText();
					managerDetails.click();
					break;
				case "PROGRAM_MANAGER":
					managerDetails = driver.findElement(By.xpath(
							"//*[@id='PopupLov_2_P2_PROGRAM_MANAGER_ID_dlg']//span[contains(text(),'Gaikwad, Kavita')]"));
					fieldText = managerDetails.getText();
					managerDetails.click();
					break;
				case "PROJECT_MANAGER":
					managerDetails = driver.findElement(By.xpath(
							"//*[@id='PopupLov_2_P2_PROJECT_MANAGER_ID_dlg']//span[contains(text(),'Maydeo, Parag')]"));
					fieldText = managerDetails.getText();
					managerDetails.click();
					break;
				case "FINANCE_REPRESENTATIVE":
					managerDetails = driver.findElement(By.xpath(
							"//*[@id='PopupLov_2_P2_FINANCE_REPRESENTATIVE_ID_dlg']//span[contains(text(),'Vaidya, Ritesh')]"));
					fieldText = managerDetails.getText();
					managerDetails.click();
					break;
				// Default case statement 
				default:
					System.out.println(
							"Manager Details Should be from : DELIVERY_HEAD , PROGRAM_MANAGER, PROJECT_MANAGER etc.");
				}
				// Read TextBox set value
				// fieldText = managerDetails.getText();
				System.out.println("Textbox value is: " + fieldText);
			}
		}
		return fieldText;
	}

	public static String getText(WebDriver driver, WebElement element) {
		return (String) ((JavascriptExecutor) driver).executeScript("return jQuery(arguments[0]).text();", element);
	}

	/**
	 * This method will type text in a textbox with the given XPath
	 * @param driver
	 * @param xpathLocator
	 * @param inputText
	 */
	public void setText(WebDriver driver, String xpathLocator, String inputText) {
		try {
			waitImplicitely(driver, 10);
			driver.findElement(By.xpath(xpathLocator));
			driver.findElement(By.xpath(xpathLocator)).clear();
			driver.findElement(By.xpath(xpathLocator)).sendKeys(inputText);
			log4jInfo("Set text " + inputText + " for " + xpathLocator);
			waitImplicitely(driver, 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * setElementValueByActions
	 * @param driver
	 * @param element
	 * @param text
	 */
	public void setElementTextValueByActions(WebDriver driver, WebElement element, String text) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.click();
		actions.sendKeys(text);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].style.border='2px solid yellow'", element);
		waitImplicitely(driver, 20);
		actions.sendKeys(Keys.ENTER);
		actions.build().perform();
	}

	/**
	 * setElementValueByActions
	 * @param text
	 * @param element
	 * @param driver
	 */
	public String getElementTextValueByActions(WebDriver driver, WebElement element) {
		String result = null;
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(element);
			element.click();
			//actions.click();
			element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			element.sendKeys(Keys.chord(Keys.CONTROL, "c"));
			//Keys.chord(Keys.CONTROL,Keys.chord("a"));
			//copy = Keys.chord(Keys.CONTROL, "c");
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].style.border='2px solid yellow'", element);
			waitImplicitely(driver, 20);
			
			/*
			 * Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			 * Transferable contents = clipboard.getContents(null); String x = (String)
			 * contents.getTransferData(DataFlavor.stringFlavor);
			 */

			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Clipboard clipboard = toolkit.getSystemClipboard();
			result = (String) clipboard.getData(DataFlavor.stringFlavor);
			System.out.println("String from Clipboard:" + result);

			log4jInfo("Getting text = " + result + " for " + element);
		} catch (Exception e) {
			log4jError("Failed to Get text " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * This method will select a desired value from dropdown for the given xpath
	 * @param driver
	 * @param xpathLocator
	 * @param inputText
	 */
	public void selectDropdown(WebDriver driver, String xpathLocator, String inputText) {
		try {
			WebElement we = driver.findElement(By.xpath(xpathLocator));
			Select sel = new Select(we);
			sel.selectByVisibleText(inputText);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].style.border='2px solid red'", we);
			log4jInfo("Selected value from dropdown " + inputText + " for " + xpathLocator);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * selectFromList
	 * @param driver
	 * @param xpath
	 * @param text
	 * @param ObjectName
	 */
	public void selectFromList(WebDriver driver, String xpathLocator, String inputText, String ListBoxName)
			throws Exception {
		try {
			List<WebElement> ListElements = driver.findElements(By.xpath(xpathLocator));
			int size = ListElements.size();
			System.out.println(" Size of " + ListBoxName + " is: " + ListElements.size());
			System.out.println(" Try to Select " + inputText + " From List box Size: " + ListElements.size());
			if (!(size == 0)) {
				java.util.Iterator<WebElement> i = ListElements.iterator();
				while (i.hasNext()) {
					WebElement ele = i.next();
					log4jInfo(ele.getText() + " -----> " + ListBoxName);
					if (ele.getText().equals(inputText)) {
						JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
						jsExecutor.executeScript("arguments[0].style.border='2px solid red'", ele);
						log4jInfo(ele.getText() + " is selected from " + ListBoxName + " List");
						ele.click();
						waitImplicitely(driver, 10);
						return;
					}
				}
			} else {
				log4jError("For List " + ListBoxName + " List size is  empty");
			}
		} catch (Exception e) {
			log4jError("Failed to Select the element " + inputText + " from the List " + ListBoxName);
			e.printStackTrace();
		}
	}

	/**
	 * This method will wait implicitly for the specified time in seconds
	 * @param driver
	 * @param maxTimeOutInSecond
	 */
	public void waitImplicitely(WebDriver driver, int maxTimeOutInSecond) {
		try {
			driver.manage().timeouts().implicitlyWait(maxTimeOutInSecond, TimeUnit.SECONDS);
			log4jInfo("Waiting implicitely for " + maxTimeOutInSecond + " seconds");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will return me text for a given xpath
	 * @param driver
	 * @param xPathLocator
	 * @return text
	 */
	public String getText(WebDriver driver, String xPathLocator) {
		String text = null;
		try {
			driver.findElement(By.xpath(xPathLocator)).click();
			text = driver.findElement(By.xpath(xPathLocator)).getText();
			log4jInfo("Getting text = " + text + " for " + xPathLocator);
		} catch (Exception e) {
			log4jError("Failed to Get text " + e.getMessage());
			e.printStackTrace();
		}
		return text;

	}
	
	/**
	 * selectFromList
	 * @param driver
	 * @param xpath
	 * @param text
	 * @param ObjectName
	 */
	public List<WebElement> searchReportTable(WebDriver driver, String xpathLocator, String inputText)
			throws Exception {
		List<WebElement> ElementsList = driver.findElements(By.xpath(xpathLocator));
		try {
			
			int size = ElementsList.size();
			logInfo("List Size is: " + ElementsList.size());
			logInfo("Try to Select " + inputText + " From List box Size: " + ElementsList.size());
			if (!(size == 0)) {
				for (int i = 0; i < ElementsList.size(); i++)
				{
					logInfo( i + " -----> " + ElementsList.get(i).getText());
					if (ElementsList.get(i).getText().equals(inputText)) {
					JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
					jsExecutor.executeScript("arguments[0].style.border='2px solid yellow'", ElementsList.get(i));
					log4jInfo(ElementsList.get(i).getText() + " is selected from Search Table");
					ElementsList.get(i).click();
					waitImplicitely(driver, 10);
				    }
				}

			} else {
				logError("Search Error : No Data found for :" + inputText);
			}
		} catch (Exception e) {
			logError("Failed to select the element " + inputText + " In Search Field");
			e.printStackTrace();
		}
		return ElementsList;
	}
	
} // End of Class
