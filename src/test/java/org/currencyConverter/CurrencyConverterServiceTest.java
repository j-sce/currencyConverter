package org.currencyConverter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CurrencyConverterServiceTest {
    private CurrencyConverterService converter;

    @BeforeEach
    public void setUp() {
        Map<String, BigDecimal> conversionRates = new HashMap<>();
        conversionRates.put("USD", BigDecimal.valueOf(1));
        conversionRates.put("EUR", BigDecimal.valueOf(0.82));
        conversionRates.put("GBP", BigDecimal.valueOf(0.73));
        conversionRates.put("AUD", BigDecimal.valueOf(1.29));
        converter = new CurrencyConverterService(conversionRates);
    }

    @Test
    public void testConvertValidCurrencies() {
        // Arrange
        String fromCurrency = "USD";
        String toCurrency = "EUR";
        BigDecimal amount = BigDecimal.valueOf(100);

        // Act
        BigDecimal result = converter.convert(fromCurrency, toCurrency, amount);

        // Assert
        BigDecimal expected = BigDecimal.valueOf(100).divide(BigDecimal.valueOf(0.82), 10, RoundingMode.HALF_UP);
        assertEquals(expected, result);
    }

    @Test
    public void testConvertValidCurrenciesWithoutBaseCurrency() {
        // Arrange
        String fromCurrency = "GBP";
        String toCurrency = "EUR";
        BigDecimal amount = BigDecimal.valueOf(10);

        // Act
        BigDecimal result = converter.convert(fromCurrency, toCurrency, amount);

        // Assert
        BigDecimal expected = BigDecimal.valueOf(10).multiply(BigDecimal.valueOf(0.73))
                .divide(BigDecimal.valueOf(0.82), 10, RoundingMode.HALF_UP);
        assertEquals(expected, result);
    }

    @Test
    public void testConvertFromCurrencyNotFound() {
        // Arrange
        String fromCurrency = "CAD"; // Not in conversion rates
        String toCurrency = "USD";
        BigDecimal amount = BigDecimal.valueOf(100);

        // Act
        BigDecimal result = converter.convert(fromCurrency, toCurrency, amount);

        // Assert
        BigDecimal expected = BigDecimal.valueOf(-1);
        assertEquals(expected, result);
    }

    @Test
    public void testConvertToCurrencyNotFound() {
        // Arrange
        String fromCurrency = "USD";
        String toCurrency = "JPY"; // Not in conversion rates
        BigDecimal amount = BigDecimal.valueOf(100);

        // Act
        BigDecimal result = converter.convert(fromCurrency, toCurrency, amount);

        // Assert
        BigDecimal expected = BigDecimal.valueOf(-1);
        assertEquals(expected, result);
    }

    @Test
    public void testConvertBothCurrenciesNotFound() {
        // Arrange
        String fromCurrency = "CAD"; // Not in conversion rates
        String toCurrency = "JPY"; // Not in conversion rates
        BigDecimal amount = BigDecimal.valueOf(100);

        // Act
        BigDecimal result = converter.convert(fromCurrency, toCurrency, amount);

        // Assert
        BigDecimal expected = BigDecimal.valueOf(-1);
        assertEquals(expected, result);
    }

    @Test
    public void testConvertNullAmount() {
        // Arrange
        String fromCurrency = "USD"; // Not in conversion rates
        String toCurrency = "EUR"; // Not in conversion rates
        BigDecimal amount = null;

        // Act and Assert
        assertThrows(NullPointerException.class, () -> converter.convert(fromCurrency, toCurrency, amount));
    }
}