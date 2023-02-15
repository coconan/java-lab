package me.coconan.cucumber.atm.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import me.coconan.cucumber.atm.TransactionProcessor;
import me.coconan.cucumber.atm.support.MyDataSource;
import org.javalite.activejdbc.Base;

public class BackgroundProcessHooks {
    private Thread transactionProcessThread;
    private MyDataSource myDataSource;

    public BackgroundProcessHooks(MyDataSource myDataSource) {
        this.myDataSource = myDataSource;
    }

    @Before
    public void startBackgroundProcessHooks() {
        transactionProcessThread = new Thread(() -> {
            if (!Base.hasConnection()) {
                Base.open(myDataSource);
            }

            TransactionProcessor processor = new TransactionProcessor();
            try {
                processor.process();
            } catch (InterruptedException e) {
                System.out.println("TransactionProcessor stopped");
            }
        });

        transactionProcessThread.start();
    }

    @After
    public void stopBackgroundThread() {
        transactionProcessThread.interrupt();
    }
}
