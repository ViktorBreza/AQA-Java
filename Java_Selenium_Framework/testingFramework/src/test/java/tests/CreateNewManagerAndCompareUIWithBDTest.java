package tests;

import database.DataBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pageobjects.ManagersPage;
import pageobjects.NewManagerPage;
import utils.RandomDataGenerator;
import utils.ScreenshotUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class CreateNewManagerAndCompareUIWithBDTest extends BaseTest {

    private ManagersPage managersPage;
    private NewManagerPage newManagerPage;
    private static RandomDataGenerator dataGenerator = new RandomDataGenerator();
    private static Map<String, String> testData = dataGenerator.getFakeManager();
    private Map<String, String> tableManagersData;
    private Map<String, String> rowData;
    private static final Logger logger = Logger.getLogger(CreateNewManagerAndCompareUIWithBDTest.class.getName());

    @BeforeAll
    public static void setUpGenerator() {
        // Generate the random data once
        testData = dataGenerator.getFakeManager();
        configureLogger(CreateNewManagerAndCompareUIWithBDTest.class);
    }

    public void shouldCreateNewManager() {
        logger.info("Creating a new manager...");
        managersPage = new ManagersPage(myDriver);
        newManagerPage = new NewManagerPage(myDriver);
        managersPage.openNewManagerPage();
        newManagerPage.fillForm(testData);

    }

    public void shouldRetrieveDataFromUI() {
        logger.info("Retrieving data from UI...");
        managersPage = new ManagersPage(myDriver);
        managersPage.searchManagerFirstNameInputField(testData.get("firstName"));
        managersPage.searchManagerLastNameInputField(testData.get("lastName"));
        managersPage.searchManagerDepartmentInputField("Комната добра");
        managersPage.clickOnSearchManagerFilterButton();
        managersPage.clickOnFilteredManagerInTable();
        tableManagersData = managersPage.createManagerTableDataMap();

    }

    public void shouldRetrieveDataFromDB() {
        logger.info("Retrieving data from DB...");
        DataBase db = DataBase.getInstance();
        db.connect();
        String firstName = testData.get("firstName");
        String lastName = testData.get("lastName");
        String managersPageTableData = "SELECT first_name AS firstname, last_name AS lastname, manager.skype, manager.phone AS phonenumber, login, email_address AS email " +
                "FROM manager " +
                "JOIN department ON manager.department_id = department.id " +
                "JOIN email ON manager.id = email.person_id " +
                "WHERE department.name = 'Комната добра' AND manager.first_name = '" + firstName + "' AND manager.last_name = '" + lastName + "'";
        List<Map<String, Object>> result = db.executeQueryWithResultList(managersPageTableData);
        rowData = new HashMap<>();
        for (Map<String, Object> row : result) {
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                rowData.put(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }

    }

    @Test
    public void shouldCompareUiAndBD() {
        shouldCreateNewManager();
        shouldRetrieveDataFromUI();
        shouldRetrieveDataFromDB();
        logger.info("Expected Result: " + tableManagersData);
        logger.info("Actual Result: " + rowData);
        Assertions.assertEquals(tableManagersData, rowData, "Maps are not equal");
        logger.info("Maps are equal");

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

