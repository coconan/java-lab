package me.coconan.cucumber.atm.support;

import me.coconan.cucumber.atm.Account;
import me.coconan.cucumber.atm.AutomatedTeller;
import me.coconan.cucumber.atm.CashSlot;
import me.coconan.cucumber.atm.Teller;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class KnowsTheDomain {
    private Account myAccount;
    private CashSlot cashSlot;
    private Teller teller;
    private EventFiringWebDriver webDriver;

    public Account getMyAccount() {
        if (myAccount == null) {
            myAccount = new Account();
        }

        return myAccount;
    }

    public CashSlot getCashSlot() {
        if (cashSlot == null) {
            cashSlot = new CashSlot();
        }

        return cashSlot;
    }

    public Teller getTeller() {
        if (teller == null) {
            teller = new AtmUserInterface(getWebDriver());
        }

        return teller;
    }

    public EventFiringWebDriver getWebDriver() {
        if (webDriver == null) {
            System.setProperty("webdriver.chrome.driver","chromedriver");
            webDriver = new EventFiringWebDriver(new ChromeDriver());
        }

        return webDriver;
    }
}
