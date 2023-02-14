package me.coconan.cucumber.atm;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:Feature/atm", plugin = {"pretty", "html:out.html"}, snippets = CucumberOptions.SnippetType.CAMELCASE)
public class AtmIntegrationTest {
}
