package models;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder

public class DataFindCar {
    String city;
    String firstDate;
    String secondDate;
}
