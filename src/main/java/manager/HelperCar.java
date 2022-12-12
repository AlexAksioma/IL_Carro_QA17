package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperCar extends HelperBase{
    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
        click(By.id("1"));
    }

    public boolean isCarFormPresent(){

        return isElementPresent(By.cssSelector("h1[class='title']"));
    }

    public void fillCarForm(Car car) {
        type(By.id("pickUpPlace"), car.getAddress());
        pause(3000);
        click(By.xpath("//span[text()='Tel Aviv']"));
        //click(By.xpath("//div[contains(text()='Tel Aviv')]"));
        pause(3000);

        type(By.id("make"), car.getMake());
        type(By.id("model"), car.getModel());
        type(By.id("year"), car.getYear());
        //type(By.id("fuel"), car.getFuel());
        click(By.xpath("//option[@value='Electric']"));
        type(By.id("seats"), car.getSeats());

        type(By.id("class"), car.getCarClass());

        click(By.xpath("//label[@for='serialNumber']"));
        type(By.xpath("//input[@id='serialNumber']"), car.getCarRegNumber());

        type(By.id("price"), car.getPrice());


    }

    public void submitCarForm() {
        click(By.xpath("//button[@type='submit']"));
    }

    public void click_AddAnotherCar_Button() {
        click(By.xpath("//div[@class='buttons-container']/button[text()='Add another car']"));
    }


    public boolean buttonSubmit_IsEnabled() {
        return buttonIsEnabled(By.xpath("//button[@type='submit']"));
    }


}
