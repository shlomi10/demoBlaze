package tests;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName() + " - Capturing screenshot");

        try {
            Object testClass = result.getInstance();
            if (testClass instanceof BaseTest) {
                WebDriver driver = ((BaseTest) testClass).driver;
                if (driver != null) {
                    attachScreenshotToAllure(driver);
                } else {
                    System.err.println("Driver is null - cannot take screenshot");
                }
            }
        } catch (Exception e) {
            System.err.println("Exception taking screenshot: " + e);
            e.printStackTrace();
        }
    }

    // Attach screenshot to Allure report (Failure only)
    @Attachment(value = "Screenshot on Failure", type = "image/png")
    public byte[] attachScreenshotToAllure(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
