package me.coconan.cucumber.atm.support;

import me.coconan.cucumber.atm.Account;
import me.coconan.cucumber.atm.CashSlot;

public class KnowsTheDomain {
    private Account myAccount;
    private CashSlot cashSlot;

    public Account getMyAccount() {
        if (myAccount == null) {
            myAccount = new Account();
        }

        return myAccount;
    }

    public CashSlot getCashSlot() {
        if (cashSlot == null) {
            cashSlot = new CashSlot();
        }

        return cashSlot;
    }
}
