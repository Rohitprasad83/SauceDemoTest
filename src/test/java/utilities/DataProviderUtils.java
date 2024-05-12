package utilities;
import org.testng.annotations.DataProvider;
import java.io.IOException;
import java.util.List;

public class DataProviderUtils {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException {
        ExcelReader excelReader = new ExcelReader();
        List<List<String>> loginTestData = excelReader.readExcel("C:\\Users\\pdroh\\IdeaProjects\\SauceDemoTest\\src\\test\\resources\\LoginTestData.xlsx",
                "Login");

        Object[][] testData = new Object[loginTestData.size() - 1][3]; // -1 to skip header row
        for (int row = 1; row < loginTestData.size(); row++) {
            testData[row - 1][0] = loginTestData.get(row).get(0); // username
            testData[row - 1][1] = loginTestData.get(row).get(1); // password
            testData[row - 1][2] = loginTestData.get(row).get(2); // expectedResults
        }
        return testData;
    }

    @DataProvider(name = "productData")
    public Object[][] getProductData() throws IOException {
        ExcelReader excelReader = new ExcelReader();
        List<List<String>> productTestData = excelReader.readExcel("C:\\Users\\pdroh\\IdeaProjects\\SauceDemoTest\\src\\test\\resources\\LoginTestData.xlsx",
                "Products");

        Object[][] testData = new Object[productTestData.size() - 1][3]; // -1 to skip header row
        for (int row = 1; row < productTestData.size(); row++) {
            testData[row - 1][0] = productTestData.get(row).get(0); // Product Name
            testData[row - 1][1] = productTestData.get(row).get(1); // Product Description
            testData[row - 1][2] = productTestData.get(row).get(2); // Product Price
        }
        return testData;
    }

}
