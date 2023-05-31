package com.phucnghi.main;

import com.phucnghi.csvreader.CSVReaderTemplate;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String CSV_FILE_PATH = "C:\\Users\\Admin\\Desktop\\ELCA\\import\\test.csv";
        String IMPORT_FOLDER_PATH = "C:\\Users\\Admin\\Desktop\\ELCA\\import";
        CSVReaderTemplate csvReader = CSVReaderTemplate.CSVReaderFactory.createCSVReader();
        csvReader.readCSV(CSV_FILE_PATH,IMPORT_FOLDER_PATH);
    }
}
