package me.coconan.cucumber.atm.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import me.coconan.cucumber.atm.Account;
import me.coconan.cucumber.atm.support.AtmInterface;

import static org.junit.Assert.assertTrue;

public class TellerSteps {
    AtmInterface teller;
    Account account;
    Integer someAmount = 20;

    public TellerSteps(AtmInterface teller, Account account) {
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

    @When("I type ${int}")
    public void iType$(int amount) {
        teller.type(amount);
    }

    @Then("I should see an out-of-order message")
    public void iShouldSeeAnOutOfOrderMessage() {
        assertTrue("Expected error message not displayed", teller.isDisplaying("Out of order"));
    }

    @Then("I should see an ask-for-less-money message")
    public void iShouldSeeAnAskForLessMoneyMessage() {
        assertTrue("Expected error message not displayed", teller.isDisplaying("Insufficient ATM funds"));
    }
}
