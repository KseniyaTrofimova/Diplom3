package prakticum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RestorePasswordPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final By LOGIN_LINK = By.xpath("//a[text()='Войти']");

    public RestorePasswordPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    @Step("Переход на страницу входа")
    public LoginPage clickLoginLink() {
        waitForElementClickable(LOGIN_LINK).click();
        return new LoginPage(driver);
    }

    private WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}