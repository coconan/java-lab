package me.coconan.instrumentation.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAtmApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyAtmApplication.class);

    public static void run(String[] args) throws InterruptedException {
        LOGGER.info("[Application] Starting ATM application");
        MyAtm.withdrawMoney(Integer.parseInt(args[2]));

        Thread.sleep(Long.parseLong(args[1]));

        MyAtm.withdrawMoney(Integer.parseInt(args[3]));
    }
}
