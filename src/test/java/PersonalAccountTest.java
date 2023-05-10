import PageObject.LoginPage;
import PageObject.PersonalAccount;
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

public class PersonalAccountTest {
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
        driver.get("https://stellarburgers.nomoreparties.site");
        user = randomUser();
        createUser = new CreateUser();
        ValidatableResponse create = createUser.create(user);
        create.assertThat().statusCode(200);
        LoginPage objLoginPage = new LoginPage(driver);
        RegistrationPage objRegistrationPage = new RegistrationPage(driver);
        objRegistrationPage.clickSignInMainPage();
        objLoginPage.setAutorizationUser(user.getEmail(), user.getPassword());
    }
    @Test
    @DisplayName("Переход по клику в «Личный кабинет»")
    public void clickPersonalAccount (){
        PersonalAccount persAccount = new PersonalAccount(driver);
        persAccount.transitionPersonalAccount();
    }
    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    public  void clickConstructor(){
        PersonalAccount persAccount = new PersonalAccount(driver);
        persAccount.transitionConstructor();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор при нажатии на логотип")
    public  void clickLogo(){
        PersonalAccount persAccount = new PersonalAccount(driver);
        persAccount.transitionLogo();
    }
    @Test
    @DisplayName("Выход из личного кабинета")
    public  void clickGoOut(){
        PersonalAccount persAccount = new PersonalAccount(driver);
        persAccount.goOut();
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
