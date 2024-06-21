package pages;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utlity.Constants;
import utlity.Utility;

import java.util.List;

public class EmailPage {


    @FindBy(how = How.XPATH, using = "(//*[contains(text(), 'Labels')]/ancestor-or-self::*/DIV/DIV/DIV[contains(text(), 'Compose')])[1]")
    private WebElement composeButton;

    @FindBy(how = How.XPATH, using = "//input[@aria-label='To recipients']")
    private WebElement toInput;

    @FindBy(how = How.XPATH, using = "//*[@name='subjectbox']")
    private WebElement subject;
    @FindBy(how = How.CSS, using = "div[aria-label='Message Body']")
    private WebElement messageBody;
    @FindBy(how = How.XPATH, using = "//div[text()='Send']")
    private WebElement sendButton;

    @FindBy(how = How.XPATH, using = "//span[contains(@class,'bAq')]")
    private WebElement validate;

    @FindBy(how = How.CSS, using = ".y2")
    private List<WebElement> errorMessage;
    @FindBy(how = How.XPATH, using = "//tr[contains(., 'no subject')]")
    private List<WebElement> noSubject;

    @FindBy(how = How.CSS, using = ".Ha")
    private WebElement draft;
    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Draft saved')]")
    private WebElement draftSavedNotification;

    @FindBy(how = How.XPATH, using = "//*[text()='(no subject)']")
    private WebElement noSubjectMessage;

    WebDriver driver;

    public EmailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickonComposeButton() {
        composeButton.click();
    }

    public void userentersEmail() {

        toInput.sendKeys(Constants.USERNAME);

    }

    public void userentersInvalidEmail() {

        toInput.sendKeys(Constants.USERNAME + "786");

    }


    public void userentersSubject() {
        subject.sendKeys("Test Mail");
        subject.sendKeys(Keys.TAB);
    }

    public void userentersEmptySubjectandEmptyBody() {
        subject.sendKeys("");
        subject.sendKeys(Keys.TAB);
        messageBody.sendKeys("");

    }

    public void userentersEmptySubject() {
        subject.sendKeys("");
        subject.sendKeys(Keys.TAB);

    }

    public void userentersBody() {
        messageBody.sendKeys("This is a test email sent using Selenium WebDriver.");
    }

    public void userclicksonDraft() {
        draft.click();
    }


    public void userValidatesDraft() {
        Utility.Wait();
        Assert.assertTrue(draftSavedNotification.isDisplayed());
    }

    public void userclicksonSendButton() {
        sendButton.click();
    }


    public void userValidatesMessageSent() {
        Utility.Wait();
        Utility.Wait();

        Assert.assertEquals("Message sent", validate.getText());
    }

    public void userValidatesErrorMessageSent() {
        Utility.Wait();
        Utility.Wait();
        Utility.Wait();

        Assert.assertTrue(errorMessage.get(0).getText().contains("Address not found"));
    }

    public void userValidatesErrorMessageSubject() {
        Utility.Wait();
        Utility.Wait();
        Utility.Wait();
        Assert.assertTrue(noSubject.get(0).getText().contains("no subject"));
    }
}