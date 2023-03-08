package me.coconan.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByteBuddyTest {
    @Test
    public void test_hello_world() throws InstantiationException, IllegalAccessException {
        Class<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(getClass().getClassLoader())
                .getLoaded();

        assertEquals(dynamicType.newInstance().toString(), "Hello World!");
    }

    @Test
    public void test_method_intercept() throws InstantiationException, IllegalAccessException {
        Class<? extends java.util.function.Function> dynamicType = new ByteBuddy()
                .subclass(java.util.function.Function.class)
                .method(ElementMatchers.named("apply"))
                .intercept(MethodDelegation.to(new GreetingInterceptor()))
                .make()
                .load(getClass().getClassLoader())
                .getLoaded();

        assertEquals((String) dynamicType.newInstance().apply("Byte Buddy"), "Hello from Byte Buddy");
    }
}

