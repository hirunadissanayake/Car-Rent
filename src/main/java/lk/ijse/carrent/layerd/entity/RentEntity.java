package lk.ijse.carrent.layerd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rent")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class RentEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "per_Day_Rent", nullable = false)
    private Double perDayRent;
    @Column(name = "from_Date", columnDefinition = "Date", nullable = false)
    private Date fromDate;
    @Column(name = "to_Date", columnDefinition = "Date", nullable = false)
    private Date toDate;
    @Column(name = "advance_dpay", nullable = false)
    private Double advancedPay;
    @Column(name = "refundable_Deposit", nullable = false)
    private Double refundableDeposit;
    @Column(name = "return_Date", columnDefinition = "Date")
    private Date retunDate;
    @Column(name = "total")
    private Double total;
    @Column(name = "balance")
    private Double balance;
    @Column(name = "user_name",length = 100,nullable = false)
    private String userName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cust_id", nullable = false)
    private CustomerEntity customerEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", nullable = false)
    private CarEntity carEntity;

    public RentEntity(String id, Double perDayRent, Date fromDate, Date toDate, Double advancedPay, Double refundableDeposit, String userName, CustomerEntity customerEntity, CarEntity carEntity) {
        this.id = id;
        this.perDayRent = perDayRent;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.advancedPay = advancedPay;
        this.refundableDeposit = refundableDeposit;
        this.userName = userName;
        this.customerEntity = customerEntity;
        this.carEntity = carEntity;
    }

    public RentEntity(String id, Date retunDate, Double total, Double balance) {
        this.id = id;
        this.retunDate = retunDate;
        this.total = total;
        this.balance = balance;
    }

    public RentEntity(String id, Date fromDate, Date toDate, Date retunDate, CustomerEntity customerEntity, CarEntity carEntity) {
        this.id = id;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.retunDate = retunDate;
        this.customerEntity = customerEntity;
        this.carEntity = carEntity;
    }
}
