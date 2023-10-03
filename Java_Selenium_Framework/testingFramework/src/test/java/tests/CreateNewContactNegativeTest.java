package tests;

import helpfiles.ContactHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pageobjects.ContactsPage;
import utils.ScreenshotUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CreateNewContactNegativeTest extends BaseTest {

    private static ContactHelper contactHelper;
    private static ContactsPage contactsPage;
    private static final Logger logger = Logger.getLogger(CreateNewContactNegativeTest.class.getName());

    @BeforeEach
    public void setUpHelper() {
        contactHelper = new ContactHelper(myDriver);
        configureLogger(CreateNewContactNegativeTest.class);
        contactsPage = new ContactsPage(myDriver);

    }

    @Test
    public void shouldShowErrorMessagesForInvalidData() {
        // Create invalid data
        Map<String, String> contactData = new HashMap<>();
        contactData.put("firstName", "John");
        contactData.put("lastName", "A"); // Last name shorter than 2 characters
        contactData.put("email", ""); // Empty email
        contactData.put("login", ""); // Empty login
        contactData.put("ticketPrefix", "Ab"); // Invalid prefix;

        // Open the New Contact page and enter all the invalid data at once
        contactsPage.openNewContactPage();
        contactHelper.fillNewContactForm(contactData);

        // Test empty email
        contactHelper.checkEmptyEmailErrorMessage();
        logger.info("Error message 'Email is required' is displayed as expected on the page.");

        // Test invalid email
        contactData.put("email", "InvalidEmail");
        contactHelper.checkInvalidEmailErrorMessage();
        logger.info("Error message 'Email must be valid' is displayed as expected on the page.");

        // Test invalid prefix
        contactHelper.checkInvalidPrefixErrorMessage();
        logger.info("Error message 'Tickets prefix must be from 3 to 6 characters long' is displayed as expected on the page.");

        // Test empty login
        contactHelper.checkEmptyLoginMessage();
        logger.info("Error message 'Login is required' is displayed as expected on the page.");

        // Test invalid last name
        contactHelper.checkInvalidLastNameErrorMessage();
        logger.info("Error message 'Last name must be at least 2 characters long.' is displayed as expected on the page.");
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








