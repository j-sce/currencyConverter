#  Currency Converter

In response to NetXMS task.
The Currency Converter is a Java application that allows users to convert amounts between different currencies using provided conversion rates. The application reads conversion rates from a text file and provides a simple command-line interface for users to perform currency conversion.


## Task 

Create currency converter. Currency data set should be read from file:
CUR,USD,GBP,CAD,EUR,AUD
USD,1,1.36905,0.73043,1.25164,0.82429


## Features
Load conversion rates from a text file.
Convert amounts between currencies.
Simple command-line interface for user interaction.

## Usage
Follow the prompts to enter the amount, currency to convert from, and currency to convert to.
The application will display the converted amount based on the provided conversion rates.

## File Format
The application expects the conversion rates to be provided in a text file in the following format:

CUR,USD,GBP,CAD,EUR,AUD
USD,1,1.36905,0.73043,1.25164,0.82429

The first line contains the list of currencies.
The second line contains the corresponding conversion rates relative to the base currency (in this example, USD).