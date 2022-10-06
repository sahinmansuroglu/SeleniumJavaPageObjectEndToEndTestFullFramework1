package pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmationPage extends AbstractComponent {
    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "h1.hero-primary")
    WebElement succesText;
    public String getSuccesText(){
        waitForWebElementToAppear(succesText,3);
        return succesText.getText();
    }
}
