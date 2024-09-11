import org.junit.*;
import org.openqa.selenium.WebDriver;
import page_object.BasePage;

import static page_object.BasePage.BASE_PAGE_URL;

public class ConstructorSectionTest extends AbstractUiTest {
    private static WebDriver driver;
    private static BasePage basePage;

    /*Так как будет просто переключаться по вкладкам, то не надо перед каждый тестом запускать браузер*/
    @BeforeClass
    public static void beforeTest() {
        driver = getDriver();

        basePage = new BasePage(driver);
        driver.get(BASE_PAGE_URL);
    }

    @AfterClass
    public static void afterTest() {
        driver.quit();
    }


    //Проверим переход к секции булки
    @Test
    public void crossSectionBunsTest() {
        //Так как по-умолчанию уже стоим на этом разделе, то чтобы не упасть в невозможность кликнуть
        //Перейдем в другой раздел
        basePage.clickButtonSauce();
        basePage.clickButtonBuns();
        //Проверяем, что появилась область с булками
        Assert.assertTrue(basePage.visibilityBuns());
    }

    //Проверим переход к секции соусы
    @Test
    public void crossSectionSauceTest() {
        basePage.clickButtonSauce();
        //Проверяем, что появилась область с булками
        Assert.assertTrue(basePage.visibilitySauce());
    }

    //Проверим переход к секции соусы
    @Test
    public void crossSectionFillingTest() {
        basePage.clickButtonFilling();
        //Проверяем, что появилась область с булками
        Assert.assertTrue(basePage.visibilityFilling());
    }
}
