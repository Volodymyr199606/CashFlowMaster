package com.pluralsight;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


// Transaction class to represent a single transaction
public class Transaction {

    String lightGreen = "\033[92m";
    String reset = "\033[0m";

    // File path for storing transactions
    public static final String FILENAME = "C:\\Pluralsight\\LearnToCode_Capstones\\CashFlowMaster\\AccountingLedgerApplication\\Files\\transactionHistory.csv";

    // Transaction details
    private final String  date;
    private final String time;
    private final String description;
    private final String vendor;
    private final double amount;

    // Constructor to initialize a transaction
    public Transaction(String date, String time, String description, String vendor, double amount) {

        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    // Method to write the transaction to a file
    public void writeToTransactionFile() {
        try {
            createFileIfNotExists();
            String data = String.format("%s|%s|%s|%s|%.2f%n", date, time, description, vendor, amount);
            Files.write(Paths.get(FILENAME), data.getBytes(), StandardOpenOption.APPEND);
            System.out.print(lightGreen  + "Transaction added successfully " + reset);
        } catch (IOException e) {
            System.out.println("Error occurred while writing to file: " + e.getMessage());
        }
    }

    // Method to create a file if it does not exist
    public void createFileIfNotExists() throws IOException {
        Path path = Paths.get(FILENAME);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }


    // Getters for transaction details

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