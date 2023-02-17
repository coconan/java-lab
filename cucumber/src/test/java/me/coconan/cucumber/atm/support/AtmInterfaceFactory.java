package me.coconan.cucumber.atm.support;

public class AtmInterfaceFactory {
    public static AtmInterface createAtmInterface() {
        return new AtmUserInterface();
    }
}
