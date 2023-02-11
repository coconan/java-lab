package me.coconan.cucumber.atm;

import io.cucumber.cucumberexpressions.Transformer;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class Steps {

    class Money {
        private int dollars;
        private int cents;
        Money(int dollars, int cents) {
            this.dollars = dollars;
            this.cents = cents;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Money)) {
                return false;
            }

            Money that = (Money) obj;
            return dollars == that.dollars && cents == that.cents;
        }
    }

    class MoneyConverter implements Transformer<Money> {
        public Money transform(String amount) {
            Pattern pattern = Pattern.compile("^[^\\d]*([\\d]+)\\.([\\d][\\d])$");
            Matcher matcher = pattern.matcher(amount);

            matcher.find();
            int dollars = Integer.parseInt(matcher.group(1));
            int cents = Integer.parseInt(matcher.group(2));

            return new Money(dollars, cents);
        }
    }

    class Account {
        private Money balance;

        public void deposit(Money amount) {
            balance = amount;
        }

        public Money getBalance() {
            return balance;
        }
    }

    @ParameterType(".*")
    public Money money(String amount) {
        return new MoneyConverter().transform(amount);
    }

    @Given("I have deposited {money} in my account")
    public void iHaveDeposited$InMyAccount(Money amount) {
        Account myAccount = new Account();
        myAccount.deposit(amount);

        assertEquals("Incorrect account balance -", amount, myAccount.getBalance());
    }

    @When("I request ${int}")
    public void iRequest$(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("${int} should be dispensed")
    public void $ShouldBeDispensed(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
