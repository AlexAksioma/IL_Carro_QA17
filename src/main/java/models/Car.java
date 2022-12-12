package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder

public class Car {

    private String address;
    private String make;
    private String model;
    private String year;
    private String fuel;
    private String seats;
    private String carClass;
    private String carRegNumber;
    private String price;



}
