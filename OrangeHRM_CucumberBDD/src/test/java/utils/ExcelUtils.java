package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelUtils {

    private Sheet sheet;
    private Workbook workbook;
    private String filePath;

    public ExcelUtils(String filePath, String sheetName) {
        try {
            this.filePath = filePath;
            FileInputStream fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            fis.close();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load Excel file: " + filePath, e);
        }
    }

    // ---------------- READ --------------------

    public String getCell(int row, int col) {
        return sheet.getRow(row).getCell(col).toString();
    }

    public int getRowCount() {
        return sheet.getLastRowNum();
    }

    public List<Map<String, String>> getDataAsList() {
        List<Map<String, String>> data = new ArrayList<>();
        Row headerRow = sheet.getRow(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Map<String, String> map = new LinkedHashMap<>();

            for (int j = 0; j < row.getLastCellNum(); j++) {
                String header = headerRow.getCell(j).toString();
                String value = row.getCell(j).toString();
                map.put(header, value);
            }
            data.add(map);
        }
        return data;
    }

    // ---------------- WRITE --------------------

    public void writeCell(int row, int col, String value) {
        try {
            Row r = sheet.getRow(row);
            if (r == null) r = sheet.createRow(row);

            Cell cell = r.getCell(col);
            if (cell == null) cell = r.createCell(col);

            cell.setCellValue(value);

            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            fos.close();
        } catch (Exception e) {
            throw new RuntimeException("Failed to write to Excel", e);
        }
    }
}
