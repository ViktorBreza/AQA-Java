package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.MyDriver;

import java.util.List;
import java.util.Map;

public class CategoriesPage extends BasePage {

    @FindBy(xpath = "//*[@id='minimizeSidebar']")
    private WebElement mainHamburgerMenu;
    @FindBy(xpath = "//a[@id='menu-categories']")
    private WebElement categoriesButton;
    @FindBy(xpath = "//*[@id='new-category-btn']")
    private WebElement newCategory;

    public CategoriesPage(MyDriver myDriver) {
        super(myDriver);
        PageFactory.initElements(driver, this);
    }

    public void clickOnMainHamburgerMenu() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='description']")));
        wait.until(ExpectedConditions.elementToBeClickable(mainHamburgerMenu)).click();
    }

    public void clickOnCategoriesButton() {
        wait.until(ExpectedConditions.elementToBeClickable(categoriesButton)).click();
    }

    public void clickOnNewCategoryButton() {
        wait.until(ExpectedConditions.elementToBeClickable(newCategory)).click();
    }



    public void openNewCategoryPage() {

        clickOnMainHamburgerMenu();
        clickOnCategoriesButton();
        clickOnNewCategoryButton();
    }


}
