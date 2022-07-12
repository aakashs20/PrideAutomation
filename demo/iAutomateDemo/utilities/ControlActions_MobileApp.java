package com.project.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.project.safetychain.mobileapp.pageobjects.UploadImage;
import com.project.testbase.TestBase;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class ControlActions_MobileApp extends TestBase {
	public class appiumDriver {

	}

	static AppiumDriver<MobileElement> appiumDriver;
	WebDriver driver;
	public static Actions action;
	public static WebDriverWait wait;
	public static WebDriverWait shortWait;

	public ControlActions_MobileApp(AppiumDriver<MobileElement> driver) {
		ControlActions_MobileApp.appiumDriver = driver;
		wait = new WebDriverWait(driver, 10);
		action = new Actions(appiumDriver);
		PageFactory.initElements(ControlActions_MobileApp.appiumDriver, this);

	}

	public static void waitForVisibility(WebElement e) {
		try {
			wait.until(ExpectedConditions.visibilityOf(e));
		} catch (Exception ex) {
			throw ex;

		}
	}

	public static void waitForVisibility(WebElement e, long waitTime) {
		try {
			WebDriverWait wait = new WebDriverWait(appiumDriver, waitTime);
			wait.until(ExpectedConditions.visibilityOf(e));
		} catch (Exception ex) {
			throw ex;

		}
	}

	public static boolean click(WebElement element) {
		try {
			waitForVisibility(element);
			element.click();
			return true;
		} catch (Exception ex) { 
			return false;
		}
	}

	public static void ClearText(WebElement e) {
		try {
			waitForVisibility(e);
			e.clear();
		} catch (Exception ex) { 
			throw ex;

		}
	}

	public static void sendKeys(WebElement e, String txt) {
		try {
			waitForVisibility(e); 
			e.sendKeys(txt);
		} catch (Exception ex) { 
			throw ex;

		}
	}

	public static String getAttribute(WebElement e, String attribute) {
		try {
			waitForVisibility(e);
			return e.getAttribute(attribute);
		} catch (Exception ex) { 
			throw ex;

		}

	}

	public static void actionEnter() {
		try {
			action.sendKeys(Keys.ENTER);
			action.build().perform();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Issue While Enter tab", e.getCause());
		}
	}

	public static void actionClick(WebElement target) {
		try {
			action.moveToElement(target).click();
			action.build().perform();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Issue While Enter tab", e.getCause());
		}
	}

	public static void actionClickAndHold(WebElement target) {
		try {
			action.moveToElement(target).clickAndHold();
			action.build().perform();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Issue While Enter tab", e.getCause());
		}
	}

	public static void performTabAction() {
		try {
			action.sendKeys(Keys.ALT);
			action.sendKeys(Keys.TAB);
			action.build().perform();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Issue While Enter tab", e.getCause());
		}
	}

	public static void actionEnterText(WebElement element, String Text) {
		try {

			action.moveToElement(element).click().perform();
			action.sendKeys(Text);
			action.build().perform();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Issue While Enter tab", e.getCause());
		}
	}

	public static void actionScrollDown() {
		try {
			action.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());

		}
	}

	public static void actionScrollUp() {
		try {
			action.keyDown(Keys.CONTROL).sendKeys(Keys.HOME).perform();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());

		}
	}

	public static void perform_scrollToElement_ByElement(WebElement element) {
		((JavascriptExecutor) appiumDriver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void perform_scrollToElement(String Dimention) {
		((JavascriptExecutor) appiumDriver).executeScript("window.scrollBy(0," + Dimention + ")", "");
	}

	public static void perform_clickElement_ByJavaScript(WebElement element) {
		((JavascriptExecutor) appiumDriver).executeScript("arguments[0].click();", element);
	}

	public static void perform_EnterText_ByJavaScript(WebElement element, String valueToEnter) {
		((JavascriptExecutor) appiumDriver).executeScript("arguments[0].value=argument[1];", element, valueToEnter);

	}

	@SuppressWarnings("rawtypes")
	public static TouchAction gettouchaction(int startX, int startY, int endX, int endY) {
		TouchAction touchAction = null;
		try {
			touchAction = new TouchAction<>(appiumDriver);
			PointOption pointStart = PointOption.point(startX, startY);
			PointOption pointEnd = PointOption.point(startY, startX);
			WaitOptions waitOption = WaitOptions.waitOptions(Duration.ofMillis(1000));
			touchAction.press(pointStart).waitAction(waitOption).moveTo(pointEnd).release().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return touchAction;
	}

	@SuppressWarnings("rawtypes")
	public static TouchAction getTapAction(int startX, int startY) {
		TouchAction touchAction = null;
		try {

			touchAction = new TouchAction(appiumDriver).press(PointOption.point(startX, startY))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).release();
			touchAction.perform();
		} catch (Exception e) {
			logInfo("Tapped on X cordinate" + startX + "and Y Co-ordinate" + startY);
			e.printStackTrace();
		}
		return touchAction;
	}

	public static void swipe(int startX, int startY, int endX, int endY) {
		// log.info("scroll from : startX " +startX + ", startY "+ startY+ ", to
		// endX "+
		// endX+ ",endY "+ endY);

		ControlActions_MobileApp.gettouchaction(startX, startY, endX, endY);
	}

	/**
	 * This method is used to dynamically retrieve WebElement.
	 * 
	 * @author
	 * @param xPath
	 *            The xpath to be manipulated
	 * @param replaceWith
	 *            String to be replaced
	 * @param replaceTo
	 *            String to be replaced with
	 * @return WebElement This returns WebElement after dynamically retrieval of
	 *         it using xpath
	 */
	public static WebElement perform_GetElementByXPath(String xPath, String replaceWith, String replaceTo) {
		WebElement webElement = null;
		String finalXpath = null;
		try {
			finalXpath = xPath.replaceAll(replaceWith, replaceTo);

			webElement = appiumDriver.findElementByXPath(finalXpath);
		} catch (Exception e) {
			logInfo("Failed to get element with xpath - " + finalXpath + " - ");
		}
		return webElement;
	}

	public static String perform_GetElementByXPath2(String xPath, String replaceWith, String replaceTo) {

		String finalXpath = null;
		try {
			finalXpath = xPath.replaceAll(replaceWith, replaceTo);

		} catch (Exception e) {
			logError("Failed to get element with xpath - " + finalXpath + " - " + e.getMessage());
		}
		return finalXpath;
	}

	/**
	 * This method is used to format date
	 * 
	 * @author Shingare_s
	 * @param 1.
	 *            Date --String, 2.Date format --String
	 * @return String -- formated dated
	 * @throws ParseException
	 */
	public static String formatdate(String date, String dateformat) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat outputFormat = new SimpleDateFormat(dateformat);

		Date originformat = sdf.parse(date);
		String duedate = outputFormat.format(originformat);
		return duedate;
	}

	/**
	 * This method is used to format date
	 * 
	 * @author Shingare_s
	 * @param 1.
	 *            Date --String, 2.Date format --String
	 * @return String -- formated dated
	 * @throws ParseException
	 */
	public static String formatdate2(String date, String dateformat) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
		DateFormat outputFormat = new SimpleDateFormat(dateformat);

		Date originformat = sdf.parse(date);
		String duedate = outputFormat.format(originformat);
		return duedate;
	}

	/**
	 * This method is used to format date
	 * 
	 * @author Shingare_s Updated: Priya Kole(Changed to 24 hr format)
	 * @param 1.
	 *            Date --String, 2.Date format --String
	 * @return String -- formated dated
	 * @throws ParseException
	 */
	public static String formatdateAndTime(String dateformat) throws ParseException {

		DateFormat df = new SimpleDateFormat(dateformat);
		Date dateobj = new Date();
		String NewDate = df.format(dateobj);
		System.out.println(df.format(dateobj));
		return NewDate;
	}

	/**
	 * This method is used to format date
	 * 
	 * @author Shingare_s
	 * @param 1.
	 *            Date --String, 2.Date format --String
	 * @return String -- formated dated
	 * @throws ParseException
	 */
	public static String formatdateAndTime2(String dateformat) throws ParseException {

		DateFormat df = new SimpleDateFormat(dateformat);
		Date dateobj = new Date();
		String NewDate = df.format(dateobj);
		System.out.println(df.format(dateobj));
		return NewDate;
	}

	/**
	 * This method is used to convert 24hr Format time into 12 hr formatted
	 * time.
	 * 
	 * @author Shingare_S
	 * @param String
	 *            time-24 hr time format
	 * @return returns 12hr time format
	 * @throws ParseException
	 * 
	 */
	public static String convert24HrFormatInto12HrTime(String time) throws ParseException {
		String Time12HrFormat = null;

		try {
			final SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
			final Date dateObj = sdf.parse(time);
			// System.out.println(dateObj);
			System.out.println(new SimpleDateFormat("hh:mm").format(dateObj));
			Time12HrFormat = new SimpleDateFormat("hh:mm a").format(dateObj);
			return Time12HrFormat;
		} catch (final ParseException e) {
			e.printStackTrace();
			return Time12HrFormat;
		}

	}

	/**
	 * This method is used to get current week range
	 * 
	 * @author Shingare_S
	 * @param String
	 *            time time format
	 * @return returns 12hr time format
	 * @throws ParseException
	 * 
	 */
	public String getCurrentWeekRange() throws ParseException {
		String CurrentWeekRange = null;
		try {
			// Get calendar set to current date and time
			Calendar c = Calendar.getInstance();

			// Set the calendar to monday of the current week
			c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

			// System.out.println();
			// Print dates of the current week starting on Monday
			DateFormat df = new SimpleDateFormat("MMM dd");
			DateFormat df1 = new SimpleDateFormat("MMM dd yyyy");
			System.out.println(df.format(c.getTime()));
			String Start_Date = df.format(c.getTime());
			for (int i = 0; i < 6; i++) {
				c.add(Calendar.DATE, 1);
			}
			System.out.println(df.format(c.getTime()));
			System.out.println();
			CurrentWeekRange = Start_Date + " - " + df1.format(c.getTime());
		} catch (Exception ex) {
			throw ex;

		}
		return CurrentWeekRange;
	}

	/**
	 * This method is used to add number of days to the current date
	 * 
	 * @author Shingare_S
	 * @param 1.Number
	 *            of days as String--"1"
	 * @return String as number of added dates
	 */
	public static String AddDaystoToddaysDate(int numberofdays) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, numberofdays);
			String newDate = sdf.format(cal.getTime());
			return newDate;
		} catch (Exception ex) {
			// e.printStackTrace();
			// Assert.fail(e.getMessage());
			throw ex;

		}
	}

	/**
	 * This method is used to add number of days to the current date
	 * 
	 * @author Shingare_S
	 * @param 1.Number
	 *            of days as String--"1"
	 * @return String as number of added dates
	 */
	public static String AddDaystoToddaysDateInGivenFormat(String DateFormat, int numberofdays) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DateFormat);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, numberofdays);
			String newDate = sdf.format(cal.getTime());
			return newDate;
		} catch (Exception ex) {
			// e.printStackTrace();
			// Assert.fail(e.getMessage());
			throw ex;

		}
	}

	public static boolean compareTwoDate(String DateFormat, Date DateToBeCompare) throws ParseException {

		boolean isBefore = true;
		try {

			SimpleDateFormat sdf = new SimpleDateFormat(DateFormat);
			String date1 = sdf.format(new Date());
			Date date = sdf.parse(date1);
			logInfo("Actual Date = " + date);
			logInfo("Expect Date = " + DateToBeCompare);
			if (DateToBeCompare.before(date) || DateToBeCompare.equals(date)) {
				isBefore = true;
			} else {
				isBefore = false;
			}

			return isBefore;
		} catch (Exception ex) {
			throw ex;

		}
	}

	public static boolean checkDateEquality(String DateFormat, Date DateToBeCompare) throws ParseException {
		boolean isBefore = true;
		SimpleDateFormat sdf = new SimpleDateFormat(DateFormat);
		String date1 = sdf.format(new Date());
		Date date = sdf.parse(date1);
		if (date.equals(DateToBeCompare)) {
			isBefore = true;
		} else {
			isBefore = false;
		}

		return isBefore;
	}

	public static void scrollAndClick(String visibleText) {
		appiumDriver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
						+ visibleText + "\").instance(0))"))
				.click();

	}

	public static void scrollIntoView(String visibleText) {
		MobileElement element = appiumDriver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"com.safetychain.SCM.M2:id/softkeyboardlayout\")).scrollIntoView("
						+ "new UiSelector().text(\"MultiSelect *\"))"));
		System.out.println("Element to be located " + element.getLocation());
	}

	/*
	 * @Purpose - This method 'isElementDisplayed' is used to find whether the
	 * WebElement is displayed or not
	 */
	public static boolean isElementDisplayed(WebElement webElement) {
		try {
			webElement.isDisplayed();
			return true;
		} catch (ElementNotVisibleException e) {
			logError("Element not visible");
			return false;
		} catch (StaleElementReferenceException e) {
			logError("Stale Element reference Exception ");
			return false;
		} catch (Exception e) {
			logError("Couldn't find element");
			return false;

		}

	}

	/**
	 * This method is used to add number of days to the current date
	 * 
	 * @author Shingare_S
	 * @param 1.WebElement
	 *            to be pressed
	 * @return Touch action
	 */

	@SuppressWarnings("rawtypes")
	public static TouchAction getLongPressAction(WebElement webElement) {
		TouchAction touchAction = null;

		try {

			touchAction = new TouchAction(appiumDriver);
			// AndroidTouchAction touch = new AndroidTouchAction(appiumDriver);
			touchAction.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(webElement)))
					.perform();
			logInfo("Long pressed on WebElement");

		} catch (Exception e) {
			logInfo("Long pressed on WebElement");
			e.printStackTrace();
		}
		return touchAction;
	}

	/**
	 * This method is used to compare two map by key value pair
	 * 
	 * @author Shingare_S
	 * @param 1.map
	 *            to be compare
	 * @return Boolean
	 */

	public static Boolean Compare_Map_By_Key_Value(HashMap<String, String> ActualMap,
			HashMap<String, String> ExpectedMap) {
		boolean isMatched = true;
		System.out.println("Actual Map: " + ActualMap);
		System.out.println("Expeccted Map: " + ExpectedMap);

		for (Map.Entry<String, String> entry : ActualMap.entrySet()) {
			String k = entry.getKey();
			String v = entry.getValue();
			// System.out.println("Key: " + k + ", Value: " + v);
			for (Map.Entry<String, String> entry1 : ExpectedMap.entrySet()) {
				String k1 = entry1.getKey();
				String v1 = entry1.getValue();
				// System.out.println("Key: " + k + ", Value: " + v);
				if (k.contentEquals(k1)) {
					if (v.contentEquals(v1)) {
						System.out.println("Matched Key: " + k + ", Value: " + v);
						// isMatched = true;
					} else {
						isMatched = false;
					}

				}

			}
		}
		return isMatched;

	}

	/**
	 * This method is used to dynamically retrieve List<WebElement>.
	 * 
	 * @author Shingare_s
	 * @param xPath
	 *            The xpath to be manipulated
	 * @param replaceWith
	 *            String to be replaced
	 * @param replaceTo
	 *            String to be replaced with
	 * @return List<WebElement> This returns List<WebElement> after dynamically
	 *         retrieval of it using xpath
	 */
	public List<WebElement> perform_GetListOfElementsByXPath(String xPath, String replaceWith, String replaceTo) {
		List<WebElement> webElement = null;
		String finalXpath = null;
		try {
			finalXpath = xPath.replaceAll(replaceWith, replaceTo);
			webElement = driver.findElements(By.xpath(finalXpath));
		} catch (Exception e) {
			logError("Failed to get elements with xpath - " + finalXpath + " - " + e.getMessage());
		}
		return webElement;
	}

	@SuppressWarnings("rawtypes")
	public static void swipeScreen(String dir) {
		System.out.println("swipeScreen(): dir: '" + dir + "'"); // always log
																	// your
																	// actions

		// Animation default time:
		// - Android: 300 ms
		// - iOS: 200 ms
		// final value depends on your app and could be greater
		final int ANIMATION_TIME = 15000; // ms

		final int PRESS_TIME = 2000; // ms

		int edgeBorder = 10; // better avoid edges
		PointOption pointOptionStart, pointOptionEnd;

		// init screen variables
		Dimension dims = appiumDriver.manage().window().getSize();

		// init start point = center of screen
		pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

		switch (dir) {
		case "DOWN": // center of footer
			pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
			break;
		case "UP": // center of header
			pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
			break;
		case "LEFT": // center of left side
			pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
			break;
		case "RIGHT": // center of right side
			pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
			break;
		default:
			throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
		}

		// execute swipe using TouchAction
		try {
			new TouchAction(appiumDriver).press(pointOptionStart)
					// a bit more reliable when we add small wait
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME))).moveTo(pointOptionEnd).release()
					.perform();
			logInfo("Swipe Action has Performed");
		} catch (Exception e) {
			System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
			return;
		}

		// always allow swipe action to complete
		try {
			Thread.sleep(ANIMATION_TIME);
		} catch (InterruptedException e) {
			// ignore
		}
	}

	public enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}

	public static boolean performClickUsingJquery() {

		try {
			((JavascriptExecutor) appiumDriver).executeScript("$('#submitBtn').trigger('tap')");
		} catch (Exception e) {
			logError("Failed to click on element using jquery - " + e.getMessage());
		}
		return true;
	}

	/*
	 * @Purpose - This method 'WaitforelementToBeClickable' is used to provide
	 * selenium explicit wait on condition,elementToBeClickable
	 */
	public static void WaitforelementToBeClickable(WebElement element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			// e.printStackTrace();
			// Assert.fail("Timeout ,as Element is not Clickable",
			// e.getCause());
			throw e;
		}
	}

	public static boolean ScrollToEnd(String maxSwipes) {
		boolean isPresent = false;
		try {
			WebElement field = null;

			try {

				appiumDriver.findElement(MobileBy.AndroidUIAutomator(
						"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollToEnd(100,100).instance(0))"));

				logInfo("Scrolling has performed");

				field = appiumDriver.findElement(By.id("refreshTextView"));

				click(field);
				isPresent = true;

			} catch (NoSuchElementException ex) {
				isPresent = false;
			}

			return isPresent;

		} catch (Exception ex) {
			System.out.println("Failed to Select Date" + ex.getMessage());
			return isPresent;
		}

	}

	public static boolean ScrollIntoView(String visibleText) {

		try {

			appiumDriver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true).instance(0)).setMaxSearchSwipes(1000).scrollIntoView(new UiSelector().text(\""
							+ visibleText + "\").instance(0))"));
			return true;

		} catch (Exception ex) {
			System.out.println("Failed to Scroll " + ex.getMessage());
			return false;
		}
	}

	public static boolean ScrollIntoViewWithoutMaxSwipes(String visibleText) {

		try {

			appiumDriver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
							+ visibleText + "\").instance(0))"));

			return true;

		} catch (Exception ex) {
			System.out.println("Failed to Scroll " + ex.getMessage());
			return false;
		}
	}

	public static boolean EnableData(boolean isEnable) {
		List<String> Args = new ArrayList<String>();
		Map<String, Object> Command = new HashMap<String, Object>();

		try {

			if (isEnable) {
				Args = Arrays.asList("data", "enable");

			} else {
				Args = Arrays.asList("data", "disable");
			}

			Command.put("command", "svc");
			Command.put("args", Args);
			appiumDriver.executeScript("mobile: shell", Command);
			logInfo("Mobile data is =" + isEnable);
			return true;

		} catch (Exception ex) {
			System.out.println("Failed to Scroll " + ex.getMessage());
			return false;
		}
	}

	public static boolean changeNetworkPreferenceUsingADBCommands(String networkType) {
		// List<String> Args = new ArrayList<String>();
		// Map<String, Object> Command = new HashMap<String, Object>();

		try {

			List<String> Args1 = Arrays.asList("put", "global preferred_network_mode " + networkType);
			Map<String, Object> command1 = new HashMap<String, Object>();
			command1.put("command", "settings");
			command1.put("args", Args1);
			appiumDriver.executeScript("mobile: shell", command1);

			List<String> Args2 = Arrays.asList("put", "global preferred_network_mode1 " + networkType);
			Map<String, Object> command2 = new HashMap<String, Object>();
			command2.put("command", "settings");
			command2.put("args", Args2);
			appiumDriver.executeScript("mobile: shell", command2);

			logInfo("Mobile prefered network has changed to =" + networkType);
			return true;

		} catch (Exception ex) {
			System.out.println("Failed to Scroll " + ex.getMessage());
			return false;
		}
	}

	public static boolean installAppUsingADBCommands(String appPath) {
		try {
			List<String> Args1 = Arrays.asList("emulator-5555", "install helloWorld.apk");
			Map<String, Object> command1 = new HashMap<String, Object>();
			command1.put("command", "-s");
			command1.put("args", Args1);
			appiumDriver.executeScript("mobile: shell", command1);
			logInfo("updated version of app has installed=" + appPath);
			return true;

		} catch (Exception ex) {
			System.out.println("Failed to Scroll " + ex.getMessage());
			return false;
		}
	}

	public static boolean attachImageThroughCamera() {
		try {
			UploadImage oUploadImage = new UploadImage(appiumDriver);
			oUploadImage.CameraClick();
			oUploadImage.OpenCamera();
			oUploadImage.CameraShutter();
			oUploadImage.CloseCamera();
			return true;
		} catch (Exception e) {
			System.out.println("Failed to attach image through Camera " + e.getMessage());
			return false;
		}

	}

	public static boolean attachImageThroughGallery() {
		try {
			UploadImage oUploadImage = new UploadImage(appiumDriver);

			oUploadImage.CameraClick();
			Thread.sleep(3000);
			oUploadImage.OpenGallery();
			Thread.sleep(3000);
			oUploadImage.SelectImage();
			oUploadImage.CloseGallery();
			return true;
		} catch (Exception e) {
			System.out.println("Failed to attach image through Galley " + e.getMessage());
			return false;
		}

	}

	public static boolean getListofFilesPresentInFileFolder() {
		try {

			List<String> Args1 = Arrays.asList("-Ral", "-Ral / | grep -i document");
			Map<String, Object> command1 = new HashMap<String, Object>();
			command1.put("command", "ls");
			command1.put("args", Args1);
			// String files=
			appiumDriver.executeScript("mobile: shell", command1);
			logInfo("updated version of app has installed=");
			return true;

		} catch (Exception ex) {
			System.out.println("Failed to Scroll " + ex.getMessage());
			return false;
		}
	}

	public static boolean pressDownKeyUsingADBCommands() {

		try {

			List<String> Args1 = Arrays.asList("keyevent", "20");
			Map<String, Object> command1 = new HashMap<String, Object>();
			command1.put("command", "input");
			command1.put("args", Args1);
			appiumDriver.executeScript("mobile: shell", command1);

			return true;

		} catch (Exception ex) {
			System.out.println("Failed to Scroll " + ex.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to dynamically retrieve a WebElement using id.
	 * 
	 * @author hingorani_a
	 * @param id
	 *            The id to be used to retrieve element
	 * @return WebElement This returns WebElement after retrieval of it using id
	 */
	public static WebElement perform_GetElementById(String id) {
		WebElement webElement = null;
		try {
			webElement = appiumDriver.findElementById(id);
		} catch (Exception e) {
			logError("Failed to get element with id - " + id + " - " + e.getMessage());
		}
		return webElement;
	}
	
	/**
	 * @author hingorani_a
	 * @return
	 */
	public static boolean hideKeyboard() {
		try {
			appiumDriver.hideKeyboard();
			logInfo("Successfully hid keyboard");
			return true;
		} catch (Exception e) {
			logError("Failed to hide keyboard " + e.getMessage());
			return false;
		}
	}
	
	/**
	 * @author hingorani_a
	 * @return
	 */
	public static boolean swipeAndWait(String swipeOption, int sleepInMilliSecs) {
		try {
			swipeScreen(swipeOption);
			threadsleep(sleepInMilliSecs);
			return true;
		} catch (Exception e) {
			logError("Failed to Swipe " + swipeOption + " and wait for " + sleepInMilliSecs + 
					" ms - "+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method takes waits for an element until it is retrieved. It loops until StaleElementReferenceException 
	 * is by passed and element is retrieved
	 * @author hingorani_a 
	 * @param xpath The xpath to retrieve element which can be Stale
	 * @return WebElement This returns element once retrieved
	 */
	public MobileElement WaitUntilElementIsStaleById(AppiumDriver<MobileElement> driver, String id)
	{   
		boolean staleElement = true; 
		MobileElement element = null;
		while(staleElement){

			try{
				element = driver.findElement(By.id(id));
				staleElement = false;
			} catch(StaleElementReferenceException e){
				staleElement = true;
			}
		}
		return element;
	}
	
	/**
	 * This method is used to get Value of the list of Elements mentioned
	 * @param webElementsList The Element list for whose values are needed
	 * @return ArrayList This returns list of values corresponding to the 
	 * list of Elements sent as a parameter to this function
	 */
	public static ArrayList<String> perform_convertListOfElements_ToListOfStrings(List<WebElement> webElementsList){
		ArrayList<String> listOfStrings = new ArrayList<>();
		for(WebElement element:webElementsList){
			listOfStrings.add(element.getText());
		}
		return listOfStrings;
	}
	
	/**
	 * @author hingorani_a
	 * @return
	 */
	public static String getElementTextValue(WebElement element) {
		String elementText = null;
		try {
			
			ControlActions_MobileApp.waitForVisibility(element, 10000);
			elementText = element.getText();
			logInfo("Element's text found as " + elementText);
		} catch (Exception ex) {
			logError("Failed to get Element's text " + ex.getMessage());
		}
		return elementText;
	}
	
	/*
	 * @Purpose - This method 'isElementEnabled' is used to find whether the
	 * WebElement is enabled or not
	 */
	public static boolean isElementEnabled(WebElement webElement) {
		try {
			webElement.isEnabled();
			return true;
		} catch (ElementNotVisibleException e) {
			logError("Element not visible");
			return false;
		} catch (StaleElementReferenceException e) {
			logError("Stale Element reference Exception ");
			return false;
		} catch (Exception e) {
			logError("Couldn't find element");
			return false;

		}

	}
	
	/**
	 * This method is used to dynamically retrieve WebElement.
	 * 
	 * @author hingorani_a
	 * @param xPath - The xpath to be manipulated
	 * @param replaceWith - String to be replaced
	 * @param replaceTo - String to be replaced with
	 * @return WebElement This returns WebElement after dynamically retrieval of it using xpath
	 */
	public static List<MobileElement> perform_GetElementsByXPath(String xPath, String replaceWith, String replaceTo) {
		List<MobileElement> element = null;
		String finalXpath = null;
		try {
			finalXpath = xPath.replaceAll(replaceWith, replaceTo);
			element = appiumDriver.findElementsByXPath(finalXpath);
		} catch (Exception e) {
			logError("Failed to get element with xpath - " + finalXpath + " - ");
		}
		return element;
	}
	
}
