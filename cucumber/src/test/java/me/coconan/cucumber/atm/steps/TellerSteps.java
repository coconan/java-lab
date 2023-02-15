package me.coconan.cucumber.atm.steps;

import io.cucumber.java.en.When;
import me.coconan.cucumber.atm.Account;
import me.coconan.cucumber.atm.Teller;
import me.coconan.cucumber.atm.support.AtmUserInterface;

public class TellerSteps {
    Teller teller;
    Account account;

    public TellerSteps(AtmUserInterface teller, Account account) {
        this.teller = teller;
        this.account = account;
    }

    @When("I withdraw ${int}")
    public void iRequest$(Integer amount) {
        teller.withdrawFrom(account, amount);
    }
}
