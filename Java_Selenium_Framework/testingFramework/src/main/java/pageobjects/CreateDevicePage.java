package pageobjects;

import models.Device;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utils.MyDriver;

public class CreateDevicePage extends BasePage{
    @FindBy(xpath = "//input[@id='name']")
    public WebElement titleInputField;
    @FindBy(xpath = "//input[@id='ip']")
    public WebElement ipInputField;
    @FindBy(xpath = "//input[@id='port']")
    public WebElement portInputField;
    @FindBy(xpath = "//input[@id='password']")
    public WebElement passwordInputField;
    @FindBy(xpath = "//select[@id='device-form-contact-select']")
    public WebElement contactSelectList;
    @FindBy(xpath = "//button[@id='device-form-submit-btn']")
    public WebElement submitButton;


    public CreateDevicePage(MyDriver myDriver) {
        super(myDriver);
        PageFactory.initElements(driver, this);
    }

    public void enterTitle(String title) {
        wait.until(ExpectedConditions.visibilityOf(titleInputField)).sendKeys(title);
    }

    public void enterIp(String ip) {
        wait.until(ExpectedConditions.visibilityOf(ipInputField)).sendKeys(ip);
    }

    public void enterPort(String port) {
        wait.until(ExpectedConditions.visibilityOf(portInputField)).sendKeys(port);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInputField)).sendKeys(password);
    }


    public CreateDevicePage selectContactByVisibleText(String contact) {
        Select contactSelect = new Select(wait.until(ExpectedConditions.visibilityOf(contactSelectList)));
        contactSelect.selectByVisibleText(contact);
        return this;
    }
    public void clickSubmitButton() {
        wait.until(ExpectedConditions.visibilityOf(submitButton)).click();
    }
    public void createDevice(Device device) {
        enterTitle(device.getTitle());
        enterIp(device.getIp());
        enterPort(device.getPort());
        enterPassword(device.getPassword());
        selectContactByVisibleText(device.getContact());
        clickSubmitButton();
    }
}
