import PageObject.Constructor;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ConstructorTest {
    private WebDriver driver;
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
    }

    @Test
    @DisplayName("Переход к разделу булки")
    public void openBuns() {
        Constructor constructor = new Constructor(driver);
        constructor.clickSauces();
        constructor.clickBun();
        constructor.checkBunsIsDisplayed();
    }

    @Test
    @DisplayName("Переход к разделу Sauses")
    public void openSauces() {
        Constructor constructor = new Constructor(driver);
        constructor.clickFillings();
        constructor.clickSauces();
        constructor.checkSaucesIsDisplayed();
    }

    @Test
    @DisplayName("Переход к разделу Fillings")
    public void openFillings() {
        Constructor constructor = new Constructor(driver);
        constructor.clickSauces();
        constructor.clickFillings();
        constructor.checkFillingsIsDisplayed();
    }
    @After
    public void teardown() {
        driver.quit();
    }
}

