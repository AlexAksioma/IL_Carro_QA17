package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperRegistration extends HelperBase{

    public HelperRegistration(WebDriver wd) {
        super(wd);
    }

    public void openRegistrationForm() {
        click(By.xpath("//a[@href='/registration?url=%2Fsearch']"));
    }

    public void filLRegistrationForm(User data) {
        type(By.xpath("//input[@id='name']"), data.getName());
        type(By.xpath("//input[@id='lastName']"), data.getLastName());
        type(By.xpath("//input[@id='email']"), data.getEmail());
        type(By.xpath("//input[@id='password']"), data.getPassword());

    }

    public void clickCheckBoxTerms(){
        click(By.xpath("(//label[contains(text(),'I agree to the')])[1]"));
    }

    public void clickYallaButton(){
        click(By.xpath("//button[@type='submit']"));
    }

    public void clickOkButton(){
        click(By.xpath("//button[text()='Ok']"));
    }
}
