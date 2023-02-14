package me.coconan.cucumber.atm;

import org.javalite.activejdbc.Model;

public class Account extends Model {
    private TransactionQueue queue = new TransactionQueue();

    public Account() {
    }

    public Account(int number) {
        setInteger("number", number);
        setString("balance", "0.00");
    }

    public void credit(Money amount) {
        queue.write("+" + amount + "," + getNumber());
    }

    public void debit(int dollars) {
        Money amount = new Money(dollars, 0);
        queue.write("-" + amount + "," + getNumber());
    }

    public int getNumber() {
        return getInteger("number");
    }

    public Money getBalance() {
        refresh();
        return Money.from(getString("balance"));
    }

    public void setBalance(Money amount) {
        setString("balance", amount.toString().substring(1));
        saveIt();
    }
}
