package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {
    private WebDriver driver;
    public final String URL = "https://stellarburgers.nomoreparties.site/account/profile";
    public final By exitButton = By.xpath("//button[text()='Выход']");
    public final By linkConstructor = By.xpath(".//p[text()='Конструктор']");
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }
    public void waitForLoadProfilePage() {
        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Account_account__vgk_w']")));
    }
    public String getUrl() {
        return URL;
    }
    public void clickExitButton() {
        driver.findElement(exitButton).click();
    }
    public void clickConstructor() {
        driver.findElement(linkConstructor).click();
    }
}
