package me.coconan.cucumber.atm.support;

import me.coconan.cucumber.atm.Account;

import java.util.ArrayList;
import java.util.List;

public class TestAccounts {
    private final List<Account> accountList;
    public TestAccounts() {
        accountList = new ArrayList<>();
        Account account = new Account(1234);
        account.saveIt();
        accountList.add(account);
    }

    public Account getTestAccount() {
        return accountList.get(0);
    }
}
