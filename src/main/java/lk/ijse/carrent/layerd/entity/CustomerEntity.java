package lk.ijse.carrent.layerd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class CustomerEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "nic", length = 15, nullable = false, unique = true)
    private String nic;
    @Column(name = "name", length = 55, nullable = false)
    private String name;
    @Column(name = "address", length = 100, nullable = false)
    private String Address;
    @Column(name = "dob",length = 15,nullable = false)
    private String dob;

    @Column(name = "user_name",length = 100,nullable = false)
    private String userName;
    @ElementCollection
    @CollectionTable(
            name = "customer_mobile",

            joinColumns = @JoinColumn(name = "customer_id")
    )
    private List<String> mobil;

    @OneToMany(mappedBy = "customerEntity", targetEntity = RentEntity.class)

    private List<RentEntity> rentEntities;

    public CustomerEntity(String id, String nic, String name, String address, String dob, String userName) {
        this.id = id;
        this.nic = nic;
        this.name = name;
        Address = address;
        this.dob = dob;
        this.userName = userName;
    }

    public CustomerEntity(List<String> mobil) {
        this.mobil = mobil;
    }

    public CustomerEntity(String id, String nic, String name, String address, String dob, String userName, List<String> mobil) {
        this.id = id;
        this.nic = nic;
        this.name = name;
        Address = address;
        this.dob = dob;
        this.userName = userName;
        this.mobil = mobil;
    }

    public CustomerEntity(String id, List<String> mobil) {
        this.id = id;
        this.mobil = mobil;
    }
}
