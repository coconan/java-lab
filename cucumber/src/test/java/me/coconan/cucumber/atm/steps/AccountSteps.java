package me.coconan.cucumber.atm.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import me.coconan.cucumber.atm.Money;
import me.coconan.cucumber.atm.support.KnowsTheDomain;

import static org.junit.Assert.assertEquals;

public class AccountSteps {
    KnowsTheDomain helper;

    public AccountSteps(KnowsTheDomain helper) {
        this.helper = helper;
    }

    @Given("I have credited {money} in my account")
    public void iHaveDeposited$InMyAccount(Money amount) {
        helper.getMyAccount().credit(amount);
    }

    @Then("the balance of my account should be {money}")
    public void theBalanceOfMyAccountShouldBe(Money amount) {
        assertEquals("Incorrect account balance -", amount, helper.getMyAccount().getBalance());
    }
}
