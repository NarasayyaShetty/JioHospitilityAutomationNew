package Test.ProperTest;

import Pages.ContentSettings.ManageContentPage;
import Pages.ContentSettings.MessageAuthority;
import Pages.ContentSettings.NotificationPage;
import Pages.HomePage;
import Pages.SignInPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

public class TestCase02 extends BaseTest{
    //Content settings test, Manage content, Message Authority and Notification Test
    SignInPage sp;
    HomePage hp;
    NotificationPage np;
    public String title;
    MessageAuthority mp;
    ManageContentPage mcp;
    String subPropertyName="narshayya shetti sub";

    @BeforeClass(alwaysRun=true)
    public void setUp(){
        sp=new SignInPage(driver);
        hp=new HomePage(driver);
        np=new NotificationPage(driver);
        mp=new MessageAuthority(driver);
        mcp=new ManageContentPage(driver);
        driver.get("https://preprod.devices.cms.jio.com/jiohotels/users/sign_in");
        sp.makelogin("narasayyashetty497@gmail.com","Test@321");

    }

    @Test(description="Manage content from content settings page", priority=1)
    public void test01(){
        boolean status;
//        driver.get("https://preprod.devices.cms.jio.com/jiohotels/users/sign_in");
//        sp.makelogin("narasayyashetty497@gmail.com","Test@321");
        hp.refreshHomePage();
        hp.selectSubProperty(subPropertyName);
        status=hp.selectDropDown("Content Setting","Manage Content");
        Assert.assertTrue(status,"Sub Property navigation is failed");
        //BootUp video path: D:\AutoItFiles\bootUpVideoUpload.exe
        //Background image path: D:\AutoItFiles\backgroundImageUpload.exe
        //BrandLogo Path: D:\AutoItFiles\BrandLogoUpload.exe
        status=mcp.createNewContent("D:\\AutoItFiles\\bootUpVideoUpload.exe","D:\\AutoItFiles\\backgroundImageUpload.exe","D:\\AutoItFiles\\BrandLogoUpload.exe");
        Assert.assertTrue(status,"Create new content is failed");
    }

    @Test(description="Content Settings, Message from authority...", priority=2)
    public void test02(){
        boolean status;

        hp.refreshHomePage();
        hp.selectSubProperty(subPropertyName);
        status=hp.selectDropDown("Content Setting","Message Authority");
        Assert.assertTrue(status,"SubProperty navigation is failed");

        status=mp.fillMessageFromAuthorityForm("Shetty","D:\\AutoItFiles\\UploadAuthorityImage.exe","Don’t watch the clock; do what it does. Keep going","Best regards Sincerely","Shetty","D:\\AutoItFiles\\uploadSign.exe");
        Assert.assertTrue(status,"Filling message authority is failed");

    }

    @Test(description="Content Settings test, Notification test", priority=3)
    public void test03(){
        boolean status;
        hp.refreshHomePage();
        hp.selectSubProperty(subPropertyName);
        status=hp.selectDropDown("Content Setting","Notification");
        Assert.assertTrue(status,"SubProperty navigation is failed");
        //image path: D:\AutoItFiles\logo\images.jpeg
       //auto it exe file path: D:\AutoItFiles\\uploadLogo.exe
        title=title.format("Test_%s", UUID.randomUUID().toString());


       status= np.createNewNotification(title,"Kirana","Provisional store","D:\\AutoItFiles\\uploadLogo.exe");
       Assert.assertTrue(status,"Logo upload is failed");
    }

    @Test(description="validate bootup video formate, check getting error message on uploading invalid format", priority=4)
    public void test04(){
        boolean status;
        hp.refreshHomePage();
        hp.selectSubProperty(subPropertyName);
        status=hp.selectDropDown("Content Setting","Manage Content");
        Assert.assertTrue(status,"Sub property navigation is failed");
        status=mcp.validateInvalidFormatUploade("D:\\AutoItFiles\\InvalidFormatChecking\\bootupVideoInvalidFormatUpload.exe","D:\\AutoItFiles\\bootUpVideoUpload.exe","D:\\AutoItFiles\\InvalidFormatChecking\\invalidFormatBackgroundInage.exe");

        Assert.assertTrue(status,"Invalid format validation is failed");
    }
}
