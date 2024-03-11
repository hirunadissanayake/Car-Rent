package lk.ijse.carrent.layerd.dto;

import lk.ijse.carrent.layerd.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CarCategoryDto {

    private String id;
    private String name;

    public CarCategoryDto(String id) {
        this.id = id;
    }

    public CarCategoryDto(String id, String name, UserEntity userEntity) {
        this.id = id;
        this.name = name;
    }

    private  String userid;
}
