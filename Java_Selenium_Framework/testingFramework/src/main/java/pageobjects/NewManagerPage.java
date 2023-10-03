package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utils.MyDriver;

import java.util.Map;

public class NewManagerPage extends BasePage {
    @FindBy(xpath = "//input[@id='firstName']")
    private WebElement firstNameInputField;
    @FindBy(xpath = "//input[@id='lastName']")
    private WebElement lastNameInputField;
    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailInputField;
    @FindBy(xpath = "//input[@id='login']")
    private WebElement loginInputField;
    @FindBy(xpath = "//select[@id='manager-form-department-select']")
    private WebElement departmentSelectList;
    @FindBy(xpath = "//input[@id='phone']")
    private WebElement phoneInputField;
    @FindBy(xpath = "//input[@id='skype']")
    private WebElement skypeInputField;
    @FindBy(xpath = "//button[@id='manager-form-submit']")
    private WebElement submitButton;

    public NewManagerPage(MyDriver myDriver) {
        super(myDriver);
        PageFactory.initElements(driver, this);
    }

    public void verifyPageTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        if (!actualTitle.equals(expectedTitle)) {
            throw new AssertionError("Expected title: " + expectedTitle + ", but found: " + actualTitle);
        }
    }

    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameInputField)).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(lastNameInputField)).sendKeys(lastName);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInputField)).sendKeys(email);
    }

    public void enterLogin(String login) {
        wait.until(ExpectedConditions.visibilityOf(loginInputField));
        loginInputField.clear();
        loginInputField.sendKeys(login);
    }


    public void selectDepartmentByVisibleText(String departmentText) {
        Select departmentSelect = new Select(wait.until(ExpectedConditions.visibilityOf(departmentSelectList)));
        departmentSelect.selectByVisibleText(departmentText);
    }

    public void enterPhone(String phone) {
        wait.until(ExpectedConditions.visibilityOf(phoneInputField)).sendKeys(phone);
    }

    public void enterSkype(String skype) {
        wait.until(ExpectedConditions.visibilityOf(skypeInputField)).sendKeys(skype);
    }

    public void clickOnSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    public void fillForm(Map<String, String> testData) {
        verifyPageTitle("Bigom");
        enterFirstName(testData.get("firstName"));
        enterLastName(testData.get("lastName"));
        enterEmail(testData.get("email"));
        enterLogin(testData.get("login"));
        selectDepartmentByVisibleText("Комната добра");
        enterPhone(testData.get("phoneNumber"));
        enterSkype(testData.get("skype"));
        clickOnSubmitButton();
    }

}
