import PageObject.EnterPage;
import PageObject.RegisterPage;
import config.Config;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.After;
import org.junit.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class RegisterTest {
        private WebDriver driver;
    private final String browser;
    public RegisterTest (String browser) {
        this.browser = browser;
    }
    @Parameterized.Parameters
    public static Object[][] getBrowser() {
        return new Object[][] {
                {"Google Chrome"},
                {"Yandex Browser"},
        };
    }

    @Before
    public void begin() {
        switch (browser) {
            case "Google Chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "Yandex Browser":
                WebDriverManager.chromedriver().driverVersion("104.0.5112.20").setup();
                driver = new ChromeDriver(new ChromeOptions().setBinary(new Config().getYandexBinaryPath()));
                break;
            default:
                System.out.println("В этом браузере не тестируется");
        }
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
        String text = driver.findElement(By.xpath("//fieldset[3]/div/p")).getText();
        Assert.assertEquals("Некорректный пароль", text);
    }

    @After
    public void teardown() {
        // Закрываю браузер
        driver.quit();
    }

    }
