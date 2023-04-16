package me.coconan.maven;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BarTest {
    @Test
    public void test_greet() {
        assertEquals("hello, maven", new Bar().greet());
    }
}
