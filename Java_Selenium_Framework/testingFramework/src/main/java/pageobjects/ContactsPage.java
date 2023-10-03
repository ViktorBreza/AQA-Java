package pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.MyDriver;

public class ContactsPage extends BasePage {

    @FindBy(xpath = "//*[@id=\"new-contact\"]")
    WebElement newContactButton;
    @FindBy(xpath = "//a[contains(@aria-label,'Next page')]")
    WebElement nextPage;
    @FindBy(xpath = "//*[@id='clear-search-contact']")
    WebElement clear;
    @FindBy(xpath = "//*[@id='minimizeSidebar']")
    WebElement mainHamburgerMenu;
    @FindBy(xpath = "//a[@id='menu-contacts']")
    WebElement contactsButton;
    @FindBy(xpath = "(//a[@id='edit-btn'])[1]")
    WebElement editContactButton;
    @FindBy(xpath = "(//*[@id='delete-btn']/i)[1]")
    WebElement deleteContactButton;

    public ContactsPage(MyDriver myDriver) {
        super(myDriver);
        PageFactory.initElements(driver, this);
    }

    public void clickOnMainHamburgerMenu() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='description']")));
        wait.until(ExpectedConditions.elementToBeClickable(mainHamburgerMenu)).click();
    }

    public void clickOnContactsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(contactsButton)).click();
    }

    public void clickOnNewContactButton() {
        wait.until(ExpectedConditions.elementToBeClickable(newContactButton)).click();
    }

    public void clickOnEditContactButton() {
        wait.until(ExpectedConditions.elementToBeClickable(editContactButton)).click();
    }

    public void openNewContactPage() {
        clickOnMainHamburgerMenu();
        clickOnContactsButton();
        clickOnNewContactButton();
    }

    public void clickOnDeleteContactButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(deleteContactButton)).click();
            try {
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                alert.accept();
            } catch (NoAlertPresentException e) {
                // Log the NoAlertPresentException
                System.err.println("NoAlertPresentException caught: " + e.getMessage());
            }
        } catch (WebDriverException e) {
            // Log the WebDriverException
            System.err.println("WebDriverException caught: " + e.getMessage());
        }
    }

}
