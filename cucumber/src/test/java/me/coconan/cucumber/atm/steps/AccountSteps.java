package me.coconan.cucumber.atm.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import me.coconan.cucumber.atm.Account;
import me.coconan.cucumber.atm.Money;

import static org.junit.Assert.assertEquals;

public class AccountSteps {
    Account account;

    public AccountSteps(Account account) {
        this.account = account;
    }

    @Given("my account has been credited with {money}")
    public void iHaveDeposited$InMyAccount(Money amount) {
        account.credit(amount);
    }

    @Then("the balance of my account should be {money}")
    public void theBalanceOfMyAccountShouldBe(Money amount) throws InterruptedException {
        int timeoutMilliSecs = 3000;
        int pollIntervalMilliSecs = 100;

        while (!account.getBalance().equals(amount) && timeoutMilliSecs > 0) {
            Thread.sleep(pollIntervalMilliSecs);
            timeoutMilliSecs -= pollIntervalMilliSecs;
        }

        assertEquals("Incorrect account balance -", amount, account.getBalance());
    }
}
