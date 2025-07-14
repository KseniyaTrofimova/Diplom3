package prakticum;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import prakticum.api.models.User;
import prakticum.api.models.UserClient;
import prakticum.pages.LoginPage;
import prakticum.pages.MainPage;
import prakticum.pages.RegistrationPage;
import prakticum.pages.RestorePasswordPage;
import prakticum.utils.Browser;
import prakticum.utils.RandomDataGenerator;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public abstract class BaseTest {
    protected WebDriver driver;
    protected String browserName;

    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected RegistrationPage registrationPage;
    protected RestorePasswordPage restorePasswordPage;

    protected UserClient userClient;
    protected User user;
    protected String accessToken;

    @Parameterized.Parameters(name = "Browser: {0}")
    public static Collection<Object[]> browsers() {
        return Arrays.asList(new Object[][]{
                {"chrome"},
                {"yandex"}
        });
    }

    public BaseTest(String browserName) {
        this.browserName = browserName;
    }

    @Before
    public void setUp() {
        System.out.println("Starting test in browser: " + browserName);

        driver = Browser.getBrowser(browserName);
        driver.manage().window().maximize();

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        restorePasswordPage = new RestorePasswordPage(driver);

        userClient = new UserClient();

        user = new User();
        user.setEmail(RandomDataGenerator.generateRandomEmail());
        user.setPassword(RandomDataGenerator.generateRandomPassword(8));
        user.setName(RandomDataGenerator.generateRandomName());

        Response response = userClient.createUser(user);
        accessToken = userClient.getAccessToken(response);

        driver.get("https://stellarburgers.nomoreparties.site");
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }

        // Close browser
        if (driver != null) {
            driver.quit();
        }

        System.out.println("Test finished in browser: " + browserName);
    }
}