package org.currencyConverter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyConverterFileReaderTest {
    @Mock
    private BufferedReader bufferedReader;

    @InjectMocks
    private CurrencyConverterFileReader reader;

    @Test
    public void testReadConversionRates() throws IOException {
        // Arrange
        String currenciesLine = "CUR,USD,GBP,CAD,EUR,AUD";
        String ratesLine = "USD,1,1.36905,0.73043,1.25164,0.82429";
        when(bufferedReader.readLine()).thenReturn(currenciesLine, ratesLine, null);
        CurrencyConverterFileReader reader = new CurrencyConverterFileReader(bufferedReader);

        // Act
        Map<String, BigDecimal> conversionRates = reader.getConversionRates();

        // Assert
        assertEquals(5, conversionRates.size());
        assertEquals(BigDecimal.ONE, conversionRates.get("USD"));
        assertEquals(new BigDecimal("1.36905"), conversionRates.get("GBP"));
        assertEquals(new BigDecimal("0.73043"), conversionRates.get("CAD"));
        assertEquals(new BigDecimal("1.25164"), conversionRates.get("EUR"));
        assertEquals(new BigDecimal("0.82429"), conversionRates.get("AUD"));
    }

    @Test
    public void testConstructorIOException() throws IOException {
        // Arrange
        IOException exception = new IOException("Test IOException");
        when(bufferedReader.readLine()).thenThrow(exception);

        // Act and Assert
        assertThrows(IOException.class, () -> new CurrencyConverterFileReader(bufferedReader));
    }
}