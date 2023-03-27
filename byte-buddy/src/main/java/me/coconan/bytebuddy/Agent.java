package me.coconan.bytebuddy;

import java.lang.instrument.Instrumentation;

public class Agent {
    public static void agentmain(String agentArgs, Instrumentation inst) {
        PreMain.premain(agentArgs, inst);
    }
}
