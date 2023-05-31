// Filename: SortCompaniesByCapitalOperation.java
package com.phucnghi.csvreader.operations;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SortCompaniesByCapitalOperation implements CsvOperation {
    private final String csvFilePath;

    public SortCompaniesByCapitalOperation(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    @Override
    public void execute() throws Exception {
        int countryColumnIndex = -1;
        List<Company> companies = new ArrayList<>();

        try (Scanner scanner = new Scanner(Paths.get(csvFilePath))){
            if (scanner.hasNextLine()) {
                String[] header = scanner.nextLine().split(",");
                int nameIndex = -1;
                int capitalIndex = -1;
                for (int i = 0; i < header.length; i++) {
                    if (header[i].equalsIgnoreCase("Name")) {
                        nameIndex = i;
                    } else if (header[i].equalsIgnoreCase("Capital")) {
                        capitalIndex = i;
                    }
                }
                while (scanner.hasNextLine()) {
                    String[] row = scanner.nextLine().split(",");
                    if (countryColumnIndex >= 0 && row[countryColumnIndex].equalsIgnoreCase("CH")) {
                        String name = row[nameIndex];
                        double capital = Double.parseDouble(row[capitalIndex]);
                        companies.add(new Company(name, capital));
                    }
                }
            }
        }

        companies.sort(Comparator.comparingDouble(Company::getCapital).reversed());
        System.out.println("Companies in 'CH' sorted by capital descending: ");
        companies.forEach(company -> System.out.println(company.getName()));
    }

    static class Company {
        private final String name;
        private final double capital;

        public Company(String name, double capital) {
            this.name = name;
            this.capital = capital;
        }

        public String getName() {
            return name;
        }

        public double getCapital() {
            return capital;
        }
    }
}
