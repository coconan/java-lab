package me.coconan.cucumber.atm.hooks;

import io.cucumber.java.Before;
import me.coconan.cucumber.atm.BalanceStore;
import me.coconan.cucumber.atm.TransactionQueue;

public class ResetHooks {
    @Before
    public void reset() {
        TransactionQueue.clear();
        BalanceStore.clear();
    }
}
