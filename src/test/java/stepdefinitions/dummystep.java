package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class dummystep {


    @When("user login")
    public void user_login() {
        System.out.println("user login ");
    }
    @When("user validate data")
    public void user_validate_data() {
        System.out.println("passs ");
    }
    @Then("user logout")
    public void user_logout() {
        System.out.println("logout");
    }

}
