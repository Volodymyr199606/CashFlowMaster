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
            System.out.println("Reports");
            System.out.println("1) Month to Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.println("H) Home");


}