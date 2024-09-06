import API.clients.ClientUser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import pageObject.RegisterPage;

import static pageObject.LoginPage.LOGIN_URL;

public class RegistrationTest {

    private WebDriver driver;
    private RegisterPage registerPage;
    //Переменные
    String name = "name11";
    String email = "ryzkov12312@diplom.ru";
    String password = "123213123";

    @Before
    public void beforeTest() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        registerPage = new RegisterPage(driver);
        // Переход на тестируемую страничку
        driver.get(RegisterPage.REG_URL);
    }

    @After
    public void afterTest() {
        driver.quit();
    }

    @Test
    public void RegistrationUserSuccessTest() {
        registerPage
                .inputNameRegistration(name)
                .inputEmailRegistration(email)
                .inputPasswordRegistration(password)
                .clickRegButton();

        //Если true значит зарегались и перешли на страницу авторизации
        Assert.assertTrue(registerPage.waitURL(LOGIN_URL));

        //Чтобы удалить созданного пользователя
        //нужно авторизоваться, получить токен и удалиться
        //Это будет быстрее через API, выносить в after лень пока всего один тест
        ClientUser clientUser = new ClientUser();
        String json = "{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}";
        clientUser.deleteUser(clientUser.loginUserReturnToken(json));
    }

    @Test
    public void RegistrationUserIncorrectPasswordTest() {
        registerPage
                .inputNameRegistration(name)
                .inputEmailRegistration(email)
                .inputPasswordRegistration("12")
                .clickRegButton();

        Assert.assertTrue("Текст об ошибке отсутствует"
                , driver.findElement(By.xpath(".//p[text()='Некорректный пароль']"))
                        .isDisplayed());
    }
}
