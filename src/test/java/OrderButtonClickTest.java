import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import static junit.framework.Assert.assertEquals;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OrderButtonClickTest {
    WebDriver driver;
    private final String EXPECTED = "https://qa-scooter.praktikum-services.ru/order";

    @Before
    public void setUp() {
        //Вебдрайвер для хрома
        //WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();

        //Вебдрайвер для фаерфокса
        System.setProperty("WebDriverManager.firefoxdriver", "C:\\Webdriver\\geckodriver.exe");
        driver = new FirefoxDriver();
        WebDriverManager.firefoxdriver().setup();

    }

    @Test
    public void HeaderOrderButtonClick()
    {
        LandingPage page = new LandingPage();
        page.openLandingPage(driver);
        page.closeCookiePopup(driver);
        String actual = page.OrderButtonClickHeader(driver);

        assertEquals("URL не совпадают.",EXPECTED,actual);
        System.out.println("Кнопка 'заказать' в заголовке работает");

    }
    @Test
    public void BottomOrderButtonClick()
    {
        LandingPage page = new LandingPage();
        page.openLandingPage(driver);
        page.closeCookiePopup(driver);
        String actual = page.OrderButtonClickBottom(driver);

        assertEquals("URL не совпадают.",EXPECTED,actual);
        System.out.println("Кнопка 'заказать' в середине страницы работает");

    }

    @After
    public void close()
    {
        driver.quit();
    }





}
