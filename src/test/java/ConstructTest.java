import PageObject.HomePageBurger;
import config.Config;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ConstructTest {
    private WebDriver driver;
    private final String browser;
    public ConstructTest(String browser) {
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
        driver.get("https://stellarburgers.nomoreparties.site"); // перехожу на страницу регистрации
        driver.manage().window().maximize(); //разворачиваю окно
    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    public void TransSauceTest() {
        HomePageBurger objHomePage = new HomePageBurger(driver); // создаю объект класса главной страницы приложения
        objHomePage.waitForLoadHomePage();
        objHomePage.clickSauceLink();
        WebElement element = driver.findElement(objHomePage.sauceHeader);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        assertEquals("Соусы", objHomePage.getTextActiveLink());
    }
    @Test
    @DisplayName("Переход к разделу Начинки")
    public void TransFillTest() {
        HomePageBurger objHomePage = new HomePageBurger(driver); // создаю объект класса главной страницы приложения
        objHomePage.waitForLoadHomePage();
        objHomePage.clickFillLink();
        WebElement element = driver.findElement(objHomePage.fillHeader);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        assertEquals("Начинки", objHomePage.getTextActiveLink());
    }
    @Test
    @DisplayName("Переход к разделу Булки")
    public void TransBunTest() {
        HomePageBurger objHomePage = new HomePageBurger(driver); // создаю объект класса главной страницы приложения
        objHomePage.waitForLoadHomePage();
        objHomePage.clickFillLink();
        objHomePage.clickBunLink();
        WebElement element = driver.findElement(objHomePage.bunHeader);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        assertEquals("Булки", objHomePage.getTextActiveLink());
    }

    @After
    public void teardown() {
        // Закрыть браузер
        driver.quit();
    }
}

