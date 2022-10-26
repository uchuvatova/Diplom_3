package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {
    private WebDriver driver;
    public final String URL = "https://stellarburgers.nomoreparties.site/register";

    // локатор заголовка страницы "Регистрация"
    public final By header = By.xpath("//h2[text()='Регистрация']");
    // локатор поля ввода "Имя"
    public final By name = By.xpath("//fieldset[1]//input[@name='name']");
    // локатор поля ввода "Email"
    public final By email = By.xpath("//fieldset[2]//input[@name='name']");
    // локатор поля ввода "Пароль"
    public final By password = By.xpath("//input[@name='Пароль']");
    // кнопка "Зарегистрироваться"
    public final By buttonReg = By.xpath("//button[text()='Зарегистрироваться']");
    // сообщение об ошибке пароля
    public final By errorPassword = By.xpath("//fieldset[3]/div/p");
    // ссылка "Войти" внизу страницы
    public final By linkEnter = By.xpath("//a[text()='Войти']");
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadRegisterPage() {
        new WebDriverWait(driver, 6)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Auth_login__3hAey")));
    }
    public void waitForRegButton() {
        new WebDriverWait(driver, 6)
                .until(ExpectedConditions.visibilityOfElementLocated(buttonReg));
    }
    public String getUrl() {
        return URL;
    }
    public void setName(String Name) {driver.findElement(name).sendKeys(Name);}
    public void setEmail(String Email) {
        driver.findElement(email).sendKeys(Email);
    }
    public void setPassword(String Password) {
        driver.findElement(password).sendKeys(Password);
    }
    public void clickRegisterButton() {
        driver.findElement(buttonReg).click();
    }
    public void clickEnterLink() {
        driver.findElement(linkEnter).click();
    }
    public void clickNameField() {
        driver.findElement(name).click();
    }

}
