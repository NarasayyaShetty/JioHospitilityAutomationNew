package Pages.ContentSettings;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NotificationPage {
    public WebDriver driver;

    public NotificationPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,5),this);
    }
    @FindBy(xpath="//h5[text()='Create New']")
    private WebElement createNewButton;

    @FindBy(xpath="//p[normalize-space(text())='Notification']")
    private WebElement notifiactionText;

    @FindBy(id="alert_title")
    private WebElement alertTitle;

    @FindBy(id="alert_subtitle")
    private WebElement aleartSubTitle;

    @FindBy(id="textarea-not")
    private WebElement descriptionField;

    @FindBy(css="label.image_upload_label")
    private WebElement uploadLogoButton;

    @FindBy(xpath="//span[text()='Upload Success']")
    private WebElement uploadSuccessToastMessage;

    @FindBy(id="upload_alert")
    private WebElement publishButton;

    @FindBy(xpath="//span[contains(text(),'Notification Added')]")
    private WebElement notifiactionAddedSuccessfullPopUp;

    public boolean createNewNotification(String title,String subTitle,String description,String path){
        boolean flag=false;
        try{
            WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
            Actions action=new Actions(driver);
            createNewButton.click();
            wait.until(ExpectedConditions.visibilityOf(notifiactionText));
            alertTitle.sendKeys(title);
            aleartSubTitle.sendKeys(subTitle);
            descriptionField.sendKeys(description);
            action.moveToElement(uploadLogoButton).click(uploadLogoButton).perform();
            Thread.sleep(2000);
            //upload logo
            //D:\AutoItFiles\logo\images.jpeg
            //auto it exe file path: D:\AutoItFiles\
            Runtime.getRuntime().exec(path);
            wait.until(ExpectedConditions.visibilityOf(uploadSuccessToastMessage));
            System.out.println(uploadSuccessToastMessage.getText());
           // Thread.sleep(3000);
           wait.until(ExpectedConditions.invisibilityOf(uploadSuccessToastMessage));
            action.moveToElement(publishButton).click(publishButton).perform();
            wait.until(ExpectedConditions.visibilityOf(notifiactionAddedSuccessfullPopUp));
            System.out.println(notifiactionAddedSuccessfullPopUp.getText());
            flag=true;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Exception is occurred during creating the new notification");
        }
        return flag;
    }



}
