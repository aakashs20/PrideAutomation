package testClass_Package;

import dataProvider.ConfigFileReader;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import testBrowserSetup.Base;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class VerifyLoginPage extends Base {
    static WebDriver driver;
    static pom_Package.Login_Page login;
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
    public void verifyLoginButton() throws InterruptedException, IOException {
       testID=100;
       String url = driver.getCurrentUrl();
       System.out.println(url);
       Thread.sleep(2000);
       Assert.assertEquals(url,"http://www.testyou.in/Student/StudentIndex.aspx");
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
