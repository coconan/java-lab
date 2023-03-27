package me.coconan.bytebuddy;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

public class PreMain {
    public static void premain(String agentArgs, Instrumentation inst) {
        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, javaModule) -> builder
                .method(ElementMatchers.named("getResultSet"))
                .intercept(MethodDelegation.to(Interceptor.class));

        AgentBuilder.Listener listener = new AgentBuilder.Listener() {

            @Override
            public void onDiscovery(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }

            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded, DynamicType dynamicType) {

            }

            @Override
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }

            @Override
            public void onError(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded, Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }
        };

        new AgentBuilder
                .Default()
                .type(ElementMatchers.named("com.mysql.jdbc.JDBC42PreparedStatement"))
                .transform(transformer)
                .with(listener)
                .installOn(inst);

    }
}
