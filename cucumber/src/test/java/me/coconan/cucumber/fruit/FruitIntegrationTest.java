package me.coconan.cucumber.fruit;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(glue="me.coconan.cucumber.fruit", features = "classpath:Feature/fruit", plugin = {"pretty", "html:out.html"}, snippets = CucumberOptions.SnippetType.CAMELCASE)
@CucumberContextConfiguration
@ContextConfiguration(locations= "/cucumber.atm.xml")
public class FruitIntegrationTest {
}
