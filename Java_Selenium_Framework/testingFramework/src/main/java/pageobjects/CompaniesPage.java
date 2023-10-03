package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.MyDriver;
import utils.PageActions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class CompaniesPage extends BasePage {

    private MyDriver myDriver;
    @FindBy(xpath = "//*[@id='minimizeSidebar']")
    private WebElement mainHamburgerMenu;
    @FindBy(xpath = "//a[@id='menu-companies']")
    private WebElement companiesButton;
    @FindBy(xpath = "//button[@id='new-company']")
    private WebElement newCompanyButton;
    @FindBy(xpath = "//*[@placeholder='Title']")
    private WebElement titleButton;
    @FindBy(xpath = "//*[@id='search-company-submit']")
    private WebElement filterButton;
    @FindBy(xpath = "//div[@class='col-sm-4' and text()='Company name:']/following-sibling::div[@class='col-sm-8']/p[@class='text-left']")
    private WebElement companyNameInTable;
    @FindBy(xpath = "//div[@class='col-sm-4' and text()='Tickets prefix:']/following-sibling::div[@class='col-sm-8']/p[@class='text-left']")
    private WebElement companyPrefixInTable;
    @FindBy(xpath = "//div[@class='col-sm-4' and text()='EDRPOU:']/following-sibling::div[@class='col-sm-8']/p[@class='text-left']")
    private WebElement edrpouInTable;
    @FindBy(xpath = "//div[@class='col-sm-4' and text()='Plan:']/following-sibling::div[@class='col-sm-8']/p[@class='text-left']")
    private WebElement planInTable;
    @FindBy(xpath = "//div[@class='col-sm-4' and text()='Phone:']/following-sibling::div[@class='col-sm-8']/p[@class='text-left']")
    private WebElement phoneInTable;
    @FindBy(xpath = "//div[@class='col-sm-4' and text()='Skype:']/following-sibling::div[@class='col-sm-8']/p[@class='text-left']")
    private WebElement skypeInTable;
    @FindBy(xpath = "//div[@class='col-sm-4' and text()='Website:']/following-sibling::div[@class='col-sm-8']/p[@class='text-left']/a[@class='default-router-link']")
    private WebElement websiteInTable;
    @FindBy(xpath = "//div[@class='col-sm-4' and text()='Email:']/following-sibling::div[@class='col-sm-8']/p[@class='text-left']")
    private WebElement emailInTable;
    @FindBy(xpath = "//div[@class='col-sm-4' and text()='Country:']/following-sibling::div[@class='col-sm-8']/p[@class='text-left']")
    private WebElement countryInTable;
    @FindBy(xpath = "//div[@class='col-sm-4' and text()='City:']/following-sibling::div[@class='col-sm-8']/p[@class='text-left']")
    private WebElement cityInTable;
    @FindBy(xpath = "//div[@class='col-sm-4' and text()='Street:']/following-sibling::div[@class='col-sm-8']/p[@class='text-left']")
    private WebElement streetInTable;
    @FindBy(xpath = "//div[@class='col-sm-4' and text()='Building:']/following-sibling::div[@class='col-sm-8']/p[@class='text-left']")
    private WebElement buildingInTable;
    @FindBy(xpath = "//div[@class='col-sm-4' and text()='Zipcode:']/following-sibling::div[@class='col-sm-4']/p[@class='text-left']")
    private WebElement zipcodeInTable;
    @FindBy(xpath = "//div[@class='col-sm-4' and text()='Room Number:']/following-sibling::div[@class='col-sm-8']/p[@class='text-left']")
    private WebElement roomNumberInTable;
    @FindBy(xpath = "//div[@class='col-sm-4' and text()='Creation Timestamp:']/following-sibling::div[@class='col-sm-8']/p[@class='text-left']")
    private WebElement creationTimestampInTable;
    @FindBy(xpath = "//div[@class='col-sm-4' and text()='Update Timestamp:']/following-sibling::div[@class='col-sm-8']/p[@class='text-left']")
    private WebElement updateTimestampInTable;


    public CompaniesPage(MyDriver myDriver) {
        super(myDriver);
        this.myDriver = myDriver;
        PageFactory.initElements(myDriver.getDriver(), this);
    }

    public void clickOnMainHamburgerMenu() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='description']")));
        wait.until(ExpectedConditions.elementToBeClickable(mainHamburgerMenu)).click();
    }

    public void clickOnCompaniesButton() {
        wait.until(ExpectedConditions.elementToBeClickable(companiesButton)).click();
    }

    public void clickOnNewCompanyButton() {
        PageActions.clickNewButtonBasedOnPage(myDriver);
    }

    public void openCreateCompanyPage() {
        clickOnMainHamburgerMenu();
        clickOnCompaniesButton();
        clickOnNewCompanyButton();
    }

    public void searchCompanyTitleInputField(String companyName) {
        wait.until(ExpectedConditions.visibilityOf(titleButton)).sendKeys(companyName);
    }

    public void clickOnFilterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(filterButton)).click();
    }

    public void clickOnCompanyTitle(String companyName) {
        String xpath = "//a[normalize-space()='" + companyName + "']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
    }

    public String getCompanyNameInTable() {
        return wait.until(ExpectedConditions.visibilityOf(companyNameInTable)).getText();
    }

    public String getCompanyPrefixInTable() {
        return wait.until(ExpectedConditions.visibilityOf(companyPrefixInTable)).getText();
    }

    public String getEdrpouInTable() {
        return wait.until(ExpectedConditions.visibilityOf(edrpouInTable)).getText();
    }

    public String getPlanInTable() {
        return wait.until(ExpectedConditions.visibilityOf(planInTable)).getText();
    }

    public String getPhoneInTable() {
        return wait.until(ExpectedConditions.visibilityOf(phoneInTable)).getText();
    }

    public String getSkypeInTable() {
        return wait.until(ExpectedConditions.visibilityOf(skypeInTable)).getText();
    }

    public String getWebsiteInTable() {
        return wait.until(ExpectedConditions.visibilityOf(websiteInTable)).getText();
    }

    public String getEmailInTable() {
        return wait.until(ExpectedConditions.visibilityOf(emailInTable)).getText();
    }

    public String getCountryInTable() {
        return wait.until(ExpectedConditions.visibilityOf(countryInTable)).getText();
    }

    public String getCityInTable() {
        return wait.until(ExpectedConditions.visibilityOf(cityInTable)).getText();
    }

    public String getStreetInTable() {
        return wait.until(ExpectedConditions.visibilityOf(streetInTable)).getText();
    }

    public String getBuildingInTable() {
        return wait.until(ExpectedConditions.visibilityOf(buildingInTable)).getText();
    }

    public String getZipcodeInTable() {
        return wait.until(ExpectedConditions.visibilityOf(zipcodeInTable)).getText();
    }

    public String getRoomNumberInTable() {
        return wait.until(ExpectedConditions.visibilityOf(roomNumberInTable)).getText();
    }

    public String getCreationTimestampInTable() {
        return wait.until(ExpectedConditions.visibilityOf(creationTimestampInTable)).getText();
    }

    public String getUpdateTimestampInTable() {
        return wait.until(ExpectedConditions.visibilityOf(updateTimestampInTable)).getText();
    }

    public Map<String, String> createCompanyTableData() {
        Map<String, String> companyData = new HashMap<>();
        companyData.put("name", getCompanyNameInTable());
        companyData.put("ticket_prefix", getCompanyPrefixInTable());
        companyData.put("edrpou", getEdrpouInTable());
        companyData.put("plan", getPlanInTable());
        companyData.put("phone", getPhoneInTable());
        companyData.put("skype", getSkypeInTable());
        companyData.put("website", getWebsiteInTable());
        companyData.put("email", getEmailInTable());
        companyData.put("country", getCountryInTable());
        companyData.put("city", getCityInTable());
        companyData.put("street", getStreetInTable());
        companyData.put("building", getBuildingInTable());
        companyData.put("zipcode", getZipcodeInTable());
        companyData.put("room_number", getRoomNumberInTable());
        companyData.put("creation_timestamp", formatTimestamp(getCreationTimestampInTable()));
        companyData.put("update_timestamp", formatTimestamp(getUpdateTimestampInTable()));

        return companyData;
    }

    private String formatTimestamp(String timestamp) {
        LocalDateTime dateTime = LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public boolean compareMaps(Map<String, Object> map1, Map<String, Object> map2) {
        if (map1 == null && map2 == null) {
            return true;
        }

        if (map1 == null || map2 == null) {
            return false;
        }

        if (map1.size() != map2.size()) {
            return false;
        }

        for (Map.Entry<String, Object> entry : map1.entrySet()) {
            String key = entry.getKey();
            Object value1 = entry.getValue();
            Object value2 = map2.get(key);

            if (value1 == null && value2 == null) {
                continue;
            }

            if (value1 == null || value2 == null) {
                return false;
            }

            if (!value1.equals(value2)) {
                return false;
            }
        }

        return true;
    }


}
