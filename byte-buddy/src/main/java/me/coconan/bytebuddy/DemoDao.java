package me.coconan.bytebuddy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DemoDao {
    private static final String URL = "jdbc:mysql://mysql-test:3306/bank?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";

    public List<Account> getAccount() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        boolean valid = connection.isValid(1000);
        System.out.println("connection valid: " + valid);
        String sql = "select * from accounts where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        preparedStatement.setInt(1, 1);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();

        List<Account> accounts = new ArrayList<>();
        while (resultSet.next()) {
            Account account = new Account();
            account.setId(resultSet.getInt("id"));
            account.setNumber(resultSet.getInt("number"));
            account.setBalance(resultSet.getBigDecimal("balance"));
            accounts.add(account);
        }

        return accounts;
    }
}
