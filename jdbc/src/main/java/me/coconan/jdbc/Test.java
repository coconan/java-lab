package me.coconan.jdbc;

import java.sql.*;
import java.util.stream.IntStream;

public class Test {

    public void run() throws Exception {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "hello123")) {
            statement(conn);

            preparedStatement(conn);

            callableStatement(conn);

            updatableResultSet(conn);

            metadata(conn);

            transactions(conn);
        }
    }

    private void transactions(Connection conn) throws SQLException {
        String updatePositionSql = "update employees set position = ? where emp_id = ?";
        String updateSalarySql = "update employees set salary = ? where emp_id = ?";
        try (
                PreparedStatement pstmt = conn.prepareStatement(updatePositionSql);
                PreparedStatement pstmt2 = conn.prepareStatement(updateSalarySql)) {
            pstmt.setString(1, "lead developer");
            pstmt.setInt(2, 1);
            pstmt2.setDouble(1, 30000);
            pstmt2.setInt(2, 1);

            boolean autoCommit = conn.getAutoCommit();
            try {
                conn.setAutoCommit(false);
                pstmt.executeUpdate();
                pstmt2.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            } finally {
                conn.setAutoCommit(autoCommit);
            }
        }
        String selectSql = "select * from employees";
        try (Statement stmt = conn.createStatement()) {
            try (ResultSet resultSet = stmt.executeQuery(selectSql)) {
                showResultSet(resultSet);
            }
        }
    }

    private void metadata(Connection conn) throws SQLException {
        DatabaseMetaData dbmd = conn.getMetaData();
        ResultSet tablesResultSet = dbmd.getTables(null, null, "%s", null);
        while (tablesResultSet.next()) {
            System.out.println(tablesResultSet.getString("TABLE_NAME"));
        }

        try (Statement stmt = conn.createStatement()) {
            String selectSql = "select * from employees";
            try (ResultSet resultSet = stmt.executeQuery(selectSql)) {
                ResultSetMetaData rsmd = resultSet.getMetaData();
                int nrColumns = rsmd.getColumnCount();

                IntStream.range(1, nrColumns).forEach(i -> {
                    try {
                        System.out.println(rsmd.getColumnName(i));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    private void updatableResultSet(Connection conn) throws SQLException {
        String selectSql = "select * from employees";
        try (Statement updatableStmt = conn.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            try (ResultSet updatableResultSet = updatableStmt.executeQuery(selectSql)) {
                updatableResultSet.moveToInsertRow();
                updatableResultSet.updateString("name", "mark");
                updatableResultSet.updateString("position", "analyst");
                updatableResultSet.updateDouble("salary", 20000);
                updatableResultSet.insertRow();
            }
            try (ResultSet resultSet = updatableStmt.executeQuery(selectSql)) {
                showResultSet(resultSet);
            }
        }
    }

    private void callableStatement(Connection conn) throws SQLException {
        String prepareSql = "{call insertEmployee(?,?,?,?)}";
        try (CallableStatement cstmt = conn.prepareCall(prepareSql)) {
            cstmt.setString(2, "ana");
            cstmt.setString(3, "tester");
            cstmt.setInt(4, 20000);
            cstmt.registerOutParameter(1, Types.INTEGER);

            cstmt.execute();
            int id = cstmt.getInt(1);
            System.out.println("cstmt result id: " + id);
        }
    }

    private void preparedStatement(Connection conn) throws SQLException {
        String updatePositionSql = "update employees set position = ? where emp_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(updatePositionSql)) {
            pstmt.setString(1, "lead developer");
            pstmt.setInt(2, 1);
            int affectedRows = pstmt.executeUpdate();
            System.out.println("pstmt affected rows: " + affectedRows);
        }
    }

    private void statement(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            String dropTableSql = "drop table if exists employees ;";
            stmt.execute(dropTableSql);

            String tableSql = "create table if not exists employees (" +
                    "emp_id int primary key auto_increment, name varchar(30)," +
                    "position varchar(30)," +
                    "salary double" +
                    ")";
            stmt.execute(tableSql);
            int affectedRows = stmt.getUpdateCount();
            System.out.println("create table affected rows: " + affectedRows);

            String insertSql = "insert into employees(name, position, salary) values('john', 'developer', 20000)";
            affectedRows = stmt.executeUpdate(insertSql);
            System.out.println("update table affected rows: " + affectedRows);

            String selectSql = "select * from employees";
            try (ResultSet resultSet = stmt.executeQuery(selectSql)) {
                showResultSet(resultSet);
            }

            String dropProcedureSql = "drop procedure if exists insertEmployee";
            stmt.execute(dropProcedureSql);

            String procedureSql = "" +
                    "create procedure insertEmployee(OUT emp_id int, " +
                    "IN emp_name varchar(30), IN position varchar(30), IN salary double) " +
                    "BEGIN " +
                    "INSERT INTO employees(name, position, salary) VALUES(emp_name, position, salary); " +
                    "SET emp_id = LAST_INSERT_ID(); " +
                    "END " +
                    "";
            stmt.execute(procedureSql);
        }
    }

    private void showResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("emp_id") + " "
                    + resultSet.getString("name") + " "
                    + resultSet.getString("position") + " "
                    + resultSet.getDouble("salary"));
        }
    }
}
