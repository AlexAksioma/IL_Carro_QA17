import models.User;
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
        User data = new User()
                .withEmail("alexmedqwerty@gmail.com")
                .withPassword("Qwerty12345!");
        app.getUser().openLoginForm();
        //app.getUser().login(data.getEmail(),data.getPassword());
        app.getUser().login(data);
        app.getUser().pause(2000);
        app.getUser().clickOkButton();
    }

    @AfterMethod
    public void postCondition(){
        if(app.getUser().isLogged())
            app.getUser().logout();
    }
}
