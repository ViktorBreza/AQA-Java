package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pageobjects.CreateTicketPage;
import pageobjects.TicketsPage;

import java.util.List;
import java.util.logging.Logger;


public class TicketsTableValuesTest extends BaseTest {
    private TicketsPage ticketsPage;
    private CreateTicketPage createTicketPage;
    private static final Logger logger = Logger.getLogger(TicketsTableValuesTest.class.getName());

    @BeforeAll
    public static void setUpLogger() {
        configureLogger(TicketsTableValuesTest.class);
    }

    @Test
    public void shouldPrintAllTicketTableValues() {
        ticketsPage = new TicketsPage(myDriver);
        createTicketPage = new CreateTicketPage(myDriver);
        ticketsPage.clickOnNewTicketButton();
        createTicketPage.clickOnCancelButton();
        List<String> ticketIds = ticketsPage.getAllIdNames();
        List<String> ticketTitles = ticketsPage.getAllTitleNames();
        List<String> ticketStages = ticketsPage.getAllStageNames();

        if (ticketIds.isEmpty()) {
            logger.severe("No Ticket Ids found");
            Assertions.fail("No Ticket Ids found");
        } else {
            logger.info("Getting all Ticket Ids: " + ticketIds);
        }

        if (ticketTitles.isEmpty()) {
            logger.severe("No Ticket Titles found");
            Assertions.fail("No Ticket Titles found");
        } else {
            logger.info("Getting all Ticket Titles: " + ticketTitles);
        }

        if (ticketStages.isEmpty()) {
            logger.severe("No Ticket Stages found");
            Assertions.fail("No Ticket Stages found");
        } else {
            logger.info("Getting all Ticket Stages: " + ticketStages);
        }
    }

}
