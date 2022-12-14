import manager.NGListeners;
import manager.ProviderData;
import models.User;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(NGListeners.class)

public class LoginTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getUser().isLogged())
            app.getUser().logout();
        app.getUser().clickButtonLogo();
    }
    // data transfer object
    @Test(dataProvider = "loginModelDto", dataProviderClass = ProviderData.class)
    public void loginPositiveModelTest(User user){
        logger.info("User: "+ user.toString());
        app.getUser().openLoginForm();
        app.getUser().login(user);
        Assert.assertTrue(app.getUser().isLoggedSucces());
        app.getUser().clickOkButton();
    }
    @Test
    public void loginPositiveTest(){

        User data = new User()
                .withEmail("alexmedqwerty@gmail.com")
                .withPassword("Qwerty12345!");
        //logger.info("loginPositiveTest with email="+data.getEmail()+" password="+data.getPassword());
        app.getUser().openLoginForm();
        app.getUser().login(data);
        System.out.println(app.getUser().isLoggedSucces());
        Assert.assertTrue(app.getUser().isLoggedSucces());
        app.getUser().clickOkButton();
    }

    @Test
    public void loginNegativeTest_WrongLogin_WO_dog(){
        User data = new User()
                .withEmail("alexmedqwertygmail.com")
                .withPassword("Qwerty12345!");
        //logger.info("loginNegativeTest_WrongLogin_WO_dog with email="+data.getEmail()+" password="+data.getPassword());
        app.getUser().openLoginForm();
        app.getUser().filLoginForm(data);
        app.getUser().clickYallaButton();
        Assert.assertFalse(app.getUser().isLogged());
    }

    @Test
    public void loginNegativeTest_WrongPassword(){
        User data = new User()
                .withEmail("alexmedqwerty@gmail.com")
                .withPassword("Q");
        //logger.info("loginNegativeTest_WrongPassword with email="+data.getEmail()+" password="+data.getPassword());
        app.getUser().openLoginForm();
        app.getUser().filLoginForm(data);
        app.getUser().clickYallaButton();
        //app.getUser().pause(3000);
        app.getUser().clickOkButton();
        Assert.assertFalse(app.getUser().isLogged());
    }

    @Test
    public void loginNegativeTest_WO_Registration(){
        //app.getUser().pause(2000);
        User data = new User()
                .withEmail("qwerty@gmail.com")
                .withPassword("QWERTy123!");
        //logger.info("loginNegativeTest_WO_Registration with email="+data.getEmail()+" password="+data.getPassword());
        app.getUser().openLoginForm();
        app.getUser().filLoginForm(data);
        app.getUser().clickYallaButton();

        app.getUser().clickOkButton();//wait in method
        Assert.assertFalse(app.getUser().isLogged());
    }


    @AfterClass
    public void logout(){
        if(app.getUser().isLogged())
            app.getUser().logout();
        app.getUser().clickButtonLogo();
    }
}
