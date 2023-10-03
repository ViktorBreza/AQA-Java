package tests;

import helpfiles.ContactHelper;
import org.junit.jupiter.api.*;
import pageobjects.ContactsPage;
import pageobjects.CreateContactPage;
import utils.ScreenshotUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EditContactTest extends BaseTest {
    private static final Logger logger = Logger.getLogger(CreateNewContactNegativeTest.class.getName());
    Map<String, String> contactData;
    String firstName;
    String newFirstName;
    private ContactHelper contactHelper;
    private ContactsPage contactsPage;
    private CreateContactPage createContactPage;

    @BeforeEach
    public void setUpHelper() {
        contactHelper = new ContactHelper(myDriver);
        configureLogger(CreateNewContactNegativeTest.class);
        contactsPage = new ContactsPage(myDriver);
        createContactPage = new CreateContactPage(myDriver);
        contactData = contactHelper.generateRandomContactData();
        firstName = contactData.get("firstName");
        newFirstName = firstName + "1";
    }

    @Test
    @Order(1)
    public void shouldCreateContact() {
        contactHelper.createNewContact(contactData);
        logger.info("Contact data: " + contactData);

        Map<String, Object> dbData = contactHelper.retrieveDataFromDB(contactData.get("firstName"));
        Assertions.assertEquals(contactData, dbData);
    }

    @Test
    @Order(2)
    public void shouldEditContact() {
        // Modify the contact data
        contactData.put("firstName", newFirstName);

        contactHelper.editExistedContact(newFirstName);
        Map<String, Object> editedDbData = contactHelper.retrieveDataFromDB(newFirstName);

        logger.info("Edited contact data: " + editedDbData);
        Assertions.assertEquals(contactData, editedDbData);
    }

    @AfterEach
    public void tearDownTest() {
        if (myDriver.getDriver() != null) {
            ScreenshotUtils.captureScreenshot(myDriver.getDriver(), this.getClass().getSimpleName() + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".png");
            myDriver.quit();
        }
    }
}
