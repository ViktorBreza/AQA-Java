package tests;

import helpfiles.ContactHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pageobjects.ContactsPage;
import pageobjects.CreateContactPage;
import utils.ScreenshotUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

public class DeleteContactTest extends BaseTest {
    private static final Logger logger = Logger.getLogger(CreateNewContactNegativeTest.class.getName());
    private static ContactHelper contactHelper;
    private static ContactsPage contactsPage;
    Map<String, String> contactData;
    String firstName;
    private CreateContactPage createContactPage;

    @BeforeEach
    public void setUpHelper() {
        contactHelper = new ContactHelper(myDriver);
        configureLogger(CreateNewContactNegativeTest.class);
        contactsPage = new ContactsPage(myDriver);
        createContactPage = new CreateContactPage(myDriver);
        contactData = contactHelper.generateRandomContactData();
        firstName = contactData.get("firstName");
    }

    @Test
    public void shouldCreateAndDeleteContact() {
        contactHelper.createNewContact(contactData);
        logger.info("Contact data: " + contactData);

        Map<String, Object> dbData = contactHelper.retrieveDataFromDB(contactData.get("firstName"));
        Assertions.assertEquals(contactData, dbData);

        contactsPage.clickOnDeleteContactButton();

        dbData = contactHelper.retrieveDataFromDB(contactData.get("firstName"));
        Assertions.assertEquals(0, dbData.size(), "The contact was not deleted successfully. "
                + "The database still contains data for the contact.");
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
