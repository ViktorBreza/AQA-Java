package pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.MyDriver;

public class EditTicketPage extends BasePage {

    @FindBy(xpath = "//input[@id='title']")
    private WebElement editedTitleInputField;
    @FindBy(xpath = "//button[@id='submit-btn']")
    private WebElement submitButton;

    public EditTicketPage(MyDriver myDriver) {
        super(myDriver);
        PageFactory.initElements(driver, this);
    }

    public void enterEditedTicketTitle(String editedTicketTitle) throws InterruptedException {
        Thread.sleep(1000); // Wait for 1 second
        wait.until(ExpectedConditions.elementToBeClickable(editedTitleInputField)).clear();
        editedTitleInputField.sendKeys(editedTicketTitle);
        editedTitleInputField.sendKeys(Keys.RETURN);
    }

    public void clickOnEditedSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }
}
