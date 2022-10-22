package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage {
        private WebDriver driver;
        // Ссылка "Войти"
        private By enterLink = By.xpath("//a[text()='Войти']");
    private String url = "https://stellarburgers.nomoreparties.site/forgot-password";
        public ForgotPasswordPage (WebDriver driver) {
            this.driver = driver;
        }
        public void waitForLoadForgotPasswordPage() {
            new WebDriverWait(driver, 6)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.className("Auth_login__3hAey")));
        }
    public String getUrl() {
        return url;
    }
        public void clickEnterLink() {
            driver.findElement(enterLink).click();
        }
    }
