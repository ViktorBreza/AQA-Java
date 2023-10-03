package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.MyDriver;

public class DevicesPage extends BasePage{

    @FindBy(xpath = "//*[@id='minimizeSidebar']")
    public WebElement mainHamburgerMenu;
    @FindBy(xpath = "//a[@id='menu-device-list']")
    public WebElement devicesButton;
    @FindBy(xpath = "//span[normalize-space()='New Device']")
    public WebElement newDeviceButton;

    public DevicesPage(MyDriver myDriver) {
        super(myDriver);
        PageFactory.initElements(driver, this);
    }

    public void clickOnMainHamburgerMenu() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='description']")));
        wait.until(ExpectedConditions.elementToBeClickable(mainHamburgerMenu)).click();
    }
    public void clickOnDevicesButton() {
        wait.until(ExpectedConditions.elementToBeClickable(devicesButton)).click();
    }
    public void clickOnNewDeviceButton() {
        wait.until(ExpectedConditions.elementToBeClickable(newDeviceButton)).click();
    }

    public void openNewDevicePage() {
        clickOnMainHamburgerMenu();
        clickOnDevicesButton();
        clickOnNewDeviceButton();
    }

}
