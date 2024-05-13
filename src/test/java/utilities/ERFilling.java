package utilities;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ERFilling {
    private FileInputStream file;
    private Workbook workbook;
    private Sheet sheet;
    private double taxAmount;
    private double totalAmount;

    public void enteringValuesInER(double itemPrice, String filePath) throws IOException {
        file = new FileInputStream(new File(filePath));
        workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheet("Main Calculation");

        sheet.getRow(1).getCell(1).setCellValue(itemPrice);
        XSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);

        taxAmount = sheet.getRow(5).getCell(1).getNumericCellValue();
        totalAmount = sheet.getRow(6).getCell(1).getNumericCellValue();

        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
        workbook.close();
        file.close();
    }

    public double getTaxAmount(){
        return taxAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}