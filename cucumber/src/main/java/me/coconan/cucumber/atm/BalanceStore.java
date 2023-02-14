package me.coconan.cucumber.atm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class BalanceStore {
    private static final String BALANCE_FILE_PATH = "./balance";

    public static void clear() {
        new File(BALANCE_FILE_PATH).delete();

        setBalance(new Money());
    }

    public static Money getBalance() {
        File balanceFile = new File(BALANCE_FILE_PATH);
        try (Scanner scanner = new Scanner(balanceFile)) {
            return Money.from(scanner.nextLine());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setBalance(Money newBalance) {
        try (PrintWriter writer = new PrintWriter(BALANCE_FILE_PATH, StandardCharsets.UTF_8)) {
            writer.println(newBalance.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
