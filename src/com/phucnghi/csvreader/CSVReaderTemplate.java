package com.phucnghi.csvreader;

import java.io.IOException;
import java.util.List;

public abstract class CSVReaderTemplate {
    private static final String CSV_FILE_PATH = "C:\\Users\\Admin\\Desktop\\ELCA\\import\\New Microsoft Excel Worksheet.csv";
    private static final String IMPORT_FOLDER_PATH = "C:\\Users\\Admin\\Desktop\\ELCA\\import";
    public static com.phucnghi.csvreader.CSVReaderFactory CSVReaderFactory;

    public final void readCSV() throws IOException, InterruptedException {
        int countryColumnIndex = findCountryColumnIndex();
        int rowCount = countRowsWithCH(countryColumnIndex);
        System.out.println("Number of rows with 'CH' in the 'Country' column: " + rowCount);

        List<Company> companies = getCompaniesSortedByCapital(countryColumnIndex);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~\nCompanies in 'CH' sorted by capital descending: ");
        companies.forEach(company -> System.out.println(company.getName()));

        monitorImportFolder(countryColumnIndex, companies);
    }

    protected abstract int findCountryColumnIndex() throws IOException;

    protected abstract int countRowsWithCH(int countryColumnIndex) throws IOException;

    protected abstract List<Company> getCompaniesSortedByCapital(int countryColumnIndex) throws IOException;

    protected abstract void monitorImportFolder(int countryColumnIndex, List<Company> companies) throws IOException, InterruptedException;


}

