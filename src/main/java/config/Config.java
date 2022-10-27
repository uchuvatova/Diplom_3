package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Config {
    //public String yandexBinaryPath = "C://Users/user/AppData/Local/Yandex/YandexBrowser/Application/browser.exe";
    //public String driverVersion = "104.0.5112.20";
    //public String getYandexBinaryPath() {        return yandexBinaryPath;    }

        public static WebDriver getBrowser() {
            String browserName;
            try {
                browserName = System.getProperty("browser");
            } catch (RuntimeException e) {
                e.printStackTrace();
                browserName = "chrome";
            }
            if (browserName == null) browserName = "chrome";
            String path = System.getProperty("user.dir");
            switch (browserName) {
                case "chrome": {
                    System.setProperty("webdriver.chrome.driver", path + "\\src\\test\\resources\\chromedriver.exe");
                    return new ChromeDriver();
                }
                case "yandex": {
                    System.setProperty("webdriver.chrome.driver", path + "\\src\\test\\resources\\yandexdriver.exe");
                    return new ChromeDriver();
                }
                default: {
                    System.setProperty("webdriver.chrome.driver", path + "\\src\\test\\resources\\chromedriver.exe");
                    throw new RuntimeException("В этом браузере не тестируем");
                }
            }
        }
    }
