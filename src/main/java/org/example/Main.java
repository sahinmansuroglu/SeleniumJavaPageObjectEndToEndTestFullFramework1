package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import pageObjects.*;

import java.time.Duration;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver;
        WebDriverManager.chromedriver().setup();

    ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
    driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));





    String email="test12@test.com";
    String password="Test1234";
    String[] productNameList = {"ZARA COAT 3", "ADIDAS ORIGINAL", "IPHONE 13 PRO"};
    String[] productNameList1 = {"ZARA COAT 3", "ADIDAS ORIGINAL", "IPHONE 13 PRO"};
    LandingPage landingPage=new LandingPage(driver);
        landingPage.goTo();
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
        driver.quit();

}
}