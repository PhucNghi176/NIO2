package com.phucnghi.csvreader;

import java.io.IOException;
import java.util.List;

public abstract class CSVReaderTemplate {
    public static com.phucnghi.csvreader.CSVReaderFactory CSVReaderFactory;

    public final void readCSV(String CSV_FILE_PATH, String IMPORT_FOLDER_PATH) throws IOException, InterruptedException {
        String CSV_FILE_NAME = getFileName(CSV_FILE_PATH);
        int countryColumnIndex = findCountryColumnIndex(CSV_FILE_PATH);
        int rowCount = countRowsWithCH(countryColumnIndex, CSV_FILE_PATH);
        System.out.println("Number of rows with 'CH' in the 'Country' column: " + rowCount);

        List<Company> companies = getCompaniesSortedByCapital(countryColumnIndex, CSV_FILE_PATH);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~\nCompanies in 'CH' sorted by capital descending: ");
        companies.forEach(company -> System.out.println(company.getName()));

        monitorImportFolder(countryColumnIndex, companies, CSV_FILE_PATH, IMPORT_FOLDER_PATH, CSV_FILE_NAME);
    }

    private String getFileName(String CSV_FILE_PATH) throws IOException {
        int lastIndex = CSV_FILE_PATH.lastIndexOf("\\");
        if (lastIndex >= 0 && lastIndex < CSV_FILE_PATH.length() - 1) {
            return CSV_FILE_PATH.substring(lastIndex + 1);
        }
        return "";
    }

    protected abstract int findCountryColumnIndex(String CSV_FILE_PATH) throws IOException;

    protected abstract int countRowsWithCH(int countryColumnIndex, String CSV_FILE_PATH) throws IOException;

    protected abstract List<Company> getCompaniesSortedByCapital(int countryColumnIndex, String CSV_FILE_PATH) throws IOException;

    protected abstract void monitorImportFolder(int countryColumnIndex, List<Company> companies, String CSV_FILE_PATH, String IMPORT_FOLDER_PATH, String CSV_FILE_NAME) throws IOException, InterruptedException;


}

