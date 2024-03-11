package lk.ijse.carrent.layerd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data

public class RentDto {
    private String id;

    private Double perDayRent;

    private LocalDate fromDate;

    private LocalDate toDate;

    private Double advancedPay;

    private Double refundableDeposit;

    private LocalDate retunDate;

    private Double total;

    private Double balance;

    private String userName;

    private String custId;

    private Double chargingForRentPeriod;

    private Double chargingForExtra;

    private String carId;

    private String vehicleNumber;

    private String custName;

    private String custNic;

    private String carBrand;

    private String carModel;

    private Integer days;


    public RentDto(String id, Double perDayRent, LocalDate fromDate, LocalDate toDate, Double advancedPay, Double refundableDeposit, String userName, String custId, String carId) {
        this.id = id;
        this.perDayRent = perDayRent;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.advancedPay = advancedPay;
        this.refundableDeposit = refundableDeposit;
        this.userName = userName;
        this.custId = custId;
        this.carId = carId;
    }

    public RentDto(Double perDayRent, LocalDate fromDate, LocalDate toDate, Double advancedPay) {
        this.perDayRent = perDayRent;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.advancedPay = advancedPay;
    }

    public RentDto(Double total, Double balance) {
        this.total = total;
        this.balance = balance;
    }

    public RentDto(String id, Double perDayRent, LocalDate fromDate, LocalDate toDate, Double advancedPay, Double refundableDeposit, LocalDate retunDate, String userName, String custId, String carId,String vehicleNumber) {
        this.id = id;
        this.perDayRent = perDayRent;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.advancedPay = advancedPay;
        this.refundableDeposit = refundableDeposit;
        this.retunDate = retunDate;
        this.userName = userName;
        this.custId = custId;
        this.carId = carId;
        this.vehicleNumber = vehicleNumber;
    }

    public RentDto(String id, LocalDate retunDate) {
        this.id = id;
        this.retunDate = retunDate;
    }

    public RentDto(String id, LocalDate fromDate, LocalDate toDate, LocalDate retunDate, String custId, String vehicleNumber, String carBrand, String carModel) {
        this.id = id;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.retunDate = retunDate;
        this.custId = custId;
        this.vehicleNumber = vehicleNumber;
        this.carBrand = carBrand;
        this.carModel = carModel;
    }

    public RentDto(String id, LocalDate fromDate, LocalDate toDate, LocalDate retunDate, String carId, String custName, String custNic) {
        this.id = id;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.retunDate = retunDate;
        this.carId = carId;
        this.custName = custName;
        this.custNic = custNic;
    }

    public RentDto(String id, LocalDate fromDate, LocalDate toDate, LocalDate retunDate, String custId, String carId, Integer days) {
        this.id = id;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.retunDate = retunDate;
        this.custId = custId;
        this.carId = carId;
        this.days = days;
    }

    public RentDto(String id, Double perDayRent, LocalDate fromDate, LocalDate toDate, Double advancedPay, Double refundableDeposit, LocalDate retunDate, Double total, Double balance, String userName, String custId, Double chargingForRentPeriod, Double chargingForExtra, String carId, String vehicleNumber, String custName, String custNic, String carBrand, String carModel) {
        this.id = id;
        this.perDayRent = perDayRent;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.advancedPay = advancedPay;
        this.refundableDeposit = refundableDeposit;
        this.retunDate = retunDate;
        this.total = total;
        this.balance = balance;
        this.userName = userName;
        this.custId = custId;
        this.chargingForRentPeriod = chargingForRentPeriod;
        this.chargingForExtra = chargingForExtra;
        this.carId = carId;
        this.vehicleNumber = vehicleNumber;
        this.custName = custName;
        this.custNic = custNic;
        this.carBrand = carBrand;
        this.carModel = carModel;
    }
}
