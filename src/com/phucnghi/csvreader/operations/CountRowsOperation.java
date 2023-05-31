// Filename: CountRowsOperation.java
package com.phucnghi.csvreader.operations;

import java.nio.file.Paths;
import java.util.Scanner;

public class CountRowsOperation implements CsvOperation {
    private final String csvFilePath;

    public CountRowsOperation(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    @Override
    public void execute() throws Exception {
        int countryColumnIndex = -1;
        int rowCount = 0;

        try (Scanner scanner = new Scanner(Paths.get(csvFilePath))){
            if (scanner.hasNextLine()) {
                String[] header = scanner.nextLine().split(",");
                for (int i = 0; i < header.length; i++) {
                    if (header[i].equalsIgnoreCase("Country")) {
                        countryColumnIndex = i;
                        break;
                    }
                }
            }

            while (scanner.hasNextLine()) {
                String[] row = scanner.nextLine().split(",");
                if (countryColumnIndex >= 0 && row[countryColumnIndex].equalsIgnoreCase("CH")) {
                    rowCount++;
                }
            }
        }

        System.out.println("Number of rows with 'CH' in the 'Country' column: " + rowCount);
    }
}
