package Utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtils {
	
	public static void ElementClick(WebDriver driver,WebElement element) {
		try {
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(element)).click();
		}catch(Exception e) {
			System.out.println("Exception occured in clicking element");
			e.printStackTrace();
		}
	}

    public static String splitingPropertyName(String s){
        String[] str=s.split("»");
        return str[1].trim();
    }

    public static void tacksScreenShotes(WebDriver driver){
        try{
            Calendar c=Calendar.getInstance();
            SimpleDateFormat sdf=new SimpleDateFormat("ddMMyyyy");
            Date date=c.getTime();
            String currentDate=sdf.format(date);
            String path=System.getProperty("user.dir")+ File.separator+"Results"+File.separator+"ScreenShots"+File.separator+currentDate;

            File file=new File(path);
            if(!file.exists()){
                file.mkdirs();
            }
            TakesScreenshot ts=(TakesScreenshot)driver;
            File srcFile=ts.getScreenshotAs(OutputType.FILE);
            String fileName=String.format("Test_%s_.png", UUID.randomUUID().toString());

            File desFile=new File(path+File.separator+fileName);

            FileUtils.copyFile(srcFile,desFile);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Exception is occurred during taking the screenshot");
        }
    }

}
