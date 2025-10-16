package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static Utils.SeleniumUtils.*;

/**
 * Represents the Home Page of the application.
 * Provides methods to interact with the menu options on the dashboard.
 */
public class HomePage {

	WebDriver driver;

	/**
	 * Constructor to initialize HomePage with WebDriver.
	 * Uses AjaxElementLocatorFactory for dynamic element loading.
	 *
	 * @param driver the WebDriver instance to interact with the browser
	 */
	public HomePage(WebDriver driver) {
		this.driver = driver;
		// Initialize web elements with a timeout of 10 seconds
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
	}

	/**
	 * List of menu options available on the dashboard navigation.
	 * Located by class name 'dashboard-nav-item'.
	 */
    @FindBy(css="a.brand-logo")
    private WebElement brandLogo;

	@FindBy(className = "dashboard-nav-item")
	private List<WebElement> menuOptions;

    @FindBy(css="h6.hotel-name")
    private WebElement locationLink;

    @FindBy(className="welcome-title")
    private WebElement chooseHotelText;

    @FindBy(xpath="//p[normalize-space(text())='Sub Property']/preceding-sibling::h5")
    private List<WebElement> allSubproperty;

    @FindBy(className="dashboard-nav-item")
    private List<WebElement> subMenuOptions;

    @FindBy(css="li#dropdown")
    private List<WebElement> dropDowns;

    public boolean selectDropDown(String dropDownName,String subMenu){
        boolean flag=false;
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
        try{
            for(WebElement dropdown:dropDowns){
                if(dropdown.getText().trim().equalsIgnoreCase(dropDownName)){
                    dropdown.click();
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@id='dropdown']//a[text()='"+subMenu+"']"))).click();
                    flag=true;
                }
            }

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Exception is occured during clicking the dropdown");
        }
        return flag;
    }

    public void selectSubMenuOptions(String subMenuName){
        try{
            WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfAllElements(subMenuOptions));
            for(WebElement subMenu:subMenuOptions){
                if(subMenu.getText().trim().equalsIgnoreCase(subMenuName)){
                    subMenu.click();
                    break;
                }
            }

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Exception is occurred during selecting the sub menu option");
        }
    }

    public void selectSubProperty(String propertyName){
        try{
            WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
            Actions action=new Actions(driver);
            locationLink.click();
            wait.until(ExpectedConditions.visibilityOf(chooseHotelText));
            for(WebElement fullPropertyName:allSubproperty){
                String subProperty=splitingPropertyName(fullPropertyName.getText());
                if(subProperty.equalsIgnoreCase(propertyName)){
                    //fullPropertyName.click();
                    action.moveToElement(fullPropertyName).click(fullPropertyName).perform();
                    break;
                }

            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void refreshHomePage(){
        try{
            brandLogo.click();
            Thread.sleep(3000);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

	/**
	 * Selects a menu option by its visible name.
	 * Clicks the first menu option that matches the provided name (case-insensitive).
	 *
	 * @param menuName the name of the menu option to select
	 */



	public void selectMenuOption(String menuName) {
		for (WebElement option : menuOptions) {
			// Compare menu option text with the provided name (ignoring case and whitespace)
			if (option.getText().trim().equalsIgnoreCase(menuName)) {
				// Click the matching menu option using a utility method
				ElementClick(driver, option);
				break;// Exit loop after clicking the desired option
			}
		}
	}
}