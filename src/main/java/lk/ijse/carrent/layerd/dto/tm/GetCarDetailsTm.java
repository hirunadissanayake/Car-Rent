package lk.ijse.carrent.layerd.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class GetCarDetailsTm {
    private String id;

    private Integer year;

    private String vehicleNumber;

    private Double pricePerDay;
}
