package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.MyDriver;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class DashboardPage extends BasePage {

    @FindBy(css = "//*[contains(text(), 'Reaction Deadline')]")
    private WebElement reactionDeadline;

    @FindBy(css = "#dashboard-done")
    private WebElement doneDeadline;

    @FindBy(css = "#minimizeSidebar")
    private WebElement minimizeSidebar;

    @FindBy(xpath = "//*[@id='menu-dashboard']")
    private WebElement dashboardSelector;

    @FindBy(xpath = "//button[@id='dashboard-done']")
    private WebElement doneDeadlineButton;

    @FindBy(xpath = "//div[@class='accordion-left-part'][normalize-space()='Deadline is over']")
    private WebElement deadlineIsOver;

    public DashboardPage(MyDriver myDriver) {
        super (myDriver);
        PageFactory.initElements(driver, this);
    }

    public List<String> getAllTitlesByCategory(String categoryName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(dashboardSelector)).click();

        Actions action = new Actions(driver);
        action.moveToElement(doneDeadlineButton).perform();
        wait.until(ExpectedConditions.elementToBeClickable(doneDeadlineButton)).click();

        wait.until(ExpectedConditions.elementToBeClickable(deadlineIsOver)).click();

        List<WebElement> rows = driver.findElements(By.xpath("//span[contains(text(), '" + categoryName + "')]//ancestor::tr//a[@id='ticket-block-title']"));

        return rows.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getAllIdsByPriorityP3() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(dashboardSelector)).click();

        Actions action = new Actions(driver);
        action.moveToElement(doneDeadlineButton).perform();
        wait.until(ExpectedConditions.elementToBeClickable(doneDeadlineButton)).click();

        wait.until(ExpectedConditions.elementToBeClickable(deadlineIsOver)).click();

        List<WebElement> rows = driver.findElements(By.xpath("//td[contains(text(), 'P3')]//ancestor::tr"));

        return rows.stream()
                .map(row -> row.findElements(By.tagName("td")).get(1).getText())
                .collect(Collectors.toList());
    }
}
