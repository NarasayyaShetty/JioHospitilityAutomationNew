package Test.ProperTest;

import Pages.GuestDining.InRoomDiningPage;
import Pages.HomePage;
import org.testng.annotations.BeforeClass;

import Pages.SignInPage;
import org.testng.annotations.Test;


public class JioHospitilityTest extends BaseTest {
    SignInPage sp;
    HomePage hp;
    InRoomDiningPage ip;

    @BeforeClass(alwaysRun=true)
    public void setUp(){
        sp=new SignInPage(driver);
        hp=new HomePage(driver);
        ip=new InRoomDiningPage(driver);
    }

    @Test(description="JioHospitality Test")
    public void testcase(){
        driver.get("https://preprod.devices.cms.jio.com/jiohotels/users/sign_in");
        sp.makelogin("narasayyashetty497@gmail.com","Test@321");
        hp.refreshHomePage();
        //Narshayya shetti » narshayya shetti sub
        //Priyanka Preprod | Priyanka For PREPROD, Bangalore HSR
        //Priyanka Preprod » Priyanka For PREPROD
        //Priyanka IPL » Priyanka IPL Pune
        //Take the subproperty name from Choose Hotel Screen.
        hp.selectSubProperty("Priyanka IPL Pune");
        hp.selectDropDown("Guest Dining","In Room Dining");
       // hp.selectMenuOption("Guest Dining");
       // hp.selectSubMenuOptions("In Room Dining");
       // hp.selectMenuOption("Welcome Letter");
        ip.selectMeal("lunch");
        ip.addNewMeal("Dosa",
                "Dosa is a thin, crispy South Indian pancake made from fermented rice and lentil batter, served with chutney and sambar. It’s light, tasty, and a popular breakfast or meal choice.",
                "Dosa mainly contains rice, urad dal (black gram), salt, and water. Some variations may include fenugreek seeds for flavor and better fermentation",
                "Dosa may cause allergies in people sensitive to lentils (urad dal) or gluten (if cross-contaminated). It’s usually gluten-free, but those with legume allergies should be cautious.",
                "Veg",
                "1000",
                "D:\\AutoItFiles\\uploadImage.exe"
                );
    }


}
