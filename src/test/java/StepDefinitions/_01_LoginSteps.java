package StepDefinitions;

import Pages.DialogContent;
import Utilities.WebDriverUtility;
import io.cucumber.java.en.*;

public class _01_LoginSteps {
    DialogContent dc = new DialogContent();
    @Given("Navigate to Amazon")
    public void navigateToBasqar() {
        WebDriverUtility.getDriver().get("https://amazon.de/");
        WebDriverUtility.getDriver().manage().window().maximize();
        dc.findAndClick("getAcceptCookies");
    }

    @When("Enter username and password and click login button")
    public void enterUsernameAndPasswordAndClickLoginButton() {
        dc.findAndClick("kontoUndListen");
        dc.findAndSend("userName","enter your email");
        dc.findAndSend("passWord","enter your password");
    }

    @Then("User should login successfully")
    public void userShouldLoginSuccessfully() {

        dc.findAndContainsText("halloBenutzer", "Benutzer");
    }

}
