package com.phucnghi.main;

import com.phucnghi.csvreader.CSVReaderTemplate;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        CSVReaderTemplate csvReader = CSVReaderTemplate.CSVReaderFactory.createCSVReader();
        csvReader.readCSV();
    }
}
