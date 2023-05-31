// Filename: Main.java

package com.phucnghi.csvreader;

import com.phucnghi.csvreader.factories.CsvOperationFactory;
import com.phucnghi.csvreader.operations.CsvOperation;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;



public class Main {
    public static void Main(String[] args) {
// Get the operation type from command line argument
        String operationType = args[0];

        // Get the path to the CSV file from command line argument
        String csvFilePath = args[1];

        // Create a new CsvOperation using the CsvOperationFactory
        CsvOperation csvOperation = CsvOperationFactory.createCsvOperation(operationType);

        try {
            // Read in the content of the CSV file as a string
            String csvContent = Files.readString(Paths.get(csvFilePath));

            // Perform the appropriate operation on the CSV data
            String result = csvOperation.performOperation(csvContent);

            // Print the result to the console
            System.out.println(result);

            // Log a success message with the current date and time
            LocalDateTime currentTime = LocalDateTime.now();
            System.out.println("Successfully performed " + operationType + " operation on " + csvFilePath + " at " + currentTime.toString());
        } catch (IOException e) {
            // Log an error message and stack trace
            System.err.println("Error reading CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}