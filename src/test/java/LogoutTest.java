import API.clients.ClientUser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.BasePage;
import pageObject.LoginPage;
import pageObject.PersonalAccountPage;

import static pageObject.LoginPage.LOGIN_URL;

public class LogoutTest extends AbstractUiTest {
    WebDriver driver;
    WebDriverWait wait;
    PersonalAccountPage personalAccountPage;
    LoginPage loginPage;
    ClientUser clientUser = new ClientUser();
    String tokken;

    //Отркываем браузер и создаем объекты
    @Before
    public void beforeTest() {
        driver = getDriver("chrome");
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
        //Объекты
        personalAccountPage = new PersonalAccountPage(driver);
        loginPage = new LoginPage(driver);

        /*Для того, чтобы выйти, нужно создать пользователя*/
        //to_do: наверное формирование JSON удобнее будет вынести в отдельный класс,
        //чтобы потом просто дергать нужные методы с переменными
        String createJson
                = "{\"name\": \"" + name + "\", \"email\": \"" + email + "\",  \"password\": \"" + password + "\" }\r\n";
        tokken = clientUser.createUser(createJson);
    }

    //в after закрывается браузер
    @After
    public void afterTest() {
        clientUser.deleteUser(tokken);
        driver.quit();
    }

    @Test
    public void logoutTest() {
        //Сначала нужно авторизоваться
        driver.get(LOGIN_URL);
        //авторизуемся
        loginPage.inputEmailRegistration(email) //email
                .inputPasswordRegistration(password) //password
                .clickLoginButton();

      /*  BasePage basepage = new BasePage(driver);
        basepage.clickPersAccButton();*/
        new BasePage(driver).clickPersAccButton();//Кликнем на "личный кабинет
        //дождемся появления нужной нам кнопки
        personalAccountPage.waitForLoadedButtonExit();
        //и нажмем на нее
        personalAccountPage.clickButtonExit();
        //Проверим что перешли на другую страницу, а соответсвенно и вышли
        Assert.assertTrue(personalAccountPage.transitionUrl(LOGIN_URL));
    }

}
