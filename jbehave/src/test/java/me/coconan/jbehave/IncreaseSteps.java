package me.coconan.jbehave;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class IncreaseSteps {
    private int counter;
    private int previousValue;

    @Given("a counter")
    public void aCounter() {
    }

    @Given("the counter has any integral value")
    public void counterHasAnyIntegralValue() {
        counter = new Random().nextInt();
        previousValue = counter;
    }

    @When("the user increase the counter")
    public void increaseTheCounter() {
        counter++;
    }

    @Then("the value of the counter must be 1 greater than previous value")
    public void theValueOfTheCounterMustBe1GraterThanPreviousValue() {
        assertEquals(1, counter - previousValue);
    }
}
