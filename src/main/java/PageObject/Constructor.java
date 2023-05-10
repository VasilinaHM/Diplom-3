package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Constructor {
    private WebDriver driver;
    private By bun = By.xpath(".//div[span[text()='Булки']]"); // локатор для вкладки булки
    private By sauces = By.xpath(".//div[span[text()='Соусы']]"); //локатор для вкладки соусы
    private By fillings = By.xpath(".//div[span[text()='Начинки']]"); //локатор для вкладки начинки

    public Constructor(WebDriver driver) {
        this.driver = driver;
    }
    public void clickSauces() {
        driver.findElement(sauces).click();
    }
    public void clickFillings() {
        driver.findElement(fillings).click();
    }
    public void clickBun() {
        driver.findElement(bun).click();
    }
    public void checkSaucesIsDisplayed() {
        assertThat("Вкладка Соусы отображается", true,
                equalTo(driver.findElement(By.xpath("//div[contains(span/text(),'Соусы') and contains(@class,'current')]")).isDisplayed()));
    }
    public void checkFillingsIsDisplayed() {
        assertThat("Вкладка Начинки отображается", true,
                equalTo(driver.findElement(By.xpath("//div[contains(span/text(),'Начинки') and contains(@class,'current')]")).isDisplayed()));
    }
    public void checkBunsIsDisplayed() {
        assertThat("Вкладка Булки отображается", true,
                equalTo(driver.findElement(By.xpath("//div[contains(span/text(),'Булки') and contains(@class,'current')]")).isDisplayed()));
    }
}

