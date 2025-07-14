package prakticum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import prakticum.pages.LoginPage;
import prakticum.pages.MainPage;
import prakticum.pages.RegistrationPage;
import prakticum.utils.RandomDataGenerator;

import static org.junit.Assert.*;

public class RegistrationTest extends BaseTest {

    public RegistrationTest(String browserName) {
        super(browserName);
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void testSuccessfulRegistration() {
        userClient.deleteUser(accessToken);
        accessToken = null;

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.register(
                RandomDataGenerator.generateRandomName(),
                RandomDataGenerator.generateRandomEmail(),
                RandomDataGenerator.generateRandomPassword(8)
        );

        assertTrue(loginPage.isLoginButtonDisplayed());
    }

    @Test
    @DisplayName("Проверка регистрации пользователя с коротким паролем")
    public void testRegistrationWithShortPassword() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.register(
                RandomDataGenerator.generateRandomName(),
                RandomDataGenerator.generateRandomEmail(),
                RandomDataGenerator.generateRandomPassword(5)
        );

        assertTrue(registrationPage.isPasswordErrorDisplayed());
    }
}