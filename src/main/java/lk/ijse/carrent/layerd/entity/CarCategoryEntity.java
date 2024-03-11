package lk.ijse.carrent.layerd.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "car_category")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class CarCategoryEntity {
    public CarCategoryEntity(String id, String name, String userName) {
        this.id = id;
        this.name = name;
        this.userName = userName;
    }

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name", length = 55, nullable = false, unique = true)
    private String name;

    @Column(name = "user_name",length = 100,nullable = false)
    private String userName;

    public CarCategoryEntity(String id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "carCategoryEntity", targetEntity = CarEntity.class)
    private List<CarEntity> carEntities;

    public CarCategoryEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }




}
