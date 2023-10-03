package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

    public class ScreenshotUtils {
        public static void captureScreenshot(WebDriver driver, String fileName) {
            if (driver instanceof TakesScreenshot screenshotDriver) {
                File screenshotFile = screenshotDriver.getScreenshotAs(OutputType.FILE);
                Path destinationPath = Paths.get("target", "screenshots", fileName);

                try {
                    Files.createDirectories(destinationPath.getParent());
                    Files.copy(screenshotFile.toPath(), destinationPath);
                    System.out.println("Screenshot saved to: " + destinationPath);
                } catch (IOException e) {
                    System.out.println("Failed to save the screenshot: " + e.getMessage());
                }
            } else {
                System.out.println("Driver does not support taking screenshots.");
            }
        }
    }



