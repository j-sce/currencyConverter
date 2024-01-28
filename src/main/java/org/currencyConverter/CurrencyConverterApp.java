package org.currencyConverter;

import java.math.BigDecimal;
import java.util.Map;

public class CurrencyConverterApp {
    private final UserInterface ui;

    public CurrencyConverterApp(Map<String, BigDecimal> conversionRates) {
        CurrencyConverterService service = new CurrencyConverterService(conversionRates);
        this.ui = new UserInterface(service);
    }

    public void run() {
        ui.startUI();
    }
}
