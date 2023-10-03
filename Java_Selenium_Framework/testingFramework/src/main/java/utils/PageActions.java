package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import strategies.CompaniesNewButtonClickStrategy;
import strategies.ContactsNewButtonClickStrategy;
import strategies.NewButtonClickStrategy;
import strategies.TicketsNewButtonClickStrategy;;

public class PageActions {
    public static void clickNewButtonBasedOnPage(MyDriver myDriver) {
        String url = myDriver.getDriver().getCurrentUrl();
        NewButtonClickStrategy newButtonClickStrategy;

        if (url.contains("/tickets")) {
            newButtonClickStrategy = new TicketsNewButtonClickStrategy();
        } else if (url.contains("/contacts")) {
            newButtonClickStrategy = new ContactsNewButtonClickStrategy();
        } else if (url.contains("/companies")) {
            newButtonClickStrategy = new CompaniesNewButtonClickStrategy();
        } else {
            throw new IllegalStateException("Invalid page URL: " + url);
        }

        WebDriverWait wait = myDriver.getWait();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='description']")));
        WebDriver driver = myDriver.getDriver();
        newButtonClickStrategy.newButtonClick(driver);
    }
}
