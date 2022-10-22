package PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageBurger {
    private WebDriver driver;
    public final String url = "https://stellarburgers.nomoreparties.site";

    // Кнопка "Войти в аккаунт"
    private By enterButton = By.xpath("//button[text()='Войти в аккаунт']");
    // Ссылка "Личный кабинет"
    private By accLink = By.xpath("//p[text()='Личный Кабинет']");

    // Кнопка "Оформить заказ"
    private By orderButton = By.xpath("//button[text()='Oформить заказ']");
    // Ссылка "Булки"
    private By bunLink = By.xpath("//span[text()='Булки']");
    private By sauceLink = By.xpath("//span[text()='Соусы']");
    private By fillLink = By.xpath("//span[text()='Начинки']");

    public final By header = By.xpath("//h1");
    public final By linkConstructor = By.xpath(".//p[text()='Конструктор']");
    public final By activeLink = By.xpath(".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span");

    //конструктор класса
    public HomePageBurger(WebDriver driver) {
        this.driver = driver;
    }

    // метод ожидания загрузки главной страницы
    public void waitForLoadHomePage() {
        new WebDriverWait(driver, 6)
                .until(ExpectedConditions.visibilityOfElementLocated(header));
    }
    public String getUrl() {
        return url;
    }
    public String getTextActiveLink() {
        return driver.findElement(activeLink).getText();    }
    public void clickEnterButton () {
        driver.findElement(enterButton).click();
    }
    public void clickProfileButton () {
        driver.findElement(accLink).click();
    }
    public void clickBunLink () {
        driver.findElement(bunLink).click();
    }
    public void clickSauceLink () {
        driver.findElement(sauceLink).click();
    }
    public void clickFillLink () {
        driver.findElement(fillLink).click();
    }
    public void clickConstructor() {
        driver.findElement(linkConstructor).click();
    }

}