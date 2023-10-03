package helpfiles;

import database.DataBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.BasePage;
import pageobjects.CategoriesPage;
import pageobjects.NewCategoryPage;
import utils.MyDriver;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class CategoryHelper extends BasePage {
    private CategoriesPage categoriesPage;
    private NewCategoryPage newCategoryPage;

    public CategoryHelper(MyDriver myDriver) {
        super(myDriver);
        PageFactory.initElements(driver, this);
        categoriesPage = new CategoriesPage(myDriver);
        newCategoryPage = new NewCategoryPage(myDriver);
    }

    private boolean isLetterOrDigit(char ch) {
        return Character.isLetter(ch) || Character.isDigit(ch);
    }

    public String generateRandomCategoryName() {
        Random random = new Random();
        int length = random.nextInt(6) + 3; // Generate a random length between 3 and 8

        StringBuilder categoryNameBuilder = new StringBuilder();
        while (categoryNameBuilder.length() < length) {
            char randomChar = (char) (random.nextInt('z' - '0' + 1) + '0');
            if (isLetterOrDigit(randomChar)) {
                categoryNameBuilder.append(randomChar);
            }
        }

        return categoryNameBuilder.toString();
    }

    public void createNewCategory(String categoryName) {
        categoriesPage.openNewCategoryPage();
        newCategoryPage.enterCategoryName(categoryName);
        newCategoryPage.clickOnSubmitButton();
        newCategoryPage.clickOnBackButton();
    }

    public boolean isCategoryDisplayedOnPage(String categoryName) {
        String categoryXPath = String.format("//a[contains(text(), '%s')]", categoryName);
        WebElement categoryElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(categoryXPath)));
        return categoryElement.isDisplayed();
    }

    public boolean isCategoryPresentInDB(String categoryName) {
        DataBase db = DataBase.getInstance();
        db.connect();

        String query = "SELECT * FROM category WHERE name = '" + categoryName + "'";
        List<Map<String, Object>> result = db.executeQueryWithResultList(query);

        return result.size() > 0;
    }
}
