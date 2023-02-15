package me.coconan.cucumber.atm.hooks;

import io.cucumber.java.Before;
import me.coconan.cucumber.atm.TransactionQueue;
import me.coconan.cucumber.atm.support.MyDataSource;
import org.flywaydb.core.Flyway;
import org.javalite.activejdbc.Base;

public class ResetHooks {
    private final MyDataSource myDataSource;

    public ResetHooks(MyDataSource myDataSource) {
        this.myDataSource = myDataSource;
    }
    @Before
    public void reset() {
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
        TransactionQueue.clear();
    }
}
