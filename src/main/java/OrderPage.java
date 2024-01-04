import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class OrderPage extends OrderRentDataPage{
        //Имя
        private final By NAMEFIELD =By.xpath(".//input[@placeholder='* Имя']") ;
        //Фамилия
        private final By SNAMEFIELD =By.xpath(".//input[@placeholder='* Фамилия']") ;
        //Адрес
        private final By ADRESSFIELD =By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']") ;
        //Метро
        private final By METROFIELD =By.className("select-search");
        //Телефон
        private final By PHONEFIELD =By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']") ;
        //Кнопка подтверждения
        private final By SUBMITBUTTON = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");


        public void openOrderPage(WebDriver driver)
        {
            driver.get("https://qa-scooter.praktikum-services.ru/order");
        }
        public void nameFillData(WebDriver driver,String value)
        {
                driver.findElement(NAMEFIELD).sendKeys(value);

        }
        public void sNameFillData(WebDriver driver,String value)
        {
                driver.findElement(SNAMEFIELD).sendKeys(value);
        }
        public void adressFillData(WebDriver driver,String value)
        {
                driver.findElement(ADRESSFIELD).sendKeys(value);
        }
        public void phoneFillData(WebDriver driver,String value)
        {
                driver.findElement(PHONEFIELD).sendKeys(value);
        }

        public void metroFillData(WebDriver driver, String value)
        {
                closeCookiePopup(driver);
                driver.findElement(METROFIELD).click();
                try {
                        WebElement element = driver.findElement(By.xpath(".//*[text()='"+value+"']"));
                        element.click();
                }catch (NoSuchElementException e){System.out.println("Станции метро '"+value+"' не существует");}
        }
        public void closeCookiePopup(WebDriver driver)
        {
                driver.findElement(By.id("rcc-confirm-button")).click();
        }
        public void clickSubmit(WebDriver driver)
        {
                driver.findElement(SUBMITBUTTON).click();
        }

    }

