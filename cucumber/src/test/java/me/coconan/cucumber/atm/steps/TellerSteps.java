package me.coconan.cucumber.atm.steps;

import io.cucumber.java.en.When;
import me.coconan.cucumber.atm.Teller;
import me.coconan.cucumber.atm.support.AtmUserInterface;
import me.coconan.cucumber.atm.support.TestAccounts;

public class TellerSteps {
    Teller teller;
    TestAccounts testAccounts;

    public TellerSteps(AtmUserInterface teller, TestAccounts testAccounts) {
        this.teller = teller;
        this.testAccounts = testAccounts;
    }

    @When("I withdraw ${int}")
    public void iRequest$(Integer amount) {
        teller.withdrawFrom(testAccounts.getTestAccount(), amount);
    }
}
