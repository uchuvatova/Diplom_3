import org.openqa.selenium.WebDriver;
import pageobject.*;
import config.Config;

public class Setup {
    protected WebDriver driver;
    protected HomePageBurger objHomePage;

    public void driverSetUp() {
        driver = Config.getBrowser();
    }
    /*public void basicChromeSetup() {
        driverSetUp();
        //setUpObjects();
    }*/
}
