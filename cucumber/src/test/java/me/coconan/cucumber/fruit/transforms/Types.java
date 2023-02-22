package me.coconan.cucumber.fruit.transforms;

import io.cucumber.java.DataTableType;
import me.coconan.cucumber.fruit.Fruit;

import java.util.Map;

public class Types {
    @DataTableType
    public Fruit fruitTransformer(Map<String, String> row) {
        Fruit fruit = new Fruit();
        fruit.setName(row.get("name"));
        fruit.setColor(row.get("color"));

        return fruit;
    }
}
