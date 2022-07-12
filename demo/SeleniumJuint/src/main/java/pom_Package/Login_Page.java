package pom_Package;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_Page {
    private WebDriver driver;

    @FindBy(xpath = "//input[@name='ctl00$CPHContainer$txtUserLogin']")
    private WebElement loginID;

    @FindBy (xpath = "//input[@name='ctl00$CPHContainer$txtPassword']")
    private WebElement password;

    @FindBy (xpath = "//input[@name='ctl00$CPHContainer$btnLoginn']")
    private WebElement login;


    public Login_Page(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


    public void sendLoginID()
    {
        loginID.sendKeys("Mahendra0903");
    }

    public void sendPassword()
    {
        password.sendKeys("Mk@09031997");
    }

    public void clickLogin()
    {
        login.click();
    }
}
