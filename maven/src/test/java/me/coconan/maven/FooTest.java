package me.coconan.maven;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FooTest {
    @Test
    public void test_secret() {
        assertEquals(42, new Foo().secret());
    }
}
