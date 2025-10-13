package Pages.GuestDining;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InRoomDiningPage {
    WebDriver driver;

    public InRoomDiningPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,5),this);
    }

    @FindBy(xpath="//a[starts-with(@id,'category_name')]")
    private List<WebElement> mealType;

    @FindBy(className="part-icon-media")
    private WebElement addButton;

    @FindBy(xpath="//h2[normalize-space(text())='Add New Dish']")
    private WebElement addNewDishText;

    @FindBy(xpath="//label[@for='image_uploaded_image']")
    private WebElement chooseFileButton;

    @FindBy(id="dish_name")
    private WebElement dishNameField;

    @FindBy(id="dish_desc")
    private WebElement dishDescriptionField;

    @FindBy(id="dish_contains")
    private WebElement dishContainsField;

    @FindBy(id="dish_allergies")
    private WebElement dishAllergiesField;

    @FindBy(id="dish_price")
    private WebElement dishPriceField;

    @FindBy(name = "Save")
    private WebElement saveButton;



    public void addNewMeal(String name,String description,String contains,String allergies,String mealType,String dishPrice,String path){
        try{
            //Veg, Non-Veg
            Actions action=new Actions(driver);
            WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
            addButton.click();
            wait.until(ExpectedConditions.textToBePresentInElement(addNewDishText,"Add New Dish"));
            chooseFileButton.click();
            Thread.sleep(2000);
            //Code for upload file using AutoIT
            Runtime.getRuntime().exec(path);
            Thread.sleep(5000);

            dishNameField.sendKeys(name);
            dishDescriptionField.sendKeys(description);
            dishContainsField.sendKeys(contains);
            dishAllergiesField.sendKeys(allergies);
            driver.findElement(By.xpath("//label[normalize-space(text())='"+mealType+"']")).click();
            dishPriceField.sendKeys(dishPrice);
            Thread.sleep(3000);
            action.moveToElement(saveButton).click(saveButton).perform();


        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Exception is occurred while adding new dish");
        }
    }

    public void selectMeal(String meal){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        try{
            wait.until(ExpectedConditions.visibilityOfAllElements(mealType));
            for(WebElement eachMeal:mealType){
                if(eachMeal.getText().trim().equalsIgnoreCase(meal)){
                    System.out.println(eachMeal.getText());
                    eachMeal.click();
                    break;
                }

            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Exception is occurred during selecting the meal");
        }
    }
}
