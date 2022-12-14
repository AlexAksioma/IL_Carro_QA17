package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        WebDriverWait wait = new WebDriverWait(wd, 5);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        click(By.xpath("//button[@type='submit']"));
    }

    public void clickOkButton(){
        WebDriverWait wait = new WebDriverWait(wd, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//button[text()='Ok']"))));
        click(By.xpath("//button[text()='Ok']"));
    }

    public boolean buttonYalla_IsEnabled() {
        return buttonIsEnabled(By.xpath("//button[@type='submit']"));
    }

    public boolean checkBox_isPresent() {
        WebElement element = wd.findElement(By.id("terms-of-use"));
        System.out.println(element.getAttribute("class"));
        return element.getAttribute("class").equals("ng-dirty ng-valid ng-touched");
    }

    public boolean isRegistrationFailed_User_already_exists() {
        WebDriverWait wait = new WebDriverWait(wd, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".message")));
        return returnContainsElement(By.cssSelector(".message")).contains("User already exists");
    }
}
