package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.MyDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketsPage extends BasePage {

    @FindBy(id = "create-new-ticket")
    private WebElement newTicketButton;
    @FindBy(xpath = "(//a[@id='ticket-edit-btn'])[1]")
    private WebElement editTicketButton;

    public TicketsPage(MyDriver myDriver) {
        super(myDriver);
        PageFactory.initElements(driver, this);
    }

    public List<String> getAllIdNames() {
        List<WebElement> idElements = driver.findElements(By.xpath("//td[@width='80px']"));
        return getWebElementTexts(idElements);
    }

    public List<String> getAllTitleNames() {
        List<WebElement> titleElements = driver.findElements(By.xpath("//*[@class='ticket_title']/a"));
        return getWebElementTexts(titleElements);
    }

    public List<String> getAllAssigneeNames() {
        List<WebElement> assigneeElements = driver.findElements(By.xpath("//*[@class='ticket_assignee']//tr"));
        return getWebElementTexts(assigneeElements);
    }

    public List<String> getAllStageNames() {
        List<WebElement> stageElements = driver.findElements(By.xpath("//table[@class='ticket-table-ex ticket-table table table-striped table-responsive']//td[@width='130px']//span[not(contains(text(), 'Продажи'))]"));
        return getWebElementTexts(stageElements);
    }

    public String getFirstIdName() {
        WebElement firstIdElement = driver.findElement(By.xpath("//td[@width='80px'][1]"));
        return firstIdElement.getText().trim();
    }

    public String getFirstTitleName() {
        WebElement firstTitleElement = driver.findElement(By.xpath("//*[@class='ticket_title']/a[1]"));
        return firstTitleElement.getText().trim();
    }

    public String getFirstCompanyName() {
        WebElement firstAssigneeElement = driver.findElement(By.xpath("//td[@class='ticket_company']/span"));
        return firstAssigneeElement.getText().trim();
    }

    public String getFirstStageName() {
        WebElement firstStageElement = driver.findElement(By.xpath("//table[@class='ticket-table-ex ticket-table table table-striped table-responsive']//td[@width='130px']//span[not(contains(text(), 'Продажи'))][1]"));
        return firstStageElement.getText().trim();
    }

    public String getFirstCategoryName() {
        By firstCategoryElementLocator = By.xpath("//td[@width='130px']/span[contains(@class, 'break-word') and contains(@class, 'fixed-span') and contains(@class, 'label') and contains(@class, 'label-info')]");
        wait.until(ExpectedConditions.elementToBeClickable(firstCategoryElementLocator));
        WebElement firstCategoryElement = driver.findElement(firstCategoryElementLocator);
        return firstCategoryElement.getText().trim();
    }


    public String getFirstTicketType() {
        WebElement firstTicketTypeElement = driver.findElement(By.xpath("//a[@class='ticket-title-id locked-link' and span[@_ngcontent-c8='']]//span[1]"));
        return firstTicketTypeElement.getText().trim();
    }

    public String getFirstInnerTicketTitle() {
        WebElement firstInnerTicketTitleElement = driver.findElement(By.xpath("//a[@class='ticket-title-id locked-link' and span[@_ngcontent-c8='']]//span[2]"));
        return firstInnerTicketTitleElement.getText().trim();
    }

    public TicketsPage clickOnNewTicketButton() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='description']")));
        wait.until(ExpectedConditions.elementToBeClickable(newTicketButton)).click();
        return this;
    }

    public void clickOnEditButton() {
        wait.until(ExpectedConditions.elementToBeClickable(editTicketButton)).click();
    }

    public TicketsPage searchTicketByTitle(String ticketTitle) {
        WebElement searchInput = driver.findElement(By.xpath("//*[@id='search-bar']"));
        searchInput.clear();
        searchInput.sendKeys(ticketTitle);
        return this;
    }

    private List<String> getWebElementTexts(List<WebElement> elements) {
        List<String> texts = new ArrayList<>();
        for (WebElement element : elements) {
            texts.add(element.getText());
        }
        return texts;
    }

    public Map<String, String> getFirstTicketData() {
        Map<String, String> firstTicketData = new HashMap<>();

        String firstCategoryName = getFirstCategoryName();
        String firstTitleName = getFirstTitleName();
        String firstStageName = getFirstStageName();
        firstTicketData.put("Category", firstCategoryName);
        firstTicketData.put("Title", firstTitleName);
        firstTicketData.put("Stage", firstStageName);

        return firstTicketData;
    }

    public Map<String, String> getFirstInnerTicketData() {
        Map<String, String> firstInnerTicketData = new HashMap<>();
        String firstInnerTicketTitle = getFirstInnerTicketTitle();
        String firstCategoryName = getFirstCategoryName();
        String firstStageName = getFirstStageName();
        firstInnerTicketData.put("Title", firstInnerTicketTitle);
        firstInnerTicketData.put("Category", firstCategoryName);
        firstInnerTicketData.put("Stage", firstStageName);

        return firstInnerTicketData;
    }
}
