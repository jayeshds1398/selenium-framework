package com.qa.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * ExcelUtils.java
 * ----------------
 * Utility class for reading test data from Excel (.xlsx) files.
 * Uses Apache POI library.
 *
 * Usage:
 *   String[][] data = ExcelUtils.getTestData("LoginData.xlsx", "LoginTests");
 *
 * The returned 2D array contains all rows and columns from the sheet.
 * Row 0 is assumed to be the header row and is SKIPPED.
 */
public class ExcelUtils {

    private static final Logger log = LogManager.getLogger(ExcelUtils.class);

    /**
     * Reads all data rows from the given sheet.
     * Skips header row (row index 0).
     *
     * @param filePath  Path to the .xlsx file
     * @param sheetName Name of the sheet to read
     * @return 2D String array [rows][columns] (excluding header row)
     */
    public static String[][] getTestData(String filePath, String sheetName) {
        String[][] data = null;

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + sheetName + " in file: " + filePath);
            }

            // Total rows minus 1 (skip header row)
            int totalRows    = sheet.getPhysicalNumberOfRows() - 1;
            int totalColumns = sheet.getRow(0).getPhysicalNumberOfCells();

            data = new String[totalRows][totalColumns];

            for (int i = 1; i <= totalRows; i++) {       // start from row 1 to skip header
                Row row = sheet.getRow(i);
                for (int j = 0; j < totalColumns; j++) {
                    Cell cell = row.getCell(j);
                    data[i - 1][j] = getCellValue(cell);
                }
            }

            log.info("Test data loaded from: " + filePath + " | Sheet: " + sheetName
                    + " | Rows: " + totalRows + " | Columns: " + totalColumns);

        } catch (IOException e) {
            log.error("Error reading Excel file: " + e.getMessage());
            throw new RuntimeException("Failed to read Excel file: " + filePath, e);
        }

        return data;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell).trim();
    }

	// Prevent instantiation
    private ExcelUtils() {}
}
