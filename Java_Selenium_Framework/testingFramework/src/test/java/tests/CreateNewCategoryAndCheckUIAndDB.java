package tests;

import helpfiles.CategoryHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

public class CreateNewCategoryAndCheckUIAndDB extends BaseTest {
    private static CategoryHelper categoryHelper;
    private static String categoryName;
    private static final Logger logger = Logger.getLogger(CreateNewCategoryAndCheckUIAndDB.class.getName());

    @BeforeEach
    public void setUpHelper() {
        categoryHelper = new CategoryHelper(myDriver);
        categoryName = categoryHelper.generateRandomCategoryName();
        configureLogger(CreateNewCategoryAndCheckUIAndDB.class);
    }

    @Test
    public void shouldVerifyNewCategoryPresence() {
        logger.info("Generating a random category name...");
        categoryHelper.createNewCategory(categoryName);
        logger.info("New category created with name: " + categoryName);

        boolean isNewCategoryDisplayed = categoryHelper.isCategoryDisplayedOnPage(categoryName);
        boolean isCategoryPresentInDB = categoryHelper.isCategoryPresentInDB(categoryName);

        Assertions.assertTrue(isNewCategoryDisplayed, "Newly created category is not displayed on the page");
        Assertions.assertTrue(isCategoryPresentInDB, "Newly created category is not found in the database");

        logger.info("Verification completed successfully!");
    }
}
