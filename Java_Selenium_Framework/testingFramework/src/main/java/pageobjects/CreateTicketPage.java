package pageobjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utils.MyDriver;

public class CreateTicketPage extends BasePage {

    @FindBy(xpath = "//*[@id='new-inner-ticket']")
    private WebElement newInnerTicketButton;
    @FindBy(xpath = "//input[@id='title']")
    private WebElement titleInputField;
    @FindBy(xpath = "//select[@id='contactId']")
    private WebElement contactSelectList;
    @FindBy(xpath = "//select[@id='priority']")
    private WebElement prioritySelectDropdownList;
    @FindBy(xpath = "//button[@id='submit-btn']")
    private WebElement submitButton;
    @FindBy(id = "stage-closed")
    private WebElement closedButton;
    @FindBy(xpath = "//a[@href='#/tickets']")
    private WebElement allTicketsLink;
    @FindBy(xpath = "//*[@id='cancel-btn']")
    private WebElement cancelButton;

    public CreateTicketPage(MyDriver myDriver) {
        super(myDriver);
        PageFactory.initElements(driver, this);
    }

    public CreateTicketPage clickOnNewInnerTicketButton() {
        wait.until(ExpectedConditions.elementToBeClickable(newInnerTicketButton)).click();
        return this;
    }

    public CreateTicketPage enterTitle(String title) {
        wait.until(ExpectedConditions.visibilityOf(titleInputField)).sendKeys(title);
        return this;
    }

    public CreateTicketPage selectContactByVisibleText(String contactText) {
        Select contactSelect = new Select(wait.until(ExpectedConditions.visibilityOf(contactSelectList)));
        contactSelect.selectByVisibleText(contactText);
        return this;
    }

    public CreateTicketPage clickOnSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        return this;
    }

    public CreateTicketPage clickOnCancelButton() {
        try {
            // Натискаємо кнопку "Cancel"
            wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();
            // Перехоплюємо виняток, якщо алерт виникає після натискання кнопки "Cancel"
            try {
                // Очікуємо на алерт
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                // Обробляємо алерт, якщо він присутній
                alert.accept();
            } catch (NoAlertPresentException ignored) {
                // Алерт не з'явився, можемо ігнорувати
            }
        } catch (WebDriverException e) {
            // Якщо виникає проблема з натисканням кнопки "Cancel", ігноруємо
        }
        return this;
    }
}
