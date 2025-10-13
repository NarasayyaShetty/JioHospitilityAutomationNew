package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

public class SignInPage {
    public WebDriver driver;

    public SignInPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,10),this);
    }

    @FindBy(id="user_email")
    private WebElement emailField;

    @FindBy(id="user_password")
    private WebElement passwordField;

    @FindBy(id="send_otp")
    private WebElement sendOTPButton;

    @FindBy(id="user_login_otp")
    private WebElement otpField;

    @FindBy(id="verify_otp")
    private WebElement loginButton;

    public void makelogin(String email, String password){
        String otp;
        try{
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
            emailField.sendKeys(email);
            passwordField.sendKeys(password);
            sendOTPButton.click();
            otp=JOptionPane.showInputDialog(null, "Enter OTP:", "OTP Input",JOptionPane.QUESTION_MESSAGE);
            wait.until(ExpectedConditions.visibilityOf(otpField)).sendKeys(otp);

            wait.until(ExpectedConditions.visibilityOf(loginButton)).click();



        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Unable to sign in ");
        }
    }


}
