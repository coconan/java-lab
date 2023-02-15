package me.coconan.cucumber.atm.transforms;

import io.cucumber.cucumberexpressions.Transformer;
import io.cucumber.java.ParameterType;
import me.coconan.cucumber.atm.Money;

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
