package prakticum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {
    private static final By LOGIN_BUTTON = By.xpath("//button[text()='Войти в аккаунт']");
    private static final By BUNS_SECTION = By.xpath("//span[text()='Булки']/..");
    private static final By SAUCES_SECTION = By.xpath("//span[text()='Соусы']/..");
    private static final By FILLINGS_SECTION = By.xpath("//span[text()='Начинки']/..");
    private static final By ACTIVE_SECTION = By.xpath("//div[contains(@class, 'tab_tab_type_current')]");
    private static final By PERSONAL_ACCOUNT_BUTTON = By.xpath("//p[text()='Личный Кабинет']/..");
    private static final By ORDER_BUTTON = By.xpath("//button[contains(., 'Оформить заказ')]");
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageLoaded() {
        try {
            return waitForElementVisible(ORDER_BUTTON).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Клик по разделу 'Булки'")
    public MainPage clickBunsSection() {
        waitForElementClickable(BUNS_SECTION).click();
        return this;
    }

    @Step("Клик по разделу 'Соусы'")
    public MainPage clickSaucesSection() {
        waitForElementClickable(SAUCES_SECTION).click();
        return this;
    }

    @Step("Клик по разделу 'Начинки'")
    public MainPage clickFillingsSection() {
        waitForElementClickable(FILLINGS_SECTION).click();
        return this;
    }

    @Step("Получение текста активного раздела")
    public String getActiveSectionText() {
        return waitForElementVisible(ACTIVE_SECTION).getText();
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public LoginPage clickLoginButton() {
        waitForElementClickable(LOGIN_BUTTON).click();
        return new LoginPage(driver);
    }

    @Step("Клик по кнопке 'Личный кабинет'")
    public LoginPage clickPersonalAccountButton() {
        waitForElementClickable(PERSONAL_ACCOUNT_BUTTON).click();
        return new LoginPage(driver);
    }

    @Step("Проверка видимости кнопки 'Оформить заказ'")
    public boolean isOrderButtonVisible() {
        try {
            return waitForElementVisible(ORDER_BUTTON).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void open() {
        driver.get(BASE_URL);
    }
}