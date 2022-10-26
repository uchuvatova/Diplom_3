package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EnterPage {

    private WebDriver driver;
    public final String URL = "https://stellarburgers.nomoreparties.site/login";
    private By emailField = By.xpath("//fieldset[1]/div/div");
    private By inputEmailField = By.xpath("//input[@name='name']");
    private By passwordField = By.xpath("//fieldset[2]/div/div");
    private By inputPasswordField = By.xpath("//input[@name='Пароль']");
    private By enterButton = By.xpath("//button[text() = 'Войти']");




    public EnterPage(WebDriver driver) {
        this.driver = driver;
    }
    public void waitForLoadEnterPage() {
        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Auth_login__3hAey']")));
    }
    public String getUrl() {
        return URL;
    }
    public void clickEmailField() {
        driver.findElement(emailField).click();
    }
    public void clickPasswordField() {
        driver.findElement(passwordField).click();
    }

    public void setEmail(String Email) {
        driver.findElement(inputEmailField).sendKeys(Email);
    }
    public void setPassword(String Password) {
        driver.findElement(inputPasswordField).sendKeys(Password);
    }
    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }

}
