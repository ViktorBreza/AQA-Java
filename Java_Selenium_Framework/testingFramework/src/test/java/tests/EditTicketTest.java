package tests;

import helpfiles.TicketHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageobjects.EditTicketPage;
import pageobjects.TicketsPage;
import utils.ScreenshotUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class EditTicketTest extends BaseTest {
    private final Logger logger = Logger.getLogger(CreateNewTicketAndCompareUIWithBDTest.class.getName());
    //
    private TicketHelper ticketHelper;
    private String ticketTitle;
    private String editedTicketTitle;

    @BeforeEach
    public void setUpHelper() {
        ticketHelper = new TicketHelper(myDriver);
        ticketTitle = ticketHelper.generateRandomTicketTitle();
        configureLogger(CreateNewTicketAndCompareUIWithBDTest.class);
        editedTicketTitle = ticketTitle + "1";
    }

    @Test
    public void shouldEditTicketAndCompareData() throws InterruptedException {
        logger.info("Creating a new ticket and comparing data...");
        ticketHelper.createNewTicket(ticketTitle);
        Assertions.assertEquals(ticketHelper.convertMapToLowerCase(ticketHelper.retrieveDataFromUI()), ticketHelper.convertMapToLowerCase(ticketHelper.retrieveEditedDataFromDB(ticketTitle)));

        logger.info("Editing newly created ticket...");
        ticketHelper.editTicketTitle(editedTicketTitle);
        Assertions.assertEquals(ticketHelper.convertMapToLowerCase(ticketHelper.retrieveDataFromUI()), ticketHelper.convertMapToLowerCase(ticketHelper.retrieveEditedDataFromDB(editedTicketTitle)));
        logger.info("Edited ticket data: " + ticketHelper.retrieveEditedDataFromDB(editedTicketTitle));
    }

    @AfterEach
    public void tearDownTest() {
        if (myDriver.getDriver() != null) {
            ScreenshotUtils.captureScreenshot(myDriver.getDriver(), this.getClass().getSimpleName() + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".png");
            myDriver.quit();
        }
    }
}
