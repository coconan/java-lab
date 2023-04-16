package me.coconan.maven;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

public class TestMain {
    public static void main(String[] args) {
        System.out.println("Running tests!");

        JUnitCore engine = new JUnitCore();
        engine.addListener(new TextListener(System.out)); // required to print reports
        engine.run(AllTests.class);
    }
}
