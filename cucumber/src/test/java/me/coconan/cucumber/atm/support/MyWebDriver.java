package me.coconan.cucumber.atm.support;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class MyWebDriver extends EventFiringWebDriver {
    static {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
    }

    public MyWebDriver(ChromeDriver chromeDriver) {
        super(chromeDriver);
    }
}
