package org.currencyConverter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest {
    private final InputStream originalInput = System.in;
    private final PrintStream originalOutput = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setIn(originalInput);
        System.setOut(originalOutput);
    }

    @Test
    public void testStartUI() {
        //Arrange
        String input = "100\nUSD\nEUR\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Map<String, BigDecimal> conversionRates = new HashMap<>();
        conversionRates.put("USD", BigDecimal.valueOf(1.0));
        conversionRates.put("EUR", BigDecimal.valueOf(0.8));

        CurrencyConverterService mockService = new CurrencyConverterService(conversionRates);
        UserInterface ui = new UserInterface(mockService);

        //Act
        ui.startUI();

        //Assert
        String expectedOutput = "Enter the amount: Enter the currency to convert from: Enter the currency to convert to: Converted amount: 125.0000000000\r\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
}