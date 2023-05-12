import PageObject.RegistrationPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.example.CreateUser;
import org.example.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.example.UserGenerator.randomUser;

public class RegistrationUserTestNoValidPasswordTest {
    private WebDriver driver;
    private User user;
    private CreateUser createUser;

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
    @DisplayName("Регистрация нового пользователя с невалидным паролем")
    public void RegistrationUserTestNoValidPassword() {
        RegistrationPage objRegistrationPage = new RegistrationPage(driver);
        objRegistrationPage.clickSignInMainPage();
        objRegistrationPage.clickButtonLogin();
        objRegistrationPage.login(user.getName(), user.getEmail(), "000");
        objRegistrationPage.checkErrorPassword();
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
