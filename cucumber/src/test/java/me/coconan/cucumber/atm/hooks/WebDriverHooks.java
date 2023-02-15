package me.coconan.cucumber.atm.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import me.coconan.cucumber.atm.support.MyWebDriver;
import org.openqa.selenium.OutputType;

public class WebDriverHooks {
    private final MyWebDriver myWebDriver;

    public WebDriverHooks(MyWebDriver myWebDriver) {
        this.myWebDriver = myWebDriver;
    }

    @After
    public void finish(Scenario scenario) {
        try {
            byte[] screenshot = myWebDriver.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "form");
        } finally {
            myWebDriver.close();
        }
    }
}
