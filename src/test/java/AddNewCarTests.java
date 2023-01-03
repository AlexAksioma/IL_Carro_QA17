import models.Car;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewCarTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (!app.getUser().isLogged()) {
            app.getUser().openLoginForm();
            app.getUser().login(new User()
                    .withEmail("alexmedqwerty@gmail.com")
                    .withPassword("Qwerty12345!"));
            app.getUser().pause(2000);
            app.getUser().clickOkButton();
        }
    }


    @Test
    public void addNewCarPositiveTest() {
        Car car = Car.builder()
                .address("Tel Aviv, Israel")
                .make("Ford")
                .model("Fiesta")
                .year("2007")
                .fuel("Diesel")
                .seats("4")
                .carClass("c")
                .carRegNumber("12-"+app.getCar().generateRandomStringNumber(3)+"-"+
                        app.getCar().generateRandomStringNumber(2))
                .price("200")
                .build();
        logger.info(car.toString());
        app.getCar().openCarForm();
        app.getCar().isCarFormPresent();
        app.getCar().fillCarForm(car);
        app.getCar().pause(3000);
        app.getCar().addCarPhoto("C:/Automation_testing/IL_Carro_QA17/src/test/resources/yacht.jpg");
        app.getCar().pause(3000);
        app.getCar().submitCarForm();
        app.getCar().pause(3000);

        //app.getCar().click_AddAnotherCar_Button();

    }

    @Test
    public void addNewCarNegativeTest_CarWithIdentical_carRegNumber() {
        Car car = Car.builder()
                .address("Tel Aviv, Israe")
                .make("Ford")
                .model("Fiesta")
                .year("2007")
                .fuel("Diesel")
                .seats("4")
                .carClass("c")
                .carRegNumber("12-"+app.getCar().generateRandomStringNumber(3)+"-77")
                .price("200")
                .build();
        logger.info("data first car "+car.toString());
        String carRegNumber = car.getCarRegNumber();
        app.getCar().openCarForm();
        app.getCar().isCarFormPresent();
        app.getCar().fillCarForm(car);
        app.getCar().pause(3000);
        app.getCar().submitCarForm();//add new car with number carRegNumber

        app.getCar().click_AddAnotherCar_Button();
        app.getCar().pause(3000);

        car = Car.builder()
                .address("Tel Aviv, Isr")
                .make("Ford")
                .model("Focus")
                .year("2022")
                .fuel("Diesel")
                .seats("5")
                .carClass("s")
                .carRegNumber(carRegNumber)
                .price("200")
                .build();
        logger.info("data second car "+car.toString());
        app.getCar().fillCarForm(car);
        app.getCar().pause(3000);

        System.out.println(app.getCar().buttonIsEnabled(By.xpath("//button[@type='submit']")));
        Assert.assertTrue(app.getCar().buttonSubmit_IsEnabled());
        //app.getCar().submitCarForm();//add new car with number identical carRegNumber

    }
    @AfterMethod
    public void afterTestClickLogo(){
        app.getUser().clickButtonLogo();
    }
    /*@AfterClass
    public void logout(){
        if(app.getUser().isLogged())
            app.getUser().logout();
        app.getUser().clickButtonLogo();
    }*/
}
