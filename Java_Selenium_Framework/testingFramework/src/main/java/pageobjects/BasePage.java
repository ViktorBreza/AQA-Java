package pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.MyDriver;

public class BasePage {

        protected WebDriver driver;
        protected WebDriverWait wait;

        public BasePage(MyDriver myDriver) {
            this.driver = myDriver.getDriver();
            this.wait = myDriver.getWait();
        }

    public boolean customEquals(Object obj1, Object obj2) {
        return (obj1 == null && obj2 == null) || (obj1 != null && obj1.equals(obj2));
    }

}
