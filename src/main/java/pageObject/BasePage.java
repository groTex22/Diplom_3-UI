package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(3));
    }

    public static String BASE_PAGE_URL = "https://stellarburgers.nomoreparties.site/";


    private final By buttonLoginInBasePage = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");
    private final By buttonPersAccInBasePage = By.xpath(".//p[text()='Личный Кабинет']");
    private final By buttonCreateOrder= By.xpath(".//button[text()='Оформить заказ']");

    private final By buttonBuns= By.xpath(".//span[text()='Булки']");
    private final By buns = By.xpath(".//h2[text()='Булки']");
    private final By buttonSauce= By.xpath(".//span[text()='Соусы']");
    private final By sauce = By.xpath(".//h2[text()='Соусы']");
    private final By buttonFilling= By.xpath(".//span[text()='Начинки']");
    private final By filling = By.xpath(".//h2[text()='Начинки']");


    @Step("Главная страница.Нажатие кнопки 'Войти в аккаунт'")
    public void clickLoginButton() {
        driver.findElement(buttonLoginInBasePage).click();
    }

    @Step("Главная страница.Нажатие кнопки 'Личный кабинет'")
    public void clickPersAccButton() {
        driver.findElement(buttonPersAccInBasePage).click();
    }

    @Step("Доступна ли кнопка Оформить заказ")
    public Boolean bolleanButtonCreateOrder() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(buttonCreateOrder)).isDisplayed();
    }

    @Step("Клик по кнопке 'Булки'")
    public void clickButtonBuns() {
        driver.findElement(buttonBuns).click();
    }

    @Step("На экране появились 'Булки'")
    public boolean visibilityBuns() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(buns)).isDisplayed();
    }

    @Step("Клик по кнопке 'Соусы'")
    public void clickButtonSauce() {
        driver.findElement(buttonSauce).click();
    }

    @Step("На экране появились 'Булки'")
    public boolean visibilitySauce() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(sauce)).isDisplayed();
    }

    @Step("Клик по кнопке 'Начинки'")
    public void clickButtonFilling() {
        driver.findElement(buttonFilling).click();
    }

    @Step("На экране появились 'Булки'")
    public boolean visibilityFilling() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(filling)).isDisplayed();
    }
}