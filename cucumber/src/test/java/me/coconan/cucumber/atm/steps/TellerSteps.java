package me.coconan.cucumber.atm.steps;

import io.cucumber.java.en.When;
import me.coconan.cucumber.atm.Teller;
import me.coconan.cucumber.atm.support.KnowsTheAccount;
import me.coconan.cucumber.atm.support.KnowsTheTeller;

public class TellerSteps {
    KnowsTheTeller tellerHelper;
    KnowsTheAccount accountHelper;

    public TellerSteps(KnowsTheTeller tellerHelper, KnowsTheAccount accountHelper) {
        this.tellerHelper = tellerHelper;
        this.accountHelper = accountHelper;
    }

    @When("I withdraw ${int}")
    public void iRequest$(Integer amount) {
        Teller teller = tellerHelper.getTeller();
        teller.withdrawFrom(accountHelper.getMyAccount(), amount);
    }
}
