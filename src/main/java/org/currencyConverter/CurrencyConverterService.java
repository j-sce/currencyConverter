package org.currencyConverter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class CurrencyConverterService {
    private final Map<String, BigDecimal> conversionRates;

    public CurrencyConverterService(Map<String, BigDecimal> conversionRates) {
        this.conversionRates = conversionRates;
    }

    public BigDecimal convert(String fromCurrency, String toCurrency, BigDecimal amount) {
        BigDecimal notFound = BigDecimal.valueOf(-1);
        if (!conversionRates.containsKey(fromCurrency) && !conversionRates.containsKey(toCurrency)) {
            System.out.println("Currencies " + fromCurrency + ", " + toCurrency + " not found!");
            return notFound;
        } else if (!conversionRates.containsKey(fromCurrency)) {
            System.out.println("Currency " + fromCurrency + " not found!");
            return notFound;
        } else if (!conversionRates.containsKey(toCurrency)) {
            System.out.println("Currency " + toCurrency + " not found!");
            return notFound;
        }

        BigDecimal fromRate = conversionRates.get(fromCurrency);
        BigDecimal toRate = conversionRates.get(toCurrency);

        // 1. Convert to the base currency 2. Return conversion to the target currency
        BigDecimal amountInBaseCurrency = amount.multiply(fromRate);
        return amountInBaseCurrency.divide(toRate, 10, RoundingMode.HALF_UP);
    }
}
