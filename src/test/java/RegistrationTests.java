import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import models.User;

public class RegistrationTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        if(app.getRegistration().isLogged())
            app.getRegistration().logout();
    }

    @Test
    public void registrationPositiveTest(){
        User data = new User().withName(app.getRegistration().generateRandomString_a_z(5))
                .withLastName(app.getRegistration().generateRandomString_a_z(10))
                .withEmail(app.getRegistration().generateRandomStringEmail())
                .withPassword(app.getRegistration().generateRandomPassword());
        app.getRegistration().openRegistrationForm();
        app.getRegistration().filLRegistrationForm(data);
        app.getRegistration().clickCheckBoxTerms();
        app.getRegistration().clickYallaButton();
        //app.getRegistration().pause(3000);
        app.getRegistration().clickOkButton();

        Assert.assertTrue(app.getRegistration().isLogged());
    }

    @AfterMethod
    public void postCondition(){
        if(app.getRegistration().isLogged())
            app.getRegistration().logout();
    }
}
