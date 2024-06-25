package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.extentReports.ExtentManager;
import utilities.Screenshot;

import java.io.IOException;

public class Listeners implements ITestListener {
    private static ExtentReports extentReports = ExtentManager.createExtentReports();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private WebDriver driver;

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extentReports.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Log the failure
        extentTest.get().log(Status.FAIL, result.getThrowable());

        // Capture screenshot
        String testName = result.getMethod().getMethodName();
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            if(driver == null){
                System.out.println("Driver is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String screenshotPath = Screenshot.getScreenshot(testName, driver);
            extentTest.get().addScreenCaptureFromPath(screenshotPath);
        } catch (IOException e) {
            extentTest.get().log(Status.FAIL, "Failed to capture screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        // Do any setup here if needed
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}
