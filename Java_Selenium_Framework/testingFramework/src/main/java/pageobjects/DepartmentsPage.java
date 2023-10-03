package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.MyDriver;

public class DepartmentsPage extends BasePage {

    @FindBy(xpath = "//*[@id='minimizeSidebar']")
    public WebElement mainHamburgerMenu;

    @FindBy(xpath = "//a[@id='menu-departments']")
    WebElement departmentsButton;

    @FindBy(xpath = "//*[@id='new-department']")
    WebElement newDepartmentButton;

    @FindBy(xpath = "//input[@id='name']")
    public WebElement titleInputField;

    @FindBy(xpath = "//button[@id='department-form-submit']")
    public WebElement submitButton;

    @FindBy(xpath = "//*[@id='department-search-filter']")
    WebElement filter;

    @FindBy(xpath = "//*[@id='department-search-title']")
    WebElement searchTitle;

    public DepartmentsPage(MyDriver myDriver) {
        super (myDriver);
        PageFactory.initElements(driver, this);
    }

    public void clickOnMainHamburgerMenu() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='description']")));
        wait.until(ExpectedConditions.elementToBeClickable(mainHamburgerMenu)).click();
    }

    public void clickOnDepartmentsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(departmentsButton)).click();
    }

    public void clickOnNewDepartmentButton() {
        wait.until(ExpectedConditions.elementToBeClickable(newDepartmentButton)).click();
    }

    public void enterTitle(String title) {
        wait.until(ExpectedConditions.visibilityOf(titleInputField)).sendKeys(title);
    }

    public void clickOnSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

}
