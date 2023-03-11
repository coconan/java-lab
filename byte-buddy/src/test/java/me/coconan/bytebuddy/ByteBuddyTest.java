package me.coconan.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;

import static net.bytebuddy.matcher.ElementMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByteBuddyTest {
    @Test
    public void test_hello_world() throws InstantiationException, IllegalAccessException {
        Class<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .method(named("toString"))
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
                .method(named("apply"))
                .intercept(MethodDelegation.to(new GreetingInterceptor()))
                .make()
                .load(getClass().getClassLoader())
                .getLoaded();

        assertEquals((String) dynamicType.newInstance().apply("Byte Buddy"), "Hello from Byte Buddy");
    }

    @Test
    public void test_method_delegation() throws InstantiationException, IllegalAccessException {
        String r = new ByteBuddy()
                .subclass(Foo.class)
                .method(named("sayHelloFoo")
                        .and(isDeclaredBy(Foo.class)
                                .and(returns(String.class))))
                .intercept(MethodDelegation.to(Bar.class))
                .make()
                .load(getClass().getClassLoader())
                .getLoaded()
                .newInstance()
                .sayHelloFoo();

        assertEquals(r, Bar.sayHelloBar());
    }
}

