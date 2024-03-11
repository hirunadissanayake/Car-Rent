package lk.ijse.carrent.layerd.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class LateReturnCarTm {

    private String id3;

    private LocalDate fromDate3;

    private LocalDate toDate3;

    private String retunDate3;

    private String custId3;

    private String carId3;

    private Integer lateDays3;
}
