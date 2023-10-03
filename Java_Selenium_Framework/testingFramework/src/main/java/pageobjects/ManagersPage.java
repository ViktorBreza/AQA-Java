package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.MyDriver;

import java.util.HashMap;
import java.util.Map;

public class ManagersPage extends BasePage {

    @FindBy(xpath = "//*[@id='minimizeSidebar']")
    private WebElement mainHamburgerMenu;
    @FindBy(xpath = "//a[@id='menu-managers']")
    private WebElement managersButton;
    @FindBy(xpath = "//button[@id='managers-new-manager']")
    private WebElement newManagerButton;
    @FindBy(xpath = "//input[@id='search-manager-firstname']")
    private WebElement searchManagerFirstNameInputField;
    @FindBy(xpath = "//input[@id='search-manager-lastname']")
    private WebElement searchManagerLastNameInputField;
    @FindBy(xpath = "//button[@id='search-manager-filter']")
    private WebElement searchManagerFilterButton;
    @FindBy(xpath = "//input[@id='search-manager-department']")
    private WebElement searchManagerDepartmentInputField;
    @FindBy(xpath = "//div[@class='col-sm-5' and text()='Phone:']/following-sibling::div[@class='col-sm-7']/p[@class='text-left']")
    private WebElement filteredManagerPhone;
    @FindBy(xpath = "//div[@class='col-sm-5' and text()='Skype:']/following-sibling::div[@class='col-sm-7']/p[@class='text-left']")
    private WebElement filteredManagerSkype;
    @FindBy(xpath = "//div[@class='col-sm-5' and text()='Login:']/following-sibling::div[@class='col-sm-7']/p[@class='text-left']")
    private WebElement filteredManagerLogin;
    @FindBy(xpath = "//div[@class='col-sm-5' and text()='Email:']/following-sibling::div[@class='col-sm-7']/p[@class='text-left']")
    private WebElement filteredManagerEmail;
    @FindBy(xpath = "//div[@class='col-sm-5' and text()='Name:']/following-sibling::div[@class='col-sm-7']/p[@class='text-left']")
    private WebElement filteredManagerFullName;
    @FindBy(xpath = "//td/a[contains(@href, '/manager/')]")
    private WebElement filteredManagerInTable;

    public ManagersPage(MyDriver myDriver) {
        super(myDriver);
        PageFactory.initElements(driver, this);
    }

    public void openNewManagerPage() {
        clickOnMainHamburgerMenu();
        clickOnManagersButton();
        clickOnNewManagerButton();
    }

    public void clickOnMainHamburgerMenu() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='description']")));
        wait.until(ExpectedConditions.elementToBeClickable(mainHamburgerMenu)).click();
    }

    public void clickOnManagersButton() {
        wait.until(ExpectedConditions.elementToBeClickable(managersButton)).click();
    }

    public void clickOnNewManagerButton() {
        wait.until(ExpectedConditions.elementToBeClickable(newManagerButton)).click();
    }

    public void searchManagerFirstNameInputField(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(searchManagerFirstNameInputField)).sendKeys(firstName);
    }

    public void searchManagerLastNameInputField(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(searchManagerLastNameInputField)).sendKeys(lastName);
    }

    public void searchManagerDepartmentInputField(String departmentText) {
        wait.until(ExpectedConditions.visibilityOf(searchManagerDepartmentInputField)).sendKeys(departmentText);
    }

    public void clickOnSearchManagerFilterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchManagerFilterButton)).click();
    }

    public void clickOnFilteredManagerInTable() {
        String firstName = searchManagerFirstNameInputField.getAttribute("value");
        String lastName = searchManagerLastNameInputField.getAttribute("value");
        if (firstName != null && lastName != null) {
            String fullName = firstName + " " + lastName;
            String xpath = "//a[normalize-space()='" + fullName + "']";
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
        } else {
            // Handle the case when firstName or lastName is null
            System.out.println("Error: firstName or lastName is null.");
        }
    }

    public String getFilteredManagerPhone() {
        return wait.until(ExpectedConditions.visibilityOf(filteredManagerPhone)).getText();
    }

    public String getFilteredManagerSkype() {
        return wait.until(ExpectedConditions.visibilityOf(filteredManagerSkype)).getText();
    }

    public String getFilteredManagerLogin() {
        return wait.until(ExpectedConditions.visibilityOf(filteredManagerLogin)).getText();
    }

    public String getFilteredManagerEmail() {
        String emailAndButtonText = wait.until(ExpectedConditions.visibilityOf(filteredManagerEmail)).getText();
        String emailText = emailAndButtonText.replace(" RESEND EMAIL FOR CONFIRMATION", "").trim();
        return emailText;
    }

    public String getFilteredManagerFullName() {
        String fullName = wait.until(ExpectedConditions.visibilityOf(filteredManagerFullName)).getText();
        return fullName;
    }

    public String getFirstName(String fullName) {
        return fullName.split(" ")[0]; // Returns the first element after splitting by space (first name)
    }

    public String getLastName(String fullName) {
        return fullName.split(" ")[1]; // Returns the second element after splitting by space (last name)
    }

    public Map<String, String> createManagerTableDataMap() {
        Map<String, String> managerData = new HashMap<>();
        String filteredManagerFullName = getFilteredManagerFullName();
        managerData.put("firstname", getFirstName(filteredManagerFullName));
        managerData.put("lastname", getLastName(filteredManagerFullName));
        managerData.put("email", getFilteredManagerEmail());
        managerData.put("login", getFilteredManagerLogin());
        managerData.put("phonenumber", getFilteredManagerPhone());
        managerData.put("skype", getFilteredManagerSkype());

        return managerData;
    }

}