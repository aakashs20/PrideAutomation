package pom_Package;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home_Page {
    private WebDriver driver;

    @FindBy(xpath = "//div[@class='test_category_img']")
    private WebElement testCategories;

    @FindBy(xpath = "//div[@class='test_list_img']")
    private WebElement testList;


    public Home_Page(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


    public void clickTestCategories()
    {
        testCategories.click();
    }

    public void clickTestList()
    {
        testList.click();
    }
}
