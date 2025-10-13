package Test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class TestCase01 {
	
	@Test
	public void testcase01() {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.jiocloud.com/");
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		
		WebElement phoneNumberField=driver.findElement(By.id("jioPhoneNumber"));
		phoneNumberField.sendKeys("8880022497");
		
		WebElement signInButton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='jioPhoneNumber']//following-sibling::button")));
		
		Actions action=new Actions(driver);
		action.moveToElement(signInButton).click(signInButton).perform();
		
		String otp=JOptionPane.showInputDialog(null, "Enter OTP:", "OTP Input", JOptionPane.QUESTION_MESSAGE);
		System.out.println(otp);
		
		wait.until(ExpectedConditions.urlContains("/signin"));
		for(int i=1;i<=6;i++) {
		WebElement otpField=driver.findElement(By.id("digit"+i));
		char c=otp.charAt(i-1);
		otpField.sendKeys(c+"");
		}
		
		WebElement continueButton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Continue']")));
		action.moveToElement(continueButton).click(continueButton).perform();
		
	}

}
