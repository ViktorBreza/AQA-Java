package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.MyDriver;

public class CreateCompanyPage extends BasePage {

    @FindBy(xpath = "//input[@id='name']")
    public WebElement companyNameInputField;

    @FindBy(xpath = "//button[@id='company-submit-btn']")
    public WebElement submitButton;


    public CreateCompanyPage(MyDriver myDriver) {
        super(myDriver);
        PageFactory.initElements(driver, this);
    }

    public void enterCompanyName(String companyName) {
        wait.until(ExpectedConditions.visibilityOf(companyNameInputField)).sendKeys(companyName);
    }
    public void clickOnSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }
}
