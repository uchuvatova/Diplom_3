import pageobject.HomePageBurger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ConstructTest extends Setup {

    @Before
    public void begin() {
        driverSetUp();
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

