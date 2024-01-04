import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LandingPage {
    //кнопка "Заказать" внизу страницы
    private final By ORDERBUTTONBOTTOM =By.xpath("//div[@class='Home_FinishButton__1_cWm']//button") ;
    //кнопка "Заказать" в заголовке страницы
    private final By ORDERBUTTONHEADER =By.className("Button_Button__ra12g") ;
    //номер вопроса
    private short QuestionNumber;


    public void openLandingPage(WebDriver driver)
    {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    public void closeCookiePopup(WebDriver driver)
    {
        driver.findElement(By.id("rcc-confirm-button")).click();
    }
    public void pressOrderButtonHeader(WebDriver driver)
    {
        driver.findElement(ORDERBUTTONHEADER).click();
    }
    public void pressOrderButtonBottom(WebDriver driver)
    {
        driver.findElement(ORDERBUTTONBOTTOM).click();
    }
    public String getListQuestion(WebDriver driver,short questionNumber)
    {
        this.QuestionNumber = questionNumber;
        By listQuestion = By.id("accordion__heading-"+QuestionNumber);
        findAndScroll(driver, questionNumber);
        return driver.findElement(listQuestion).getText();
    }
    public String getListAnswer(WebDriver driver,short questionNumber)
    {
        this.QuestionNumber = questionNumber;
        By listAnswer = By.id("accordion__panel-"+QuestionNumber);
        findAndScroll(driver, questionNumber);
        return driver.findElement(listAnswer).getText();
    }
    
    private void findAndScroll(WebDriver driver,short questionNumber)
    {
        WebElement element = driver.findElement(By.xpath("//div[@id='accordion__panel-"+questionNumber+"']/parent::div"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public String OrderButtonClickBottom (WebDriver driver)
    {
        WebElement element = driver.findElement(By.xpath("//div[@class='Home_FinishButton__1_cWm']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(ORDERBUTTONBOTTOM)));
        driver.findElement(ORDERBUTTONBOTTOM).click();
        return driver.getCurrentUrl();
    }
    public String OrderButtonClickHeader (WebDriver driver)
    {
        driver.findElement(ORDERBUTTONHEADER).click();
        return driver.getCurrentUrl();
    }

}
