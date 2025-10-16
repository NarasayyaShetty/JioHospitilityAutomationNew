package Pages.ContentSettings;

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

public class ManageContentPage {
    public WebDriver driver;

    public ManageContentPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,5),this);
    }
    @FindBy(xpath="//h4[normalize-space(text())='Content Setting']")
    private WebElement contentSettingsText;

    @FindBy(className = "content_setting")
    private List<WebElement> createContent;

    @FindBy(xpath="//a[text()='Create Content']")
    private WebElement createContentButton;

    @FindBy(xpath="//h5[normalize-space(text())='Bootup Video']")
    private WebElement bootupVideoText;

    @FindBy(xpath = "//label[contains(@for,'image')]")
    private WebElement bootupChooseFileButton;

    @FindBy(xpath="//span[text()='Upload Successfull']")
    private WebElement successfullUploadPopUp;

    @FindBy(id="save_anchor")
    private WebElement bootupSaveAndNextButton;

    @FindBy(xpath="//h5[text()='Background Image']")
    private WebElement backgroundImageText;

    @FindBy(xpath="//label[contains(@for,'image_uploaded_ba')]")
    private WebElement backgroundImageUploadButton;

    @FindBy(xpath="//span[text()='Uploaded Successfully']")
    private WebElement backgroundImageSuccessFulPopUp;

    @FindBy(id="brand_logo_image_choose")
    private WebElement brandLogoImageChooseButton;

    @FindBy(xpath="//span[text()='Uploaded Successfully']")
    private WebElement brandImageSuccessfulPopUp;

    @FindBy(id="saveNextBtn")
    private WebElement saveAndNextButton1;

    @FindBy(id="services_")
    private List<WebElement> services;

    @FindBy(xpath="//h4[text()='Select Default Features']")
    private WebElement defaultServicesText;

    @FindBy(id="save_services")
    private WebElement saveServicesButton;

    @FindBy(css="button.btn-save-next ")
    private WebElement saveCreateTheamButton;

    @FindBy(id="content_setting_save_and_finish")
    private WebElement contentScreenSaveButton;

    @FindBy(id="alertContent")
    private WebElement warningBootupUploadPopUp;

    @FindBy(id="alertContent")
    private WebElement invalidBackgroundImagePopUp;

    public boolean createNewContent(String bootupVideoPath, String backgroundImagePath, String brandLogoPath){
        boolean flag=false;
        try{
            WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
            Actions action=new Actions(driver);
            wait.until(ExpectedConditions.visibilityOf(contentSettingsText));
            createContent.get(0).click();
            wait.until(ExpectedConditions.visibilityOf(createContentButton));
            action.moveToElement(createContentButton).click(createContentButton).perform();
            wait.until(ExpectedConditions.visibilityOf(bootupVideoText));
            bootupChooseFileButton.click();
            Thread.sleep(2000);
            //bootup video path: D:\AutoItFiles\bootUpVideoUpload.exe
            Runtime.getRuntime().exec(bootupVideoPath);
            Thread.sleep(2000);

            WebDriverWait wait1=new WebDriverWait(driver,Duration.ofMinutes(2));
            wait1.until(ExpectedConditions.visibilityOf(successfullUploadPopUp));
            wait.until(ExpectedConditions.invisibilityOf(successfullUploadPopUp));
            action.moveToElement(bootupSaveAndNextButton).click(bootupSaveAndNextButton).perform();

            //background image page

            wait.until(ExpectedConditions.visibilityOf(backgroundImageText));
            backgroundImageUploadButton.click();
            Thread.sleep(2000);
            //background image upload path: D:\AutoItFiles\backgroundImageUpload.exe
            Runtime.getRuntime().exec(backgroundImagePath);
            Thread.sleep(2000);

            wait.until(ExpectedConditions.visibilityOf(backgroundImageSuccessFulPopUp));
            wait.until(ExpectedConditions.invisibilityOf(backgroundImageSuccessFulPopUp));

            action.moveToElement(brandLogoImageChooseButton).click(brandLogoImageChooseButton).perform();

            //brand logo path: D:\AutoItFiles\BrandLogoUpload.exe
            Thread.sleep(2000);
            Runtime.getRuntime().exec(brandLogoPath);
            Thread.sleep(2000);

            wait.until(ExpectedConditions.visibilityOf(brandImageSuccessfulPopUp));
            wait.until(ExpectedConditions.invisibilityOf(brandImageSuccessfulPopUp));

            action.moveToElement(saveAndNextButton1).click(saveAndNextButton1).perform();

            wait.until(ExpectedConditions.visibilityOf(defaultServicesText));
            for(WebElement service:services){
                action.moveToElement(service).click(service).perform();
            }

            action.moveToElement(saveServicesButton).click(saveServicesButton).perform();
            wait.until(ExpectedConditions.elementToBeClickable(saveCreateTheamButton));
            action.moveToElement(saveCreateTheamButton).click(saveCreateTheamButton).perform();

            wait1.until(ExpectedConditions.visibilityOf(contentScreenSaveButton));
            action.moveToElement(contentScreenSaveButton).click(contentScreenSaveButton).perform();
            flag=true;


        }catch(Exception e){
            System.out.println("Exception is occurred during new content settings");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean validateInvalidFormatUploade(String invalidBootFilePath, String validBootFilePath, String invalidBackgroundImagePath){
        try{
            boolean flag=false;
            WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
            WebDriverWait wait1=new WebDriverWait(driver,Duration.ofMinutes(2));
            Actions action=new Actions(driver);
            wait.until(ExpectedConditions.visibilityOf(contentSettingsText));
            createContent.get(0).click();
            wait.until(ExpectedConditions.visibilityOf(createContentButton));
            action.moveToElement(createContentButton).click(createContentButton).perform();
            wait.until(ExpectedConditions.visibilityOf(bootupVideoText));
            bootupChooseFileButton.click();
            Thread.sleep(2000);

            //select file: path: D:\AutoItFiles\InvalidFormatChecking\bootupVideoInvalidFormatUpload.exe
            Runtime.getRuntime().exec(invalidBootFilePath);
            String text=wait.until(ExpectedConditions.visibilityOf(warningBootupUploadPopUp)).getText();
            if(text.equalsIgnoreCase("Please Select Correct File Format")){
                System.out.println(warningBootupUploadPopUp.getText());
                flag=true;
            }
            wait.until(ExpectedConditions.invisibilityOf(warningBootupUploadPopUp));
            bootupChooseFileButton.click();
            Thread.sleep(2000);
            //valid File path: D:\AutoItFiles\bootUpVideoUpload.exe
            Runtime.getRuntime().exec(validBootFilePath);
            wait1.until(ExpectedConditions.visibilityOf(successfullUploadPopUp));
            wait.until(ExpectedConditions.invisibilityOf(successfullUploadPopUp));
            action.moveToElement(bootupSaveAndNextButton).click(bootupSaveAndNextButton).perform();
            wait.until(ExpectedConditions.visibilityOf(backgroundImageText));
            backgroundImageUploadButton.click();
            //InvalidFormat of background image path: D:\AutoItFiles\InvalidFormatChecking\invalidFormatBackgroundInage.exe
            Thread.sleep(2000);
            Runtime.getRuntime().exec(invalidBackgroundImagePath);

            String warningMessage=wait.until(ExpectedConditions.visibilityOf(invalidBackgroundImagePopUp)).getText();
            if(warningMessage.equalsIgnoreCase("Please upload an image with resolution should be 1920x1080.")){
                flag=true;
            }
            wait.until(ExpectedConditions.invisibilityOf(invalidBackgroundImagePopUp));

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Exception is occurred during the validating invalid format image/video");
        }
        return true;
    }
}
