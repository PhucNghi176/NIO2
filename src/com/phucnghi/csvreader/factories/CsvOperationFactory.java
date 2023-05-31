// Filename: CsvOperationFactory.java
package com.phucnghi.csvreader.factories;

import com.phucnghi.csvreader.operations.CsvOperation;
import com.phucnghi.csvreader.operations.CountRowsOperation;
import com.phucnghi.csvreader.operations.SortCompaniesByCapitalOperation;

public class CsvOperationFactory {
    public static CsvOperation createCsvOperation(String operationType) {
        switch (operationType.toUpperCase()) {
            case "COUNT_ROWS":
                return new CountRowsOperation(csvFilePath);
            case "SORT_COMPANIES_BY_CAPITAL":
                return new SortCompaniesByCapitalOperation(csvFilePath);
            default:
                throw new IllegalArgumentException("Invalid operation type");
        }
    }
}
