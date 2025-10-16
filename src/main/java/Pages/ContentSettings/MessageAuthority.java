package Pages.ContentSettings;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MessageAuthority {
    public WebDriver driver;

    public MessageAuthority(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,5),this);
    }
    @FindBy(className="page-title")
    private WebElement messageAuthorityText;

    @FindBy(id="authority_name")
    private WebElement authorityName;

    @FindBy(xpath="//label[@for='fileInput']")
    private WebElement uploadImage;

    @FindBy(xpath="//label[@for='fileInput1']")
    private WebElement uploadSignature;

    @FindBy(id="write_msg")
    private WebElement writeMessageField;

    @FindBy(id="sign_grtng")
    private WebElement signatureGreetingsField;

    @FindBy(id="sign_text")
    private WebElement signTextField;

    @FindBy(id="add_user")
    private WebElement addUserButton;

    @FindBy(xpath="//img[@alt='delete']")
    private WebElement deleteAutgorityIcon;

    @FindBy(id="delete-msg-header")
    private WebElement deleteAuthorityText;

    @FindBy(xpath="//button[normalize-space(text())='Delete']")
    private WebElement deleteButton;

    @FindBy(xpath="//span[text()='Successfully deleted']")
    private WebElement successfullyDeletePopUp;

    @FindBy(css="span.remove-image")
    private WebElement removeImage1;

    @FindBy(className="remove-image1")
    private WebElement removeImage2;

    @FindBy(xpath="//span[text()='Successfully Created']")
    private WebElement successToastMessage;




    public boolean fillMessageFromAuthorityForm(String name,String imagePath, String writeMessage, String signatureGreeting, String signText, String signPath){
        boolean flag=false;

        try{
            WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(8));
            //image path: D:\AutoItFiles\UploadAuthorityImage.exe
            Actions action=new Actions(driver);
            wait.until(ExpectedConditions.textToBePresentInElement(messageAuthorityText,"Message from Authority"));
            messageAuthorityText.click();
            authorityName.sendKeys(name);
            uploadImage.click();
            Thread.sleep(2000);
            Runtime.getRuntime().exec(imagePath);
            Thread.sleep(14000);

            writeMessageField.sendKeys(writeMessage);
            signatureGreetingsField.sendKeys(signatureGreeting);
            signTextField.sendKeys(signText);

            uploadSignature.click();
            Thread.sleep(2000);

            //sign path: D:\AutoItFiles\\uploadSign.exe
            Runtime.getRuntime().exec(signPath);
            Thread.sleep(14000);
          //  wait.until(ExpectedConditions.visibilityOf(removeImage2));
            action.moveToElement(addUserButton).click(addUserButton).perform();
            wait.until(ExpectedConditions.invisibilityOf(successToastMessage));
            flag=true;

        }catch(Exception e){
            System.out.println("form fill message From catch block");
            WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(deleteAutgorityIcon)).click();
            wait.until(ExpectedConditions.visibilityOf(deleteAuthorityText));
            deleteButton.click();
            wait.until(ExpectedConditions.invisibilityOf(successfullyDeletePopUp));
            return fillMessageFromAuthorityForm(name,imagePath,writeMessage,signatureGreeting,signText,signPath);
        }
        return flag;
    }


}
