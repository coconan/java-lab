package me.coconan.cucumber.atm.support;

public class AtmInterfaceFactory {
    private static boolean bypassTellerUI = false;

    public static void reset() {
        bypassTellerUI = false;
    }

    public static  void bypassTellerUI() {
        bypassTellerUI = true;
    }

    public static AtmInterface createAtmInterface() {
        if (bypassTellerUI) {
            return new AtmProgrammaticInterface();
        } else {
            return new AtmUserInterface();
        }
    }
}
