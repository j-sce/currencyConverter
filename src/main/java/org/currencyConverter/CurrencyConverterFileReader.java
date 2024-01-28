package org.currencyConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverterFileReader {
    private final Map<String, BigDecimal> conversionRates;
    private final BufferedReader bufferedReader;

    public CurrencyConverterFileReader(BufferedReader bufferedReader) throws IOException {
        this.conversionRates = new HashMap<>();
        this.bufferedReader = bufferedReader;
        readConversionRates();
    }

    private void readConversionRates() throws IOException {
        String line;
        if ((line = bufferedReader.readLine()) != null) {
            String[] currencies = line.split(",");
            String[] rates = bufferedReader.readLine().split(",");

            for (int i = 1; i < currencies.length; i++) {
                String currency = currencies[i].trim();
                BigDecimal rate = new BigDecimal(rates[i].trim());
                conversionRates.put(currency, rate);
            }
        }
    }

    public Map<String, BigDecimal> getConversionRates() {
        return conversionRates;
    }
}
