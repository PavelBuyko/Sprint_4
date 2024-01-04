import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static junit.framework.Assert.assertEquals;


@RunWith(Parameterized.class)
public class Question_Answer_test_texts {
    //Добавь необходимые поля
    private static String question;
    private static String answer;
    private static short listPosition;
    WebDriver driver;


    public Question_Answer_test_texts(String question, String answer,short listPosition) {
        this.question =question;
        this.answer =answer;
        this.listPosition =listPosition;
    }

    @Parameterized.Parameters
    public static Object[][] getTexts() {

        return new Object[][]{
                {"Сколько это стоит? И как оплатить?","Сутки — 400 рублей. Оплата курьеру — наличными или картой.",(short)0},
                {"Хочу сразу несколько самокатов! Так можно?","Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",(short)1},
                {"Как рассчитывается время аренды?","Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",(short)2},
                {"Можно ли заказать самокат прямо на сегодня?","Только начиная с завтрашнего дня. Но скоро станем расторопнее.",(short)3},
                {"Можно ли продлить заказ или вернуть самокат раньше?","Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",(short)4},
                {"Вы привозите зарядку вместе с самокатом?","Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",(short)5},
                {"Можно ли отменить заказ?","Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",(short)6},
                {"Я жизу за МКАДом, привезёте?","Да, обязательно. Всем самокатов! И Москве, и Московской области.",(short)7},

        };
    }

    @Before
    public void setUp() {
        //Тесты для хрома
        // WebDriverManager.chromedriver().setup();
        // driver = new ChromeDriver();
        //Тесты для фаерфокса
        System.setProperty("WebDriverManager.firefoxdriver", "C:\\Webdriver\\geckodriver.exe");
        driver = new FirefoxDriver();
        WebDriverManager.firefoxdriver().setup();

    }

    @Test
    public void questionAnswerTextTest() throws InterruptedException {

        LandingPage page = new LandingPage();
        page.openLandingPage(driver);
        page.closeCookiePopup(driver);

        String actualQuestion = page.getListQuestion(driver,listPosition);
        assertEquals("Вопросы не совпадают. ожидаемое: "+actualQuestion+"фактическое: ",question,actualQuestion);
        System.out.println("Вопрос "+ listPosition+" тест прошел");

        String actualAnswer = page.getListAnswer(driver,listPosition);
        assertEquals("Ответы не совпадают. ожидаемое: "+answer+"фактическое: "+actualAnswer,answer,actualAnswer);
        System.out.println("Ответ "+ listPosition+" тест прошел");

    }
    @After
    public void close()
    {
        driver.quit();
    }
}
