package tests;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.DashboardPage;
import java.util.logging.Logger;

public class DashboardPageValuesTest extends BaseTest {
    private DashboardPage dashboardPage;
    private static final Logger logger = Logger.getLogger(DashboardPageValuesTest.class.getName());

    @BeforeAll
    public static void setUpLogger() {
        configureLogger(DashboardPageValuesTest.class);
    }

    @Test
    public void shouldPrintAllTitlesDevelopmentCategory() {
        logger.info("Printing all titles in Development category...");
        WebDriver driver = myDriver.getDriver();
        dashboardPage = new DashboardPage(myDriver);
        logger.info("All Tickets in Development category titles: " + (dashboardPage.getAllTitlesByCategory("Разработка")));
        logger.info("All Tickets in Finances category titles: " + (dashboardPage.getAllTitlesByCategory("Финансы")));
        logger.info("All Tickets with P3 priority Id's: " + (dashboardPage.getAllIdsByPriorityP3()));
    }

}
