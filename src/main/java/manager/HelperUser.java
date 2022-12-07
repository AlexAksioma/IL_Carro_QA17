package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class HelperUser extends HelperBase {

    public HelperUser(WebDriver wd) {
        super(wd);
    }




    public void submitLogin() {
        click(By.xpath("//button[@type='submit']"));
    }

    public void submitRegistration() {
        click(By.xpath("//button[@name='registration']"));
    }

    //public boolean isLogged() {
        //return isElementPresent(By.xpath("//a[text()=' Logout ']"));
   // }

    //public void logout() {
        //click(By.xpath("//a[text()=' Logout ']"));
    //}

    public void login(User data) {
        filLoginForm(data);
        submitLogin();

    }

    public void openLoginForm() {
        click(By.xpath("//a[@href='/login?url=%2Fsearch']"));
    }

    public void filLoginForm(String email, String password) {
        type(By.id("email"), email);
        type(By.id("password"), password);
    }

    public void filLoginForm(User data) {
        type(By.id("email"), data.getEmail());
        type(By.id("password"), data.getPassword());
    }

    public void clickOkButton(){
        click(By.xpath("//button[text()='Ok']"));
    }

}