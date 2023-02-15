package me.coconan.cucumber.atm.support;

import me.coconan.cucumber.atm.Account;
import me.coconan.cucumber.atm.Teller;
import me.coconan.cucumber.atm.hooks.ServerHooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

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

    public boolean isDisplaying(String message) {
        List<WebElement> list = myWebDriver
                .findElements(By.xpath("//*[contains(text(),'" + message + "')]"));
        return (list.size() > 0);
    }
}
