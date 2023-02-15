package me.coconan.cucumber.atm.support;

import me.coconan.cucumber.atm.Account;

public class KnowsTheAccount {
    private Account myAccount;

    public Account getMyAccount() {
        if (myAccount == null) {
            myAccount = new Account(1234);
            myAccount.saveIt();
        }

        return myAccount;
    }
}
