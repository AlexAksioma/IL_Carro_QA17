package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    WebDriver wd;

    HelperUser user;

    HelperRegistration registration;

    HelperCar car;

    public void init(){
        wd = new ChromeDriver();
        wd.manage().window().maximize();
        //wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.navigate().to("https://ilcarro.web.app/");
        user = new HelperUser(wd);
        registration = new HelperRegistration(wd);
        car = new HelperCar(wd);
    }

    public HelperCar getCar() {
        return car;
    }

    public HelperUser getUser() {
        return user;
    }

    public HelperRegistration getRegistration() {
        return registration;
    }
}
