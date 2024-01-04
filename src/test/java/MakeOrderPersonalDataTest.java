import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

@RunWith(Parameterized.class)
public class MakeOrderPersonalDataTest {

        //Добавь необходимые поля
        private static String Name;
        private static String secondName;
        private static String adress;
        private static String metro;
        private static String phone;
        WebDriver driver;

        public MakeOrderPersonalDataTest(String Name, String secondName, String adress, String metro, String phone) {
            this.Name=Name;
            this.secondName=secondName;
            this.adress=adress;
            this.metro=metro;
            this.phone=phone;
        }

        @Parameterized.Parameters
        public static Object[][] getTexts() {
            //Сгенерируй тестовые данные (нам нужно название городов и результат поиска)
            return new Object[][]{
                    //Позитивные проверки
                    {"Иван", "Иванов","ул. Пушкина, д. 10", "Пушкинская", "89991234567"}, // проверка валидных данных.
                    {"Мария", "Петрова","ул. Ленина, д. 5", "Раменки", "89051231245"},
                    {"Алексей", "Сидоров","пр-т Вернадского, д. 20", "Университет", "89991231212"},

                    //Негативные проверки:
                    {"", "Иванов","ул. Пушкина, д. 10", "Пушкинская", "89991234567"}, // проверка пустого имени.
                    {"Иван", "","ул. Пушкина, д. 10", "Пушкинская", "89991234567"}, // проверка пустой фамилии.
                    {"Иван", "Иванов","ул. Пушкина, д. 10", "", "89991234567"}, // проверка пустой станции метро.
                    {"Иван", "Иванов","ул. Пушкина, д. 10", "Пушкинская", ""}, // проверка пустого номера телефона.
                    {"Ivan", "Ivanov","ул. Пушкина, д. 10", "Пушкинская", "89991234567"}, // проверка на использование невалидных символов в имени.
                    {"Иван", "Ivanov","ул. Пушкина, д. 10", "Пушкинская", "89991234567"}, // проверка на использование латинских символов в фамилии.
                    {"Иван", "Иванов","ул.", "Пушкинская", "89991234567"}, // проверка на короткое значение поля адреса.
                    {"Иван", "Иванов","ул. Пушкина, д. 10", "SomeRandomStation", "89991234567"}, // проверка на несуществующую станцию метро.
                    {"Иван", "Иванов","ул. Пушкина, д. 10", "Пушкинская", "89991234567345"}, // проверка на длину номера телефона.
                    {"Иван", "Иванов","ул. Пушкина, д. 10", "Пушкинская", "899912345"}, // проверка на короткую длину номера телефона.

            };
        }
        private void fillAllData(WebDriver driver,OrderPage page)
        {
            page.nameFillData(driver,Name);
            page.sNameFillData(driver,secondName);
            page.adressFillData(driver,adress);
            page.phoneFillData(driver,phone);
            page.metroFillData(driver,metro);
        }
        private void checkSubmitClickResult(WebDriver driver)
        {
            try {
                WebElement element = driver.findElement(By.className("Order_MixedDatePicker__3qiay"));
                assertTrue(element.isDisplayed());
            } catch (NoSuchElementException e) {
                fail("Данные не прошли валидацию.\nДанные:\nИмя: "+Name+"\nФамилия: "+secondName+"\nАдрес: "+adress+"\nМетро: "+metro+"\nТелефон: "+phone);
            }
        }

        @Before
        public void setUp() {
            //Тесты для хрома
            //WebDriverManager.chromedriver().setup();
            //driver = new ChromeDriver();

            //Тесты для фаерфокса
            System.setProperty("WebDriverManager.firefoxdriver", "C:\\Webdriver\\geckodriver.exe");
            driver = new FirefoxDriver();
            WebDriverManager.firefoxdriver().setup();
        }

        @Test
        public void fillUserDataTest() {
            //создаем объект страницы и открываем нужный URL
            OrderPage page = new OrderPage();
            page.openOrderPage(driver);


            fillAllData(driver,page);   //заполняем данные
            page.clickSubmit(driver);  //Нажимаем на кнопку "Далее"
            checkSubmitClickResult(driver); //Проверка на то, что переход на 2-й этап произошел.
            page.hardCodeSkipPhase2(driver); //Тут хардкодом прошел до страницы информации о заказе.

            OrderInfoPage infoPage = new OrderInfoPage(); //Создаем объект страницы с инфо о заказе
            String[] actual = infoPage.getInfoInOrder(driver); //Собираем с неё данные

            //Проверяем корректность того, что данные на сервере не отличаются от того, что мы вводили.
            assertEquals("Имя не совпало",Name,actual[0]);
            assertEquals("Фамилия не совпала",secondName,actual[1]);
            assertEquals("Адрес не совпал",adress,actual[2]);
            assertEquals("Метро не совпало",metro,actual[3]);
            assertEquals("Телефон не совпал",phone,actual[4]);

        }

        @After
        public void close()
        {
           driver.quit();
        }
    }


