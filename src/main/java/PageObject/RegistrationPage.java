package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RegistrationPage {
    private WebDriver driver;
    private By name = By.xpath("//label[contains(text(),'Имя')]/../input");//локатор поля имя в форме регистрации
    private By email = By.xpath("//label[contains(text(),'Email')]/../input");//локатор поля email в форме регистрации
    private By password = By.xpath("//label[contains(text(),'Пароль')]/../input");//локатор поля пароль в форме регистрации
    private By registrationButton = By.className("Auth_link__1fOlj");//локатор для кнопки зарегистрироваться в форме авторизации
    private By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");//локатор для кнопки зарегистрироваться в форме регистрации
    private final By signInMainPage = By.xpath("//*[@id=\"root\"]/div/main/section[2]/div/button");//локатор для кнопки «Войти в аккаунт» на главной
    private By errorPassword = By.xpath(".//p[text() = 'Некорректный пароль']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setName(String userName) {
        driver.findElement(name).sendKeys(userName);
    }

    public void setEmail(String userEmail) {
        driver.findElement(email).sendKeys(userEmail);
    }

    public void setPassword(String userPassword) {
        driver.findElement(password).sendKeys(userPassword);
    }

    @Step("Заполнение полей name, email, password")
    public void login(String userName, String userEmail, String userPassword) {
        setName(userName);
        setEmail(userEmail);
        setPassword(userPassword);
    }

    @Step("Клик по кнопке Войти в аккаунт")
    public void clickSignInMainPage() {
        driver.findElement(signInMainPage).click();
    }

    @Step("Клик по кнопке зарегистрироваться в форме авторизации")
    public void clickButtonLogin() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(registrationButton));
        driver.findElement(registrationButton).click();
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Введение невалидного пароля")
    public void checkErrorPassword() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",driver.findElement(registerButton));
        driver.findElement(registerButton).click();
        assertThat("Введен пароль меньше 6 символов", true,
                equalTo(driver.findElement(errorPassword).isDisplayed()));
    }
}