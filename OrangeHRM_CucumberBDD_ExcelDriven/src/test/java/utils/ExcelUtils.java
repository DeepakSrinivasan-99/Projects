package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtils {

    private static final String EXCEL_PATH =
            System.getProperty("user.dir") + "/src/test/resources/testdata.xlsx";

    public static Map<String, String> getRowData(String scenarioName) {
        Map<String, String> data = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(EXCEL_PATH);
             Workbook wb = new XSSFWorkbook(fis)) {

            Sheet sheet = wb.getSheet("Sanity");

            Row headerRow = sheet.getRow(0);
            int rows = sheet.getPhysicalNumberOfRows();

            for (int i = 1; i < rows; i++) {
                Row row = sheet.getRow(i);

                if (row.getCell(0).getStringCellValue().equalsIgnoreCase(scenarioName)) {

                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        String key = headerRow.getCell(j).getStringCellValue();
                        Cell cell = row.getCell(j);
                        String value = (cell == null) ? "" : cell.toString();

                        data.put(key, value);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Failed to read Excel");
        }

        return data;
    }
}
