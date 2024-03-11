package lk.ijse.carrent.layerd.service.custom.impl;

import lk.ijse.carrent.layerd.dto.RentDto;
import lk.ijse.carrent.layerd.entity.CarCategoryEntity;
import lk.ijse.carrent.layerd.entity.CarEntity;
import lk.ijse.carrent.layerd.entity.CustomerEntity;
import lk.ijse.carrent.layerd.entity.RentEntity;
import lk.ijse.carrent.layerd.repository.RepoFactory;
import lk.ijse.carrent.layerd.repository.custom.CarCategoryRepo;
import lk.ijse.carrent.layerd.repository.custom.CarDetailsRepo;
import lk.ijse.carrent.layerd.repository.custom.CustomerRepo;
import lk.ijse.carrent.layerd.repository.custom.RentRepo;
import lk.ijse.carrent.layerd.service.custom.RentService;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class RentServiceImpl implements RentService {
    RentRepo rentRepo = (RentRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoType.RENT);
    CustomerRepo customerRepo = (CustomerRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoType.CUSTOMER);

    CarDetailsRepo carDetailsRepo = (CarDetailsRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoType.CARDETAILS);

    CarCategoryRepo carCategoryRepo = (CarCategoryRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoType.CARCATEGORY);
    @Override
    public String addRent(RentDto rentDto) throws Exception {

        String rentId = rentRepo.carRentId(rentDto.getCarId());
        Boolean isRightCar = true;
        if (rentId == null){
            isRightCar =true;
        }else {

            java.util.Date retunDates = rentRepo.carReturnNull(rentDto.getCarId());
        if(retunDates == null){

           isRightCar = false;

        }else  if (retunDates != null){
            LocalDate returnDate = rentRepo.carReturnDate(rentDto.getCarId()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

           if (rentDto.getFromDate().isAfter(returnDate))
            {
                isRightCar = true;
            }else {
                isRightCar = false;
            }}}

        String custId = rentRepo.custRentId(rentDto.getCustId());
        Boolean isRightCustomer = true;
        if (custId == null){
            isRightCustomer = true;
        }else {
            java.util.Date retunDates = rentRepo.custReturnNull(rentDto.getCustId());

            if (retunDates == null){

                isRightCustomer = false;
            }else if (retunDates != null){

                LocalDate returnDate = rentRepo.custReturnDate(rentDto.getCustId()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if (rentDto.getFromDate().isAfter(returnDate))
                {
                    isRightCustomer = true;
                }else {
                    isRightCustomer = false;}

            }


        }





        if (isRightCustomer == true){

    if(isRightCar == true ){
        LocalDate from = rentDto.getFromDate();
        LocalDate to = rentDto.getToDate();
        Double perDayRent = rentDto.getPerDayRent();
        Double advance = rentDto.getAdvancedPay();

        Long dayDiff = ChronoUnit.DAYS.between(from,to);



        Integer dayDifferent = Math.toIntExact(dayDiff)+1;

        Double total = perDayRent*dayDifferent;

        if (total >= rentDto.getAdvancedPay()) {

            CustomerEntity customerEntity = customerRepo.get(rentDto.getCustId());
            CarEntity carEntity = carDetailsRepo.get(rentDto.getCarId());
            CarCategoryEntity carCategoryEntity = carCategoryRepo.get(carEntity.getCarCategoryEntity().getName());

            RentEntity rentEntity = new RentEntity(rentDto.getId(), rentDto.getPerDayRent(), Date.valueOf(rentDto.getFromDate()), Date.valueOf(rentDto.getToDate()), rentDto.getAdvancedPay(), rentDto.getRefundableDeposit(), rentDto.getUserName(), new CustomerEntity(customerEntity.getId(), customerEntity.getNic(), customerEntity.getName(), customerEntity.getAddress(), customerEntity.getDob(), customerEntity.getUserName()), new CarEntity(carEntity.getId(), carEntity.getBrand(), carEntity.getModel(), carEntity.getYear(), carEntity.getVehicleNumber(), carEntity.getUserName(), carEntity.getPricePerDay(), new CarCategoryEntity(carCategoryEntity.getId(), carCategoryEntity.getName(), carCategoryEntity.getUserName())));

            Integer id = rentRepo.add(rentEntity);
            if (id != -1) {

                return "Successfully Added";
            } else {
                return "Fail Added";
            }
        }else {
            return "Advance pay is Not Valid";

        }}else {

        return "This car is out ";
        }}else {
            return "Customer is not Available this time period";
        }

    }

    @Override
    public RentDto totalAndBalance(RentDto rentDto) throws Exception {
        LocalDate from = rentDto.getFromDate();
        LocalDate to = rentDto.getToDate();
        Double perDayRent = rentDto.getPerDayRent();
        Double advance = rentDto.getAdvancedPay();

        Long dayDiff = ChronoUnit.DAYS.between(from,to);



        Integer dayDifferent = Math.toIntExact(dayDiff)+1;

        Double total = perDayRent*dayDifferent;
        Double balance = total-advance;

        RentDto rentDto1 = new RentDto(total,balance);
        return rentDto1;
    }

    @Override
    public List<RentDto> getAll() throws Exception {
        List<RentEntity> rentEntities = rentRepo.getAll();
        List<RentDto> rentDtos = new ArrayList<>();

        LocalDate dates = null;



        for (RentEntity entity:rentEntities
             ) {
        if(entity.getRetunDate() != null){
            rentDtos.add(new RentDto(entity.getId(),
                    entity.getPerDayRent(),
                    entity.getFromDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                            entity.getToDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                            entity.getAdvancedPay(),
                            entity.getRefundableDeposit(),
                            entity.getRetunDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                            entity.getUserName(),
                            entity.getCustomerEntity().getId(),
                            entity.getCarEntity().getId(),entity.getCarEntity().getVehicleNumber())
                    );}else {
            rentDtos.add(new RentDto(entity.getId(),
                    entity.getPerDayRent(),
                    entity.getFromDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                    entity.getToDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                    entity.getAdvancedPay(),
                    entity.getRefundableDeposit(),
                    dates,
                    entity.getUserName(),
                    entity.getCustomerEntity().getId(),
                    entity.getCarEntity().getId(),entity.getCarEntity().getVehicleNumber()));
        }

        }
        return rentDtos;
    }

    @Override
    public String addReturnDate(RentDto rentDto) throws Exception {
        RentEntity rentEntity = rentRepo.get(rentDto.getId());

        LocalDate from = rentEntity.getFromDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate returnDate= rentDto.getRetunDate();
        Double perDayRent = rentEntity.getPerDayRent();
        Double advance = rentEntity.getAdvancedPay();

        Long dayDiff = ChronoUnit.DAYS.between(from,returnDate);



        Integer dayDifferent = Math.toIntExact(dayDiff)+1;

        Double total = perDayRent*dayDifferent;
        Double balance = total-advance;
        if(rentEntity.getRetunDate() == null){
        RentEntity rent = new RentEntity(rentDto.getId(),Date.valueOf(rentDto.getRetunDate()),total,balance);

        Integer id = rentRepo.updateReturnDate(rent);
        System.out.println(total+balance);
        if (id != -1) {

            return "Successfully Update";
        } else {
            return "Fail Update";
        }}else {
            return "Cant Update return Date";
        }
    }

    @Override
    public RentDto getRentData(String id) throws Exception {
        RentEntity entity = rentRepo.get(id);

        if (entity.getRetunDate() != null) {
            LocalDate fromDate = entity.getFromDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate toDate = entity.getToDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate returnDate = entity.getRetunDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            Long rentDayDiff = ChronoUnit.DAYS.between(fromDate, toDate);
            Long returnDayDiff = ChronoUnit.DAYS.between(toDate, returnDate);


            Integer dayDifferent = Math.toIntExact(rentDayDiff) + 1;
            Integer dayReturnDiff = Math.toIntExact(returnDayDiff);

            Double chargeForRentPeriod = entity.getPerDayRent() * dayDifferent;
            Double chargeForExtra = entity.getPerDayRent() * dayReturnDiff;


            RentDto dto = new RentDto(entity.getId(),
                    entity.getPerDayRent(),
                    fromDate,
                    toDate,
                    entity.getAdvancedPay(),
                    entity.getRefundableDeposit(),
                    returnDate,
                    entity.getTotal(),
                    entity.getBalance(),
                    entity.getUserName(),
                    entity.getCustomerEntity().getId(),
                    chargeForRentPeriod,
                    chargeForExtra,
                    entity.getCarEntity().getId(),
                    entity.getCarEntity().getVehicleNumber(),
                    entity.getCustomerEntity().getName(),
                    entity.getCustomerEntity().getNic(),
                    entity.getCarEntity().getBrand(),
                    entity.getCarEntity().getModel()
            );

            return dto;
        }else {
            return null;
        }



    }

    @Override
    public List<RentDto> getList(String id) throws Exception {
        List<RentEntity> rentEntities = rentRepo.carRentDetails(id);
        List<RentDto> rentDtos = new ArrayList<>();

        for (RentEntity entity:rentEntities
             ) {
            if (entity.getRetunDate() != null) {
                rentDtos.add(new RentDto(entity.getId(), entity.getFromDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        entity.getToDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        entity.getRetunDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        entity.getCustomerEntity().getId(),
                        entity.getCarEntity().getVehicleNumber(),
                        entity.getCarEntity().getBrand(),
                        entity.getCarEntity().getModel()));
            }else {
                rentDtos.add(new RentDto(entity.getId(), entity.getFromDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        entity.getToDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        null,
                        entity.getCustomerEntity().getId() ,
                        entity.getCarEntity().getVehicleNumber(),
                        entity.getCarEntity().getBrand(),
                        entity.getCarEntity().getModel()));
            }
        }

        return rentDtos;

    }

    @Override
    public List<RentDto> getCustomerRentDetailsList(String id) throws Exception {
        List<RentEntity> rentEntities = rentRepo.customerRentDetails(id);
        List<RentDto> rentDtos = new ArrayList<>();

        for (RentEntity entity:rentEntities
             ) {
            if (entity.getRetunDate() != null) {
                rentDtos.add(new RentDto(entity.getId(),
                        entity.getFromDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        entity.getToDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        entity.getRetunDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        entity.getCarEntity().getId(),
                        entity.getCustomerEntity().getName(),
                        entity.getCustomerEntity().getNic()));
            }else {
                rentDtos.add(new RentDto(entity.getId(),
                        entity.getFromDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        entity.getToDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        null,
                        entity.getCarEntity().getId(),
                        entity.getCustomerEntity().getName(),
                        entity.getCustomerEntity().getNic()));

            }
        }
        return rentDtos;
    }

    @Override
    public List<RentDto> getLateReturnList() throws Exception {
        List<RentEntity> rentEntities = rentRepo.getLateReturnList();
        List<RentDto> rentDtos = new ArrayList<>();

        for (RentEntity entity:rentEntities
             ) {


            Long dayDiff = ChronoUnit.DAYS.between(entity.getToDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                    entity.getRetunDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());



            Integer dayDifferent = Math.toIntExact(dayDiff);
            rentDtos.add(new RentDto(entity.getId(),
                    entity.getFromDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                    entity.getToDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                    entity.getRetunDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                    entity.getCustomerEntity().getId(),
                    entity.getCarEntity().getId(),
                    dayDifferent
                    ));
        }
        return rentDtos;
    }


}
