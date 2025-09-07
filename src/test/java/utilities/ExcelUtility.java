package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    private String filePath;
    private FileInputStream fis;
    private FileOutputStream fos;
    private Workbook workbook;
    private Sheet sheet;

    // Constructor - load Excel file
    public ExcelUtility(String filePath) throws IOException {
        this.filePath = filePath;
        fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        fis.close();
    }

    // Get row count
    public int getRowCount(String sheetName) {
        sheet = workbook.getSheet(sheetName);
        return sheet.getLastRowNum();
    }

    // Get column count
    public int getCellCount(String sheetName, int rowIndex) {
        sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowIndex);
        return row.getLastCellNum();
    }

    // Get cell data
    public String getCellData(String sheetName, int rowIndex, int colIndex) {
        sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowIndex);
        Cell cell = row.getCell(colIndex);

        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

    // Set cell data
    public void setCellData(String sheetName, int rowIndex, int colIndex, String data) throws IOException {
        sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }

        Cell cell = row.getCell(colIndex);
        if (cell == null) {
            cell = row.createCell(colIndex);
        }
        cell.setCellValue(data);

        fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
    }

    // Close workbook
    public void closeWorkbook() throws IOException {
        workbook.close();
    }
}
