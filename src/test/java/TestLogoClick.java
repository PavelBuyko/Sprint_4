import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Set;
import static junit.framework.Assert.assertEquals;

public class TestLogoClick extends BaseTest{

    private final String EXPECTEDMAIN = "https://qa-scooter.praktikum-services.ru/";
    private final String EXPECTEDYANDEX = "https://dzen.ru/?yredirect=true";

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

}
