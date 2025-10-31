package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSingleton {
    private static WebDriver driver = null;

    private DriverSingleton() {
    }

    ;

    public static WebDriver getDriverInstance(String browserName) {
        if (driver == null) {
            switch (browserName) {
                case "chrome":
//                    ChromeOptions option=new ChromeOptions();
//                    option.addArguments("--start-maximized");
//                    option.addArguments("--headless");
//                    option.addArguments("--incognito");
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new RuntimeException("Browser name is not valid");
            }
        }
        driver.manage().window().maximize();
        return driver;
    }
}