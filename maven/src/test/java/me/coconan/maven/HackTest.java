package me.coconan.maven;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HackTest {
    @Test
    public void test_phase() {
        assertEquals(1, new Foo().add(1, 1));
    }
}
