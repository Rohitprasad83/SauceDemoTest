package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshot {
    public static String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        if(driver == null){
            System.out.println("Driver is null from screenshot class");
        }
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot fs = (TakesScreenshot)driver;
        File source = fs.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//extent-reports//Screenshots//"+testCaseName+"_"+dateName+".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("user.dir")+"//reports"+testCaseName+".png";
    }
}
