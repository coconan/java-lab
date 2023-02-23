package me.coconan.cucumber.atm;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(tags="@bypass_teller_ui", glue="me.coconan.cucumber.atm", features = "classpath:Feature/atm", plugin = {"pretty", "html:out.html"}, snippets = CucumberOptions.SnippetType.CAMELCASE)
@CucumberContextConfiguration
@ContextConfiguration(locations= "/cucumber.atm.xml")
public class AtmIntegrationTest {
    static {
        System.setProperty("cucumber.environment", "DEVELOPMENT");
    }
}
