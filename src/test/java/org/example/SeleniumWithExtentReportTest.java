package org.example;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.util.Arrays;

import static java.lang.Thread.*;

public class SeleniumWithExtentReportTest {

    WebDriver driver;
    ExtentReports report;
    ExtentTest test;
    JavascriptExecutor js;

    @BeforeTest
    public void startReport(){
        report = new ExtentReports(System.getProperty("user.dir") + "/test-output/Report.html", true);
        report
                .addSystemInfo("Host Name", "QA")
                .addSystemInfo("Tester","David");
        report.loadConfig(new File(System.getProperty("user.dir") + "\\extent-report.xml"));

    }

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
    }


    @Test
    public void testStockMarketRiser() throws InterruptedException, IOException {
        test = report.startTest("Checking for the largest stock price index riser");
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Started chrome browser and maximised it");
        sleep(1000);

        driver.get("https://www.hl.co.uk/shares/stock-market-summary/ftse-100");
        test.log(LogStatus.INFO, "Navigating to the FTSE 100 market overview");
        sleep(1000);

        js.executeScript("window.scrollBy(0,500)");

        WebElement risersButton = driver.findElement(By.partialLinkText("RISERS"));
        risersButton.click();

        WebElement risersTable = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.presenceOfElementLocated(By.tagName("tbody")));
        String[] arrayRiser = risersTable.getText().split("\n");

        js.executeScript("window.scrollBy(0,1000)");

        File risersPage = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(risersPage, new File(System.getProperty("user.dir") + "/test-output/risersPage.jpg"));

        test.log(LogStatus.PASS, "This is a screenshot showing that " + arrayRiser[0] + " is the largest riser", "<img src=\"risersPage.jpg\"/>");

        test.log(LogStatus.INFO, "This is a screenshot showing that " + arrayRiser[0] + " is the largest riser");


        sleep(10000);

    }

    @Test
    public void testStockMarketFaller() throws InterruptedException, IOException {
        test = report.startTest("Checking for the largest stock price index faller");
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Started chrome browser and maximised it");
        sleep(1000);

        driver.get("https://www.hl.co.uk/shares/stock-market-summary/ftse-100");
        test.log(LogStatus.INFO, "Navigating to the FTSE 100 market overview");
        sleep(1000);

        js.executeScript("window.scrollBy(0,500)");

        WebElement risersButton = driver.findElement(By.partialLinkText("FALLERS"));
        risersButton.click();

        WebElement fallersTable = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.presenceOfElementLocated(By.tagName("tbody")));
        String[] arrayFaller = fallersTable.getText().split("\n");

        js.executeScript("window.scrollBy(0,1000)");

        File risersPage = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(risersPage, new File(System.getProperty("user.dir") + "/test-output/fallersPage.jpg"));

        test.log(LogStatus.PASS, "This is a screenshot showing that " + arrayFaller[0] + " is the largest faller", "<img src=\"fallersPage.jpg\"/>");

        test.log(LogStatus.INFO, "This is a screenshot showing that " + arrayFaller[0] + " is the largest faller");


        sleep(10000);

    }



    @AfterMethod
    public void getResult(ITestResult result){
        driver.close();
        if(result.getStatus() == ITestResult.FAILURE){
            test.log(LogStatus.FAIL, "Test has failed " + result.getName());
            test.log(LogStatus.FAIL, "Test has failed " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(LogStatus.PASS, "Test has passed " + result.getName());
        }
        report.endTest(test);
    }

    @AfterTest
    public void endReport(){
        report.flush();
        report.close();
    }

}
