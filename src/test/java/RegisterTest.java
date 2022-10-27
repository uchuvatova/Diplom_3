import pageobject.EnterPage;
import pageobject.RegisterPage;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RegisterTest extends Setup {
    @Before
    public void begin() {
        driverSetUp();
        }
        @Test
        @DisplayName("Успешная регистрация")
        public void SuccessRegisterTest() throws InterruptedException {
            String Email = (RandomStringUtils.randomAlphanumeric(10) + "@yandex.ru");
            String CorrectPassword = (RandomStringUtils.randomNumeric(6));
            driver.get("https://stellarburgers.nomoreparties.site/register"); // перехожу на страницу регистрации
            driver.manage().window().maximize();
            RegisterPage registerPage = new RegisterPage(driver); // создаю объект класса страницы регистрации
            registerPage.waitForLoadRegisterPage(); // ожидаю загрузку страницы
            registerPage.setName("Ира");
            registerPage.setEmail(Email);
            registerPage.setPassword(CorrectPassword);
            registerPage.clickNameField();
            registerPage.clickRegisterButton();
            registerPage.clickRegisterButton();
            Thread.sleep(10000);
            EnterPage enterPage = new EnterPage(driver);
            enterPage.waitForLoadEnterPage();
            assertEquals(enterPage.getUrl(), driver.getCurrentUrl());
        }
    @Test
    @DisplayName("Pегистрация с некорректным паролем")
    public void IncorrectPasswordRegisterTest() {
        String Email = (RandomStringUtils.randomAlphanumeric(10) + "@yandex.ru");
        String NotCorrectPassword = (RandomStringUtils.randomNumeric(5));
        driver.get("https://stellarburgers.nomoreparties.site/register"); // перехожу на страницу регистрации
        driver.manage().window().maximize();
        RegisterPage registerPage = new RegisterPage(driver); // создаю объект класса страницы регистрации
        registerPage.waitForLoadRegisterPage(); // ожидаю загрузку страницы
        registerPage.setName("Ира");
        registerPage.setEmail(Email);
        registerPage.setPassword(NotCorrectPassword);
        registerPage.clickRegisterButton();
        String text = driver.findElement(registerPage.errorPassword).getText();
        Assert.assertEquals("Некорректный пароль", text);
    }

    @After
    public void teardown() {
        // Закрываю браузер
        driver.quit();
    }

    }
