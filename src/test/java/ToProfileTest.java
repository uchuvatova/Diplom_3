import pageobject.*;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ToProfileTest extends Setup {
    public static String Email= (RandomStringUtils.randomAlphanumeric(10) + "@yandex.ru");

    @Before
    public void begin(){
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
    @DisplayName("Переход в личный кабинет")
    public void ToProfilePageTest() {
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
        assertEquals(profilePage.getUrl(), driver.getCurrentUrl());
    }
    @After
    public void teardown() {
        driver.quit();
    }
}
