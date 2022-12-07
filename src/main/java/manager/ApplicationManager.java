package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationManager {

    WebDriver wd;

    HelperUser user;

    HelperRegistration registration;

    public void init(){
        wd = new ChromeDriver();
        wd.manage().window().maximize();
        wd.navigate().to("https://ilcarro.web.app/");
        user = new HelperUser(wd);
        registration = new HelperRegistration(wd);
    }

    public HelperUser getUser() {
        return user;
    }

    public HelperRegistration getRegistration() {
        return registration;
    }
}
