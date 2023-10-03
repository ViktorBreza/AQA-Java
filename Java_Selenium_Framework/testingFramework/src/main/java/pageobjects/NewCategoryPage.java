package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.MyDriver;

public class NewCategoryPage extends BasePage {

    @FindBy(xpath = "//input[@id='name']")
    WebElement categoryNameInputField;
    @FindBy(xpath = "//button[@id='category-form-submit']")
    public WebElement submitButton;
    @FindBy(xpath = "//button[@id='stage-details-back-to-list']")
    public WebElement backButton;


    public NewCategoryPage(MyDriver myDriver) {
        super(myDriver);
        PageFactory.initElements(driver, this);
    }

    public void enterCategoryName(String categoryName) {
        wait.until(ExpectedConditions.visibilityOf(categoryNameInputField)).sendKeys(categoryName);
    }

    public void clickOnSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }


    public void clickOnBackButton(){
        wait.until(ExpectedConditions.elementToBeClickable(backButton)).click();
    }


}
