package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Locale;

public class MyDriver {
    private static MyDriver instance = null;
    private WebDriver driver;
    private static final String OPERATION_SYSTEM = System.getProperty("os.name");

    private MyDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            setChromeDriver();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            setGeckoDriver();
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Invalid browser name: " + browser);
        }

        driver.manage().window().maximize();
    }

    public static synchronized MyDriver getInstance(String browser) {
        instance = new MyDriver(browser);
        return instance;
    }


    private void setChromeDriver() {
        String driverDir = System.getProperty("user.home").replaceAll("\\\\", "/") +
                "/.m2/repository/webdriver/chrome/";
        if (OPERATION_SYSTEM.toLowerCase(Locale.ROOT).contains("windows")) {
            System.setProperty("webdriver.chrome.driver", driverDir + "chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", driverDir + "chromedriver");
        }
    }

    private void setGeckoDriver() {
        String driverDir = System.getProperty("user.home").replaceAll("\\\\", "/") +
                "/.m2/repository/webdriver/gecko/";
        if (OPERATION_SYSTEM.toLowerCase(Locale.ROOT).contains("windows")) {
            System.setProperty("webdriver.gecko.driver", driverDir + "geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", driverDir + "geckodriver");
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        Duration timeout = Duration.ofSeconds(15);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait;
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
            instance = null;
        }
    }
}
