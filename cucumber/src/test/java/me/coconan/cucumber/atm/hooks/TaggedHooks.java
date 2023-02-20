package me.coconan.cucumber.atm.hooks;

import io.cucumber.java.Before;
import me.coconan.cucumber.atm.support.AtmInterfaceFactory;

public class TaggedHooks {
    @Before("@bypass_teller_ui")
    public void bypassTellerUI() {
        AtmInterfaceFactory.bypassTellerUI();
    }
}
