package me.coconan.cucumber.atm;

import io.cucumber.cucumberexpressions.Transformer;
import io.cucumber.java.ParameterType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Types {

    static class MoneyConverter implements Transformer<Steps.Money> {
        public Steps.Money transform(String amount) {
            Pattern pattern = Pattern.compile("^[^\\d]*([\\d]+)\\.([\\d][\\d])$");
            Matcher matcher = pattern.matcher(amount);

            matcher.find();
            int dollars = Integer.parseInt(matcher.group(1));
            int cents = Integer.parseInt(matcher.group(2));

            return new Steps.Money(dollars, cents);
        }
    }
    @ParameterType(".*")
    public Steps.Money money(String amount) {
        return new MoneyConverter().transform(amount);
    }
}
