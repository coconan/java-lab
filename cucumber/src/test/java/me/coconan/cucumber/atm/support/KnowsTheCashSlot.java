package me.coconan.cucumber.atm.support;

import me.coconan.cucumber.atm.CashSlot;

public class KnowsTheCashSlot {
    private CashSlot cashSlot;

    public CashSlot getCashSlot() {
        if (cashSlot == null) {
            cashSlot = new CashSlot();
        }

        return cashSlot;
    }

}
