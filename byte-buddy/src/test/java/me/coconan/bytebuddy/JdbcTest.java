package me.coconan.bytebuddy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JdbcTest {
    @Test
    public void test_jdbc_interception() throws Exception {
        DemoDao demoDao = new DemoDao();
        for (Account account : demoDao.getAccount()) {
            System.out.println("----------------------------------------------------");
            System.out.println("id: " + account.getId());
            System.out.println("number: " + account.getNumber());
            System.out.println("balance: " + account.getBalance());
            System.out.println("----------------------------------------------------");
            assertEquals("10000.89", account.getBalance().toString());
        }
    }
}
