package Pages.GuestDining;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

public class OrderHistoryPage {
    WebDriver driver;

    public OrderHistoryPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,5),this);
    }

    @FindBy(xpath = "//a[contains(@class,' class1')]")
    private List<WebElement> orderHistoryOptions;


    public boolean selectOption(String optionName){
        boolean flag=false;
        try{
            for(WebElement option:orderHistoryOptions){
                if(option.getText().equalsIgnoreCase(optionName)){
                    System.out.println("Option Name is "+option.getText());
                    option.click();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Exception is occurred during selecting the order history option");
        }

        return flag;
    }


}
