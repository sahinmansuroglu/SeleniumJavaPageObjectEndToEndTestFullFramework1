package Tests;

import TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.*;

import java.io.IOException;
import java.time.Duration;

public class SubmitOrderTest extends BaseTest {

    @Test
    public  void submitOrder() throws IOException, InterruptedException {
        String email="test12@test.com";
        String password="Test1234";
        String[] productNameList = {"ZARA COAT 3", "ADIDAS ORIGINAL", "IPHONE 13 PRO"};
        String[] productNameList1 = {"ZARA COAT 3", "ADIDAS ORIGINAL", "IPHONE 13 PRO"};
        ProductCatalogue productCatalogue=landingPage.login(email,password);
        productCatalogue.getProductList();
        productCatalogue.addProductsToChart(productNameList);
        CartPage cartPage=productCatalogue.goToCartPage();
        cartPage.verifyProductsDisplay(productNameList1);
        CheckOutPage checkOutPage= cartPage.goToCheckOutPage();
        checkOutPage.enterCountry("tur","Turkey");
        ConfirmationPage orderComplePage=checkOutPage.submitCheckut();
        String succesMessage=orderComplePage.getSuccesText();
        Assert.assertTrue(succesMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));


    }
}
