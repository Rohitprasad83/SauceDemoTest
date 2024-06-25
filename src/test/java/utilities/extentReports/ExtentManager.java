package utilities.extentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
        public static final ExtentReports extentReports = new ExtentReports();

        public synchronized static ExtentReports createExtentReports() {
            ExtentSparkReporter reporter = new ExtentSparkReporter("./extent-reports/extent-report.html");
            reporter.config().setReportName("SauceDemo Report");
            extentReports.attachReporter(reporter);
            extentReports.setSystemInfo("Author", "Rohit Prasad");
            return extentReports;
        }
}
