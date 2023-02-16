package me.coconan.cucumber.atm.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import me.coconan.cucumber.atm.Account;
import me.coconan.cucumber.atm.support.AtmUserInterface;

import static org.junit.Assert.assertTrue;

public class TellerSteps {
    AtmUserInterface teller;
    Account account;

    Integer someAmount = 20;

    public TellerSteps(AtmUserInterface teller, Account account) {
        this.teller = teller;
        this.account = account;
    }

    @When("I withdraw ${int}")
    public void iRequest$(Integer amount) {
        teller.withdrawFrom(account, amount);
    }

    @When("I request some of my money")
    public void iRequestSomeOfMyMoney() {
        iRequest$(someAmount);
    }

    @Then("I should see an out-of-order message")
    public void iShouldSeeAnOutOfOrderMessage() {
        assertTrue("Expected error message not displayed", teller.isDisplaying("Out of order"));
    }
}
