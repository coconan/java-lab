package me.coconan.bytebuddy;

import java.sql.*;

public class JdbcTest {
    private static final String URL = "jdbc:mysql://mysql-test:3306/bank?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "hello123";

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        boolean valid = connection.isValid(1000);
        System.out.println("连接是否有效:" + valid);
        String sql = "select * from accounts where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        preparedStatement.setInt(1, 1);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println("----------------------------------------------------");
            System.out.println("id:" + resultSet.getInt("id"));
            System.out.println("number:" + resultSet.getInt("number"));
            System.out.println("balance:" + resultSet.getBigDecimal("balance"));
            System.out.println("----------------------------------------------------");
        }
    }
}
