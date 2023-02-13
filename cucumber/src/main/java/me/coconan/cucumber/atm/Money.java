package me.coconan.cucumber.atm;

public class Money {
    private int dollars;
    private int cents;

    Money(int dollars, int cents) {
        this.dollars = dollars;
        this.cents = cents;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Money)) {
            return false;
        }

        Money that = (Money) obj;
        return dollars == that.dollars && cents == that.cents;
    }
}
