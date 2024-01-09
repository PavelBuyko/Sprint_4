import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {
    public WebDriver driver;
    @Before
    public void setUp() {
        //создаем вебдрайвер
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @After
    public void close()
    {
        //закрываем браузер
    //    driver.quit();
    }
}
