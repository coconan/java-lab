package me.coconan.cucumber.atm.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import me.coconan.cucumber.atm.AtmServer;
import me.coconan.cucumber.atm.support.KnowsTheAccount;
import me.coconan.cucumber.atm.support.KnowsTheCashSlot;
import me.coconan.cucumber.atm.support.MyDataSource;
import org.flywaydb.core.Flyway;
import org.javalite.activejdbc.Base;

public class ServerHooks {
    public static final int PORT = 9988;

    private AtmServer server;
    private final KnowsTheAccount accountHelper;
    private final KnowsTheCashSlot cashSlotHelper;
    private final MyDataSource myDataSource;

    public ServerHooks(KnowsTheAccount accountHelper, KnowsTheCashSlot cashSlotHelper, MyDataSource myDataSource) {
        this.accountHelper = accountHelper;
        this.cashSlotHelper = cashSlotHelper;
        this.myDataSource = myDataSource;
    }

    @Before
    public void startServer() throws Exception {
        Flyway flyway = Flyway.configure()
                .dataSource(myDataSource)
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .load();
        flyway.clean();
        flyway.migrate();

        if (!Base.hasConnection()) {
            Base.open(myDataSource);
        }

        server = new AtmServer(PORT, cashSlotHelper.getCashSlot(), accountHelper.getMyAccount());
        server.start();
    }

    @After
    public void stopServer() throws Exception {
        server.stop();
    }
}
