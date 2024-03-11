package lk.ijse.carrent.layerd.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class RentCarDetailsTm {

    private String id;

    private LocalDate toDate;

    private String retunDate;

    private String userName;

    private String custId;

    private String carId;

   private String vehicleNumber;




}
