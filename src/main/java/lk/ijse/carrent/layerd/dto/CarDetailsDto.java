package lk.ijse.carrent.layerd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CarDetailsDto {
    private String id;

    private String carCategoryId;

    private String userName;

    private String brand;

    private String model;

    private Integer year;

    public CarDetailsDto(String carCategoryId, String brand, String model) {
        this.carCategoryId = carCategoryId;
        this.brand = brand;
        this.model = model;
    }

    public CarDetailsDto(String id, String userName, Integer year, String vehicleNumber, Double pricePerDay) {
        this.id = id;
        this.userName = userName;
        this.year = year;
        this.vehicleNumber = vehicleNumber;
        this.pricePerDay = pricePerDay;
    }

    private String vehicleNumber;

    private Double pricePerDay;

    private String CarCategoryName;

    public CarDetailsDto(String id) {
        this.id = id;
    }
}
