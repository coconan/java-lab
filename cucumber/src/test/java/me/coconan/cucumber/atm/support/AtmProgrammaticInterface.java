package me.coconan.cucumber.atm.support;

import me.coconan.cucumber.atm.Account;
import me.coconan.cucumber.atm.AutomatedTeller;
import me.coconan.cucumber.atm.CashSlot;
import org.springframework.beans.factory.annotation.Autowired;

public class AtmProgrammaticInterface implements AtmInterface {
    @Autowired
    private CashSlot cashSlot;
    RuntimeException runtimeException;

    @Override
    public void withdrawFrom(Account account, int dollars) {
        try {
            AutomatedTeller.withdrawFrom(cashSlot, account, dollars);
        } catch (RuntimeException e) {
            runtimeException = e;
        }
    }

    @Override
    public void type(int amount) {

    }

    @Override
    public boolean isDisplaying(String message) {
        return true;
    }
}
