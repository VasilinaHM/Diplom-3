import PageObject.LoginPage;
import PageObject.RegistrationPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.CreateUser;
import org.example.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.example.UserGenerator.randomUser;

public class RegistrationTest {
    private WebDriver driver;
    private User user;
    private CreateUser createUser;
    private String accessToken;

    @Before
    public void setUp() {
        user = randomUser();
        createUser = new CreateUser();
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        options.addArguments("--remote-allow-origins=*");
       // ChromeOptions options = new ChromeOptions();
        //options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://stellarburgers.nomoreparties.site");

    }

    @Test
    @DisplayName("Регистрация нового пользователя")
    public void RegistrationUserTest() {
        RegistrationPage objRegistrationPage = new RegistrationPage(driver);
        LoginPage logPage = new LoginPage(driver);
        objRegistrationPage.clickSignInMainPage();
        objRegistrationPage.clickButtonLogin();
        objRegistrationPage.login(user.getName(), user.getEmail(), user.getPassword());
        objRegistrationPage.clickRegisterButton();
        logPage.setAutorizationUser(user.getEmail(), user.getPassword());
    }

    @After
    public void teardown() {
        driver.quit();
        ValidatableResponse login = createUser.login(user);
        accessToken = login.extract().path("accessToken");
        ValidatableResponse deleteResponse = createUser.delete(accessToken);
        deleteResponse.assertThat().statusCode(202);
    }
}


