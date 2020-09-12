package Tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilis.Utilis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class browser {

    WebDriver driver;
    public static final String url = "http://adactinhotelapp.com/HotelAppBuild2/";
    static Logger log = Logger.getLogger(Utilis.class);
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest test;


    @BeforeMethod
    public void setBrowser() throws MalformedURLException {
        WebDriverManager.edgedriver().arch64();
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        log.info("Browsre launched");
        driver.manage().window().maximize();
        log.info("Browsers maximized");
        driver.get(url);
        log.info("open the url " + url);
    }

    @AfterMethod
    public void closeBrowser(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test case Failed is " + result.getName());
            test.log(Status.FAIL, "Test case Failed is " + result.getThrowable());
            String screenshotpath = browser.getScreenshot(driver, result.getName());
            test.addScreenCaptureFromPath(screenshotpath);

        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test case skipped is " + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test case passed is " + result.getName());
        }
        driver.close();
        log.info("Browser closed");
    }

    @BeforeTest
    public void extentReporterSetup() {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/output/report.html");
        htmlReporter.config().setDocumentTitle("Automation report");
        htmlReporter.config().setReportName("Hotel automation");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setAutoCreateRelativePathMedia(true);
        htmlReporter.config().enableTimeline(true);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @AfterSuite
    public void endReport() {
        extent.flush();
    }


    private static String getScreenshot(WebDriver driver, String screenshotname) throws IOException {
        Date oDate = new Date();
        SimpleDateFormat oSDF = new SimpleDateFormat("yyyyMMddHHmmss");
        String sDate = oSDF.format(oDate);
        String encodedBase64 = null;
        FileInputStream fileInputStream = null;
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "\\screenshots\\" + screenshotname + sDate + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);

        try {
            fileInputStream = new FileInputStream(finalDestination);
            byte[] bytes = new byte[(int) finalDestination.length()];
            fileInputStream.read(bytes);
            encodedBase64 = new String(Base64.encodeBase64(bytes));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "data:image/png;base64," + encodedBase64;


    }

}
