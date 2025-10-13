package Utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtils {
	
	public static void ElementClick(WebDriver driver,WebElement element) {
		try {
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(element)).click();
		}catch(Exception e) {
			System.out.println("Exception occured in clicking element");
			e.printStackTrace();
		}
	}

    public static String splitingPropertyName(String s){
        String[] str=s.split("»");
        return str[1].trim();
    }

}
