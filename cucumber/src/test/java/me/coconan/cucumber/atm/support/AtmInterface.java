package me.coconan.cucumber.atm.support;

import me.coconan.cucumber.atm.Teller;

public interface AtmInterface extends Teller {
    void type(int amount);
    boolean isDisplaying(String message);
}
