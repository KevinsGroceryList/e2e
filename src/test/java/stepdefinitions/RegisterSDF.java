package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import poms.LoginPOM;
import poms.RegisterPOM;

public class RegisterSDF {

    RegisterPOM registerPOM;

    @Given("A user is on the register page")
    public void a_user_is_on_the_register_page() throws InterruptedException {

        DriverSingleton.getInstance().get("http://localhost:4200/register");
        Thread.sleep(5000);
        this.registerPOM = new RegisterPOM(DriverSingleton.getInstance());
        Assertions.assertEquals("http://localhost:4200/register", this.registerPOM.getCurrentUrl());
    }
    @When("A user inputs incorrect credentials on the register form")
    public void a_user_inputs_incorrect_credentials_on_the_register_form() {
        this.registerPOM.enterUsername("automatedUsername");
        this.registerPOM.enterPassword("pass123");
        this.registerPOM.enterFirstname("automated");
        this.registerPOM.enterLastname("test");
        this.registerPOM.submitRegister();
    }
    @Then("username is already taken will be displayed to the user")
    public void username_is_already_taken_will_be_displayed_to_the_user() {
        Assertions.assertEquals("username is already taken", this.registerPOM.getMessage());
    }

    @When("A user inputs correct credentials on the register form")
    public void a_user_inputs_correct_credentials_on_the_register_form() {
        this.registerPOM.enterUsername("automatedUniqueUsername");
        this.registerPOM.enterPassword("pass123");
        this.registerPOM.enterFirstname("automated");
        this.registerPOM.enterLastname("test");
        this.registerPOM.submitRegister();
    }
    @Then("user will be redirected to the login page")
    public void user_will_be_redirected_to_the_login_page() {
        this.registerPOM.waitForSuccessfulRegister();
        Assertions.assertEquals("http://localhost:4200/", this.registerPOM.getCurrentUrl());
    }
}
