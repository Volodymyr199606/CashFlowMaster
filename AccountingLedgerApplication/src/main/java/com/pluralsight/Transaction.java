package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Transaction {
    public static final String FILENAME = "Files/transactionHistory.csv";

    public String date;
    public String time;
    public String description;
    public String vendor;
    public double amount;

    public Transaction(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
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

    public static void addDeposit(String date, String time, String description, String vendor, double amount) {
        String transaction = String.format("%s|%s|%s|%s|$%.2f", date, time, description, vendor, amount);
        writeTransactionToFile(transaction);
    }

    public static void makePayment(String date, String time, String description, String vendor, double amount) {
        String transaction = String.format("%s|%s|%s|%s|-$%.2f", date, time, description, vendor, amount);
        writeTransactionToFile(transaction);
    }

    public static void writeTransactionToFile(String transaction) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME, true))) {
            writer.println(transaction);
            System.out.println("Transaction added successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while writing to file.");

        }
    }


}