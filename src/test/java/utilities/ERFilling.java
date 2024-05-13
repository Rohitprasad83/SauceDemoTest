package utilities;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ERFilling {

    private double taxAmount;
    private double totalAmount;

    public void enteringValuesInER(double itemPrice, String filePath) throws IOException {
        FileInputStream file = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet("Main Calculation");
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
    public void makeERCopy(String filePath, String name) throws IOException {
        FileInputStream ER = new FileInputStream(new File(filePath));
        Workbook ERWorkbook = new XSSFWorkbook(ER);

        FileOutputStream copyER = new FileOutputStream(new File("C:\\Users\\pdroh\\IdeaProjects\\SauceDemoTest\\src\\test\\resources\\"+name+".xlsx"));
        ERWorkbook.write(copyER);
        copyER.close();
        ERWorkbook.close();
        ER.close();
    }
}