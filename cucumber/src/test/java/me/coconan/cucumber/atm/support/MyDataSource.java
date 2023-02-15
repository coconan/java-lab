package me.coconan.cucumber.atm.support;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MyDataSource extends MysqlDataSource {
    public MyDataSource() {
        super();
        setUrl("jdbc:mysql://dev/bank");
        setUser("root");
        setPassword("root");
    }
}
