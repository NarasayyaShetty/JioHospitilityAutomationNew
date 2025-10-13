package Pages.SubProperty;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static Utils.SeleniumUtils.*;

public  class SubProperty {
	
	WebDriver driver;
	
	public SubProperty(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,10),this);
	}
	
	@FindBy(xpath="//ul[contains(@class,'nav mb')]//li[@class='active']/a")
	List<WebElement> menuOptions;
	
	public void selectMenuOption(String menu) {

		for(WebElement option:menuOptions) {
			System.out.println(option.getText());
			if(option.getText().trim().equalsIgnoreCase(menu)) {
				ElementClick(driver,option);
				break;
			}
		}
	}



}
