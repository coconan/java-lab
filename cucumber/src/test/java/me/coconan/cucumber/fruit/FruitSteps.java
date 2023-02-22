package me.coconan.cucumber.fruit;

import com.google.gson.Gson;
import io.cucumber.java.en.Given;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FruitSteps {
    @Given("the system knows about the following fruit:")
    public void theSystemKnowsAboutTheFollowingFruit(List<Fruit> fruitList) throws IOException {
        Gson gson = new Gson();
        PrintWriter writer = new PrintWriter("fruit.json", StandardCharsets.UTF_8);
        writer.println(gson.toJson(fruitList));
        writer.close();
    }
}
