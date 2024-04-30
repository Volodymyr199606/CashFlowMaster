package com.pluralsight;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ledger ledger = new Ledger();
        Reports reports = new Reports(ledger);

        while (true) {
            System.out.println();
            System.out.println("HOME SCREEN");
            System.out.println();
            System.out.println("D - Add deposit");
            System.out.println("P - Make payment (Debit)");
            System.out.println("L - Ledger");
            System.out.println("R - Reports");
            System.out.println("X - Exit");
            System.out.print("Choose option: ");

            String choice = scanner.nextLine();

            switch (choice.toUpperCase()) {
                case "D":
                    addDeposit(scanner);
                    break;
                case "P":
                    makePayment(scanner);
                    break;
                case "L":
                    ledger.displayLedger();
                    break;
                case "R":
                    reports.runReportMenu(scanner);
                    break;
                case "X":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void addDeposit(Scanner scanner) {
        System.out.println("Enter deposit information:");
        System.out.print("Date (yyyy-mm-dd): ");
        String date = scanner.nextLine();
        System.out.print("Time (hh:mm): ");
        String time = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        Transaction.addDeposit(date, time, description, vendor, amount);
        System.out.println("Deposit added successfully. Amount: $"+ amount);
    }

    
}










