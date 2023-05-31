package com.phucnghi.csvreader;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


    class CSVReader extends CSVReaderTemplate {

        @Override
        protected int findCountryColumnIndex(String CSV_FILE_PATH) throws IOException {
            int countryColumnIndex = -1;
            try (Scanner scanner = new Scanner(Paths.get(CSV_FILE_PATH))) {
                if (scanner.hasNextLine()) {
                    String[] header = scanner.nextLine().split(",");
                    for (int i = 0; i < header.length; i++) {
                        if (header[i].equalsIgnoreCase("Country")) {
                            countryColumnIndex = i;
                            break;
                        }
                    }
                }
            }
            return countryColumnIndex;
        }

        @Override
        protected int countRowsWithCH(int countryColumnIndex,String CSV_FILE_PATH) throws IOException {
            int rowCount = 0;
            try (Scanner scanner = new Scanner(Paths.get(CSV_FILE_PATH))) {
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
                while (scanner.hasNextLine()) {
                    String[] row = scanner.nextLine().split(",");
                    if (countryColumnIndex >= 0 && row[countryColumnIndex].equalsIgnoreCase("CH")) {
                        rowCount++;
                    }
                }
            }
            return rowCount;
        }

        @Override
        protected List<Company> getCompaniesSortedByCapital(int countryColumnIndex,String CSV_FILE_PATH) throws IOException {
            List<Company> companies = new ArrayList<>();
            try (Scanner scanner = new Scanner(Paths.get(CSV_FILE_PATH))) {
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
            return companies;
        }

        @Override
        protected void monitorImportFolder(int countryColumnIndex, List<Company> companies,String CSV_FILE_PATH,String IMPORT_FOLDER_PATH,String CSV_FILE_NAME) throws IOException, InterruptedException {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path importFolderPath = Paths.get(IMPORT_FOLDER_PATH);
            importFolderPath.register(watchService/*, StandardWatchEventKinds.ENTRY_CREATE*/, StandardWatchEventKinds.ENTRY_MODIFY);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~\n"+"Watching folder: " + IMPORT_FOLDER_PATH);

            boolean isRunning = true;
            while (isRunning) {
                WatchKey key = watchService.take();
                LocalDateTime now = LocalDateTime.now();
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~\n"+now + ": Import folder changed");
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    if (kind == StandardWatchEventKinds.OVERFLOW) {
                        continue;
                    }

                    Path changedPath = (Path) event.context();
                    if (CSV_FILE_NAME.equals(changedPath.getFileName().toString())) {
                        System.out.println(now + ": Detected CSV file change");
                        companies.clear();
                        try (Scanner scanner = new Scanner(Paths.get(CSV_FILE_PATH))) {
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
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~\n"+now + ": Companies in 'CH' sorted by capital descending: ");
                        companies.forEach(company -> System.out.println(company.getName()));
                        int rowCount = 0;
                        try (Scanner scanner = new Scanner(Paths.get(CSV_FILE_PATH))) {
                            if (scanner.hasNextLine()) {
                                scanner.nextLine();
                            }
                            while (scanner.hasNextLine()) {
                                String[] row = scanner.nextLine().split(",");
                                if (countryColumnIndex >= 0 && row[countryColumnIndex].equalsIgnoreCase("CH")) {
                                    rowCount++;
                                }
                            }
                            System.out.println("~~~~~~~~~~~~~~~~~~~~~~\n"+now + ": Number of rows with 'CH' in the 'Country' column: " + rowCount);
                        }
                    }
                }
                key.reset();
            }
        }
    }

