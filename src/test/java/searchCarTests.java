import models.DataFindCar;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class searchCarTests extends TestBase{ //class works
    @BeforeMethod
    public void precondition(){
        app.getDataSearchCar().clickSearchButton();

    }

    @Test
    public void searchCarPositiveTest_inputDateManual(){
        DataFindCar dataFindCar = DataFindCar.builder()
                .city("Haifa")
                .firstDate("11/1/2023")
                .secondDate("11/15/2023")
                .build();
        app.getDataSearchCar().inputCity(dataFindCar.getCity());
        //date format mm/dd/yyyy-mm/dd/yyyy
        app.getDataSearchCar().inputDatesManual(dataFindCar.getFirstDate(), dataFindCar.getSecondDate());
        //app.getDataSearchCar().clickYallaButton();
        Assert.assertTrue(app.getDataSearchCar().isElementPresent_ListFoundCars());
    }

    /*@Test
    public void searchCarPositiveTest_inputDateCurrentMonth(){
        DataFindCar dataFindCar = DataFindCar.builder()
                .city("Haifa")
                .firstDate("1/10/2023")
                .secondDate("1/25/2023")
                .build();
        app.getDataSearchCar().inputCity(dataFindCar.getCity());
        //date format mm/dd/yyyy-mm/dd/yyyy
        app.getDataSearchCar().inputDatesCurrentMonth(dataFindCar.getFirstDate(), dataFindCar.getSecondDate());
        app.getDataSearchCar().clickYallaButton();
        Assert.assertTrue(app.getDataSearchCar().isElementPresent_ListFoundCars());
    }*/

    /*@Test
    public void searchCarPositiveTest_inputDateCurrentYear(){
        DataFindCar dataFindCar = DataFindCar.builder()
                .city("Haifa")
                .firstDate("2/10/2023")
                .secondDate("11/25/2023")
                .build();
        app.getDataSearchCar().inputCity(dataFindCar.getCity());
        //date format mm/dd/yyyy-mm/dd/yyyy
        app.getDataSearchCar().inputDatesCurrentYear(dataFindCar.getFirstDate(), dataFindCar.getSecondDate());
        app.getDataSearchCar().clickYallaButton();
        Assert.assertTrue(app.getDataSearchCar().isElementPresent_ListFoundCars());
    }*/

    @Test
    public void searchCarPositiveTest_inputAnyDate() {
        DataFindCar dataFindCar = DataFindCar.builder()
                .city("Haifa")
                .firstDate("1/10/2023")
                .secondDate("1/2/2024")
                .build();
        app.getDataSearchCar().inputCity(dataFindCar.getCity());
        app.getDataSearchCar().inputAnyDate(dataFindCar.getFirstDate(), dataFindCar.getSecondDate());
        app.getDataSearchCar().clickYallaButton();
        Assert.assertTrue(app.getDataSearchCar().isElementPresent_ListFoundCars());
    }

    @AfterMethod
    public void afterMethod(){
        app.getDataSearchCar().clickSearchButton();
        app.getDataSearchCar().clearSearchForm();
    }
}
