package prakticum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class RegistrationPage extends BasePage {
    private static final By NAME_FIELD = By.xpath("//input[@name='name']");
    private static final By EMAIL_FIELD = By.xpath("//label[contains(text(),'Email')]/following-sibling::input");
    private static final By PASSWORD_FIELD = By.cssSelector("input[type='password']");
    private static final By REGISTER_BUTTON = By.xpath("//button[text()='Зарегистрироваться']");
    private static final By PASSWORD_ERROR = By.xpath("//p[text()='Некорректный пароль']");
    private static final By LOGIN_LINK = By.xpath("//a[text()='Войти']");
    private static final By REGISTRATION_FORM = By.cssSelector(".auth_form");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageLoaded() {
        return waitForElementVisible(REGISTRATION_FORM).isDisplayed()
                && waitForElementVisible(REGISTER_BUTTON).isDisplayed();
    }

    @Step("Ввод имени: {name}")
    public RegistrationPage setName(String name) {
        WebElement field = waitForElementVisible(NAME_FIELD);
        field.clear();
        field.sendKeys(name);
        return this;
    }

    @Step("Ввод email: {email}")
    public RegistrationPage setEmail(String email) {
        WebElement field = waitForElementVisible(EMAIL_FIELD);
        field.clear();
        field.sendKeys(email);
        return this;
    }

    @Step("Ввод пароля")
    public RegistrationPage setPassword(String password) {
        waitForElementVisible(PASSWORD_FIELD).sendKeys(password);
        return this;
    }

    @Step("Нажатие кнопки 'Зарегистрироваться'")
    public LoginPage clickRegisterButton() {
        waitForElementClickable(REGISTER_BUTTON).click();
        waitForRegistrationComplete();
        return new LoginPage(driver);
    }

    @Step("Ожидание завершения регистрации")
    private void waitForRegistrationComplete() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.or(
                            ExpectedConditions.urlContains("/login"),
                            ExpectedConditions.visibilityOfElementLocated(PASSWORD_ERROR)
                    ));
        } catch (TimeoutException e) {
            throw new AssertionError("Регистрация не завершилась в течение 10 секунд");
        }
    }

    @Step("Проверка отображения ошибки пароля")
    public boolean isPasswordErrorDisplayed() {
        try {
            return waitForElementVisible(PASSWORD_ERROR).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Полный процесс регистрации")
    public LoginPage register(String name, String email, String password) {
        setName(name)
                .setEmail(email)
                .setPassword(password)
                .clickRegisterButton();
        return new LoginPage(driver);
    }

    @Step("Переход на страницу входа")
    public LoginPage clickLoginLink() {
        waitForElementClickable(LOGIN_LINK).click();
        return new LoginPage(driver);
    }
}