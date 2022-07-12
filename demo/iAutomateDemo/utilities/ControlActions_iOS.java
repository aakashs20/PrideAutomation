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
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions; 
import org.openqa.selenium.mobile.NetworkConnection;
import org.openqa.selenium.mobile.NetworkConnection.ConnectionType;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.project.testbase.TestBase;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class ControlActions_iOS extends TestBase {
	public class appiumDriver {

	}

//static	IOSDriver<IOSElement> driveriOS;
	WebDriver driver;
	public static Actions action;
	public static WebDriverWait wait;
	public static WebDriverWait shortWait;
	public static IOSDriver<IOSElement> driveriOS;

	public ControlActions_iOS(IOSDriver<IOSElement> driver) {
		ControlActions_iOS.driveriOS = driver;
		wait = new WebDriverWait(driver, 10);
		action = new Actions(driveriOS);
		PageFactory.initElements(new AppiumFieldDecorator(driveriOS), this);
		System.out.println("contructor driver" + driveriOS.toString());
		System.out.println(driveriOS);

	}

	public static int expectedAirplaneStatus;

	public static By airplaneBtn = MobileBy.xpath("//XCUIElementTypeSwitch[@name=\\\"airplane-mode-button\\\"]");

	public static By Allow = MobileBy.xpath("//XCUIElementTypeButton[@name='Allow']");

	public static String settingsMenu = "com.apple.Preferences";

	public static String Wifi = "//XCUIElementTypeSwitch[@name=\"wifi-button\"]";
	public static String stock = "com.apple.stocks";

	public static void waitForVisibility(WebElement e) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated((By) e));
		} catch (Exception ex) {
			throw ex;

		}
	}

	public static void acceptAlert() {

		driveriOS.switchTo().alert().accept();

	}

	public void deviceNetworkToggle() {
		NetworkConnection mobileDriver = (NetworkConnection) driveriOS;
		if (mobileDriver.getNetworkConnection() != ConnectionType.AIRPLANE_MODE) {
			// enabling Airplane mode
			ConnectionType tyo = mobileDriver.setNetworkConnection(ConnectionType.AIRPLANE_MODE);
			logInfo(tyo.toString());

		}
	}

	 
	public static boolean enableAirplaneMode() {
		boolean flag = false;
		try {
			ControlActions_iOS.gettouchaction(358, 30, 354, 8); 
			Thread.sleep(10000);
			int expectedAirplaneStatus = Integer.parseInt(driveriOS
					.findElement(By.xpath("//XCUIElementTypeSwitch[@name=\\\"wifi-button\\\"]")).getAttribute("value"));

			if (expectedAirplaneStatus == 0) {

				driveriOS.findElement(airplaneBtn).click();

				System.out.println("Airplane mode is Turned On:");

				ControlActions_iOS.gettouchaction(203, 880, 199, 781);
				flag = true;

			} else {

				System.out.println("Airplane mode is already On");

				flag = false;
				;

			}
		} catch (Exception e) {
			logInfo(e.getMessage());
		}
		return flag;

	}

	public static boolean disableAirplaneMode() {

		expectedAirplaneStatus = Integer.parseInt(driveriOS.findElement(airplaneBtn).getAttribute("value"));

		if (expectedAirplaneStatus == 1) {

			driveriOS.findElement(airplaneBtn).click();

			System.out.println("Airplane mode is Turned OFF:");

			return true;

		} else {

			System.out.println("Airplane mode is already OFF");

			return false;

		}

	}

	public static boolean waitForVisibility(WebElement e, long waitTime) {

		boolean present = false;
		try {

			WebDriverWait wait = new WebDriverWait(driveriOS, waitTime);
			wait.until(ExpectedConditions.visibilityOf(e));
			if (e.isDisplayed()) {
				present = true;
			}
		} catch (

		Exception ex) {

			present = false;
			System.out.println(ex.getLocalizedMessage());
			System.out.println(ex.getMessage());
			throw ex;

		}
		return present;
	}

	public static boolean click(WebElement formName, long waitTime) {
		try {
			WebDriverWait wait = new WebDriverWait(driveriOS, waitTime);

			wait.until(ExpectedConditions.visibilityOf(formName));
			wait.until(ExpectedConditions.elementToBeClickable(formName));
			formName.click();
			return true;
		} catch (Exception ex) {

			return false;

		}
	}

	public static boolean click(WebElement formName) {
		try {
			WebDriverWait wait = new WebDriverWait(driveriOS, 10);

			wait.until(ExpectedConditions.visibilityOf(formName));
			formName.click();
			return true;
		} catch (Exception ex) {
			return false;

		}
	}

	public static void ClearText(WebElement e) {
		try {
			wait.until(ExpectedConditions.visibilityOf(e));
			e.clear();
		} catch (Exception ex) {
			// e.printStackTrace();
			// Assert.fail(e.getMessage());
			throw ex;

		}
	}

	public static void sendKeys(WebElement e, String txt) {

		try {
//			System.out.println("Entering Text");
			e.clear();
			e.sendKeys(txt);
//			System.out.println("Entered Text");
//			driveriOS.navigate().back();
		} catch (Exception ex) {
			// e.printStackTrace();
			// Assert.fail(e.getMessage());
			throw ex;

		}
	}

	public static String getAttribute(WebElement e, String attribute) {
		try {
			waitForVisibility(e);
			return e.getAttribute(attribute);
		} catch (Exception ex) {
			// e.printStackTrace();
			// Assert.fail(e.getMessage());
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
		action = new Actions(driveriOS);
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

	/**
	 * This method is used to perform tab action.
	 * 
	 * @author Priya_Kole
	 */

	public static void performTabAction() {
		action = new Actions(driveriOS);
		try {
			action.sendKeys(Keys.ALT);
			action.sendKeys(Keys.TAB);
			action.build().perform();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Issue While Enter tab", e.getCause());
		}
	}

	/**
	 * This method is used to action enter without pressing tab key.
	 * 
	 * @author Priya_Kole
	 * @param element : path of webelement
	 * @param Text    : Text to be entered
	 */

	public static void actionEnterText(WebElement element, String Text) {
		try {
			action = new Actions(driveriOS);
			action.moveToElement(element).click().perform();
			action.sendKeys(Text);
			action.build().perform();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Issue While Enter tab", e.getCause());
		}
	}

	/**
	 * This method is used to action enter without pressing tab key.
	 * 
	 * @author Priya_Kole
	 * @param element : path of webelement
	 * @param Text    : Text to be entered
	 */

	public static void actionEnterTextWOTab(WebElement element, String Text) {
		try {
			action = new Actions(driveriOS);
			action.moveToElement(element).click().perform();
			action.sendKeys(Text);
			action.build().perform();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Issue While Enter tab", e.getCause());
		}
	}

	/**
	 * This method is used to scroll down to search element.
	 * 
	 * @author Priya_Kole
	 * @param VisibeTextName value of name attribute of element
	 */

	public static void JSScrollDown(String VisibeTextName) {
		try {
			HashMap<String, Object> scrollObject = new HashMap<>();
			scrollObject.put("direction", "down");
			scrollObject.put("name", VisibeTextName);
			driveriOS.executeScript("mobile:scroll", scrollObject);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());

		}
	}

	public static void JSScroll(WebElement ElementToFind) {
		try {

			RemoteWebElement element = (RemoteWebElement) ElementToFind;
			String elementID = element.getId();
			logInfo("Elemnet id is : " + elementID);
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", elementID); // Only for ‘scroll in element’
			scrollObject.put("direction", "down");
			driveriOS.executeScript("mobile:scroll", scrollObject);

//			HashMap<String, Object> scrollObject = new HashMap<>();
//			scrollObject.put("direction", "down"); 
//			
//			driveriOS.executeScript("mobile:scroll", scrollObject);
			logInfo("Scrolled down");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());

		}
	}

	/**
	 * This method is used to scroll up to search element.
	 * 
	 * @author Priya_Kole
	 * @param VisibeTextName value of name attribute of element
	 */

	public static void JSScrollUp(WebElement ElementToFind) {
		try {
			RemoteWebElement element = (RemoteWebElement) ElementToFind;
			String elementID = element.getId();
			logInfo("Elemnet id is : " + elementID);
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", elementID); // Only for ‘scroll in element’
			scrollObject.put("direction", "up");
			driveriOS.executeScript("mobile:scroll", scrollObject);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());

		}
	}

	public static void perform_scrollToElement_ByElement(WebElement element) {
		((JavascriptExecutor) driveriOS).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void perform_scrollToElement(String Dimention) {
		((JavascriptExecutor) driveriOS).executeScript("window.scrollBy(0," + Dimention + ")", "");
	}

	public static void perform_clickElement_ByJavaScript(WebElement element) {
		((JavascriptExecutor) driveriOS).executeScript("arguments[0].click();", element);
	}

	public static void perform_EnterText_ByJavaScript(WebElement element, String valueToEnter) {
		((JavascriptExecutor) driveriOS).executeScript("arguments[0].value=argument[1];", element, valueToEnter);

	}
 
	public static boolean gettouchaction(int startX, int startY, int endX, int endY) {
	 
		try {
			TouchAction<?>  touchAction = new TouchAction<>(driveriOS);
			PointOption<?> pointStart = PointOption.point(startX, startY);
			PointOption<?> pointEnd = PointOption.point(startY, startX);
			WaitOptions waitOption = WaitOptions.waitOptions(Duration.ofMillis(1000));
			touchAction.press(pointStart).waitAction(waitOption).moveTo(pointEnd).release().perform();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@SuppressWarnings("rawtypes")
	public static TouchAction getTapAction(int startX, int startY) {
		TouchAction touchAction = null;
		try {

			touchAction = new TouchAction(driveriOS).press(PointOption.point(startX, startY))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).release();
			touchAction.perform();
		} catch (Exception e) {
			logInfo("Tapped on X cordinate" + startX + "and Y Co-ordinate" + startY);
			e.printStackTrace();
		}
		return touchAction;
	}

	public static void swipe(int startX, int startY, int endX, int endY, int count) {
		// log.info("scroll from : startX " +startX + ", startY "+ startY+ ", to
		// endX "+
		// endX+ ",endY "+ endY);
		for (int i = 0; i < count; i++) {
			ControlActions_iOS.gettouchaction(startX, startY, endX, endY);
		}
	}

	/**
	 * This method is used to dynamically retrieve WebElement.
	 * 
	 * @author
	 * @param xPath       The xpath to be manipulated
	 * @param replaceWith String to be replaced
	 * @param replaceTo   String to be replaced with
	 * @return WebElement This returns WebElement after dynamically retrieval of it
	 *         using xpath
	 */
	public static WebElement perform_GetElementByXPath(String xPath, String replaceWith, String replaceTo) {
		WebElement webElement = null;
		String finalXpath = null;
		try {
			finalXpath = xPath.replaceAll(replaceWith, replaceTo);

			webElement = driveriOS.findElementByXPath(finalXpath);
		} catch (Exception e) {
			logInfo("Failed to get element with xpath - " + finalXpath + " - ");
		}
		return webElement;
	}

	public static MobileElement perform_GetMobileElementByXPath(String xPath, String replaceWith, String replaceTo) {
		MobileElement webElement = null;
		String finalXpath = null;
		try {
			finalXpath = xPath.replaceAll(replaceWith, replaceTo);

			webElement = driveriOS.findElementByXPath(finalXpath);
		} catch (Exception e) {
			logInfo("Failed to get element with xpath - " + finalXpath + " - ");
		}
		return webElement;
	}

	public static String perform_GetElementByXPath2(String xPath, String replaceWith, String replaceTo) {

		String finalXpath = null;
		try {
			finalXpath = xPath.replaceAll(replaceWith, replaceTo);

			// webElement = appiumDriver.findElementByXPath(finalXpath);
		} catch (Exception e) {
			logError("Failed to get element with xpath - " + finalXpath + " - " + e.getMessage());
		}
		return finalXpath;
	}

	/**
	 * This method is used to format date
	 * 
	 * @author Shingare_s
	 * @param 1. Date --String, 2.Date format --String
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
	 * @param 1. Date --String, 2.Date format --String
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
	 * @param 1. Date --String, 2.Date format --String
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
	 * @param 1. Date --String, 2.Date format --String
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
	 * This method is used to convert 24hr Format time into 12 hr formatted time.
	 * 
	 * @author Shingare_S
	 * @param String time-24 hr time format
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
	 * @param String time time format
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
	 * @param 1.Number of days as String--"1"
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
	 * @param 1.Number of days as String--"1"
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

	public void addTimeBySecondsDemo(Date date, int sec) {
		// int sec = 300;
		System.out.println("Given date:" + date);
		Calendar calender = Calendar.getInstance();
		calender.setTimeInMillis(date.getTime());
		calender.add(Calendar.SECOND, sec);
		Date changeDate = calender.getTime();
		System.out.println("changeDate ..:" + changeDate);
	}

	public static boolean compareTwoDate(String DateFormat, Date DateToBeCompare) throws ParseException {

		boolean isBefore = true;
		try {

			SimpleDateFormat sdf = new SimpleDateFormat(DateFormat);
			String date1 = sdf.format(new Date());
			Date date = sdf.parse(date1);
			logInfo("Actual Date = " + date);
			if (DateToBeCompare.before(date)) {
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

	/*
	 * @Purpose - This method 'isElementDisplayed' is used to find whether the
	 * WebElement is displayed or not
	 */
	public static boolean isElementDisplayed(WebElement webElement) {
		try {

			webElement.isDisplayed();
			return true;
		} catch (ElementNotVisibleException e) {
			logInfo("Element not visible");
			return false;
		} catch (StaleElementReferenceException e) {
			logInfo("Stale Element reference Exception ");
			return false;
		} catch (Exception e) {
			logInfo("Couldn't find element");
			return false;

		}

	}

	/**
	 * This method is used to add number of days to the current date
	 * 
	 * @author Shingare_S
	 * @param 1.WebElement to be pressed
	 * @return Touch action
	 */

	public static boolean getLongPressAction(MobileElement mobileElement) {

		try {
			IOSTouchAction touch = new IOSTouchAction(driveriOS);
			touch.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(mobileElement))
					.withDuration(Duration.ofSeconds(5))).release().perform();
			logInfo("Long pressed on WebElement");
			return true;

		} catch (Exception e) {
			logInfo("Long pressed on WebElement");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is used to compare two map by key value pair
	 * 
	 * @author Shingare_S
	 * @param 1.map to be compare
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
	 * @param xPath       The xpath to be manipulated
	 * @param replaceWith String to be replaced
	 * @param replaceTo   String to be replaced with
	 * @return List<WebElement> This returns List<WebElement> after dynamically
	 *         retrieval of it using xpath
	 */
	public List<WebElement> perform_GetListOfElementsByXPath(String xPath, String replaceWith, String replaceTo) {
		List<WebElement> webElement = null;
		String finalXpath = null;
		try {
			finalXpath = xPath.replaceAll(replaceWith, replaceTo);
			webElement = driver.findElements(By.xpath(finalXpath));
			// webElement = perform_waitUntilListPresent(By.xpath(finalXpath));
		} catch (Exception e) {
			logError("Failed to get elements with xpath - " + finalXpath + " - " + e.getMessage());
		}
		return webElement;
	}

	/**
	 * Performs screen scroll
	 *
	 * @param dir the direction of scroll
	 * @version java-client: 7.3.0
	 **/
	public void mobileScrollScreenIOS(Direction dir) {
		System.out.println("mobileScrollScreenIOS(): dir: '" + dir + "'"); // always log your actions

		// Animation default time:
		// - iOS: 200 ms
		// final value depends on your app and could be greater
		final int ANIMATION_TIME = 200; // ms
		final HashMap<String, String> scrollObject = new HashMap<String, String>();

		switch (dir) {
		case DOWN: // from down to up (! differs from mobile:swipe)
			scrollObject.put("direction", "down");
			break;
		case UP: // from up to down (! differs from mobile:swipe)
			scrollObject.put("direction", "up");
			break;
		case LEFT: // from left to right (! differs from mobile:swipe)
			scrollObject.put("direction", "left");
			break;
		case RIGHT: // from right to left (! differs from mobile:swipe)
			scrollObject.put("direction", "right");
			break;
		default:
			throw new IllegalArgumentException("mobileScrollIOS(): dir: '" + dir + "' NOT supported");
		}
		try {
			driveriOS.executeScript("mobile:scroll", scrollObject); // swipe faster then scroll
			Thread.sleep(ANIMATION_TIME); // always allow swipe action to complete
		} catch (Exception e) {
			System.err.println("mobileScrollIOS(): FAILED\n" + e.getMessage());
			return;
		}
	}

	/**
	 * Performs screen swipe
	 *
	 * @param dir the direction of swipe
	 * @version java-client: 7.3.0
	 * @return
	 **/
	public static boolean mobileSwipeScreenIOS(String dir) {
		System.out.println("mobileSwipeScreenIOS(): dir: '" + dir + "'"); // always log your actions
		final int ANIMATION_TIME = 200; // ms
		final HashMap<String, String> scrollObject = new HashMap<String, String>();

		switch (dir) {
		case "DOWN": // from up to down (! differs from mobile:scroll)
			scrollObject.put("direction", "down");
			break;
		case "UP": // from down to up (! differs from mobile:scroll)
			scrollObject.put("direction", "up");
			break;
		case "LEFT": // from right to left (! differs from mobile:scroll)
			scrollObject.put("direction", "left");
			break;
		case "RIGHT": // from left to right (! differs from mobile:scroll)
			scrollObject.put("direction", "right");
			break;
		default:
			throw new IllegalArgumentException("mobileSwipeScreenIOS(): dir: '" + dir + "' NOT supported");
		}
		try {
			driveriOS.executeScript("mobile:swipe", scrollObject);
			Thread.sleep(ANIMATION_TIME); // always allow swipe action to complete
			return true;
		} catch (Exception e) {
			System.err.println("mobileSwipeScreenIOS(): FAILED\n" + e.getMessage());
			return false;
		}
	}

	public enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}

	public static boolean performClickUsingJquery() {

		try {
			((JavascriptExecutor) driveriOS).executeScript("$('#submitBtn').trigger('tap')");
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
			e.printStackTrace();
			Assert.fail("Timeout ,as Element is not Clickable", e.getCause());
			throw e;
		}
	}

	public static boolean ScrollToEnd(String maxSwipes) {
		boolean isPresent = false;
		try {
			WebElement field = null;

			try {

				driveriOS.findElement(MobileBy.AndroidUIAutomator(
						"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollToEnd(100,100).instance(0))"));

				logInfo("Scrolling has performed");

				field = driveriOS.findElement(By.id("refreshTextView"));

				click(field, 30);
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

			driveriOS.findElement(MobileBy.AndroidUIAutomator(
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

			driveriOS.findElement(MobileBy.AndroidUIAutomator(
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
			driveriOS.executeScript("mobile: shell", Command);
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
			driveriOS.executeScript("mobile: shell", command1);

			List<String> Args2 = Arrays.asList("put", "global preferred_network_mode1 " + networkType);
			Map<String, Object> command2 = new HashMap<String, Object>();
			command2.put("command", "settings");
			command2.put("args", Args2);
			driveriOS.executeScript("mobile: shell", command2);

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
			driveriOS.executeScript("mobile: shell", command1);
			logInfo("updated version of app has installed=" + appPath);
			return true;

		} catch (Exception ex) {
			System.out.println("Failed to Scroll " + ex.getMessage());
			return false;
		}
	}

//	public static boolean attachImageThroughCamera() {
//		try {
//			UploadImage oUploadImage = new UploadImage(driveriOS);
//			oUploadImage.CameraClick();
//			oUploadImage.OpenCamera();
//			oUploadImage.CameraShutter();
//			oUploadImage.CloseCamera();
//			return true;
//		} catch (Exception e) {
//			System.out.println("Failed to attach image through Camera " + e.getMessage());
//			return false;
//		}
//
//	}
//
//	public static boolean attachImageThroughGallery() {
//		try {
//			UploadImage oUploadImage = new UploadImage(driveriOS);
//
//			oUploadImage.CameraClick();
//			Thread.sleep(3000);
//			oUploadImage.OpenGallery();
//			Thread.sleep(3000);
//			oUploadImage.SelectImage();
//			oUploadImage.CloseGallery();
//			return true;
//		} catch (Exception e) {
//			System.out.println("Failed to attach image through Galley " + e.getMessage());
//			return false;
//		}
//
//	}

	public static boolean getListofFilesPresentInFileFolder() {
		try {

			List<String> Args1 = Arrays.asList("-Ral", "-Ral / | grep -i document");
			Map<String, Object> command1 = new HashMap<String, Object>();
			command1.put("command", "ls");
			command1.put("args", Args1);
			// String files=
			driveriOS.executeScript("mobile: shell", command1);
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
			driveriOS.executeScript("mobile: shell", command1);

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
	 * @param id The id to be used to retrieve element
	 * @return WebElement This returns WebElement after retrieval of it using id
	 */
	public static WebElement perform_GetElementById(String id) {
		WebElement webElement = null;
		try {
			webElement = driveriOS.findElementById(id);
		} catch (Exception e) {
			logInfo("Failed to get element with id - " + id + " - ");
		}
		return webElement;
	}

	public static void ToggleDeviceWifi() {

		driveriOS.activateApp("com.apple.Preferences");
		driveriOS.findElementByXPath("//XCUIElementTypeSwitch[@name=\"wifi-button\"]").click();
		driveriOS.activateApp("com.safetychain.SCM.M2");
	}
}
