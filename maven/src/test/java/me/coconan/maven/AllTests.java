package me.coconan.maven;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ HackTest.class, FooTest.class, BarTest.class })
public class AllTests {

}
