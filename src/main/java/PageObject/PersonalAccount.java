package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccount {
    private WebDriver driver;
    private final By personalAccount  = By.xpath("/html/body/div/div/header/nav/a/p");//локатор для кнопки Личный кабинет
    private final By profile  = By.xpath("//*[@id=\"root\"]/div/main/div/nav/ul/li[1]/a");//локатор для поля Профиль
    private final  By constructor = By.xpath("/html/body/div/div/header/nav/ul/li[1]/a");//локатор для кнопки Конструктор в личном кабинете
    private final  By assembleBurger = By.xpath("/html/body/div/div/main/section[1]/h1");//локатор для текста Соберите бургер
    private final By logo = By.className("AppHeader_header__logo__2D0X2");//локатор для кнопки Stellar Burgers в личном кабинете
    private final By exit = By.xpath("//*[@id=\"root\"]/div/main/div/nav/ul/li[3]/button");//локатор кнопки Выйти в личном кабинете
    private final By login = By.xpath("//*[@id=\"root\"]/div/main/div/form/button");//локатор кнопки Вход в форме авторизации
    public PersonalAccount(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Переход по клику в «Личный кабинет»")
    public void transitionPersonalAccount(){
        driver.findElement(personalAccount).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(profile));
    }
    @Step("Переход из личного кабинета в конструктор")
    public void transitionConstructor(){
        driver.findElement(constructor).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(assembleBurger));
    }
    @Step("Переход из личного кабинета в конструктор при нажатии на логотип")
    public void transitionLogo(){
        driver.findElement(logo).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(assembleBurger));
    }
    @Step("Выход из личного кабинета")
    public void goOut(){
        driver.findElement(exit).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(login));
    }
}
