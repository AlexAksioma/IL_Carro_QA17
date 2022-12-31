package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    //WebDriver wd;
    EventFiringWebDriver wd;

    HelperUser user;

    HelperRegistration registration;

    HelperCar car;

    String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init(){
        //wd = new ChromeDriver();
        if(browser.equals(BrowserType.CHROME))
            wd = new EventFiringWebDriver(new ChromeDriver());
        else if(browser.equals(BrowserType.FIREFOX))
            wd = new EventFiringWebDriver(new FirefoxDriver());
        wd.register(new MyListener());
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
