package org.bddpractice.demoblaze.tests;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author sbryt on 29/03/2021
 */
public class MyListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (!result.isSuccess()) {
            File screenShot = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.FILE);
            File dest = new File("screenshots", result.getName() + " " + new Date().getTime() + ".png");
            try {
                FileUtils.copyFile(screenShot, dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                getScreenShotAsByteArray(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] getScreenShotAsByteArray(ITestResult iTestResult) throws IOException {
        File fileForCopy = new File(iTestResult.getName() + ".jpg");
        File screenshotFile = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, fileForCopy);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FileUtils.readFileToByteArray(fileForCopy);
    }
}
