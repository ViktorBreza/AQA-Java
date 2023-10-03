package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.MyDriver;

public class CreateContactPage extends BasePage {

    @FindBy(xpath = "//*[@id='firstName']")
    public WebElement firstNameInputField;
    @FindBy(xpath = "//*[@id='lastName']")
    public WebElement lastNameInputField;
    @FindBy(xpath = "//*[@id='email']")
    public WebElement emailInputField;
    @FindBy(xpath = "//*[@id='login']")
    public WebElement loginInputField;
    @FindBy(xpath = "//*[@id='ticketPrefix']")
    public WebElement ticketPrefixInputField;
    @FindBy(xpath = "//*[@id='contact-form-submit']")
    public WebElement submitButton;
    @FindBy(xpath = "//div[contains(@class,'alert alert-danger')]/div[contains(text(),'Email must be valid')]")
    public WebElement invalidEmailError;
    @FindBy(xpath = "//div[contains(@class,'alert alert-danger')]/div[contains(text(),'Email is required')]")
    public WebElement emptyEmailError;
    @FindBy(xpath = "//div[contains(@class,'alert alert-danger')]/div[contains(text(),'Tickets prefix must be from 3 to 6 characters long')]")
    public WebElement invalidTicketPrefixError;
    @FindBy(xpath = "//div[contains(@class,'alert alert-danger')]/div[contains(text(),'Login is required')]")
    public WebElement emptyLoginError;
    @FindBy(xpath = "//div[contains(@class,'alert alert-danger')]/div[contains(text(),'Last name must be at least 2 characters long.')]")
    public WebElement invalidLastNameError;

    public CreateContactPage(MyDriver myDriver) {
        super(myDriver);
        PageFactory.initElements(driver, this);
    }

    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.elementToBeClickable(firstNameInputField)).clear();
        firstNameInputField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.elementToBeClickable(lastNameInputField)).sendKeys(lastName);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailInputField)).sendKeys(email);
    }

    public void enterLogin(String login) {
        wait.until(ExpectedConditions.elementToBeClickable(loginInputField));
        loginInputField.clear();
        loginInputField.sendKeys(login);
    }

    public void enterTicketPrefix(String ticketPrefix) {
        wait.until(ExpectedConditions.elementToBeClickable(ticketPrefixInputField));
        ticketPrefixInputField.clear();
        ticketPrefixInputField.sendKeys(ticketPrefix);
    }

    public void clickOnSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }
}
