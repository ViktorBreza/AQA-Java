package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoginTests {

    private WebDriver driver;
    private String url;

    @BeforeAll
    public void setUp() throws IOException {
        Properties prop = new Properties();
        FileInputStream input = new FileInputStream("src/test/resources/properties");
        prop.load(input);
        //without this I received 403 error and "Unable to establish websocket connection" during chrome test
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        url = prop.getProperty("url");
    }


    @Test
    public void testFirefoxLogin() {
        System.setProperty("webdriver.gecko.driver", "C:/foxminded/webdrivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get(url);
        driver.quit();
    }

    @Test
    public void testChromeLogin() {
        System.setProperty("webdriver.chrome.driver", "C:/foxminded/webdrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
        driver.quit();
    }

    @AfterAll
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}