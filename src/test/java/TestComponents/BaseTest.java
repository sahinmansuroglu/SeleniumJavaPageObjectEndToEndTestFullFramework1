package TestComponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageObjects.LandingPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
   public WebDriver driver;
   public LandingPage landingPage;
    public WebDriver initalizeDriver() throws IOException {
       // String path=System.getProperty("user.dir")+"src\\main\\resources\\GlobalData.properties";
        String path=System.getProperty("user.dir")+"/src/main/resources/GlobalData.properties";
        Properties prop=new Properties();

        FileInputStream fis=new FileInputStream(path);
        prop.load(fis);
        String browserName=prop.getProperty("browser");

        if(browserName.equalsIgnoreCase("Chrome")){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);

        }else if (browserName.equalsIgnoreCase("firefox") ){
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--start-maximized");
            driver = new FirefoxDriver(options);

        }else if (browserName.equalsIgnoreCase("edge") ) {

            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--start-maximized");
            driver = new EdgeDriver(options);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    @BeforeMethod
    public LandingPage launchApplication() throws IOException {
      driver= initalizeDriver();
      landingPage=new LandingPage(driver);
      landingPage.goTo();
      return landingPage;
    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
