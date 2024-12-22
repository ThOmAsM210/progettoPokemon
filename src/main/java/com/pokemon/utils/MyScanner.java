package com.pokemon.utils;

import java.util.Scanner;

public class MyScanner {
    private static Scanner scanner;
    
    private MyScanner() {}
   
    public static Scanner getInstance() {
        if (scanner == null) {
            synchronized (MyScanner.class) {
                if (scanner == null) {
                    scanner = new Scanner(System.in);
                }
            }
        }
        return scanner;
    }

    public static void closeScanner() {
        if (scanner != null) {
            scanner.close();
            scanner = null;
        }
    }
}
