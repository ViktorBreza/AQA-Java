package tests;

import helpfiles.CompanyHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.ScreenshotUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class AddNewCompanyTest extends BaseTest {
    private static CompanyHelper companyHelper;
    private static String companyName;
    private static final Logger logger = Logger.getLogger(AddNewCompanyTest.class.getName());

    @BeforeEach
    public void setUpHelper() {
        companyHelper = new CompanyHelper(myDriver);
        companyName = companyHelper.generateRandomCompanyName();
        configureLogger(AddNewCompanyTest.class);
    }

    @Test
    public void shouldCompareUiAndDB() {
        logger.info("Generating random company name...");
        companyHelper.createNewCompany(companyName);
        logger.info("New company created with name: " + companyName);

        logger.info("Retrieving data from UI for company: " + companyName);
        Map<String, String> tableCompanyData = companyHelper.retrieveDataFromUI(companyName);

        logger.info("Retrieving data from DB for company: " + companyName);
        Map<String, String> rowData = companyHelper.retrieveDataFromDB(companyName);

        logger.info("Comparing data from UI and DB...");
        Assertions.assertEquals(tableCompanyData, rowData, "Data from UI and DB do not match");
    }

    @Test
    public void shouldCompareDataUsedForCreatingCompanyWithUIData() {
        logger.info("Generating random company name...");
        String companyName = companyHelper.generateRandomCompanyName();
        companyHelper.createNewCompany(companyName);
        logger.info("New company created with name: " + companyName);

        logger.info("Retrieving data from UI for company: " + companyName);
        Map<String, String> tableCompanyData = companyHelper.retrieveDataFromUI(companyName);

        Map<String, String> expectedCompanyData = new HashMap<>();
        expectedCompanyData.put("name", companyName);

        logger.info("Comparing data used for creating the company with data from UI...");

        for (Map.Entry<String, String> entry : expectedCompanyData.entrySet()) {
            String key = entry.getKey();
            String expectedValue = entry.getValue();

            if (tableCompanyData.containsKey(key)) {
                String actualValue = tableCompanyData.get(key);
                Assertions.assertEquals(expectedValue, actualValue, "Data mismatch for field: " + key);
            } else {
                Assertions.fail("Field not found in data from UI: " + key);
            }
        }

        logger.info("Data used for creating the company matches the data from UI.");
    }

    @AfterEach
    public void tearDownTest() {
        if (myDriver.getDriver() != null) {
            WebDriver driver = myDriver.getDriver();
            if (driver instanceof TakesScreenshot) {
                String testClassName = this.getClass().getSimpleName();
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String fileName = testClassName + "_" + timeStamp + ".png";
                ScreenshotUtils.captureScreenshot(driver, fileName);
            }
            myDriver.quit();
        }
    }
}
