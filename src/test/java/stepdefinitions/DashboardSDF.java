package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import poms.DashboardPOM;
import poms.LoginPOM;

public class DashboardSDF {

    LoginPOM loginPOM;
    DashboardPOM dashboardPOM;


    @Given("A user is logged in")
    public void a_user_is_logged_in() {
        DriverSingleton.getInstance().get("http://localhost:4200");
        this.loginPOM = new LoginPOM(DriverSingleton.getInstance());
        this.loginPOM.enterUsername("automatedUsername");
        this.loginPOM.enterPassword("pass123");
        this.loginPOM.submitLogin();
        this.loginPOM.waitForSuccessfulLogin();
        // Write code here that turns the phrase above into concrete actions
        Assertions.assertEquals("http://localhost:4200/dashboard", this.loginPOM.getCurrentUrl());
        this.dashboardPOM = new DashboardPOM(DriverSingleton.getInstance());
    }
    @When("a user inputs a item in the create item input")
    public void a_user_inputs_a_item_in_the_create_item_input() {
       this.dashboardPOM.enterItem("grape");
       this.dashboardPOM.submitItem();
    }
    @Then("the item will be added to the item list")
    public void the_item_will_be_added_to_the_item_list() {
        // Write code here that turns the phrase above into concrete actions
        this.dashboardPOM.waitForListToAddItem();
        Assertions.assertTrue(this.dashboardPOM.isItemPresent("grape"));
    }


    @When("a user clicks on an item")
    public void a_user_clicks_on_an_item() {
        // Write code here that turns the phrase above into concrete actions
        Assertions.assertTrue(this.dashboardPOM.clickItemGivenName("milk"));
    }
    @Then("the item will toggle in cart")
    public void the_item_will_toggle_in_cart() {
        Assertions.assertTrue(this.dashboardPOM.isItemInCart("milk"));
    }

    @When("a user clicks the delete button on an item")
    public void a_user_clicks_the_delete_button_on_an_item() {
        Assertions.assertTrue(this.dashboardPOM.clickDeleteBtn("grape"));
    }
    @Then("the item will be removed from the list")
    public void the_item_will_be_removed_from_the_list() {
        this.dashboardPOM.waitForListToRemoveItem();
        Assertions.assertFalse(this.dashboardPOM.isItemPresent("grape"));
    }

    @When("a user clicks the Check Out Button")
    public void a_user_clicks_the_check_out_button() {
        // Write code here that turns the phrase above into concrete actions
        this.dashboardPOM.clickCheckOutBtn();
    }
    @Then("all in cart items will be removed")
    public void all_in_cart_items_will_be_removed() {
        Assertions.assertFalse(this.dashboardPOM.isCheckoutBtnVisible());
    }
}
