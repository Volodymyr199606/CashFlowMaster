package com.pluralsight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ledger ledger = new Ledger();
        Reports reports = new Reports(ledger);

        String lightGreen = "\033[92m";
        String reset = "\033[0m";

        while (true) {
            System.out.println();
            System.out.printf(lightGreen + "%95s%n" + reset, "WELCOME TO THE HOME SCREEN");
            System.out.println();


            System.out.println(lightGreen  + "┌───────────────────────────┐" + reset);
            System.out.println(lightGreen  + "│ D - Add deposit           │" + reset);
            System.out.println(lightGreen  + "│ P - Make payment          │" + reset);
            System.out.println(lightGreen  + "│ L - Ledger                │" + reset);
            System.out.println(lightGreen  + "│ R - Reports               │" + reset);
            System.out.println(lightGreen  + "│ X - Exit                  │" + reset);
            System.out.println(lightGreen  + "└───────────────────────────┘" + reset);
            System.out.print(lightGreen + "Pick option: " + reset);

            String choice = scanner.nextLine();

            switch (choice.toUpperCase()) {
                case "D":
                    addDeposit(scanner, ledger);
                    break;
                case "P":
                    makePayment(scanner, ledger);
                    break;
                case "L":
                    System.out.println(lightGreen  + "┌──────────────┐ " + reset);
                    System.out.println(lightGreen  + "│ A - All      │ " + reset);
                    System.out.println(lightGreen  + "│ D - Deposits │ " + reset);
                    System.out.println(lightGreen +  "│ P - Payments │ " + reset);
                    System.out.println(lightGreen  + "└──────────────┘ " + reset);
                    System.out.print(lightGreen  + "Make a selection: " + reset);

                    String ledgerOption = scanner.nextLine();
                    ledger.displayLedger(ledgerOption);
                    break;
                case "R":
                    reports.runReportMenu(scanner);
                    break;
                case "X":
                    System.out.println(lightGreen + "Exiting..." + reset);
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void addDeposit(Scanner scanner, Ledger ledger) {

        String lightGreen = "\033[92m";
        String reset = "\033[0m";

        System.out.println(lightGreen + "Enter deposit information:" + reset);
        System.out.print(lightGreen + "Date (yyyy-mm-dd): " + reset);
        String date = scanner.nextLine();
        System.out.print(lightGreen + "Time (hh:mm): " + reset);
        String time = scanner.nextLine();
        System.out.print(lightGreen + "Description: " + reset);
        String description = scanner.nextLine();
        System.out.print(lightGreen + "Vendor: " + reset);
        String vendor = scanner.nextLine();
        System.out.print(lightGreen + "Amount: " + reset);
        double amount = Double.parseDouble(scanner.nextLine());

        Transaction deposit = new Transaction(date, time, description, vendor, amount);
        deposit.writeToTransactionFile();
        ledger.addTransaction(deposit);
    }

    public static void makePayment(Scanner scanner, Ledger ledger) {

        String lightGreen = "\033[92m";
        String reset = "\033[0m";

        System.out.println(lightGreen + "Enter payment information:" + reset);
        System.out.print(lightGreen + "Date (yyyy-mm-dd): " + reset);
        String date = scanner.nextLine();
        System.out.print(lightGreen + "Time (hh:mm): " + reset);
        String time = scanner.nextLine();
        System.out.print(lightGreen + "Description: " + reset);
        String description = scanner.nextLine();
        System.out.print(lightGreen + "Vendor: " + reset);
        String vendor = scanner.nextLine();
        System.out.print(lightGreen + "Amount: " + reset);
        double amount = Double.parseDouble(scanner.nextLine());

        Transaction payment = new Transaction(date, time, description, vendor, -amount);
        payment.writeToTransactionFile();
        ledger.addTransaction(payment);
    }
}