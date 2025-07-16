package prakticum.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Browser {
    public static WebDriver getBrowser(String browserName) {
        String driverPath = "src/test/resources/";

        if (browserName.equalsIgnoreCase("yandex")) {
            System.setProperty("webdriver.chrome.driver", driverPath + "yandexdriver.exe");

            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Program Files (x86)\\Yandex\\YandexBrowser\\Application\\browser.exe");
            return new ChromeDriver(options);
        } else {
            System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
            return new ChromeDriver();
        }
    }
}