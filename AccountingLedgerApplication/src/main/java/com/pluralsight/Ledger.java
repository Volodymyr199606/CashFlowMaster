package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;

public class Ledger {
    private final ArrayList<Transaction> transactions;

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
                if (parts.length >= 5) {
                    String date = parts[0];
                    String time = parts[1];
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);
                    transactions.add(new Transaction(date, time, description, vendor, amount));
                }
            }
            reader.close();
            sortTransactions();
        } catch (IOException | NumberFormatException e) {
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
        transactions.sort(Comparator.comparing(Transaction::getDate).reversed());
    }



    public void displayLedger(String option) {
        System.out.println("Ledger Screen");
        System.out.println("┌───────────┬──────┬────────────────────┬────────────────┬─────────┐  ");
        System.out.println("│   Date    │ Time │    Description     │    Vendor      │  Amount |  ");
        System.out.println("├───────────┼──────┼────────────────────┼────────────────┼─────────┤  ");

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

        displayTransactions.sort(Comparator.comparing(Transaction::getDate).reversed());


        int[] columnWidths = new int[]{10, 6, 20, 16, 9};
        for (Transaction transaction : displayTransactions) {
            columnWidths[0] = Math.max(columnWidths[0], transaction.getDate().length());
            columnWidths[1] = Math.max(columnWidths[1], transaction.getTime().length());
            columnWidths[2] = Math.max(columnWidths[2], transaction.getDescription().length());
            columnWidths[3] = Math.max(columnWidths[3], transaction.getVendor().length());
            String amountString = String.format("%.2f", Math.abs(transaction.getAmount()));
            columnWidths[4] = Math.max(columnWidths[4], amountString.length() + 3);
        }


        for (Transaction transaction : displayTransactions) {
            String amountString = transaction.getAmount() >= 0 ?
                    String.format("$%.2f", transaction.getAmount()) :
                    String.format("-$%.2f", Math.abs(transaction.getAmount()));

            System.out.printf("│%-"+columnWidths[0]+"s│%-"+columnWidths[1]+"s│%-"+columnWidths[2]+"s│%-"+columnWidths[3]+"s│%-"+columnWidths[4]+"s│%n",
                    transaction.getDate(), transaction.getTime(),
                    transaction.getDescription(), transaction.getVendor(),
                    amountString);
        }
        System.out.println("└───────────┴──────┴────────────────────┴────────────────┴─────────┘");
    }
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        sortTransactions();
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}