package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;

public class HelperSearchCar extends HelperBase{

    public HelperSearchCar(WebDriver wd) {
        super(wd);
    }

    public void clickSearchButton() {
        click(By.xpath("//a[@href='/search']"));
    }

    public void inputCity(String city) {
        type(By.cssSelector("#city"), city);
        pause(2000);
        click(By.xpath("//span[text()='"+city+"']"));
        //clickCity_By_Rectangle();
    }

    public void clickCity_By_Rectangle(){
        Rectangle rectangle = wd.findElement(By.cssSelector("#city")).getRect();
        System.out.println("x= "+rectangle.getX());
        System.out.println("Y= "+rectangle.getY());
        System.out.println(rectangle.getWidth());
        System.out.println(rectangle.getHeight());
        int x = rectangle.getX() + rectangle.getWidth()/2;
        int y = rectangle.getY() + rectangle.getHeight()*3/2;
        Actions actions = new Actions(wd);
        actions.moveByOffset(x, y).click().perform();
    }

    public void inputDatesManual(String firstDate, String secondDate) {
        //date format mm/dd/yyyy-mm/dd/yyyy
        //click(By.id("dates"));
        type(By.id("dates"),firstDate+"-"+secondDate);
        wd.findElement(By.id("dates")).sendKeys(Keys.ENTER);
    }

    public boolean isElementPresent_ListFoundCars() {
        new WebDriverWait(wd, 8).until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='cars-container ng-star-inserted']")));
        return isElementPresent(By.xpath("//div[@class='cars-container ng-star-inserted']"));
    }

    public void clickYallaButton() {
        click(By.cssSelector("button[type='submit']"));
    }

    public void inputDatesCurrentMonth(String firstDate, String secondDate) {//works with first month, year == second month, year
        //click(By.id("dates"));
        //clear(By.id("dates"));
        int nowMonth = LocalDate.now().getMonthValue();
        System.out.println(numberMonth(firstDate));
        for(int i=nowMonth;i<numberMonth(firstDate);i++){
            click(By.xpath("//button[@aria-label='Next month']"));
        }
        click(By.xpath("//td[@aria-label="+"'"+reformateDate(firstDate)+"'"+"]"));
        click(By.xpath("//td[@aria-label="+"'"+reformateDate(secondDate)+"'"+"]"));

    }

    private String reformateDate(String dateInput){// mm/dd/yyyy  --->   January 5, 2023
        String dateOutput="";
        String[] datePart = dateInput.split("/");
        switch (datePart[0]){
            case "1": dateOutput="January";
                break;
            case "2": dateOutput="February";
                break;
            case "3": dateOutput="March";
                break;
            case "4": dateOutput="April";
                break;
            case "5": dateOutput="May";
                break;
            case "6": dateOutput="June";
                break;
            case "7": dateOutput="July";
                break;
            case "8": dateOutput="August";
                break;
            case "9": dateOutput="September";
                break;
            case "10": dateOutput="October";
                break;
            case "11": dateOutput="November";
                break;
            case "12": dateOutput="December";
                break;
        }
        dateOutput = dateOutput+" "+datePart[1]+", "+datePart[2];

        return dateOutput;
    }

    public void inputDatesCurrentYear(String firstDate, String secondDate) {//works with first year == second year, but first month != second month
        int nowMonth = LocalDate.now().getMonthValue();
        System.out.println(numberMonth(firstDate));
        for(int i=nowMonth;i<numberMonth(firstDate);i++){
            click(By.xpath("//button[@aria-label='Next month']"));
        }
        click(By.xpath("//td[@aria-label="+"'"+reformateDate(firstDate)+"'"+"]"));
        System.out.println(numberMonth(secondDate));
        pause(1000);
        if(numberMonth(firstDate)<numberMonth(secondDate)){
            for(int i=numberMonth(firstDate);i<numberMonth(secondDate);i++){
                click(By.xpath("//button[@aria-label='Next month']"));
            }
        }else if(numberMonth(firstDate)>numberMonth(secondDate))
            for(int i=numberMonth(secondDate);i<numberMonth(firstDate);i++){
                click(By.xpath("//button[@aria-label='Previous month']"));
            }
        click(By.xpath("//td[@aria-label="+"'"+reformateDate(secondDate)+"'"+"]"));
    }

    private int numberMonth(String dateInput) { //try catch
        String[] datePart = dateInput.split("/");
        try {
            int i = Integer.parseInt(datePart[0]);
            return i;
        }
        catch (NumberFormatException nfe)
        {
            System.out.println("NumberFormatException: " + nfe.getMessage());
        }
        return 0;
    }

    public void clearSearchForm() {
        clear(By.cssSelector("#city"));
        //pause(3000);
        click(By.id("dates"));
        wd.findElement(By.id("dates")).sendKeys(Keys.CONTROL,"a");
        wd.findElement(By.id("dates")).sendKeys(Keys.DELETE);
        wd.findElement(By.id("dates")).sendKeys(Keys.ESCAPE);

    }

    public void inputAnyDate(String firstDate, String secondDate) {// mm/dd/yyyy  --->   January 5, 2023
        int nowYear = LocalDate.now().getYear();
        int nowMonth = LocalDate.now().getMonthValue();
        int nowDate = LocalDate.now().getDayOfMonth();
        System.out.println(nowDate+" "+nowMonth+" "+nowYear);
        click(By.id("dates"));//???
        String[] firstDateArray = firstDate.split("/");
        String[] secondDateArray = secondDate.split("/");

        if(firstDateArray[0].equals(secondDateArray[0])
                &&firstDateArray[2].equals(secondDateArray[2]))     //first month, year == second month, year
            inputDatesCurrentMonth(firstDate, secondDate);
        else if(firstDateArray[2].equals(secondDateArray[2]))       //first year == second year but first month != second month
            inputDatesCurrentYear(firstDate, secondDate);
        else                                                        //first year != second year
            inputDatesDifferentYear(firstDate, secondDate);
    }

    private void inputDatesDifferentYear(String firstDate, String secondDate) {//first year != second year
        int nowYear = LocalDate.now().getYear();
        int nowMonth = LocalDate.now().getMonthValue();
        int nowDate = LocalDate.now().getDayOfMonth();
        String[] firstDateArray = firstDate.split("/");
        String[] secondDateArray = secondDate.split("/");
        int firstMonth = Integer.parseInt(firstDateArray[0]);
        int secondMonth = Integer.parseInt(secondDateArray[0]);
        int quantityClicks = 12;
        if(firstMonth>secondMonth)
            quantityClicks = quantityClicks - firstMonth + secondMonth;
        else if(firstMonth<secondMonth)
            quantityClicks = quantityClicks - secondMonth + firstMonth;
        System.out.println(quantityClicks);

        if(Integer.parseInt(firstDateArray[2])>Integer.parseInt(secondDateArray[2])){
            click(By.xpath("//button[@aria-label='Choose month and year']"));
            click(By.xpath(String.format("//div[normalize-space()='%s']", firstDateArray[2])));
            click(By.xpath(String.format("//div[normalize-space()='%s']",reformatMonth_3char(firstDateArray[0]))));
            click(By.xpath(String.format("//div[normalize-space()='%s']",firstDateArray[1])));//click first date
            for(int i=0; i<quantityClicks;i++)
                click(By.xpath("//button[@aria-label='Previous month']"));
            pause(1000);
            click(By.xpath("//td[@aria-label="+"'"+reformateDate(secondDate)+"'"+"]"));//click second date
        }else {
            for(int i=0;i<firstMonth-nowMonth;i++){
                click(By.xpath("//button[@aria-label='Next month']"));
            }
            click(By.xpath("//td[@aria-label="+"'"+reformateDate(firstDate)+"'"+"]"));//click first date
            pause(1000);
            for(int i=0;i<quantityClicks;i++){
                click(By.xpath("//button[@aria-label='Next month']"));
            }
            click(By.xpath("//td[@aria-label="+"'"+reformateDate(secondDate)+"'"+"]"));//click second date
        }
    }

    private String reformatMonth_3char(String numberMonth) {
        String dateOutput="";
        switch (numberMonth){
            case "1": dateOutput="JAN";
                break;
            case "2": dateOutput="FEB";
                break;
            case "3": dateOutput="MAR";
                break;
            case "4": dateOutput="APR";
                break;
            case "5": dateOutput="MAY";
                break;
            case "6": dateOutput="JUN";
                break;
            case "7": dateOutput="JUL";
                break;
            case "8": dateOutput="AUG";
                break;
            case "9": dateOutput="SEP";
                break;
            case "10": dateOutput="OCT";
                break;
            case "11": dateOutput="NOV";
                break;
            case "12": dateOutput="DEC";
                break;
        }
        return dateOutput;
    }


}
