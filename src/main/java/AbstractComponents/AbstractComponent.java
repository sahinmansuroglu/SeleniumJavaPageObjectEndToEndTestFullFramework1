package AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.CartPage;

import java.time.Duration;

public class AbstractComponent {
   protected WebDriver driver;
    public AbstractComponent(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "[routerlink*='cart']")
    WebElement gotoCartPageButton;
    protected void waitForElementToAppear(By byName,int second){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(second));
        wait.until(ExpectedConditions.visibilityOfElementLocated(byName));
    }
    //Todo create Action method  for İframe and Javascript
    protected void waitForWebElementToAppear(WebElement webElement,int second){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(second));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }
    protected void waitForElementToDisappear(By findBy, int second){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(second));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
    }
    protected void waitForWebElementToDisappear(WebElement element, int second){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(second));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public CartPage goToCartPage(){
            gotoCartPageButton.click();
            return new CartPage(driver);
    }

    public  void scroolToWebElement(WebElement webElement) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
        Thread.sleep(500);
    }
}
