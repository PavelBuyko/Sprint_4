import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderRentDataPage {
    private final By DATE =By.className("Order_MixedDatePicker__3qiay") ;
    private final By RENTTIME =By.className("Dropdown-control") ;
    private final By COLORBLACKCHECKBOX =By.xpath(".//input[@id='black']");
    private final By COLORGREYCHECKBOX =By.xpath(".//input[@id='grey']");
    private final By COMMENT =By.className("Input_InputContainer__3NykH") ;
    private final By BACKBUTTON = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM Button_Inverted__3IF-i']");
    private final By SUBMITBUTTON = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final By CHOOSEDATE = By.xpath(".//div[@class='react-datepicker__day react-datepicker__day--029']");
    private final By CHOOSERENTTIME = By.xpath("//div[@class='Dropdown-menu']/div[1]");
    private final By ORDERBUTTON = By.xpath(".//div[@class='Order_Modal__YZ-d3']/div[@class='Order_Buttons__1xGrp']/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
private final By FINISHMAKEORDERBUTTON = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    public void hardCodeSkipPhase2(WebDriver driver)
    {
        driver.findElement(DATE).click();
        driver.findElement(CHOOSEDATE).click();
        driver.findElement(RENTTIME).click();
        driver.findElement(CHOOSERENTTIME).click();
        driver.findElement(COLORGREYCHECKBOX).click();
        driver.findElement(SUBMITBUTTON).click();
        driver.findElement(ORDERBUTTON).click();
        new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(FINISHMAKEORDERBUTTON)));
        driver.findElement(FINISHMAKEORDERBUTTON).click();
    }





}
