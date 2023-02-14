package me.coconan.cucumber.atm.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import me.coconan.cucumber.atm.TransactionProcessor;

public class BackgroundProcessHooks {
    private Thread transactionProcessThread;

    @Before
    public void startBackgroundProcessHooks() {
        transactionProcessThread = new Thread(() -> {
            TransactionProcessor processor = new TransactionProcessor();
            processor.process();
        });

        transactionProcessThread.start();
    }

    @After
    public void stopBackgroundThread() {
        transactionProcessThread.interrupt();
    }
}
