import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderInfoPage {

    private final By NAME = By.xpath("//div[text()='Имя']/following-sibling::div[@class='Track_Value__15eEX']");
    private final By SNAME = By.xpath("//div[text()='Фамилия']/following-sibling::div[@class='Track_Value__15eEX']");
    private final By ADRESS = By.xpath("//div[text()='Адрес']/following-sibling::div[@class='Track_Value__15eEX']");
    private final By METRO = By.xpath("//div[text()='Станция метро']/following-sibling::div[@class='Track_Value__15eEX']");
    private final By PHONE = By.xpath("//div[text()='Телефон']/following-sibling::div[@class='Track_Value__15eEX']");


    public OrderInfoPage(){}

    public String[] getInfoInOrder(WebDriver driver)
    {
        return new String[]{
                driver.findElement(NAME).getText(),
                driver.findElement(SNAME).getText(),
                driver.findElement(ADRESS).getText(),
                driver.findElement(METRO).getText(),
                driver.findElement(PHONE).getText(),
        };

    }

}
