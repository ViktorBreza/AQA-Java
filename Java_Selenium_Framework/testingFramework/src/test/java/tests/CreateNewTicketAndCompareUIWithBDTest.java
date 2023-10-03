package tests;

import helpfiles.TicketHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

public class CreateNewTicketAndCompareUIWithBDTest extends BaseTest {

    private TicketHelper ticketHelper;
    private String ticketTitle;
    private final Logger logger = Logger.getLogger(CreateNewTicketAndCompareUIWithBDTest.class.getName());


    @BeforeEach
    public void setUpHelper() {
        ticketHelper = new TicketHelper(myDriver);
        ticketTitle = ticketHelper.generateRandomTicketTitle();
        configureLogger(CreateNewTicketAndCompareUIWithBDTest.class);
    }

    @Test
    public void shouldCreateNewTicketAndCompareData() {
        logger.info("Creating a new ticket and comparing data...");
        ticketHelper.createNewTicket(ticketTitle);

        Map<String, String> ticketUIData = ticketHelper.retrieveDataFromUI();
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
