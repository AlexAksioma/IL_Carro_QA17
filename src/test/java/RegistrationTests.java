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

        if(!app.getRegistration().checkBox_isPresent())
            app.getRegistration().clickCheckBoxTerms();

        app.getRegistration().clickYallaButton();//wait in method 5sec elementToBeClickable

        app.getRegistration().clickOkButton();//wait in method 5sec presenceOfElementLocated

        Assert.assertTrue(app.getRegistration().isLogged());
    }
    @Test
    public void registrationNegativeTest_Email_WO_dog() {
        User data = new User().withName(app.getRegistration().generateRandomString_a_z(5))
                .withLastName(app.getRegistration().generateRandomString_a_z(10))
                .withEmail(app.getRegistration().generateRandomString_a_z(7))
                .withPassword(app.getRegistration().generateRandomPassword());
        app.getRegistration().openRegistrationForm();
        app.getRegistration().filLRegistrationForm(data);

        if(!app.getRegistration().checkBox_isPresent())
            app.getRegistration().clickCheckBoxTerms();

        Assert.assertFalse(app.getRegistration().buttonYalla_IsEnabled());

    }

    @Test
    public void registrationNegativeTest_with_Registered_data(){
        User data = new User().withName(app.getRegistration().generateRandomString_a_z(5))
                .withLastName(app.getRegistration().generateRandomString_a_z(10))
                .withEmail(app.getRegistration().generateRandomStringEmail())
                .withPassword(app.getRegistration().generateRandomPassword());
        app.getRegistration().openRegistrationForm();
        app.getRegistration().filLRegistrationForm(data);

        if(!app.getRegistration().checkBox_isPresent())
            app.getRegistration().clickCheckBoxTerms();

        app.getRegistration().clickYallaButton();

        app.getRegistration().clickOkButton();//wait in method 5sec
        //register new user with data
        app.getRegistration().pause(3000);
        app.getRegistration().logout();

        app.getRegistration().openRegistrationForm();
        app.getRegistration().filLRegistrationForm(data);
        if(!app.getRegistration().checkBox_isPresent())
            app.getRegistration().clickCheckBoxTerms();
        app.getRegistration().clickYallaButton();

        Assert.assertTrue(app.getRegistration().isRegistrationFailed_User_already_exists());
        //wait in method isR... 5 sec
        app.getRegistration().clickOkButton();


    }


    @AfterMethod
    public void postCondition(){
        //if(app.getRegistration().isLogged())
           // app.getRegistration().logout();
    }
}
