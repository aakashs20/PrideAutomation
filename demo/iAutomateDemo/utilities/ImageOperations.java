package com.project.utilities;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import com.project.testbase.TestBase;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class ImageOperations extends TestBase {

	public static String mobileToCompareImagesLocation = System.getProperty("user.dir") + "\\test-data-files\\MobImagesToCompare\\";
	
	/** This method is used to get the element color in RGB format
	 * @author ahmed_tw
	 * @param driver [AppiumDriver<MobileElement>] : Driver instance which is currently running the automation
	 * @param elem [MobileElement] : The element whose color needs to be fetched 
	 * @return [List<Integer>] : List of RGB values of that element
	 */
	public static List<Integer> getElementColor(AppiumDriver<MobileElement> driver,MobileElement elem) {

		List<Integer> RGBValues =  new ArrayList<Integer>();
		try {
			Point point = elem.getLocation();

			//calculating the mid point of the element
			int centerx = point.getX()+elem.getSize().height/5;
			int centerY = point.getY()+elem.getSize().width/2;

			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

			BufferedImage image = ImageIO.read(scrFile);
			// Getting pixel color by position x and y 
			int clr=  image.getRGB(centerx,centerY); 
			Color elementColor = new Color(clr);
			
			RGBValues.add(elementColor.getRed());
			RGBValues.add(elementColor.getGreen());
			RGBValues.add(elementColor.getBlue());
			
			logInfo("Successfully Fetched the color of the element in the RGB as - " + RGBValues );
			return RGBValues;
		} catch (Exception e) {
			logError("Could not fet the color of the element " + e.getMessage());
			return RGBValues;
		}
	}
	
	/** This method is used to take the screenshot of a particular element and return it as BufferedImae
	 * @author ahmed_tw
	 * @param driver [AppiumDriver<MobileElement>] : Driver instance which is currently running the automation
	 * @param elem [MobileElement] : The element whose screenshot needs to be taken 
	 * @return [BufferedImage] : Screenshot of the element
	 */
	public static BufferedImage takeElementScreenshot(AppiumDriver<MobileElement> driver,MobileElement ele){

		try{
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

			BufferedImage  fullImg = ImageIO.read(scrFile);

			Point point = ele.getLocation();
			int eleWidth = ele.getSize().getWidth();
			int eleHeight = ele.getSize().getHeight();

			BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(), eleWidth,eleHeight);

			logInfo("Successfully took the element screenshot");
			return eleScreenshot ;
		} catch (IOException e) {
			logError("Failed to take element screenshot" + e.getMessage());
			return null;
		}
	}
	
	/** This method is used to take the screenshot of element and store it in the Folder "MobImagesToCompare"
	 * @author ahmed_tw
	 * @param driver driver [AppiumDriver<MobileElement>] : Driver instance which is currently running the automation
	 * @param elem [MobileElement] : The element whose screenshot needs to be taken and stored
	 * @param imgFileName [String] : Name for the imagefile to be created and saved
	 * @return [String] : Location of the image file created
	 */
	public static String takeElementScreenshotAndStoreIt(AppiumDriver<MobileElement> driver,MobileElement ele, String imgFileName){

		File screenshotLocation = null;
		try{
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

			BufferedImage  fullImg = ImageIO.read(scrFile);
			//Get the location of element on the page
			Point point = ele.getLocation();
			//Get width and height of the element
			int eleWidth = ele.getSize().getWidth();
			int eleHeight = ele.getSize().getHeight();
			//Crop the entire page screenshot to get only element screenshot
			BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(), eleWidth,eleHeight);
			ImageIO.write(eleScreenshot, "png", scrFile);

			String path = "\\test-data-files\\MobImagesToCompare\\" + imgFileName + "" + ".png";

			screenshotLocation = new File(System.getProperty("user.dir") + "\\" + path);

			FileUtils.copyFile(scrFile, screenshotLocation);
			logInfo("Successfully taken screenshot and stored in the directory MobImagesToCompare");
		} catch (IOException e) {
			logError("Could not perform the action " + e.getMessage());
			return null;
			
		}
		return screenshotLocation.toString();
	}

	/** This method is used to compare 2 Buffered Images pixel to pixel for equality
	 * @author ahmed_tw
	 * @param imgCaptured [BufferedImage] : The currently captured image from the application
	 * @param imgToCompareName [String] : The name of the image with which comparision need to be carried out (the directory is already known)
	 * @return [boolean] : True if the images are equal else false.
	 */
	public boolean compareImage(BufferedImage imgCaptured, String imgToCompareName ) {
		BufferedImage imgFromDisk = null;
		try {
			File imgInByte = new File (mobileToCompareImagesLocation+imgToCompareName);
			imgFromDisk = ImageIO.read(imgInByte);

			if (imgCaptured.getWidth() != imgFromDisk.getWidth() || imgCaptured.getHeight() != imgFromDisk.getHeight()) {
				logInfo("The Images are Not Equal");
				return false;
			}
			int width  = imgCaptured.getWidth();
			int height = imgCaptured.getHeight();
			// Loop over every pixel.
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					// Compare the pixels for equality.
					if (imgCaptured.getRGB(x, y) != imgFromDisk.getRGB(x, y)) {
						logInfo("The Images are Not Equal");
						return false;
					}
				}
			}
			logInfo("The Images are Equal");
			return true;
		} catch (Exception e) {
			logError("Could Not check for Equality of Images" + e.getMessage());
			return false;
		}

	}

	/** This method accepts 2 Buffered images as parameters and compare them for equality
	 * @author ahmed_tw
	 * @param imgCaptured [BufferedImage] : The currently captured image from the application
	 * @param imgFromDisk [BufferedImage] : The Image form the disk with which comparision need to be done
	 * @return [boolean] : True if the images are equal else false.
	 */
	public boolean compareImage(BufferedImage imgCaptured, BufferedImage imgFromDisk ) {
		try {
			if (imgCaptured.getWidth() != imgFromDisk.getWidth() || imgCaptured.getHeight() != imgFromDisk.getHeight()){
				logInfo("The Images are Not Equal");
				return false;
			}
			int width  = imgCaptured.getWidth();
			int height = imgCaptured.getHeight();
			// Loop over every pixel.
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					// Compare the pixels for equality.
					if (imgCaptured.getRGB(x, y) != imgFromDisk.getRGB(x, y)) {
						logInfo("The Images are Not Equal");
						return false;
					}
				}
			}
			logInfo("The Images are Equal");
			return true;
		} catch (Exception e) {
			logError("Could Not check for Equality of Images" + e.getMessage());
			return false;
		}

	}
	
}

