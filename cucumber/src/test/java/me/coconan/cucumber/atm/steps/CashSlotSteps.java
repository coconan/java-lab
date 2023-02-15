package me.coconan.cucumber.atm.steps;

import io.cucumber.java.en.Then;
import me.coconan.cucumber.atm.support.KnowsTheCashSlot;

import static org.junit.Assert.assertEquals;

public class CashSlotSteps {
    KnowsTheCashSlot cashSlotHelper;

    public CashSlotSteps(KnowsTheCashSlot cashSlotHelper) {
        this.cashSlotHelper = cashSlotHelper;
    }

    @Then("${int} should be dispensed")
    public void $ShouldBeDispensed(int dollars) {
        assertEquals("Incorrect amount dispensed - ", dollars, cashSlotHelper.getCashSlot().getContents());
    }
}
