package me.coconan.instrumentation.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAtm {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyAtm.class);

    private static final int account = 10;

    public static void withdrawMoney(int amount) throws InterruptedException {
        Thread.sleep(20001);
        LOGGER.info("[Application] Successful withdraw of [{}] units!", amount);
    }
}
