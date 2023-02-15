package me.coconan.cucumber.atm.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import me.coconan.cucumber.atm.support.TestCashSlot;

import static org.junit.Assert.assertEquals;

public class CashSlotSteps {
    TestCashSlot cashSlot;

    public CashSlotSteps(TestCashSlot cashSlot) {
        this.cashSlot = cashSlot;
    }

    @Given("the cash slot has developed a fault")
    public void theCashSlotHasDevelopedAFault() {
        cashSlot.injectFault();
    }

    @Then("${int} should be dispensed")
    public void $ShouldBeDispensed(int dollars) {
        assertEquals("Incorrect amount dispensed - ", dollars, cashSlot.getContents());
    }
}
