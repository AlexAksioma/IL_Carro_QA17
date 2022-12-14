package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class HelperUser extends HelperBase {

    public HelperUser(WebDriver wd) {
        super(wd);
    }




    public void clickYallaButton() {
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
        clickYallaButton();

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
        WebDriverWait wait = new WebDriverWait(wd, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Ok']")));
        click(By.xpath("//button[text()='Ok']"));
    }

}