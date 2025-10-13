package Test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.SubProperty.SubProperty;
import Properties.PropertiesClass;

public class JioHospitility {
	WebDriver driver;
	HomePage hp;
	SubProperty sp;

	@Test(priority = 1)
	public void testcase01() throws IOException {
		ChromeOptions option = new ChromeOptions();
		//option.addArguments("--incognito");
		driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://preprod.devices.cms.jio.com/jiohotels/users/sign_in");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		while (true) {
			String value = (String) js.executeScript("return document.readyState");
			if (value.equals("complete")) {
				System.out.println("Page is fully loaded");
				break;
			}
		}
		PropertiesClass pc = new PropertiesClass();
		System.out.println(pc.getProperty("email"));
		System.out.println(pc.getProperty("password"));

		driver.findElement(By.id("user_email")).sendKeys("narasayyashetty497@gmail.com");
		driver.findElement(By.id("user_password")).sendKeys("Test@321");
		driver.findElement(By.id("send_otp")).click();

		String otp = JOptionPane.showInputDialog(null, "Enter OTP:", "OTP Input", JOptionPane.QUESTION_MESSAGE);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement otpField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_login_otp")));
		otpField.sendKeys(otp);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("verify_otp"))).click();

		driver.findElement(By.id("update_workset")).click();
		// wait.until(ExpectedConditions.urlContains("https://preprod.devices.cms.jio.com/jiohotels"));

	}

	@Test(description = "Navigating to subproperty", priority = 2, enabled = false)
	public void navigationToSubProperty() throws InterruptedException {
		hp = new HomePage(driver);
		hp.selectMenuOption("Manage Property");

	}

	@Test(description = "navigating to subProperty", priority = 3)
	public void subProperty() throws InterruptedException {
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.id("open_Model")).click();

		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchinput")));
		// searchField.sendKeys("");

		List<WebElement> properties = driver
				.findElements(By.xpath("//p[normalize-space(text())='Sub Property']//preceding-sibling::h5"));
		for (WebElement property : properties) {
			if (property.getText().contains("narshayya shetti")) {
				System.out.println(property.getText());
				property.click();
				break;
			}
			// boolean
			// value=properties.stream().anyMatch(property->property.getText().contains("narshayya
			// shetti"));
		}
		Thread.sleep(3000);
		sp = new SubProperty(driver);
		sp.selectMenuOption("STB Mapping");

		//// td[contains(@class,'sorting')]
		/// all the rows values from serial number column
//		WebElement serialNum=driver.findElement(By.xpath("//th[contains(@class,'sorting ')]"));
//		Actions action=new Actions(driver);
//		action.moveToElement(serialNum).click(serialNum).perform();
		List<WebElement> rows = driver.findElements(By.xpath("//td[contains(@class,'sorting')]"));
		// rows.stream().map(row->row.getText()).forEach(System.out::println);

		List<String> originalValues = rows.stream().map(row -> row.getText()).collect(Collectors.toList());

		// Now sort the elements

		List<String> sortedValues = originalValues.stream().sorted().collect(Collectors.toList());

		Assert.assertTrue(originalValues.equals(sortedValues), "Values are miss matched");

		// need to get the STB serial number of selected serial number

		List<String> serialNumbers = rows.stream().filter(s -> s.getText().contains("5")).map(s -> getSerialNumber(s))
				.collect(Collectors.toList());

		serialNumbers.forEach(System.out::println);
	}

	public String getSerialNumber(WebElement s) {
		String serailNumber = s.findElement(By.xpath("following-sibling::td[2]")).getText();
		return serailNumber;
	}

	@Test (description = "Uploading the csv file",priority=4, enabled=true)
	public void uploadCSVFile(){
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		try{
			////label[text()='Choose File']
		driver.findElement(By.xpath("//label[text()='Choose File']")).click();
			Thread.sleep(3000);

            //Adding CSV File
			Runtime.getRuntime().exec("D:\\EclipsTests\\JioCloud\\CSVFiles\\CSVFileUpload.exe");
			Thread.sleep(2000);
			

			WebElement uploadButton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("upload_anchor")));
			uploadButton.click();

            WebElement successPopUp=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h5#noticeModalLabel")));
            Assert.assertTrue(successPopUp.getText().equalsIgnoreCase("Success"),"Assertion is failed due to file upload is failed");

		}catch(Exception e){
			e.printStackTrace();
            WebElement closeButton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2/following-sibling::button[contains(@class,'btn')]")));
            closeButton.click();
		}
	}


	@AfterClass(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
			((JavascriptExecutor) driver).executeScript("window.sessionStorage.clear();");
		}
	}
}
