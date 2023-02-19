package me.coconan.cucumber.atm;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:Feature/atm", plugin = {"pretty", "html:out.html"}, snippets = CucumberOptions.SnippetType.CAMELCASE)
@CucumberContextConfiguration
@ContextConfiguration(locations= "/cucumber.xml")
public class AtmIntegrationTest {
    static {
        System.setProperty("cucumber.environment", "DEVELOPMENT");
    }
}
