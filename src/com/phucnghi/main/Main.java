package com.phucnghi.main;

import com.phucnghi.csvreader.CSVReaderTemplate;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String CSV_FILE_PATH = "/* Your file path location */";
        String IMPORT_FOLDER_PATH = "/* Your folder you want to import */";
        CSVReaderTemplate csvReader = CSVReaderTemplate.CSVReaderFactory.createCSVReader();
        csvReader.readCSV(CSV_FILE_PATH,IMPORT_FOLDER_PATH);
    }
}
