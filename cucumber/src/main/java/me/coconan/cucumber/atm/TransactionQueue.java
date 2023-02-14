package me.coconan.cucumber.atm;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class TransactionQueue {
    private static String MESSAGES_FOLDER = "./messages";
    private static String MESSAGE_FILE_PATH = "%s/%03d";

    private int nextId = 1;

    public static void clear() {
        try {
            FileUtils.deleteDirectory(new File(MESSAGES_FOLDER));
        } catch (IOException e) {
            e.printStackTrace();
        }

        new File(MESSAGES_FOLDER).mkdirs();
    }

    public void write(String transaction) {
        String messageFilePath = String.format(MESSAGE_FILE_PATH, MESSAGES_FOLDER, nextId);

        try (PrintWriter writer = new PrintWriter(messageFilePath, StandardCharsets.UTF_8)) {
            writer.println(transaction);
            nextId++;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String read() {
        File messagesFolder = new File(MESSAGES_FOLDER);
        File[] messages = messagesFolder.listFiles();

        String message = "";
        if (messages != null && messages.length > 0) {
            Arrays.sort(messages, Comparator.comparingInt(f -> Integer.parseInt(f.getName())));
            try (Scanner scanner = new Scanner(messages[0])) {
                message = scanner.nextLine();
                messages[0].delete();
            } catch (FileNotFoundException e) {
                // File has gone away!
            }
        }

        return message;
    }
}
