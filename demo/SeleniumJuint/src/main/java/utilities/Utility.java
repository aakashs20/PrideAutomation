package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Utility {

    public static void takescreenshot (TakesScreenshot driver, int testID) throws IOException {
        Date currentdate = new Date();
        String screenshotfilename = "Test_"+testID+currentdate.toString().replace(" ","-").replace(":","-");
        File source=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String path = "C:\\Users\\u729097\\IdeaProjects\\demo\\test-output\\testscreenshots\\"+screenshotfilename+".jpg";
        File dest = new File(path);
        FileHandler.copy(source, dest);
    }

}
