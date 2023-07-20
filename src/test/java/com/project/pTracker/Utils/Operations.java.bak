package com.project.pTracker.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

//import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;
import com.project.pageobjects.pTracker.CommonPages;
import com.project.pageobjects.pTracker.PL_ActiveProjectsPage;

public class Operations extends ControlActions {
	
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor jsExecutor;
	CommonPages cp;
	PL_ActiveProjectsPage activeProject;

	public Operations(WebDriver driver) {
		//this.bot = driver;
		super(driver);
		wait = new WebDriverWait(driver, 20000);
		jsExecutor = (JavascriptExecutor) driver;
		//cp=new CommonPages(driver);
}
	
	
	public void waitTillSpinnerDisable() throws InterruptedException {
		int count = 0;
		while (cp.spinner.size() != 0 && count < 90) {
			threadsleep(5000);
			count++;
		}
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	

	public void switchToIframe(WebDriver driver, String iFrameName) {
		driver.switchTo().frame(driver.findElement(By.name(iFrameName)));
	}


	public void switchToiFrameByXpath(WebDriver driver, String iFrameXpath) {
		driver.switchTo().frame(driver.findElement(By.xpath(iFrameXpath)));
	}
	
	public void sleepInMiliSeconds(int duration)
	{
		try {
			threadsleep(duration);
		} catch (Exception e) {
			logWarn("[ERRROR] InterruptedException -> "+ e.getMessage());
			//e.printStackTrace();
		}
	}
	
	/**
	* If a element is present, returns true, else return false
	* @param WebElement whose presence is being checked
	* @return true if webElement is present, else false
	*/
	public boolean checkElementPresent(WebElement webelement) {
	  boolean exists = false;
	  try {
		    webelement.getTagName();
		    logInfo("Element found." + webelement);
		    exists = true;
	  } catch (NoSuchElementException e) {
		  logInfo(webelement + "Element not found. " + e.getMessage());
	  }
	  return exists;
	}

	
	public boolean checkElementPresent(WebDriver driver, String xpath) {
	    try {
		      driver.findElement(By.xpath(xpath));
		      logInfo("Element found. " + xpath);
		      return true;
	    } catch (NoSuchElementException e) {
	    	logInfo("For xpath " +xpath + " element not found. " + e.getMessage());
	    	return false;
	    }
	}
	
	  public boolean isElementExists(String xpath) {
		    boolean isExists = true;
		    try {
		        driver.findElement(By.xpath(xpath)).isDisplayed();
		        logInfo("Element found." + xpath);
		        isExists = true;
		    } catch (NoSuchElementException e) {
		    	isExists = false;
		    	logInfo("For xpath " +xpath + " element not found. " + e.getMessage());
		    }
		    return isExists;
		}

	public void waitforElement(WebElement element)
	{
	    try
	    {
	      int second;
	      for (second = 0; ; second++) 
	      {
	        if (checkElementPresent(element))
	        {   
	          break;
	        } 
	        if (second >= 20)
	        { 
	          break;
	        }
	        threadsleep(1000);	
	      }
	    }
	    catch(Exception e)
	    {
	      e.printStackTrace();
	    }
	 }
	
	/**
	 * scrollPageTo
	 * @param element
	 * @param driver
	 */
	public void scrollPageTo(WebElement element, WebDriver driver) {
		logInfo("scroll to Element : " + element);
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
			logInfo("Clicked on the element " + xpathLocator);
		} catch (Exception e) {
			logError("Failed to click on the element: " + xpathLocator);
			logError(e.getMessage());
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
			logInfo("Clicked on the element " + element);
		} catch (Exception e) {
			logError("Failed to click on the element: " + element);
			logError(e.getMessage());
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
			logInfo("Clicked on the element " + element);
			waitImplicitely(driver, 10);
		} catch (Exception e) {
			logError("Failed to click on the element: " + element);
			logError(e.getMessage());
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
		logInfo("javascriptclick" + " " + webElement);
	}
	
	/**
	 * mouseHoverJScript
	 * This method will perform mouseHover using Java Script
	 * @param driver
	 * @param xpathLocator
	 */
	public void mouseHoverJScript(WebDriver driver, String xpathLocator) {
		try {
		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
		WebElement HoverElement = driver.findElement(By.xpath(xpathLocator));
		((JavascriptExecutor) driver).executeScript(mouseOverScript, HoverElement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * mouseHoverScript
	 * This method will perform mouseHover using Java Script
	 * @param driver
	 * @param xpathLocator
	 */
	public void mouseHoverScript(WebDriver driver,WebElement xpathLocator) {
	try {
		Actions actions = new Actions(driver);
		waitImplicitely(driver,30);
		actions.moveToElement(xpathLocator).perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			logInfo("Clicked on the link for " + xpathLocator);
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
			logInfo("Clicked on the radiobutton for " + xpathLocator);
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
			logInfo("Clicked on the checkbox for " + xpathLocator);
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
					managerDetails = driver.findElement(By.xpath("//*[@id='PopupLov_2_P2_PROJECT_SPONSOR_dlg']//span[contains(text(),'Ved, Bhavesh')]"));
					fieldText = managerDetails.getText();
					managerDetails.click();
					break;
				case "SALES_PERSON":
					managerDetails = driver.findElement(By.xpath("//*[@id='PopupLov_2_P2_SALESPERSON_ID_dlg']//span[contains(text(),'Thakur, Monika')]"));
					fieldText = managerDetails.getText();
					managerDetails.click();
					break;
				case "ACCOUNT_MANAGER":
					managerDetails = driver.findElement(By.xpath("//*[@id='PopupLov_2_P2_ACCOUNT_MANAGER_ID_dlg']//span[contains(text(),'Subramanyam, Ram')]"));
					fieldText = managerDetails.getText();
					managerDetails.click();
					break;
				case "DELIVERY_HEAD":
					managerDetails = driver.findElement(By.xpath("//*[@id='PopupLov_2_P2_DELIVERY_HEAD_ID_dlg']//span[contains(text(),'Mahajan, Milind')]"));
					fieldText = managerDetails.getText();
					managerDetails.click();
					break;
				case "SUBVERTICAL_HEAD":
					managerDetails = driver.findElement(By.xpath("//*[@id='PopupLov_2_P2_SUB_VERTICAL_HEAD_ID_dlg']//*[contains(text(),'Kavatgi, Pallavi')]"));
					fieldText = managerDetails.getText();
					managerDetails.click();
					break;
				case "PROGRAM_MANAGER":
					managerDetails = driver.findElement(By.xpath("//*[@id='PopupLov_2_P2_PROGRAM_MANAGER_ID_dlg']//span[contains(text(),'Gaikwad, Kavita')]"));
					fieldText = managerDetails.getText();
					managerDetails.click();
					break;
				case "PROJECT_MANAGER":
					managerDetails = driver.findElement(By.xpath("//*[@id='PopupLov_2_P2_PROJECT_MANAGER_ID_dlg']//span[contains(text(),'Maydeo, Parag')]"));
					fieldText = managerDetails.getText();
					managerDetails.click();
					break;
				case "FINANCE_REPRESENTATIVE":
					managerDetails = driver.findElement(By.xpath("//*[@id='PopupLov_2_P2_FINANCE_REPRESENTATIVE_ID_dlg']//span[contains(text(),'Vaidya, Ritesh')]"));
					fieldText = managerDetails.getText();
					managerDetails.click();
					break;
				// Default case statement 
				default:
					logInfo("Manager Details Should be from : DELIVERY_HEAD , PROGRAM_MANAGER, PROJECT_MANAGER etc.");
				}
				// Read TextBox set value
				// fieldText = managerDetails.getText();
				logInfo("Textbox value is: " + fieldText);
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
			logInfo("Set text " + inputText + " for " + xpathLocator);
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

			logInfo("Getting text = " + result + " for " + element);
		} catch (Exception e) {
			logError("Failed to Get text " + e.getMessage());
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
			//JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			//jsExecutor.executeScript("arguments[0].style.border='2px solid red'", we);
			logInfo("Selected value from dropdown " + inputText + " for " + xpathLocator);
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
					//logInfo(ele.getText() + " -----> " + ListBoxName);
					if (ele.getText().equals(inputText)) {
						JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
						jsExecutor.executeScript("arguments[0].style.border='2px solid red'", ele);
						logInfo(ele.getText()  + " <-- is selected from " + ListBoxName + " <-- the List");
						ele.click();
						waitImplicitely(driver, 10);
						return;
					}
				}
			} else {
				logError("For List " + ListBoxName + " List size is  empty");
			}
		} catch (Exception e) {
			logError("Failed to Select the element " + inputText + " from the List " + ListBoxName);
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
			logInfo("Waiting implicitely for " + maxTimeOutInSecond + " seconds");
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
			logInfo("Getting text = " + text + " for " + xPathLocator);
		} catch (Exception e) {
			logError("Failed to Get text " + e.getMessage());
			e.printStackTrace();
		}
		return text;
	}
	
	/**
	 * selectFromList
	 * @param driver
	 * @param xpath
	 * @param text
	 * @return List<String>
	 */
	public List<String> searchReportTable(WebDriver driver, String xpathLocator, String inputText) throws Exception {
		threadsleep(2000);
		List<WebElement> ElementsList = driver.findElements(By.xpath(xpathLocator));
		List<String> searchList = new ArrayList<>();
		try {
			int size = ElementsList.size();
			logInfo("List Size is: " + ElementsList.size());
			logInfo("Try to Select " + inputText + " From List box Size: " + ElementsList.size());
			if (!(size == 0)) {
				for (int i = 0; i < ElementsList.size(); i++)
				{
					searchList.add(ElementsList.get(i).getText());
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
		threadsleep(2000);
		return searchList ;
	}
	
	/**
     * Blocks for a given time, or until the associated condition is met. The
     * associated condition is checked every half second.
     * <p>
     * Only an {@link InterruptedException} will prematurely halt the waiting
     * process.
     * 
     * @param waitTime
     *            the time to wait in milliseconds
     * @throws WidgetTimeoutException
     */
    public void waitUntil(long waitTime, WebElement locator ) throws Exception {

        long currentTimeMillis = System.currentTimeMillis();
        long maxRequestTimeout = waitTime;
        long endTime = currentTimeMillis + maxRequestTimeout;
        while (System.currentTimeMillis() < endTime) {
            try {
                if (locator.isDisplayed())
                	threadsleep(2000);
                    return;
             } catch (Exception e) {
                // ignore any other type of Exception and continue
                logInfo(" Exception while waiting for element  " + locator + " -> "+ e );
            }
        }

        throw new Exception("Timed out performing action " + locator);
    }
    
	public void closeBrowser(WebDriver driver) throws InterruptedException 
	{
		try
		{
			 if (driver.getWindowHandles().size() > 1)
			 {
				 driver.close();
			 }
			 else
			 {
				 driver.quit();  
			 }
		}
		catch(Exception e)
		{
			logError("Failed to close the driver " + e.getMessage());
			System.out.println("Exception of type " + e.getClass().getName() + " caught: " + e.getMessage());
		}
		finally
		{
			threadsleep(2000);
			driver.quit(); 
	    }
	}

	
//	public <T> T getElement(WebElement<T> webElement, int attempts, int count) throws Exception {
//        T result = null;
//        int i = 0;
//        while (++i <= attempts) {
//            try {
//                result = webElement.get();
//                if (result == null) {
//                	logWarn("Element was not found, go to next iteration: {}"+ i);
//                } else if (result instanceof WebElement) {
//                    if (((WebElement) result).isEnabled()) {
//                        break;
//                    }
//                    logWarn("Element was found, but it is not enabled, go to next iteration: {}"+ i);
//                } else if (result instanceof List) {
//                    if (((List<?>) result).size() == count) {
//                        break;
//                    }
//                    logWarn("Elements were not found, go to next iteration: {}"+ i);
//                }
//            } catch (Exception ex) {
//            	logWarn("Element was not found, go to next iteration: {}"+ i);
//            }
//            Thread.sleep(1000);
//        }
//        return result;
//    }
	public boolean checkSpecialChar(String str) {
		String regex = "[^a-zA-Z0-9]+";
		Pattern p = Pattern.compile(regex);
		if (str == null)
			return false;
		Matcher m = p.matcher(str);
		if (m.matches())
			return true;
		else
			return false;
	}

	public boolean checkOnlyCharacter(String str) {
		if (str != null && (str.matches("^[a-zA-Z]*$")))
			return true;
		else
			return false;
	}

	public int generateRandomNum() {
		Random random = new Random();
		// Generates random integers 0 to 49
		int x = random.nextInt(500);
		return x;
	}
	
	
	/**
	 * This method is used for clicking "li" elements from passed ul
	 *  elements in a method as a paramenter
	 * @author pardhi_a
	 * @param webElement the ul element
	 * @param strText for matching li element text
	 */
	public void clickLiElementByText(WebElement webElement, String strText)
	{
	  try {
		List<WebElement> list=webElement.findElements(By.tagName("li"));
		for (WebElement li : list) {
			if (li.getText().equals(strText)) {
				li.click();
			}
		  }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method is used searching text in list
	 * @author pardhi_a
	 * @param list of WebElement whose text we are searching in it. 
	 * @param strText for matching element text
	 */
	public boolean searchTextInList(List<WebElement> lst, String strExpected)
	{
	  boolean flag=false;
	  try {
			for (int i = 0; i < lst.size(); i++) {
				String strTemp=lst.get(i).getText();
				if(strExpected.equals(strTemp))
				{
					flag=true;
					break;
				}
			}
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	  return flag;
	}
	
	/**
	 * This method is used searching text in list
	 * @author pardhi_a
	 * @param Currently not in Use
	 */
	public void InvoiceGenericMethods() {
	try {
			mouseHoverScript(driver,activeProject.ActiveProMouseHover);
			waitUntilElementIsClickable(activeProject.InvoiceButton);
			clickLiElementByText(activeProject.Apex_RDX_CONTAINER_UL, "Invoices");
			// Working On Frame
			click(activeProject.RaiseInvoiceButton);
			switchToFrameByLocators(activeProject.Frame_01);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

} // End of Class
