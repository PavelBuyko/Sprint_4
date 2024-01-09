import org.junit.Test;
import org.openqa.selenium.WebDriver;
import static junit.framework.Assert.assertEquals;

public class OrderButtonClickTest extends BaseTest{
    private final String EXPECTED = "https://qa-scooter.praktikum-services.ru/order";

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
}
