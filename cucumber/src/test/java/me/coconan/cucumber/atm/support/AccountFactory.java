package me.coconan.cucumber.atm.support;

import me.coconan.cucumber.atm.Account;

public class AccountFactory {
    public static Account createTestAccount() {
        Account account = new Account(1234);
        account.saveIt();

        return account;
    }
}
