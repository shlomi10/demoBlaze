package tests;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotListener implements ITestListener {

    private static final String SCREENSHOT_FOLDER = "Reports/screenshots/";

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName() + " - Taking screenshot");

        try {
            Object testClass = result.getInstance();
            if (testClass instanceof BaseTest) {
                WebDriver driver = ((BaseTest) testClass).driver;
                if (driver != null) {
                    // Attach screenshot to Allure report
                    byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    attachScreenshotToAllure(screenshotBytes);

                    // Save screenshot locally
                    saveScreenshotLocally(driver, result.getName());
                } else {
                    System.err.println("Driver is null - cannot take screenshot");
                }
            }
        } catch (Exception e) {
            System.err.println("Exception taking screenshot: " + e);
            e.printStackTrace();
        }
    }

    // ✅ This method attaches the screenshot to Allure
    @Attachment(value = "Screenshot on Failure", type = "image/png")
    public static byte[] attachScreenshotToAllure(byte[] screenshot) {
        return screenshot;  // Ensure Allure receives the screenshot bytes
    }

    // Save screenshot locally
    public void saveScreenshotLocally(WebDriver driver, String testName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filePath = SCREENSHOT_FOLDER + testName + "_" + timestamp + ".png";

        try {
            File screenshotFolder = new File(SCREENSHOT_FOLDER);
            if (!screenshotFolder.exists()) {
                screenshotFolder.mkdirs();  // Ensure the directory exists
            }
            FileUtils.copyFile(screenshot, new File(filePath));
            System.out.println("Screenshot saved: " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
