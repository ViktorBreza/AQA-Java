package tests;

import helpfiles.TicketHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

public class CreateNewInnerTicketAndCompareUIWithBDTest extends BaseTest {

    private static TicketHelper ticketHelper;
    private static String ticketTitle;
    private static final Logger logger = Logger.getLogger(CreateNewInnerTicketAndCompareUIWithBDTest.class.getName());

    @BeforeEach
    public void setUpHelper() {
        // Generate the random data once
        ticketHelper = new TicketHelper(myDriver);
        ticketTitle = ticketHelper.generateRandomTicketTitle();
        configureLogger(CreateNewInnerTicketAndCompareUIWithBDTest.class);
    }

    @Test
    public void shouldCompareTicketData() {
        logger.info("Creating a new inner ticket...");
        ticketHelper.createNewInnerTicket(ticketTitle, myDriver);

        Map<String, String> ticketUIData = ticketHelper.retrieveInnerTicketDataFromUI(myDriver);
        Map<String, Object> ticketDBData = ticketHelper.retrieveDataFromDB(ticketTitle);

        // Transform maps to lowercase for case-insensitive comparison
        Map<String, String> ticketUIDataIgnoreCase = ticketHelper.convertMapToLowerCase(ticketUIData);
        Map<String, String> ticketDBDataIgnoreCase = ticketHelper.convertMapToLowerCase(ticketDBData);

        // Compare the maps using TreeMap
        TreeMap<String, String> sortedUIData = new TreeMap<>(ticketUIDataIgnoreCase);
        TreeMap<String, String> sortedDBData = new TreeMap<>(ticketDBDataIgnoreCase);

        if (sortedUIData.equals(sortedDBData)) {
            logger.info("Maps are equal:\n" + sortedUIData);
        } else {
            logger.info("Maps are not equal.");
            logger.info("Data from UI:\n" + sortedUIData);
            logger.info("Data from DB:\n" + sortedDBData);
        }

        Assertions.assertEquals(sortedUIData, sortedDBData);
    }
}
