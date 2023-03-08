package me.coconan.bytebuddy;


public class GreetingInterceptor {
    public Object greet(Object argument) {
        return "Hello from " + argument;
    }
}
