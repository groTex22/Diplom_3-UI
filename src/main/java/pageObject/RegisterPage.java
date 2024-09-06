package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static pageObject.LoginPage.LOGIN_URL;

public class RegisterPage {
    WebDriver driver;
    WebDriverWait wait;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(3));
    }

    public static String REG_URL = "https://stellarburgers.nomoreparties.site/register";

    private final By nameUserInput = By.xpath(".//div[label[text()='Имя']]/input[@name='name']");
    private final By emailUserInput = By.xpath(".//div[label[text()='Email']]/input[@name='name']");
    private final By passwordUserInput = By.xpath(".//div[label[text()='Пароль']]/input[@name='Пароль']");
    private final By regButton = By.xpath(".//button[text()='Зарегистрироваться']");


    @Step("Регистрация.Заполнение имени")
    public RegisterPage inputNameRegistration(String name) {
        driver.findElement(nameUserInput).sendKeys(name);
        return this;
    }

    @Step("Регистрация.Заполнение email")
    public RegisterPage inputEmailRegistration(String email) {
        driver.findElement(emailUserInput).sendKeys(email);
        return this;
    }

    @Step("Регистрация.Заполнение пароля")
    public RegisterPage inputPasswordRegistration(String password) {
        driver.findElement(passwordUserInput).sendKeys(password);
        return this;
    }

  /*  @Step("Регистрация.Нажатие кнопки регистрации")
    public Boolean clickRegButton(String url) {
        driver.findElement(regButton).click();
       // return ;
        return wait.until(ExpectedConditions.urlToBe(url));
    }*/

    @Step("Регистрация.Нажатие кнопки регистрации")
    public void clickRegButton() {
        driver.findElement(regButton).click();
         }

    @Step("Регистрация.Ожидание перехода на другую страницу")
    public Boolean waitURL(String url) {
        return  wait.until(ExpectedConditions.urlToBe(url));
    }


}
