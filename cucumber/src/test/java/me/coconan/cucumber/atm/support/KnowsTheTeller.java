package me.coconan.cucumber.atm.support;

import me.coconan.cucumber.atm.Teller;

public class KnowsTheTeller {
    private final Teller teller;

    public KnowsTheTeller(AtmUserInterface teller) {
        this.teller = teller;
    }

    public Teller getTeller() {
        return teller;
    }
}
