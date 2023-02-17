package me.coconan.cucumber.atm.support;

import me.coconan.cucumber.atm.CashSlot;

public class TestCashSlot extends CashSlot {
    private boolean faulty;

    public TestCashSlot() {
        super();
        load(1000);
    }

    public void injectFault() {
        faulty = true;
    }

    public void dispense(int dollars) {
        if (faulty) {
            throw new RuntimeException("Out of order");
        } else {
            super.dispense(dollars);
        }
    }
}
