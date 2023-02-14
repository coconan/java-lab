package me.coconan.cucumber.atm.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import me.coconan.cucumber.atm.support.KnowsTheDomain;
import org.openqa.selenium.OutputType;

public class WebDriverHooks {
    private final KnowsTheDomain helper;

    public WebDriverHooks(KnowsTheDomain helper) {
        this.helper = helper;
    }

    @After
    public void finish(Scenario scenario) {
        try {
            byte[] screenshot = helper.getWebDriver().getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "form");
        } finally {
            helper.getWebDriver().close();
        }
    }
}
