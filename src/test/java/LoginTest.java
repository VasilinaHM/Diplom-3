import PageObject.LoginPage;
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


public class LoginTest {
    private WebDriver driver;
    private User user;
    private CreateUser createUser;
    private String accessToken;
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        options.addArguments("--remote-allow-origins=*");
        // ChromeOptions options = new ChromeOptions();
        //options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://stellarburgers.nomoreparties.site/login");
        user = randomUser();
        createUser = new CreateUser();
        ValidatableResponse create = createUser.create(user);
        create.assertThat().statusCode(200);
    }

    @Test
    @DisplayName("Авторизация через кнопку Вход")
    public void LoginUserTest() {
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.setAutorizationUser(user.getEmail(), user.getPassword());
    }
    @Test
    @DisplayName("Авторизация через кнопку «Войти в аккаунт» на главной странице")
    public void LoginUserMainPageTest() {
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.setAutorizationMainPage();
        objLoginPage.setAutorizationUser(user.getEmail(), user.getPassword());
    }
    @Test
    @DisplayName("Авторизация через кнопку «Личный кабинет»")
    public void LoginUserPersonalAccountTest(){
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.setAutorizatioPersonalAccount();
        objLoginPage.setAutorizationUser(user.getEmail(), user.getPassword());
    }
    @Test
    @DisplayName("Авторизация через кнопку Вход в форме восстановления пароля")
    public void LoginUserPasswordRecoveryTest(){
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.setAutorizatioPasswordRecovery();
        objLoginPage.setAutorizationUser(user.getEmail(), user.getPassword());
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
