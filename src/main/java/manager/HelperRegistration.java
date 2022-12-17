package manager;

import models.User;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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

    public void clickCheckBox_By_Click_Texst(){
        click(By.xpath("(//label[contains(text(),'I agree to the')])[1]"));
        //JavascriptExecutor js = (JavascriptExecutor) wd;
        //js.executeScript("document.querySelector('#terms-of-use').click()");
    }
    public void clickCheckBox_By_JavaScript(){
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("document.querySelector('#terms-of-use').click()");
    }

    public void clickCheckBox_By_Rectangle(){
        Rectangle rectangle = wd.findElement(By.cssSelector(".checkbox-container")).getRect();
        int x = rectangle.getX() + 5;
        int y = rectangle.getY() + 1/4*rectangle.getHeight();
        Actions actions = new Actions(wd);
        actions.moveByOffset(x, y).click().perform();
    }

    public void clickYallaButton(){
        WebDriverWait wait = new WebDriverWait(wd, 10);
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

    public boolean isPasswordInvalid_Must_contains() {
        //WebDriverWait wait = new WebDriverWait(wd, 5);
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".message")));
        return returnContainsElement(By.xpath("//div[@class='error']/div[1]"))
                .contains("Password must contain 1 uppercase letter,");
    }



}
