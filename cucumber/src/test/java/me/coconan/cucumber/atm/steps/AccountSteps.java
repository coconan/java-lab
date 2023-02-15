package me.coconan.cucumber.atm.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import me.coconan.cucumber.atm.Money;
import me.coconan.cucumber.atm.support.KnowsTheAccount;

import static org.junit.Assert.assertEquals;

public class AccountSteps {
    KnowsTheAccount accountHelper;

    public AccountSteps(KnowsTheAccount accountHelper) {
        this.accountHelper = accountHelper;
    }

    @Given("I have credited {money} in my account")
    public void iHaveDeposited$InMyAccount(Money amount) {
        accountHelper.getMyAccount().credit(amount);
    }

    @Then("the balance of my account should be {money}")
    public void theBalanceOfMyAccountShouldBe(Money amount) throws InterruptedException {
        int timeoutMilliSecs = 3000;
        int pollIntervalMilliSecs = 100;

        while (!accountHelper.getMyAccount().getBalance().equals(amount) && timeoutMilliSecs > 0) {
            Thread.sleep(pollIntervalMilliSecs);
            timeoutMilliSecs -= pollIntervalMilliSecs;
        }

        assertEquals("Incorrect account balance -", amount, accountHelper.getMyAccount().getBalance());
    }
}
