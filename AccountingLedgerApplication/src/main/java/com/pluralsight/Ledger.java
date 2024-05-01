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
            createFileIfNotExists();
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

    public void displayLedger(String option) {
        System.out.println("Ledger Screen");
        System.out.printf("%-11s%-8s%-24s%-18s%-10s%n", "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("-----------------------------------------------------------");

        ArrayList<Transaction> displayTransactions = new ArrayList<>();

        switch (option.toUpperCase()) {
            case "A":
                displayTransactions.addAll(transactions);
                break;
            case "D":
                for (Transaction transaction : transactions) {
                    if (transaction.getAmount() > 0) {
                        displayTransactions.add(transaction);
                    }
                }
                break;
            case "P":
                for (Transaction transaction : transactions) {
                    if (transaction.getAmount() < 0) {
                        displayTransactions.add(transaction);
                    }
                }
                break;
            default:
                System.out.println("Invalid option. Displaying all entries.");
                displayTransactions.addAll(transactions);
        }

        Collections.sort(displayTransactions, Comparator.comparing(Transaction::getDate).reversed());

        for (Transaction transaction : displayTransactions) {
            String amountString = transaction.getAmount() >= 0 ?
                    String.format("$%.2f", transaction.getAmount()) :
                    String.format("-$%.2f", Math.abs(transaction.getAmount())); // Add dollar sign and handle negative amounts

            System.out.printf("%-11s%-8s%-24s%-18s%-10s%n",
                    transaction.getDate(), transaction.getTime(),
                    transaction.getDescription(), transaction.getVendor(),
                    amountString);
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