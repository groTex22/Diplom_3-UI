import api.clients.ClientUser;
import json.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_object.BasePage;
import page_object.ForgotPasswordPage;
import page_object.LoginPage;
import page_object.RegisterPage;

import static page_object.BasePage.BASE_PAGE_URL;
import static page_object.ForgotPasswordPage.FORGOT_PASS_URL;
import static page_object.LoginPage.LOGIN_URL;
import static page_object.RegisterPage.REG_URL;

/*Класс тестирует авторизацию, а именно
* 1. Переходы к странице авторизации из разных мест веб интерфейса
* 2. Сама авторизация (Предварительно создается пользователь,
* ____________________ По завершению теста удаляется)*/
public class LoginTest extends AbstractUiTest {
    private WebDriver driver;
    private BasePage basePage;
    private LoginPage loginPage;
    private ClientUser clientUser;
    //Ожидание общее, поэтому вынесено в этот блок
    private WebDriverWait wait;

    //В before запускается браузер и открывается страница регистрации
    @Before
    public void beforeTest() {
        driver = getDriver();

        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
        //Создаем экземпляры объектов
        basePage = new BasePage(driver);
        loginPage = new LoginPage(driver);
        clientUser = new ClientUser();

    }

    //в after закрывается браузер
    @After
    public void afterTest() {
        driver.quit();
    }

    //Проверяем, что при клике на кнопку 'Войти в аккаунт'
    //Осуществляется переход на страницу авторизации
    @Test
    public void goToLoginPageClickLoginTest() {
        // Переход на стартовую страницу
        driver.get(BASE_PAGE_URL);
        basePage.clickLoginButton();
        //Если true значит зарегались и перешли на страницу авторизации
        //И тест пройден
        Assert.assertTrue(wait.until(ExpectedConditions.urlToBe(LOGIN_URL)));
    }

    //переход на страницу авторизации через кнопку «Личный кабинет»,
    @Test
    public void goToLoginPageClickPersAccTest() {
        // Переход на стартовую страницу
        driver.get(BASE_PAGE_URL);
        basePage.clickPersAccButton();
        //Если true значит зарегались и перешли на страницу авторизации
        //И тест пройден
        Assert.assertTrue(wait.until(ExpectedConditions.urlToBe(LOGIN_URL)));
    }

    //переход на страницу авторизации из формы регистрации,
    @Test
    public void goToLoginPageWithRegPageTest() {
        // Переход на стартовую страницу
        driver.get(REG_URL);
        new RegisterPage(driver).clickLoginButton();
        //Если true значит зарегались и перешли на страницу авторизации
        //И тест пройден
        Assert.assertTrue(wait.until(ExpectedConditions.urlToBe(LOGIN_URL)));
    }

    //переход на страницу авторизации из формы восстановления пароля,
    @Test
    public void goToLoginPageWithForgotPassPageTest() {
        // Переход на стартовую страницу
        driver.get(FORGOT_PASS_URL);
        new ForgotPasswordPage(driver).clickLoginButton();
        //Если true значит зарегались и перешли на страницу авторизации
        //И тест пройден
        Assert.assertTrue(wait.until(ExpectedConditions.urlToBe(LOGIN_URL)));
    }

    //Собственно сама авторизация
    @Test
    public void loginTest() {
        //Для этого теста нужен пользователь, создадим его
        //Создаем JSON из объекта
        User user = new User(name, email, password);
        //Создадим юзера
        String tokken = clientUser.createUser(user);
        Assert.assertNotNull(tokken);
        //Открываем страницу авторизации
        driver.get(LOGIN_URL);
        try {
            //Заполняем данные для авторизации
            loginPage.inputEmailRegistration(email) //email
                    .inputPasswordRegistration(password) //password
                    .clickLoginButton(); //клик "Войти"

            //Проверки
            //Перешли на главную страницу
            Assert.assertTrue(wait.until(ExpectedConditions.urlToBe(BASE_PAGE_URL)));
            //И на всякий случай, убидимся, что появилась кнопка "Оформить заказ", недоступная не авторизованным
            Assert.assertTrue(basePage.bolleanButtonCreateOrder());
        } catch (Exception e) {
            Assert.assertNull("Получили неожиданную ошибку", e.getMessage());
        } finally {
            //Должны удалить пользователя если успели создать и упали в ошибку
            //Удалить пользователя
            clientUser.deleteUser(tokken);
        }
    }
}



