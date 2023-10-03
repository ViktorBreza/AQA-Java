package strategies;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TicketsNewButtonClickStrategy implements NewButtonClickStrategy {
    @Override
    public void newButtonClick(WebDriver driver) {
        driver.findElement(By.id("create-new-ticket")).click();
    }

}