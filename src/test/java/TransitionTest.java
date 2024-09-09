import API.clients.ClientUser;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.BasePage;
import pageObject.LoginPage;
import pageObject.PersonalAccountPage;


import java.util.concurrent.TimeUnit;

import static pageObject.BasePage.BASE_PAGE_URL;
import static pageObject.LoginPage.LOGIN_URL;
import static pageObject.PersonalAccountPage.PERS_ACC_URL;

/*В этом классе проверяются переходы
 * 1. Переход авторизованного пользвоателя в личный кабинет
 * 2. Переход неавторизованного пользователя из страницы логина
 * 3. Переход авторизоаванного пользователя из личного кабинета в конструктор*/
public class TransitionTest extends AbstractUiTest {
    private  WebDriver driver;
    private WebDriverWait wait;

    private final ClientUser clientUser = new ClientUser();


    private String tokken;
    private BasePage basePage;
    private PersonalAccountPage personalAccountPage;
    private LoginPage loginPage;


    @Before
    public void beforeTest() {
        //Запускаем браузер
        driver = getDriver("chrome");
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));

        //Создадим необходимые объекты
        basePage = new BasePage(driver);
        loginPage = new LoginPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
        //to_do: наверное формирование JSON удобнее будет вынести в отдельный класс,
        //чтобы потом просто дергать нужные методы с переменными
        String createJson
                = "{\"name\": \"" + name + "\", \"email\": \"" + email + "\",  \"password\": \"" + password + "\" }\r\n";
        tokken = clientUser.createUser(createJson);

    }

    @After
    public void afterTest() {
        driver.quit();
        clientUser.deleteUser(tokken);
    }

    //Переход авторизованного пользователя в личный кабинет
    @Test
    public void transitionBasePageIntoPerrAccTest() {
        //Сначала нужно авторизоваться
        driver.get(LOGIN_URL);
        //авторизуемся
        loginPage.inputEmailRegistration(email) //email
                .inputPasswordRegistration(password) //password
                .clickLoginButton();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.MILLISECONDS);
        //Далее проверяем сценарий
       // driver.get(BASE_PAGE_URL);
        basePage.clickPersAccButton();

        Assert.assertTrue(wait.until(ExpectedConditions.urlToBe(PERS_ACC_URL)));
    }

    //Переход из личного кабинета в конструктор не авторизованного пользователя
    @Test
    public void transitionPerrAccIntoConstructTest() {
        driver.get(LOGIN_URL);
        loginPage.clickConstructButton();

        Assert.assertTrue(wait.until(ExpectedConditions.urlToBe(BASE_PAGE_URL)));
    }

    //Переход из личного кабинета в конструктор авторизованного пользователя
    @Test
    public void transitionPerrAccIntoConstructAuthTest() {
        //Сначала нужно авторизоваться
        driver.get(LOGIN_URL);
        //авторизуемся
        loginPage.inputEmailRegistration(email) //email
                .inputPasswordRegistration(password) //password
                .clickLoginButton();
        //Далее проверяем сценарий
        //Перейдем в личный кабинет
        basePage.clickPersAccButton();
        wait.until(ExpectedConditions.urlToBe(PERS_ACC_URL));
        //Кликнем на "Конструктор"
        personalAccountPage.clickConstructButton();

        Assert.assertTrue(wait.until(ExpectedConditions.urlToBe(BASE_PAGE_URL)));
    }

}
