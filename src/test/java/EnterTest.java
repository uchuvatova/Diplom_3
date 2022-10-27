import pageobject.*;

import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;


public class EnterTest extends Setup {
    public static String Email= (RandomStringUtils.randomAlphanumeric(10) + "@yandex.ru");

    @Before
    public void begin() {
        driverSetUp();
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
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void EnterHomePageTest() {
        driver.get("https://stellarburgers.nomoreparties.site/"); // перехожу на страницу тестового приложения
        driver.manage().window().maximize();
        HomePageBurger objHomePage = new HomePageBurger(driver); // создаю объект класса главной страницы приложения
        objHomePage.waitForLoadHomePage(); // загрузка главной страницы
        objHomePage.clickEnterButton();
        EnterPage enterPage = new EnterPage(driver);
        enterPage.waitForLoadEnterPage();
        enterPage.setEmail(Email);
        enterPage.setPassword("qadz12");
        enterPage.clickEnterButton();
        HomePageBurger loginHomePage = new HomePageBurger(driver);
        loginHomePage.waitForLoadHomePage();
        String getTextButton = driver.findElement(loginHomePage.orderButton).getText();
        Assert.assertEquals("Оформить заказ", getTextButton);

    }
    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void EnterProfileTest() {
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
        String getTextButton = driver.findElement(loginHomePage.orderButton).getText();
        Assert.assertEquals("Оформить заказ", getTextButton);}

        @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void EnterFromRegisterPageTest() {
        driver.get("https://stellarburgers.nomoreparties.site/register"); // перехожу на страницу регистрации тестового приложения
        driver.manage().window().maximize();
        RegisterPage registerPage = new RegisterPage(driver); // создаю объект класса страницы регистрации
        registerPage.waitForLoadRegisterPage(); // загрузка страницы регистрации
        registerPage.clickEnterLink();
        EnterPage enterPage = new EnterPage(driver);
            enterPage.waitForLoadEnterPage();
            enterPage.clickEmailField();
            enterPage.setEmail(Email);
            enterPage.clickPasswordField();
            enterPage.setPassword("qadz12");
            enterPage.clickEnterButton();
            HomePageBurger loginHomePage = new HomePageBurger(driver);
            loginHomePage.waitForLoadHomePage();
            String getTextButton = driver.findElement(loginHomePage.orderButton).getText();
            Assert.assertEquals("Оформить заказ", getTextButton);
        }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void EnterFromForgotPasswordPageTest() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password"); // перехожу на страницу регистрации тестового приложения
        driver.manage().window().maximize();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver); // создаю объект класса страницы восстановления пароля
        forgotPasswordPage.waitForLoadForgotPasswordPage(); // загрузка главной страницы
        forgotPasswordPage.clickEnterLink();
        EnterPage enterPage = new EnterPage(driver);
        enterPage.waitForLoadEnterPage();
        enterPage.clickEmailField();
        enterPage.setEmail(Email);
        enterPage.clickPasswordField();
        enterPage.setPassword("qadz12");
        enterPage.clickEnterButton();
        HomePageBurger loginHomePage = new HomePageBurger(driver);
        loginHomePage.waitForLoadHomePage();
        String getTextButton = driver.findElement(loginHomePage.orderButton).getText();
        Assert.assertEquals("Оформить заказ", getTextButton);
    }
    @After
    public void teardown() {
        // Закрыть браузер
        driver.quit();
    }

}
