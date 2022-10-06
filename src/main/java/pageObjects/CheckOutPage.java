package pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Optional;

public class CheckOutPage extends AbstractComponent {


    public CheckOutPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(css = ".ta-item span")
    List<WebElement> countryList;

    By countryListBy=By.cssSelector(".ta-item span");

    @FindBy(css = "input[placeholder='Select Country']")
    WebElement countrtyTestBox;

    @FindBy(css = "a.action__submit")
    WebElement submitButton;
    By paymentTitleBy=By.cssSelector(".payment__shipping .payment__title");

    public  void enterCountry(String input, String selectCountry){
        waitForElementToAppear(paymentTitleBy,3);
        countrtyTestBox.sendKeys(input);
        waitForElementToAppear(countryListBy,3);
        Optional<WebElement> turkeyElement = countryList.stream().filter(x -> x.getText().equalsIgnoreCase(selectCountry)).findFirst();
        if (!turkeyElement.equals(Optional.empty())) {
            turkeyElement.get().click();
        } else {
            System.out.println("Turkey Element is not found");
        }
    }
   public ConfirmationPage submitCheckut(){
       submitButton.click();
       return new ConfirmationPage(driver);
   }

}
