package com.phucnghi.csvreader;

public class CSVReaderFactory {
    public static CSVReaderTemplate createCSVReader() {
        return new CSVReader();
    }
}
