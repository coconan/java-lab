package me.coconan.cucumber.atm.steps;

import io.cucumber.java.en.Given;
import me.coconan.cucumber.atm.Money;
import me.coconan.cucumber.atm.support.KnowsTheDomain;

import static org.junit.Assert.assertEquals;

public class AccountSteps {
    KnowsTheDomain helper;

    public AccountSteps(KnowsTheDomain helper) {
        this.helper = helper;
    }

    @Given("I have deposited {money} in my account")
    public void iHaveDeposited$InMyAccount(Money amount) {
        helper.getMyAccount().deposit(amount);

        assertEquals("Incorrect account balance -", amount, helper.getMyAccount().getBalance());
    }
}
