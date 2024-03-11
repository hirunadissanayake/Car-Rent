package lk.ijse.carrent.layerd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDto {
    private String id;

    private String nic;

    private String name;

    private String address;

    private String dob;

    private String userName;

    private List<String> mobil;

    public CustomerDto(String id, String nic, String name, String address, String dob, String userName) {
        this.id = id;
        this.nic = nic;
        this.name = name;
      this.address = address;
        this.dob = dob;
        this.userName = userName;
    }

    public CustomerDto(List<String> mobil) {
        this.mobil = mobil;
    }
}
