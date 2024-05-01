package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Ledger {
    private ArrayList<Transaction> transactions;

    public Ledger() {
        transactions = new ArrayList<>();
        loadTransactions();
    }

    public void loadTransactions() {
        try {
            createFileIfNotExists(); // Create the file if it doesn't exist
            BufferedReader reader = new BufferedReader(new FileReader(Transaction.FILENAME));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String date = parts[0];
                String time = parts[1];
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);
                transactions.add(new Transaction(date, time, description, vendor, amount));
            }
            reader.close();
            sortTransactions();
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Error occurred while reading from file: " + e.getMessage());
        }
    }

    private void createFileIfNotExists() {
        Path path = Paths.get(Transaction.FILENAME);
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                System.out.println("Error occurred while creating file: " + e.getMessage());
            }
        }
    }

    public void sortTransactions() {
        Collections.sort(transactions, Comparator.comparing(Transaction::getDate).reversed());
    }

    public void displayLedger() {
        System.out.println("Ledger Screen");
        System.out.println("Date       Time     Description       Vendor       Amount");
        System.out.println("-------------------------------------------------------");
        for (Transaction transaction : transactions) {
            System.out.printf("%-10s%-8s%-20s%-12s%.2f%n",
                    transaction.getDate(), transaction.getTime(),
                    transaction.getDescription(), transaction.getVendor(),
                    transaction.getAmount());
        }
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        sortTransactions();
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}