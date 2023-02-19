package me.coconan.cucumber.atm.support;

public class AtmInterfaceFactory {
    public static AtmInterface createAtmInterface() {
        String cucumberEnvironment = System.getProperty("cucumber.environment");

        if (cucumberEnvironment != null && cucumberEnvironment.equals("DEVELOPMENT")) {
            return new AtmProgrammaticInterface();
        } else {
            return new AtmUserInterface();
        }
    }
}
