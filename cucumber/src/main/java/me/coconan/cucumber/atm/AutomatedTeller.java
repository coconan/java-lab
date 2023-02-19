package me.coconan.cucumber.atm;

public class AutomatedTeller {
    public static void withdrawFrom(CashSlot cashSlot, Account account, int dollars) {
        cashSlot.dispense(dollars);
        account.debit(dollars);
    }
}
