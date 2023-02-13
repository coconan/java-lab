package me.coconan.cucumber.atm.steps;

import io.cucumber.java.en.Then;
import me.coconan.cucumber.atm.support.KnowsTheDomain;

import static org.junit.Assert.assertEquals;

public class CashSlotSteps {
    KnowsTheDomain helper;

    public CashSlotSteps(KnowsTheDomain helper) {
        this.helper = helper;
    }

    @Then("${int} should be dispensed")
    public void $ShouldBeDispensed(int dollars) {
        assertEquals("Incorrect amount dispensed - ", dollars, helper.getCashSlot().getContents());
    }
}
