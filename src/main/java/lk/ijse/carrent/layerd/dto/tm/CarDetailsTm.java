package lk.ijse.carrent.layerd.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CarDetailsTm {
    private String id;

    private String carCategoryId;

    private String brand;



    private String model;

    private String vehicleNumber;

    private Double pricePerDay;


}
