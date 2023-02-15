package me.coconan.cucumber.atm.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import me.coconan.cucumber.atm.Account;
import me.coconan.cucumber.atm.AtmServer;
import me.coconan.cucumber.atm.CashSlot;

public class ServerHooks {
    public static final int PORT = 9988;

    private AtmServer server;
    private final Account account;
    private final CashSlot cashSlot;

    public ServerHooks(Account account, CashSlot cashSlot) {
        this.account = account;
        this.cashSlot = cashSlot;
    }

    @Before
    public void startServer() throws Exception {
        server = new AtmServer(PORT, cashSlot, account);
        server.start();
    }

    @After
    public void stopServer() throws Exception {
        server.stop();
    }
}
