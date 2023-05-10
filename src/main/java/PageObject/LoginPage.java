package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private final By personalAccount  = By.xpath("/html/body/div/div/header/nav/a/p");//локатор для кнопки Личный кабинет
    private final By signInMainPage  = By.xpath("//*[@id=\"root\"]/div/main/section[2]/div/button");//локатор для кнопки «Войти в аккаунт» на главной
    private final By login =By.xpath(".//button[text()='Войти']");//локатор кнопки Вход в форме авторизации
    private final By email = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input"); //локатор поля email в форме авторизации
    private final By password = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");//локатор поля password в форме авторизации
    private final By passwordRecovery = By.xpath("/html/body/div/div/main/div/div/p[2]/a");//локатор для кнопки Восстановление пароля
    private final By signInPasswordRecovery = By.xpath("/html/body/div/div/main/div/div/p/a");//локатор для кнопки Войти в форме Восстановления пароля
    private final By profile  = By.xpath("//*[@id=\"root\"]/div/main/div/nav/ul/li[1]/a");//локатор для поля Профиль
    private final By logo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");//локатор для логотипа Stellar Burgers
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Авторизация")
    public void setAutorizationUser(String emailForAutorization,String passwordForAutorization) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(login));
        driver.findElement(email).sendKeys(emailForAutorization);
        driver.findElement(password).sendKeys(passwordForAutorization);
        driver.findElement(login).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(personalAccount));
        driver.findElement(personalAccount).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(profile));
    }

    @Step("Авторизация через кнопку «Войти в аккаунт» на главной странице")
    public void setAutorizationMainPage(){
        driver.findElement(logo).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(signInMainPage));
        driver.findElement(signInMainPage).click();
    }
    @Step("Авторизация через кнопку «Личный кабинет»")
    public void setAutorizatioPersonalAccount(){
        driver.findElement(personalAccount).click();

    }
    @Step("Авторизация через кнопку в форме восстановления пароля")
    public void setAutorizatioPasswordRecovery(){
        driver.findElement(personalAccount).click();
        driver.findElement(passwordRecovery).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(signInPasswordRecovery));
        driver.findElement(signInPasswordRecovery).click();
    }
}











