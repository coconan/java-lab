package me.coconan.bytebuddy;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

public class PinpointBootStrap {
    public static void premain(String agentArgs, Instrumentation inst) {
        //构建一个转换器
        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, javaModule) -> builder
                // 拦截静态而且前缀为test的方法
                .method(ElementMatchers.named("executeQuery"))
                // 委托
                .intercept(MethodDelegation.to(JdbcMonitor.class));

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
                // 指定需要拦截的类
                .type(ElementMatchers.named("com.mysql.cj.jdbc.ClientPreparedStatement"))
                //添加一个转换器
                .transform(transformer)
                .with(listener)
                //将上述的转换安装到javaagent的介入器
                .installOn(inst);

    }
}
