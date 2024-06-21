package step_definations;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.EmailPage;
import utlity.Utility;

public class StepDefination {
    public static WebDriver driver;

    EmailPage emailPage;

    LoginPage loginPage;

    public StepDefination() {
        driver = Hooks.driver;

        emailPage = new EmailPage(driver);
        loginPage = new LoginPage(driver);

    }


    @Given("^User enters Username$")
    public void user_enters_Username() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Utility.Wait();
        loginPage.userentersEmail();
        loginPage.userClicksOnNext();
    }

    @Given("^User enters Password$")
    public void user_enters_Password() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        loginPage.userentersPassword();

    }

    @Given("^User Clicks on login button$")
    public void user_Clicks_on_login_button() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        loginPage.userClicksOnNext();

    }

    @Given("^User clicks on compose tab$")
    public void user_clicks_on_compose_tab() throws Throwable {
        emailPage.clickonComposeButton();
        Utility.Wait();
    }

    @When("^User enters email address$")
    public void user_enters_email_address() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        emailPage.userentersEmail();
    }

    @When("^User enters subject$")
    public void user_enters_subject() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        emailPage.userentersSubject();
    }

    @When("^User enters message body$")
    public void user_enters_message_body() throws Throwable {
        emailPage.userentersBody();
    }

    @Then("^User clicks on send button$")
    public void user_clicks_on_send_button() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        emailPage.userclicksonSendButton();
    }

    @Then("^User validates message sent$")
    public void user_validates_message_sent() throws Throwable {
        emailPage.userValidatesMessageSent();
    }

    @When("^User enters invalid email address$")
    public void user_enters_invalid_email_address() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        emailPage.userentersInvalidEmail();

    }

    @Then("^User validates error message sent$")
    public void user_validates_error_message_sent() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        emailPage.userValidatesErrorMessageSent();

    }

    @When("^I leave both the subject and email body empty$")
    public void i_leave_both_the_subject_and_email_body_empty() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        emailPage.userentersEmptySubjectandEmptyBody();
    }

    @When("^I click the save draft button$")
    public void i_click_the_save_draft_button() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        emailPage.userclicksonDraft();

    }

    @Then("^I should see a confirmation message that the draft is saved$")
    public void i_should_see_a_confirmation_message_that_the_draft_is_saved() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        emailPage.userValidatesDraft();
    }

    @When("^I leave the subject field blank$")
    public void i_leave_the_subject_field_blank() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        emailPage.userentersEmptySubject();
    }

    @Then("^I should see an error message indicating the subject is required$")
    public void i_should_see_an_error_message_indicating_the_subject_is_required() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        emailPage.userValidatesErrorMessageSubject();

    }
}



