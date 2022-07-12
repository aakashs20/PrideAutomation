package testClass_Package;

import dataProvider.ConfigFileReader;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pom_Package.Home_Page;
import pom_Package.LogOut_Page;
import testBrowserSetup.Base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class VerifyLogoutPage extends Base {
    static WebDriver driver;
    static pom_Package.Login_Page login;
    static pom_Package.Home_Page homepage;
    static pom_Package.LogOut_Page logout;
    static ConfigFileReader configFileReader;
    int testID;

    @BeforeClass
    public static void beforeClass()
    {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\u729097\\selenium\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        login = new pom_Package.Login_Page(driver);
        homepage = new Home_Page(driver);
        logout = new LogOut_Page(driver);
        configFileReader = new ConfigFileReader();
    }

    @Before
    public void before() throws IOException {
        driver.get(configFileReader.getApplicationUrl());
        driver.manage().window().maximize();
        login.sendLoginID();
        login.sendPassword();
        login.clickLogin();
    }

    @Rule
    public RetryTest retry = new RetryTest(3);

    @Test
    public void verifyLogOutButton() throws InterruptedException, IOException {
        testID=200;
        logout.clickLogOut();
        Thread.sleep(2000);
        String expectedUrl = driver.getCurrentUrl();
        System.out.println(expectedUrl);
        Thread.sleep(2000);
        Assert.assertEquals(expectedUrl,configFileReader.pageurl());
    }

    @After
    public void logOut() throws IOException, InterruptedException {
        //if(ITestResult.FAILURE == result.getStatus())
        //{
        //Utility.takescreenshot((TakesScreenshot)driver,testID);
        //}
        Thread.sleep(3000);
    }

    @AfterClass

    public static void clearObjects()
    {
        login=null;
        driver.quit();
        driver=null;
    }
}
