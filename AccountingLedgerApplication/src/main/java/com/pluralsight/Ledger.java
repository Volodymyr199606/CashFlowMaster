package com.pluralsight;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Ledger {
    public static final String FILENAME = "Files/transactionHistory.csv";
    public ArrayList<Transaction> transactions;
    public Ledger() {
        transactions = new ArrayList<>();
        loadTransactions();
    }



}
