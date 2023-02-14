package me.coconan.cucumber.atm;

import org.javalite.activejdbc.Model;

public class Account extends Model {
    private TransactionQueue queue = new TransactionQueue();

    public void credit(Money amount) {
        queue.write("+" + amount);
    }

    public void debit(int dollars) {
        Money amount = new Money(dollars, 0);
        queue.write("-" + amount);
    }

    public Money getBalance() {
        return BalanceStore.getBalance();
    }
}
