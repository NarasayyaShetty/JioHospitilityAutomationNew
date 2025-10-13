package Test.ProperTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import static Utils.DriverSingleton.getDriverInstance;

public class BaseTest {
    public WebDriver driver;

    @Parameters({"browserName"})
    @BeforeTest(alwaysRun=true)
    public void config(String browserName){
        driver=getDriverInstance(browserName);
    }
}
