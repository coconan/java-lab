package me.coconan.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class JDBCExample {
    public static void main(String[] args) throws Exception {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from person");
            writeResultSet(resultSet);
        
            PreparedStatement preparableStatement = connection.prepareStatement("insert into person (`name`) values (?)");
            preparableStatement.setString(1, "alex");
            preparableStatement.executeUpdate();

            preparableStatement = connection.prepareStatement("select * from person");
            resultSet = preparableStatement.executeQuery();
            writeResultSet(resultSet);

            preparableStatement = connection.prepareStatement("delete from person where name=?");
            preparableStatement.setString(1, "alex");
            preparableStatement.executeUpdate();

            resultSet = statement.executeQuery("select * from person");
            writeMetaData(resultSet);
        } catch (Exception e) {
            throw e;
        }
    }

    private static Connection getConnection() throws Exception {
        //Class.forName("com.mysql.cj.jdbc.driver");
        return DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "hello123");
    }

    private static void writeResultSet(ResultSet resultSet) throws Exception {
        while (resultSet.next()) {
            System.out.println("name: " + resultSet.getString("name"));
        }
    }

    private static void writeMetaData(ResultSet resultSet) throws Exception {
        System.out.println("The columns in the table are:");
        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
            System.out.println("Column " + i + " " + resultSet.getMetaData().getColumnName(i));
        }
    }
}
