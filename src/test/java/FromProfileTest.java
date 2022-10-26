import PageObject.EnterPage;
import PageObject.HomePageBurger;
import PageObject.ProfilePage;
import PageObject.RegisterPage;
import config.Config;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FromProfileTest {
    private WebDriver driver;
    public static String Email= (RandomStringUtils.randomAlphanumeric(10) + "@yandex.ru");
    private final String browser;
    public FromProfileTest (String browser) {
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
        driver.get("https://stellarburgers.nomoreparties.site/register"); // перехожу на страницу регистрации
        driver.manage().window().maximize();
        RegisterPage registerPage = new RegisterPage(driver); // создаю объект класса страницы регистрации
        registerPage.waitForLoadRegisterPage(); // ожидаю загрузку страницы
        registerPage.setName("Ира");
        registerPage.setEmail(Email);
        registerPage.setPassword("qadz12");
        registerPage.clickRegisterButton();
        EnterPage enterPage = new EnterPage(driver);
        enterPage.waitForLoadEnterPage();

    }
    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    public void ProfileToConstrTest() {
        driver.get("https://stellarburgers.nomoreparties.site/"); // перехожу на страницу тестового приложения
        driver.manage().window().maximize();
        HomePageBurger objHomePage = new HomePageBurger(driver); // создаю объект класса главной страницы приложения
        objHomePage.waitForLoadHomePage(); // загрузка главной страницы
        objHomePage.clickProfileButton();
        EnterPage enterPage = new EnterPage(driver);
        enterPage.waitForLoadEnterPage();
        enterPage.setEmail(Email);
        enterPage.setPassword("qadz12");
        enterPage.clickEnterButton();
        HomePageBurger loginHomePage = new HomePageBurger(driver);
        loginHomePage.waitForLoadHomePage();
        loginHomePage.clickProfileButton();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForLoadProfilePage();
        profilePage.clickConstructor();
        loginHomePage.waitForLoadHomePage();
        String text = driver.findElement(loginHomePage.createBurgerSection).getText();
       Assert.assertTrue(text.contains("Соберите бургер"));
    }
    @Test
    @DisplayName("Выход из аккаунта")
    public void ExitTest() {
        driver.get("https://stellarburgers.nomoreparties.site/"); // перехожу на страницу тестового приложения
        driver.manage().window().maximize();
        HomePageBurger objHomePage = new HomePageBurger(driver); // создаю объект класса главной страницы приложения
        objHomePage.waitForLoadHomePage(); // загрузка главной страницы
        objHomePage.clickProfileButton();
        EnterPage enterPage = new EnterPage(driver);
        enterPage.waitForLoadEnterPage();
        enterPage.setEmail(Email);
        enterPage.setPassword("qadz12");
        enterPage.clickEnterButton();
        HomePageBurger loginHomePage = new HomePageBurger(driver);
        loginHomePage.waitForLoadHomePage();
        loginHomePage.clickProfileButton();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForLoadProfilePage();
        profilePage.clickExitButton();
        enterPage.waitForLoadEnterPage();
        String text = driver.findElement(enterPage.enterHeader).getText();
        Assert.assertTrue(text.contains("Вход"));
        assertEquals(enterPage.getUrl(), driver.getCurrentUrl());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
