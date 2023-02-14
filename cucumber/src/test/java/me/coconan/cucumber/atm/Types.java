package me.coconan.cucumber.atm;

import io.cucumber.cucumberexpressions.Transformer;
import io.cucumber.java.ParameterType;

public class Types {

    static class MoneyConverter implements Transformer<Money> {
        public Money transform(String amount) {
            return Money.from(amount);
        }
    }
    @ParameterType(".*")
    public Money money(String amount) {
        return new MoneyConverter().transform(amount);
    }
}
