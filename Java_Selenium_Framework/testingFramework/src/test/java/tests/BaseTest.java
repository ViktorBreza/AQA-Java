package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.MyDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class BaseTest {
    protected MyDriver myDriver;
    private static final String OPERATION_SYSTEM = System.getProperty("os.name");
    protected static final Logger logger = Logger.getLogger(BaseTest.class.getName());

    @BeforeEach
    public void setUp() {
        // Set the root logger's level to INFO
        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(java.util.logging.Level.INFO);

        System.setProperty("webdriver.http.factory", "jdk-http-client");
        myDriver = MyDriver.getInstance("chrome"); // Вказати бажаний браузер (chrome або firefox)
        myDriver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        myDriver.getDriver().manage().window().maximize();
        login("thadmin5", "tickethub");
    }


    @AfterEach
    public void tearDown() {
        myDriver.quit();
    }

    public static void configureLogger(Class<?> testClass) {
        try {
            String logFileName = testClass.getSimpleName() + "_log.log";
            String logsFolderPath = System.getProperty("user.dir") + "/logs/";
            String logFilePath = logsFolderPath + logFileName;

            File logsFolder = new File(logsFolderPath);
            if (!logsFolder.exists()) {
                logsFolder.mkdirs();
            }

            FileHandler fileHandler = new FileHandler(logFilePath, true);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);

            Logger logger = Logger.getLogger(testClass.getName());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void login(String username, String password) {
        WebDriver driver = myDriver.getDriver();
        driver.get("http://176.36.27.131:8180/#/tickets");
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-signin"));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

}
