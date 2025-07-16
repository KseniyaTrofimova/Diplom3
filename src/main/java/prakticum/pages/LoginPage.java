package prakticum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    private static final By EMAIL_FIELD = By.xpath("//input[@name='name']");
    private static final By PASSWORD_FIELD = By.xpath("//input[@name='Пароль']");
    private static final By LOGIN_BUTTON = By.xpath("//button[text()='Войти']");
    private static final By REGISTER_LINK = By.linkText("Зарегистрироваться");
    private static final By FORGOT_PASSWORD_LINK = By.linkText("Восстановить пароль");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageLoaded() {
        try {
            return waitForElementVisible(LOGIN_BUTTON).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверка отображения кнопки входа")
    public boolean isLoginButtonDisplayed() {
        return isPageLoaded();
    }

    @Step("Ввод email: {email}")
    public LoginPage setEmail(String email) {
        waitForElementVisible(EMAIL_FIELD).sendKeys(email);
        return this;
    }

    @Step("Ввод пароля")
    public LoginPage setPassword(String password) {
        waitForElementVisible(PASSWORD_FIELD).sendKeys(password);
        return this;
    }

    @Step("Нажатие кнопки 'Войти'")
    public void clickLoginButton() {
        waitForElementClickable(LOGIN_BUTTON).click();
    }

    @Step("Выполнение входа")
    public void login(String email, String password) {
        setEmail(email)
                .setPassword(password)
                .clickLoginButton();
    }

    @Step("Переход на страницу регистрации")
    public RegistrationPage clickRegisterLink() {
        waitForElementClickable(REGISTER_LINK).click();
        return new RegistrationPage(driver);
    }

    @Step("Переход на страницу восстановления пароля")
    public RestorePasswordPage clickForgotPasswordLink() {
        waitForElementClickable(FORGOT_PASSWORD_LINK).click();
        return new RestorePasswordPage(driver);
    }
}