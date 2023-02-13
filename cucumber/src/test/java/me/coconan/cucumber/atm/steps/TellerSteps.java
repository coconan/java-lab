package me.coconan.cucumber.atm.steps;

import io.cucumber.java.en.When;
import me.coconan.cucumber.atm.Teller;
import me.coconan.cucumber.atm.support.KnowsTheDomain;

public class TellerSteps {
    KnowsTheDomain helper;

    public TellerSteps(KnowsTheDomain helper) {
        this.helper = helper;
    }

    @When("I withdraw ${int}")
    public void iRequest$(Integer amount) {
        Teller teller = new Teller(helper.getCashSlot());
        teller.withdrawFrom(helper.getMyAccount(), amount);
    }
}
