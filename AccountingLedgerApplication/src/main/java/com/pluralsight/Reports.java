package com.pluralsight;

import java.util.Calendar;
import java.util.Scanner;

public class Reports {
    public Ledger ledger;

    public Reports(Ledger ledger) {
        this.ledger = ledger;
    }

    public void runReportMenu(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.printf("%88s%n", "REPORTS SCREEN");
            System.out.println("┌───────────────────────┐");
            System.out.println("│ 1 - Month to Date     │");
            System.out.println("│ 2 - Previous Month    │");
            System.out.println("│ 3 - Year To Date      │");
            System.out.println("│ 4 - Previous Year     │");
            System.out.println("│ 5 - Search by Vendor  │");
            System.out.println("│ 0 - Back              │");
            System.out.println("│ H - Home              │");
            System.out.println("└───────────────────────┘");
            System.out.print("Pick from options: ");


            String choice = scanner.nextLine();

            switch (choice.toUpperCase()) {
                case "1":
                    generateMonthToDateReport();
                    break;
                case "2":
                    generatePreviousMonthReport();
                    break;
                case "3":
                    generateYearToDateReport();
                    break;
                case "4":
                    generatePreviousYearReport();
                    break;
                case "5":
                    searchByVendor(scanner);
                    break;
                case "0":
                    return;
                case "H":
                    System.out.println("Going back to Home Screen...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void generateMonthToDateReport() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        String startDate = String.format("%04d-%02d-01", year, month);
        String endDate = String.format("%04d-%02d-%02d", year, month, day);


        System.out.println("Month to Date Report:");
        System.out.println();

        displayReport(startDate, endDate);
    }

    private void generatePreviousMonthReport() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

        String startDate = String.format("%04d-%02d-01", year, month);
        String endDate = String.format("%04d-%02d-%02d", year, month, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        System.out.println("Previous Month Report:");
        System.out.println();
        displayReport(startDate, endDate);
    }

    private void generateYearToDateReport() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        String startDate = String.format("%04d-01-01", year);
        String endDate = String.format("%04d-%02d-%02d", year, month, day);

        System.out.println("Year to Date Report:");
        System.out.println();
        displayReport(startDate, endDate);
    }

    private void generatePreviousYearReport() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        int year = calendar.get(Calendar.YEAR);

        String startDate = String.format("%04d-01-01", year);
        String endDate = String.format("%04d-12-31", year);

        System.out.println("Previous Year Report:");
        System.out.println();
        displayReport(startDate, endDate);
    }

    private void displayReport(String startDate, String endDate) {
        System.out.printf("%-12s%-8s%-20s%-15s%-10s%n",
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("──────────────┬──────┬────────────────────┬───────────────┬──────────");

        for (Transaction transaction : ledger.getTransactions()) {
            if (transaction.getDate().compareTo(startDate) >= 0 && transaction.getDate().compareTo(endDate) <= 0) {
                System.out.printf("%-12s%-8s%-20s%-15s$%.2f%n",
                        transaction.getDate(), transaction.getTime(),
                        transaction.getDescription(), transaction.getVendor(),
                        transaction.getAmount());
            }
        }
    }

    private void searchByVendor(Scanner scanner) {
        System.out.print("Enter vendor name: ");
        String vendor = scanner.nextLine();
        System.out.println("Transactions for Vendor: " + vendor);
        System.out.println();
        System.out.printf("%-12s%-8s%-20s%-15s%-10s%n",
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("──────────────┬──────┬────────────────────┬───────────────┬──────────");

        for (Transaction transaction : ledger.getTransactions()) {
            if (transaction.getVendor().equalsIgnoreCase(vendor)) {
                System.out.printf("%-12s%-8s%-20s%-17s$%.2f%n",
                        transaction.getDate(), transaction.getTime(),
                        transaction.getDescription(), transaction.getVendor(),
                        transaction.getAmount());
            }
        }
    }
}