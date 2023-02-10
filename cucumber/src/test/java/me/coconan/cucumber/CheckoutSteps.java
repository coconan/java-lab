package me.coconan.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class CheckoutSteps {
    int bananaPrice;
    Checkout checkout;

    @Given("the price of a {string} is {int}c")
    public void thePriceOfAIsC(String item, int price) {
        bananaPrice = price;
    }

    @When("I checkout {int} {string}")
    public void iCheckout(int itemCount, String itemName) {
        checkout = new Checkout();
        checkout.add(itemCount, bananaPrice);
    }

    @Then("the total price should be {int}c")
    public void theTotalPriceShouldBeC(int total) {
        assertEquals(total, checkout.total());
    }
}
