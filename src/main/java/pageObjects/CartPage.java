package pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class CartPage extends AbstractComponent {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".cart h3")
    List<WebElement> productsListInCartPage;
    @FindBy(xpath = "//button[normalize-space()='Checkout']")
    WebElement checkOutButton;

    public boolean verifyProductDisplay(String productName){
        boolean test = productsListInCartPage.stream().anyMatch(x -> x.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(test, "Product Name : " + productName + " is not available in cart");
        return test;
    }

    public void verifyProductsDisplay(String [] productNameList){
        Arrays.stream(productNameList).forEach(productName -> {
           verifyProductDisplay(productName);
       });
    }

    public CheckOutPage goToCheckOutPage() throws InterruptedException {
        waitForWebElementToAppear(checkOutButton,4);
        scroolToWebElement(checkOutButton);
        checkOutButton.click();
        return new CheckOutPage(driver);
    }
}
