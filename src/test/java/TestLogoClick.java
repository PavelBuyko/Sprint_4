import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Set;

import static junit.framework.Assert.assertEquals;

public class TestLogoClick {
    WebDriver driver;
    private final String EXPECTEDMAIN = "https://qa-scooter.praktikum-services.ru/";
    private final String EXPECTEDYANDEX = "https://dzen.ru/?yredirect=true";

    @Before
    public void setUp() {
        //Вебдрайвер для хрома
       WebDriverManager.chromedriver().setup();
       driver = new ChromeDriver();

        //Вебдрайвер для фаерфокса
        //System.setProperty("WebDriverManager.firefoxdriver", "C:\\Webdriver\\geckodriver.exe");
        //driver = new FirefoxDriver();
        //WebDriverManager.firefoxdriver().setup();

    }

    @Test
    public void yandexWordInHeaderClickTest()
    {
        LandingPage page = new LandingPage();
        page.openLandingPage(driver);
        page.closeCookiePopup(driver);

        driver.findElement(By.className("Header_LogoYandex__3TSOI")).click();

        //переключение на свеже-открытую вкладку и получение её URL
        Set<String> handles = driver.getWindowHandles();
        String newWindowHandle = (String) handles.toArray()[handles.size() - 1];
        driver.switchTo().window(newWindowHandle);
        String actual = driver.getCurrentUrl();

        assertEquals("URL не совпадают.",EXPECTEDYANDEX,actual);


    }
    @Test
    public void samokatWordInHeaderClickTest()
    {
        OrderPage page = new OrderPage();
        page.openOrderPage(driver);
        page.closeCookiePopup(driver);
        driver.findElement(By.className("Header_LogoScooter__3lsAR")).click();
        String actual =driver.getCurrentUrl();

        assertEquals("URL не совпадают.",EXPECTEDMAIN,actual);
    }

    @After
    public void close()
    {
        driver.quit();
    }

}
