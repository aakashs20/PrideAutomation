package com.project.pageobjects.pTracker;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.pTracker.Utils.Operations;
import com.project.utilities.ControlActions;

public class PaymentMilestonePage {
	
    Map<String, String> data;
    WebDriver driver ;
	WebDriverWait wait;
	ControlActions controlActions;
	Operations op;
	private static final int DELAY = 200;
	private int timeout = 20;

	public PaymentMilestonePage() {
		// TODO Auto-generated constructor stub
	}
	

    public PaymentMilestonePage(WebDriver driver) {
    	this();
    	this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, DELAY);
		controlActions = new ControlActions(driver);
		op=new Operations(driver);
		driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);
    }
    
    private final String popUpExistsText = "Do you want to obsolete this Payment Milestone";

    public PaymentMilestonePage(WebDriver driver, Map<String, String> data) {
        this(driver);
        this.data = data;
    }

    public PaymentMilestonePage(WebDriver driver, Map<String, String> data, int timeout) {
        this(driver, data);
        this.timeout = timeout;
    }
    
    
    @FindBy(xpath = "//*[@id='report_table_payrpt']/tbody/tr[1]//button[@title='Delete']")
    private WebElement deletePaymentMilestone;
    
    @FindBy(xpath = "//*[@class='ui-dialog-buttonset']//button[contains(text(),'OK')]")
    private WebElement okButton;
    
    
    /**
     * Click on Delete Button.
     *
     * @return the PaymentMilestone class instance.
     */
    public PaymentMilestonePage deletePaymentMilestone() {
     op.click(deletePaymentMilestone);
        return this;
    }
    
    
    
    /**
     * Click on Ok Button.
     *
     * @return the PaymentMilestonePage class instance.
     */
    public PaymentMilestonePage clickOkButton() {
    	okButton.click();
        return this;
    }
    
    /**
     * Verify that the page loaded completely.
     *
     * @return the PaymentMilestonePage class instance.
     */
    public PaymentMilestonePage verifyPopUpExists() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(popUpExistsText);
            }
        });
        return this;
    }
    
	public boolean getNoDataFoundText() {
		
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return driver.findElement(By.xpath("//*[@id='report_433477544053207551_catch']//span[contains(@class, 'data-not-found-text')]")).isDisplayed();
            }
        });
        return driver.findElement(By.xpath("//*[@id='report_433477544053207551_catch']//span[contains(@class, 'data-not-found-text')]")).isDisplayed();
	}

}
