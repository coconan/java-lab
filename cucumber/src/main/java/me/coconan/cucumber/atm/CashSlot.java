package me.coconan.cucumber.atm;

public class CashSlot {
    private int contents;
    private int available;

    public int getContents() {
        return contents;
    }

    public void load(int dollars) {
        available = dollars;
    }

    public void dispense(int requested) {
        if (available >= requested) {
            contents = requested;
            available -= requested;
        } else {
            throw new RuntimeException("Insufficient ATM funds");
        }
    }

    public boolean canDispense(int amount) {
        return amount <= available;
    }
}
