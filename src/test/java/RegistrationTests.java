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
        app.getRegistration().openRegistrationForm();//wait in method 5 sec
        app.getRegistration().filLRegistrationForm(data);

        logger.info("registrationPositiveTest with Email "+ data.getEmail()+" password "+data.getPassword());

        if(!app.getRegistration().checkBox_isPresent())
            app.getRegistration().clickCheckBox_By_Click_Texst();

        app.getRegistration().clickYallaButton();//wait in method 10sec elementToBeClickable

        app.getRegistration().clickOkButton();//wait in method 5sec presenceOfElementLocated/

        Assert.assertTrue(app.getRegistration().isLogged());
    }

    @Test
    public void registrationNegativeTest_Incorrect_Password(){
        User data = new User().withName(app.getRegistration().generateRandomString_a_z(5))
                .withLastName(app.getRegistration().generateRandomString_a_z(10))
                .withEmail(app.getRegistration().generateRandomStringEmail())
                .withPassword(app.getRegistration().generateRandomString_a_z(10));
        logger.info("registrationNegativeTest_Incorrect_Password email="+data.getEmail()
                +" password="+data.getPassword());
        app.getRegistration().openRegistrationForm();
        app.getRegistration().filLRegistrationForm(data);

        app.getRegistration().clickCheckBox_By_Click_Texst();
        Assert.assertTrue(app.getRegistration().isPasswordInvalid_Must_contains());

    }
    @Test
    public void registrationNegativeTest_Email_WO_dog() {//do this as incorrect password
        User data = new User().withName(app.getRegistration().generateRandomString_a_z(5))
                .withLastName(app.getRegistration().generateRandomString_a_z(10))
                .withEmail(app.getRegistration().generateRandomString_a_z(7))
                .withPassword(app.getRegistration().generateRandomPassword());

        logger.info("registrationNegativeTest_Email_WO_dog email="+data.getEmail()
                +" password="+data.getPassword());

        app.getRegistration().openRegistrationForm();
        app.getRegistration().filLRegistrationForm(data);

        if(!app.getRegistration().checkBox_isPresent())
            app.getRegistration().clickCheckBox_By_Click_Texst();
        Assert.assertTrue(app.getRegistration().isEmailWrong_Wrong_email_format());
        Assert.assertFalse(app.getRegistration().buttonYalla_IsEnabled());

    }

    @Test
    public void registrationNegativeTest_with_Registered_data(){
        User data = new User().withName(app.getRegistration().generateRandomString_a_z(5))
                .withLastName(app.getRegistration().generateRandomString_a_z(10))
                .withEmail(app.getRegistration().generateRandomStringEmail())
                .withPassword(app.getRegistration().generateRandomPassword());
        logger.info("registrationNegativeTest_with_Registered_data email="+data.getEmail()
                +" password="+data.getPassword());
        app.getRegistration().openRegistrationForm();
        app.getRegistration().filLRegistrationForm(data);

        if(!app.getRegistration().checkBox_isPresent())
            app.getRegistration().clickCheckBox_By_Click_Texst();

        app.getRegistration().clickYallaButton();

        app.getRegistration().clickOkButton();//wait in method 5sec
        //register new user with data
        app.getRegistration().pause(3000);
        app.getRegistration().logout();

        app.getRegistration().openRegistrationForm();
        app.getRegistration().filLRegistrationForm(data);
        if(!app.getRegistration().checkBox_isPresent())
            app.getRegistration().clickCheckBox_By_Click_Texst();
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
