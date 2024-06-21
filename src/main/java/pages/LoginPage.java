package pages;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utlity.Constants;
import utlity.Utility;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//input[@type='email']")
    private WebElement emailaddress;

    @FindBy(how = How.XPATH, using = "//input[@type='password']")
    public WebElement password;

    @FindBy(how = How.XPATH, using = "//*[text()='Next']")
    private WebElement nextButton;

    public void userentersEmail() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", emailaddress);
        emailaddress.sendKeys(Constants.USERNAME);
        userClicksOnNext();
        Utility.Wait();
    }

    public void userentersPassword() {
        //WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
        Utility.scrollIntoView(driver, password);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", password);
        password.sendKeys(Constants.PASSWORD);
    }


    public void userClicksOnNext() {

        nextButton.click();

    }
}


