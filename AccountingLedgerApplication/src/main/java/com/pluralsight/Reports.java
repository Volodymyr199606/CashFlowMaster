package com.pluralsight;

import java.util.Calendar;
import java.util.Scanner;

public class Reports {
    public Ledger ledger;

    public Reports(Ledger ledger) {
        this.ledger = ledger;
    }

    String orange = "\033[38;5;208m";
    String reset = "\033[0m";

    public void runReportMenu(Scanner scanner) {
        while (true) {

            System.out.println();
            System.out.printf(orange + "%88s%n" + reset, "REPORTS SCREEN");
            System.out.println();
            System.out.println(orange + "┌───────────────────────┐" + reset);
            System.out.println(orange + "│ 1 - Month to Date     │" + reset);
            System.out.println(orange + "│ 2 - Previous Month    │" + reset);
            System.out.println(orange + "│ 3 - Year To Date      │" + reset);
            System.out.println(orange + "│ 4 - Previous Year     │" + reset);
            System.out.println(orange + "│ 5 - Search by Vendor  │" + reset);
            System.out.println(orange + "│ 6 - Custom Search     │" + reset);
            System.out.println(orange + "│ 0 - Back              │" + reset);
            System.out.println(orange + "│ H - Home              │" + reset);
            System.out.println(orange + "└───────────────────────┘" + reset);

            System.out.print(orange + "Pick from options: " + reset);


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
                case "6":
                    customSearch(scanner);
                    break;
                case "0":
                    return;
                case "H":
                    System.out.println(orange + "Going back to Home Screen..." + reset);
                    return;
                default:
                    System.out.println(orange + "Invalid choice. Please try again." + reset);
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

        System.out.println();
        System.out.println(orange + "Month to Date Report:" + reset);
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

        System.out.println();
        System.out.println(orange + "Previous Month Report:" + reset);
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

        System.out.println();
        System.out.println(orange + "Year To Date Report:" + reset);
        System.out.println();
        displayReport(startDate, endDate);
    }

    private void generatePreviousYearReport() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        int year = calendar.get(Calendar.YEAR);

        String startDate = String.format("%04d-01-01", year);
        String endDate = String.format("%04d-12-31", year);

        System.out.println();
        System.out.println(orange + "Previous Year Report:" + reset);
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
        System.out.print(orange + "Enter vendor name:" + reset);
        String vendor = scanner.nextLine();
        System.out.println(orange + "Transactions for Vendor: " + vendor + reset);
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
    private void customSearch(Scanner scanner) {
        System.out.print(orange + "Enter start date (yyyy-mm-dd) or leave blank: " + reset);
        String startDate = scanner.nextLine();
        System.out.print(orange + "Enter end date (yyyy-mm-dd) or leave blank: " + reset);
        String endDate = scanner.nextLine();
        System.out.print(orange + "Enter description or leave blank: " + reset);
        String description = scanner.nextLine();
        System.out.print(orange + "Enter vendor or leave blank: " + reset);
        String vendor = scanner.nextLine();
        System.out.print(orange + "Enter amount or leave blank: " + reset);
        String amountString = scanner.nextLine();
        Double amount = amountString.isEmpty() ? null : Double.parseDouble(amountString);

        

        System.out.println(orange + "Custom Search Results:" + reset);
        System.out.println();
        System.out.printf("%-12s%-8s%-20s%-15s%-10s%n",
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("──────────────┬──────┬────────────────────┬───────────────┬──────────");

        for (Transaction transaction : ledger.getTransactions()) {
            if ((startDate.isEmpty() || transaction.getDate().compareTo(startDate) >= 0) &&
                    (endDate.isEmpty() || transaction.getDate().compareTo(endDate) <= 0) &&
                    (description.isEmpty() || transaction.getDescription().equalsIgnoreCase(description)) &&
                    (vendor.isEmpty() || transaction.getVendor().equalsIgnoreCase(vendor)) &&
                    (amount == null || transaction.getAmount() == amount)) {
                System.out.printf("%-12s%-8s%-20s%-15s%.2f%n",
                        transaction.getDate(), transaction.getTime(),
                        transaction.getDescription(), transaction.getVendor(),
                        transaction.getAmount());
            }
        }
    }


}