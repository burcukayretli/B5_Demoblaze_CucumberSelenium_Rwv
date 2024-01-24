package com.demoblaze.step_definitions;

import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.LoginPage;
import com.demoblaze.utilities.ConfigurationReader;
import com.demoblaze.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;


public class Login_stepDefs {

    LoginPage loginPage=new LoginPage();
    HomePage homePage=new HomePage();

    @Given("The user navigates to website")
    public void the_user_navigates_to_website() {
        Driver.get().get(ConfigurationReader.get("url"));
    }

    @When("The user clicks on login button and enters valid credentials")
    public void the_user_clicks_on_login_button_and_enters_valid_credentials() {
        loginPage.login();
    }

    @Then("The user verifies welcome message")
    public void the_user_verifies_welcome_message() {
        homePage.verifyWelcomeMessage();
    }

    @When("The user clicks on login button and enters {string} {string} credentials")
    public void the_user_clicks_on_login_button_and_enters_credentials(String username, String password) {
        loginPage.login(username, password);
    }
    @Then("The user verifies welcome {string} message")
    public void the_user_verifies_welcome_message(String username) {
        homePage.verifyWelcomeMessage(username);
    }


    @When("The user clicks on login button and enters following credentials")
    public void theUserClicksOnLoginButtonAndEntersFollowingCredentials(Map<String,String> userInfo) {
        loginPage.login(userInfo.get("username"),userInfo.get("password"));
    }

    @Then("The user verifies invalid access message is {string}")
    public void theUserVerifiesInvalidAccessMessageIs(String exceptedMessage) {

        loginPage.verifyWrongCredentailsPopUpMessage(exceptedMessage);

    }
}
