package lk.ijse.carrent.layerd.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Access;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CustomerRentDetailsTM {

    private String id1;

    private String toDate1;

    private String fromDate1;

    private String returnDate1;

    private String carId1;
}
