package pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;

public class ProductCatalogue extends AbstractComponent {

     public ProductCatalogue(WebDriver driver){
            super(driver);
    }

    @FindBy(css = ".mb-3")
    List<WebElement> productsList;
    By productBy=By.cssSelector(".mb-3");


    @FindBy(css = ".ng-animating")
    WebElement ngAnimating;
    @FindBy(css = "#toast-container")
    WebElement toastContainer;

    By cardButtonBY=By.cssSelector(".card-body button:last-of-type");
    By cardTitleBY=By.cssSelector("b");
    By toastContainerBY=By.cssSelector("#toast-container");
    By ngAnimatingBY=By.cssSelector(".ng-animating");
    By ngAnimatingBYSpin=By.cssSelector(".ngx-spinner-overlay");

    public  WebElement getProductByName(String productName){
        WebElement searchedProduct = getProductList().stream().filter(product ->
                product.findElement(cardTitleBY).getText().equals(productName)
        ).findFirst().orElse(null);
        return  searchedProduct;
    }

    public  void addProductToChart(String  productName){
           if (getProductByName(productName)!=null){
                getProductByName(productName).findElement(cardButtonBY).click();
                waitForElementToAppear(toastContainerBY,5);
                waitForElementToDisappear(toastContainerBY,5);
            }else {
               try {
                   throw  new Exception("The product as "+productName+ " is not found..");
               } catch (Exception e) {
                   throw new RuntimeException(e);
               }
           }


    }

    public  void addProductsToChart(String [] productNameList){
        Arrays.stream(productNameList).forEach(productName -> {
            addProductToChart(productName);

        });

    }
    public List<WebElement> getProductList(){
        waitForElementToAppear(productBy,10);
        return productsList;
    }

}
