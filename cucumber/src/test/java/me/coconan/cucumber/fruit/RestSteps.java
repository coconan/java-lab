package me.coconan.cucumber.fruit;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RestSteps {
    @Given("the system knows about the following fruit:")
    public void theSystemKnowsAboutTheFollowingFruit(DataTable dataTable) {
        // throw new io.cucumber.java.PendingException();
    }

    @When("the client request GET \\/fruits")
    public void theClientRequestGETFruits() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the response should be JSON:")
    public void theResponseShouldBeJSON(String docString) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
