package com.project.pageobjects.pTracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.pTracker.Utils.Operations;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.lang.model.element.Element;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActiveGrantedTabPage extends TestBase {
	
	WebDriverWait wait;
	ControlActions controlActions;
	Operations op ;
	private static final int DELAY = 20;
	
	
	public ActiveGrantedTabPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, DELAY);
		controlActions = new ControlActions(driver);
		op = new Operations(driver);
		driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);
	}
	
	public List<WebElement> validateGrantedProjectsTabs(WebDriver driver,List<String> defaultValue ) {

		WebElement findElement = driver.findElement(By.xpath("//*[@id='530071882311821340_RDS']"));
		List<WebElement> findElements = findElement.findElements(By.tagName("li"));
		for (WebElement webElement : findElements) {
			System.out.println(webElement.getText());
		}
		
		List<String> expt = new ArrayList<String>();
//		List<String> defaultValue = new ArrayList<String>(
//				Arrays.asList("Active Projects", "Closed Projects", "New Projects"));

		for (WebElement webElement : findElements) {
			String text = webElement.getText();
			expt.add(text);
		}

		if (expt.equals(defaultValue)) {
			logInfo("Grandted projects Tabs are equal");
		} else {
			logError("Grandted projects Tabs are not equal");
		}

		return findElements;

	}

	public boolean validateIconUnderProjectTab(WebDriver driver) throws InterruptedException {
		threadsleep(3000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//ul[@id='R505691641167493060_cards']"))));
		WebElement findElement = driver.findElement(By.xpath("//ul[@id='R505691641167493060_cards']"));
		List<WebElement> findElements = findElement.findElements(By.tagName("li"));
		for (WebElement webElement : findElements) {
			logInfo(webElement.getText());
		}
		return false;

	}

	public boolean changeProjectTab(WebDriver driver, List<String> expt) throws InterruptedException {
		int n = expt.size();
		List testCase = new ArrayList(
				Arrays.asList("Active Project List", "Close Project List", "New Project List", "New Project List"));

		
		try {
			for (int i = 0; i <= expt.size(); i++) {

				String tabName = expt.get(i);
				threadsleep(3000);
				WebElement findElement = driver.findElement(By.xpath("//*[text()='" + tabName + "']"));
				findElement.click();
				threadsleep(3000);

				// find icon under specific Porject tab
				String tag = (String) testCase.get(i);
				WebElement findEle = driver
						.findElement(By.xpath("//h2[text()='" + tag + "']/following-sibling::div/ul"));
				List<WebElement> findElem = findEle.findElements(By.tagName("li"));

				for (WebElement webElement : findElem) {
				
				//	logInfo("The Icons Present"+webElement.getText());
				}
				
			}
			logInfo("Tab changing Successfull");
			

		} catch (Exception e) {
			logInfo("Tab is not changing Successfull");
		}

		return true;

	}
	
	public boolean clickHomeButton(WebDriver driver) throws InterruptedException {
		WebElement findElement = driver.findElement(By.xpath("//*[@id='481561109094491094']//li[2]/a"));
		findElement.click();
		threadsleep(13000);
		return true;
	}

}
