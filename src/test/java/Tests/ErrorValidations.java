package Tests;

import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.CheckOutPage;
import pageObjects.ConfirmationPage;
import pageObjects.ProductCatalogue;

import java.io.IOException;

public class ErrorValidations extends BaseTest {

    @Test
    public  void submitOrder() throws IOException, InterruptedException {
        String email="test12@test.com";
        String password="wrong";
        landingPage.login(email,password);
        Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());

    }
}
