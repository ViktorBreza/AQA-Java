package tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pageobjects.DepartmentsPage;

public class CreateNewDepartmentTest extends BaseTest {

    private DepartmentsPage departmentsPage;
    private static final Logger logger = Logger.getLogger(CreateNewDepartmentTest.class.getName());

    @BeforeAll
    public static void setUpLogger() {
        configureLogger(CreateNewDepartmentTest.class);
    }

    @Test
    public void shouldCreateNewDepartment() {
        departmentsPage = new DepartmentsPage(myDriver);
        departmentsPage.clickOnMainHamburgerMenu();
        departmentsPage.clickOnDepartmentsButton();
        logger.log(Level.INFO, "Clicked on the Departments button in the main hamburger menu.");

        departmentsPage.clickOnNewDepartmentButton();
        logger.log(Level.INFO, "Clicked on the New Department button.");

        departmentsPage.enterTitle("Test Department");
        logger.log(Level.INFO, "Entered title for the new department.");

        departmentsPage.clickOnSubmitButton();
        logger.log(Level.INFO, "Clicked on the Submit button.");
    }

}