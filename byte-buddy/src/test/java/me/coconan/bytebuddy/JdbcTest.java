package me.coconan.bytebuddy;

import org.junit.jupiter.api.Test;

public class JdbcTest {
    private static final String URL = "jdbc:mysql://mysql-test:3306/bank?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";

    @Test
    public void test() throws Exception {

        DemoDao demoDao = new DemoDao();
        for (Account account : demoDao.getAccount()) {
            System.out.println("----------------------------------------------------");
            System.out.println("id: " + account.getId());
            System.out.println("number: " + account.getNumber());
            System.out.println("balance: " + account.getBalance());
            System.out.println("----------------------------------------------------");
        }
    }
}
