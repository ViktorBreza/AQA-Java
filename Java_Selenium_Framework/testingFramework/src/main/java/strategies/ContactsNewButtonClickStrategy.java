package strategies;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactsNewButtonClickStrategy implements NewButtonClickStrategy {
    @Override
    public void newButtonClick(WebDriver driver) {
        driver.findElement(By.xpath("//a[@id='new-contact']")).click();
    }

}
