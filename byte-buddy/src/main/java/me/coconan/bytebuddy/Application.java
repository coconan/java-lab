package me.coconan.bytebuddy;

public class Application {

    public static void main(String[] args) throws Exception {
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
