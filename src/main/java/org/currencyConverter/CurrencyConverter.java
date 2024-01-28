package org.currencyConverter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class CurrencyConverter {
    private static final String FILE_PATH = "src/main/resources/conversion_rates.txt";

    public static void main(String[] args) {
        try {
            Map<String, BigDecimal> conversionRates = loadConversionRatesFromFile(FILE_PATH);
            CurrencyConverterApp app = new CurrencyConverterApp(conversionRates);
            app.run();
        } catch (IOException e) {
            System.err.println("Error loading conversion rates from file: " + e.getMessage());
        }
    }

    private static Map<String, BigDecimal> loadConversionRatesFromFile(String filePath) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            CurrencyConverterFileReader fileReader = new CurrencyConverterFileReader(bufferedReader);
            return fileReader.getConversionRates();
        }
    }
}
