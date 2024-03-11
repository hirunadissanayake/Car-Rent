package lk.ijse.carrent.layerd.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor

@Data

public class RentDetailsTm {

    private String id;

    private String toDate;

    private String fromDate;

    private String returnDate;

    private String custId;





    public RentDetailsTm(String id, String toDate, String fromDate, String returnDate, String custId) {
        this.id = id;
        this.toDate = toDate;
        this.fromDate = fromDate;
        this.returnDate = returnDate;
        this.custId = custId;
    }



}
