package prakticum;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import prakticum.api.models.User;
import prakticum.api.models.UserClient;
import prakticum.pages.LoginPage;
import prakticum.pages.MainPage;
import prakticum.pages.RegistrationPage;
import prakticum.pages.RestorePasswordPage;
import prakticum.utils.Browser;
import prakticum.utils.RandomDataGenerator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    protected String browserName;

    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected RegistrationPage registrationPage;
    protected RestorePasswordPage restorePasswordPage;

    protected UserClient userClient;
    protected User user;
    protected String accessToken;

    @Before
    public void setUp() {
        browserName = System.getProperty("browser");

        if (browserName == null || browserName.isEmpty()) {
            browserName = "chrome";
        }

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

    private String loadBrowserFromProperties() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            props.load(fis);
            return props.getProperty("browser");
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties file", e);
        }
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
        if (driver != null) {
            driver.quit();
        }
        System.out.println("Test finished in browser: " + browserName);
    }
}