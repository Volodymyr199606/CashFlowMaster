package com.pluralsight;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Transaction {
    public static final String FILENAME = "Files/transactionHistory.csv";

    private final String  date;
    private final String time;
    private final String description;
    private final String vendor;
    private final double amount;

    public Transaction(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public void writeToTransactionFile() {
        try {
            createFileIfNotExists();
            String data = String.format("%s|%s|%s|%s|%.2f%n", date, time, description, vendor, amount);
            Files.write(Paths.get(FILENAME), data.getBytes(), StandardOpenOption.APPEND);
            System.out.println("Transaction added successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while writing to file: " + e.getMessage());
        }
    }

    public void createFileIfNotExists() throws IOException {
        Path path = Paths.get(FILENAME);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }
}