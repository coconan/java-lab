package me.coconan.cucumber.atm.support;

import me.coconan.cucumber.atm.Account;
import me.coconan.cucumber.atm.Teller;
import me.coconan.cucumber.atm.hooks.ServerHooks;
import org.openqa.selenium.By;

public class AtmUserInterface implements Teller {
    private final MyWebDriver myWebDriver;

    public AtmUserInterface(MyWebDriver myWebDriver) {
        this.myWebDriver = myWebDriver;
    }

    @Override
    public void withdrawFrom(Account account, int dollars) {
        myWebDriver.get("http://localhost:" + ServerHooks.PORT);
        myWebDriver.findElement(By.id("amount"))
                .sendKeys(String.valueOf(dollars));
        myWebDriver.findElement(By.id("withdraw")).click();
    }
}
