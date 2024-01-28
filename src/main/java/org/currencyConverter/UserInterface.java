package org.currencyConverter;

import java.math.BigDecimal;
import java.util.Scanner;

public class UserInterface {
    private final CurrencyConverterService service;
    private final Scanner scanner;

    public UserInterface(CurrencyConverterService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void startUI() {
        System.out.print("Enter the amount: ");
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.nextLine();

        System.out.print("Enter the currency to convert from: ");
        String fromCurrency = scanner.nextLine().trim().toUpperCase();

        System.out.print("Enter the currency to convert to: ");
        String toCurrency = scanner.nextLine().trim().toUpperCase();

        BigDecimal result = service.convert(fromCurrency, toCurrency, amount);
        if (result.compareTo(BigDecimal.valueOf(-1)) != 0) {
            System.out.println("Converted amount: " + result);
        }
    }
}
