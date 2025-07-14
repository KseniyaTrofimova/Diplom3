package prakticum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    public LoginTest(String browserName) {
        super(browserName);
    }

    @Before
    public void createTestUser() {
        userClient.createUser(user);
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт'")
    public void testLoginViaMainButton() {
        mainPage.clickLoginButton();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    public void testLoginViaPersonalAccount() {
        mainPage.clickPersonalAccountButton();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void testLoginViaRegistrationForm() {
        mainPage.clickPersonalAccountButton();
        loginPage.clickRegisterLink();
        registrationPage.clickLoginLink();
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue("Ожидается, что вход через форму регистрации выполнен успешно",
                mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void testLoginViaPasswordRecovery() {
        mainPage.clickPersonalAccountButton();
        loginPage.clickForgotPasswordLink();
        restorePasswordPage.clickLoginLink();
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue("Ожидается, что вход через форму восстановления пароля выполнен успешно",
                mainPage.isOrderButtonVisible());
    }

    @After
    public void deleteTestUser() {
        try {
            String token = userClient.getAccessToken(userClient.login(user));
            if (token != null) {
                userClient.deleteUser(token);
            }
        } catch (Exception e) {
            System.out.println("Failed to delete test user: " + e.getMessage());
        }
    }
}