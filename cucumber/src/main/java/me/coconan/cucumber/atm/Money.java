package me.coconan.cucumber.atm;

public class Money {
    private int dollars;
    private int cents;

    Money(int dollars, int cents) {
        this.dollars = dollars;
        this.cents = cents;
    }

    public Money() {
       this(0, 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Money)) {
            return false;
        }

        Money that = (Money) obj;
        return dollars == that.dollars && cents == that.cents;
    }

    @Override
    public String toString() {
        return String.format("$%d.%02d", dollars, cents);
    }

    public Money add(Money amount) {
        int newCents = cents + amount.cents;
        int newDollars = dollars + amount.dollars;

        if (newCents >= 100) {
            newCents -= 100;
            newDollars++;
        }

        return new Money(newDollars, newCents);
    }

    public Money minus(Money amount) {
        int newCents = cents - amount.cents;
        int newDollars = dollars - amount.dollars;

        if (newCents < 0) {
            newCents += 100;
            newDollars--;
        }

        return new Money(newDollars, newCents);
    }
}
