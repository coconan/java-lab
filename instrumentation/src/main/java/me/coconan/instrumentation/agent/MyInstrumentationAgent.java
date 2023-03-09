package me.coconan.instrumentation.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;

public class MyInstrumentationAgent {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyInstrumentationAgent.class);

    public static void premain(String agentArgs, Instrumentation inst) {
        LOGGER.info("[Agent] In premain method");

        String className = "me.coconan.instrumentation.application.MyAtm";
        transformClass(className, inst);
    }

    private static void transformClass(String className, Instrumentation instrumentation) {
        Class<?> targetCls = null;
        ClassLoader targetClassLoader = null;

        try {
            targetCls = Class.forName(className);
            targetClassLoader = targetCls.getClassLoader();
            transform(targetCls, targetClassLoader, instrumentation);
            return;
        } catch (Exception e) {
            LOGGER.error("class [{}] not found with Class.forName");
        }

        for (Class<?> clazz : instrumentation.getAllLoadedClasses()) {
            if (clazz.getName().equals(className)) {
                targetCls = clazz;
                targetClassLoader = targetCls.getClassLoader();
                transform(targetCls, targetClassLoader, instrumentation);
                return;
            }
        }

        throw new RuntimeException("Failed to find class [" + className + "]");
    }

    private static void transform(Class<?> clazz, ClassLoader classLoader, Instrumentation instrumentation) {
        AtmTransformer dt = new AtmTransformer(clazz.getName(), classLoader);
        instrumentation.addTransformer(dt, true);
        try {
            instrumentation.retransformClasses(clazz);
        } catch (Exception e) {
            throw new RuntimeException("Transform failed for class: [" + clazz.getName() + "]", e);
        }
    }
}
