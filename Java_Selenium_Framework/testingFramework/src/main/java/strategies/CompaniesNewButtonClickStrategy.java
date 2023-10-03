package strategies;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CompaniesNewButtonClickStrategy implements NewButtonClickStrategy {
    @Override
    public void newButtonClick(WebDriver driver) {
        driver.findElement(By.xpath("//button[@id='new-company']")).click();
    }

}
