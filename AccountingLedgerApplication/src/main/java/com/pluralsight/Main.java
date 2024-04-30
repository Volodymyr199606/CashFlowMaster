package com.pluralsight;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ledger ledger = new Ledger();
        Reports reports = new Reports(ledger);

        while (true) {
            System.out.println("Home Screen");
            System.out.println("D) Add deposit");
            System.out.println("P) Make payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("R) Reports");
            System.out.println("X) Exit");


}
