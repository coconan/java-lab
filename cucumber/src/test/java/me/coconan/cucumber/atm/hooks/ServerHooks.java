package me.coconan.cucumber.atm.hooks;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import me.coconan.cucumber.atm.AtmServer;
import me.coconan.cucumber.atm.support.KnowsTheDomain;
import org.flywaydb.core.Flyway;
import org.javalite.activejdbc.Base;

public class ServerHooks {
    public static final int PORT = 9988;

    private AtmServer server;
    private final KnowsTheDomain helper;

    public ServerHooks(KnowsTheDomain helper) {
        this.helper = helper;
    }

    @Before
    public void startServer() throws Exception {
        final MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://dev/bank");
        dataSource.setUser("root");
        dataSource.setPassword("root");

        if (!Base.hasConnection()) {
            Base.open(dataSource);
        }

        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .load();
        flyway.clean();
        flyway.migrate();

        server = new AtmServer(PORT, helper.getCashSlot(), helper.getMyAccount());
        server.start();
    }

    @After
    public void stopServer() throws Exception {
        server.stop();
    }
}
