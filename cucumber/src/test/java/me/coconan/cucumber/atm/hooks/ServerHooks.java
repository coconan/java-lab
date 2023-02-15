package me.coconan.cucumber.atm.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import me.coconan.cucumber.atm.AtmServer;
import me.coconan.cucumber.atm.CashSlot;
import me.coconan.cucumber.atm.support.TestAccounts;

public class ServerHooks {
    public static final int PORT = 9988;

    private AtmServer server;
    private final TestAccounts testAccounts;
    private final CashSlot cashSlot;

    public ServerHooks(TestAccounts testAccounts, CashSlot cashSlot) {
        this.testAccounts = testAccounts;
        this.cashSlot = cashSlot;
    }

    @Before
    public void startServer() throws Exception {
        server = new AtmServer(PORT, cashSlot, testAccounts.getTestAccount());
        server.start();
    }

    @After
    public void stopServer() throws Exception {
        server.stop();
    }
}
