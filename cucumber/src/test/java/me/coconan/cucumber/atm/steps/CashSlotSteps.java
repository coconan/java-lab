package me.coconan.cucumber.atm.steps;

import io.cucumber.java.en.Then;
import me.coconan.cucumber.atm.CashSlot;

import static org.junit.Assert.assertEquals;

public class CashSlotSteps {
    CashSlot cashSlot;

    public CashSlotSteps(CashSlot cashSlot) {
        this.cashSlot = cashSlot;
    }

    @Then("${int} should be dispensed")
    public void $ShouldBeDispensed(int dollars) {
        assertEquals("Incorrect amount dispensed - ", dollars, cashSlot.getContents());
    }
}
