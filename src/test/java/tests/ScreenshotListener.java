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

    // Attach screenshot to Allure report
    @Attachment(value = "Screenshot on Failure", type = "image/png")
    public byte[] captureScreenshotForAllure(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
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

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).driver;

        if (driver != null) {
            // Attach screenshot to Allure report
            captureScreenshotForAllure(driver);

            // Save screenshot locally
            saveScreenshotLocally(driver, result.getName());
        } else {
            System.err.println("WebDriver instance is null, unable to capture screenshot.");
        }
    }
}
