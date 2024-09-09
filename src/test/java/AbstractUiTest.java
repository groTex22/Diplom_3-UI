import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Базовый клас для ui-тестов
 * Здесь прописаны процедуры, используемые для создания драйверов
 */
public abstract class AbstractUiTest {
    //некоторые переменные засунем в одно место, удобнее будет поддерживать
    protected String name = "name11";
    protected String email = "ryzkov12312@diplom.ru";
    protected String password = "123213123!";

    //Запуск указанного браузера
    protected static WebDriver getDriver (String driverType){
        switch (driverType) {
            case "chrome" :
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                return new ChromeDriver(options);
            case "mozila" :
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                return new FirefoxDriver(firefoxOptions);
            default:
                throw new IllegalArgumentException("Driver type is not support");
        }
    }

}
