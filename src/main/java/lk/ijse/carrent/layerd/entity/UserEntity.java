package lk.ijse.carrent.layerd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data




public class UserEntity {
    @Id

    @Column(name = "id")

    private String id;

    @Column(name = "user_name", nullable = false, length = 100, unique = true)

    private String userName;

    @Column(name = "name", nullable = false, length = 100)
    private String name;



    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false, unique = true, length = 100)
    private String password;






}
