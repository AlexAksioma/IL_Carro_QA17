import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getUser().isLogged())
            app.getUser().logout();
    }

    @Test
    public void loginPositiveTest(){
        app.getUser().pause(2000);
        User data = new User()
                .withEmail("alexmedqwerty@gmail.com")
                .withPassword("Qwerty12345!");
        app.getUser().openLoginForm();
        //app.getUser().login(data.getEmail(),data.getPassword());
        app.getUser().login(data);
        app.getUser().pause(2000);

        Assert.assertTrue(app.getUser().isLoggedSucces());
        app.getUser().clickOkButton();
    }

    @Test
    public void loginNegativeTest_WrongLogin_WO_dog(){
        app.getUser().pause(2000);
        User data = new User()
                .withEmail("alexmedqwertygmail.com")
                .withPassword("Qwerty12345!");
        app.getUser().openLoginForm();
        app.getUser().filLoginForm(data);
        app.getUser().clickYallaButton();
        Assert.assertFalse(app.getUser().isLogged());
    }

    @Test
    public void loginNegativeTest_WrongPassword(){
        app.getUser().pause(2000);
        User data = new User()
                .withEmail("alexmedqwerty@gmail.com")
                .withPassword("Q");
        app.getUser().openLoginForm();
        app.getUser().filLoginForm(data);
        app.getUser().clickYallaButton();
        app.getUser().pause(3000);
        app.getUser().clickOkButton();
        Assert.assertFalse(app.getUser().isLogged());
    }

    @Test
    public void loginNegativeTest_WO_Registration(){
        app.getUser().pause(2000);
        User data = new User()
                .withEmail("qwerty@gmail.com")
                .withPassword("QWERTy123!");
        app.getUser().openLoginForm();
        app.getUser().filLoginForm(data);
        app.getUser().clickYallaButton();
        app.getUser().pause(3000);
        app.getUser().clickOkButton();
        Assert.assertFalse(app.getUser().isLogged());
    }


    @AfterMethod
    public void postCondition(){
        if(app.getUser().isLogged())
            app.getUser().logout();
    }
}
