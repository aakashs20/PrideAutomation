package pom_Package;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogOut_Page {
    private WebDriver driver;

    @FindBy(xpath = "//a[@class='signout']")
    private WebElement logOut;

    public LogOut_Page(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void clickLogOut()
    {
        logOut.click();
    }

}
