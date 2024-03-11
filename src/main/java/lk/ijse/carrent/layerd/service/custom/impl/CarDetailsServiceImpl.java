package lk.ijse.carrent.layerd.service.custom.impl;

import lk.ijse.carrent.layerd.dto.CarDetailsDto;
import lk.ijse.carrent.layerd.entity.CarCategoryEntity;
import lk.ijse.carrent.layerd.entity.CarEntity;
import lk.ijse.carrent.layerd.repository.RepoFactory;
import lk.ijse.carrent.layerd.repository.custom.CarCategoryRepo;
import lk.ijse.carrent.layerd.repository.custom.CarDetailsRepo;
import lk.ijse.carrent.layerd.service.custom.CarDetailsSrevice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class CarDetailsServiceImpl implements CarDetailsSrevice {
    static Integer year = 1970;
CarCategoryRepo carCategoryRepo = (CarCategoryRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoType.CARCATEGORY);
CarDetailsRepo carDetailsRepo = (CarDetailsRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoType.CARDETAILS);
    @Override
    public String addCar(CarDetailsDto carDetailsDto) throws Exception {
        LocalDate localDate = LocalDate.now();
        Integer date = localDate.getYear();
        if (carDetailsDto.getYear()>year && carDetailsDto.getYear()<(date+1)){
         CarCategoryEntity carCategoryEntity = new CarCategoryEntity();
         carCategoryEntity.setName(carDetailsDto.getCarCategoryName());

         carCategoryEntity = carCategoryRepo.get(carCategoryEntity.getName());

       CarEntity carEntity = new CarEntity(carDetailsDto.getId(),carDetailsDto.getBrand(),carDetailsDto.getModel(),carDetailsDto.getYear(),carDetailsDto.getVehicleNumber(),carDetailsDto.getUserName(), carDetailsDto.getPricePerDay(), new CarCategoryEntity(carCategoryEntity.getId(),carCategoryEntity.getName(),carCategoryEntity.getUserName()));
        Integer id = carDetailsRepo.add(carEntity);

        if(id != -1){
            return " Success Added";
        }else {
            return "Fail Added";
        }}else {

            return "Car Year is Not Valid";
        }
    }

    @Override
    public List<CarDetailsDto> getAll() throws Exception {
       List<CarEntity> carEntities = carDetailsRepo.getAll();
       List<CarDetailsDto> carDetailsDtos = new ArrayList<>();

        for (CarEntity entity:carEntities
             ) {
            carDetailsDtos.add(new CarDetailsDto(entity.getId(), entity.getCarCategoryEntity().getId(), entity.getUserName(), entity.getBrand(), entity.getModel(), entity.getYear(), entity.getVehicleNumber(), entity.getPricePerDay(), entity.getCarCategoryEntity().getName()));

        }
        return carDetailsDtos;
    }

    @Override
    public CarDetailsDto search(String id) throws Exception {
        CarEntity entity = carDetailsRepo.get(id);

        CarDetailsDto carDetailsDto = new CarDetailsDto(entity.getId(), entity.getCarCategoryEntity().getId(), entity.getUserName(), entity.getBrand(), entity.getModel(), entity.getYear(), entity.getVehicleNumber(), entity.getPricePerDay(), entity.getCarCategoryEntity().getName());

     return carDetailsDto;
    }

    @Override
    public String update(CarDetailsDto carDetailsDto) throws Exception {

        LocalDate localDate = LocalDate.now();
        Integer date = localDate.getYear();
        if (carDetailsDto.getYear()>year && carDetailsDto.getYear()<(date+1)){
        CarCategoryEntity carCategoryEntity = new CarCategoryEntity();
        carCategoryEntity.setName(carDetailsDto.getCarCategoryName());

        carCategoryEntity = carCategoryRepo.get(carCategoryEntity.getName());

        CarEntity carEntity = new CarEntity(carDetailsDto.getId(),carDetailsDto.getBrand(),carDetailsDto.getModel(),carDetailsDto.getYear(),carDetailsDto.getVehicleNumber(),carDetailsDto.getUserName(), carDetailsDto.getPricePerDay(), new CarCategoryEntity(carCategoryEntity.getId(),carCategoryEntity.getName(),carCategoryEntity.getUserName()));
        Integer id = carDetailsRepo.update(carEntity);

        if(id != -1){
            return " Update Success";
        }else {
            return "Fail Update";
        }}else {
            return "Car Year is Not Valid";
        }
    }

    @Override
    public String delete(String id) throws Exception {


        Integer ids = carDetailsRepo.delete(id);

        if (ids != -1){

            return "Delete Success";
        }else {
            return "Delete Fail";
        }
    }

    @Override
    public List<String> getBrand(String id) throws Exception {
        List<String> carBrand = carDetailsRepo.getCarBrand(id);
        return carBrand;
    }

    @Override
    public List<String> getCarModel(String carCategoryId, String brand) throws Exception {
      List<String> carModel = carDetailsRepo.getCarModel(carCategoryId,brand);
      return  carModel;

    }

    @Override
    public List<CarDetailsDto> getCarAll(CarDetailsDto carDetailsDto) throws Exception {
        List<CarEntity>carEntities = carDetailsRepo.getCarAll(carDetailsDto.getCarCategoryId(),carDetailsDto.getBrand(),carDetailsDto.getModel());
        List<CarDetailsDto> carDetailsDtos = new ArrayList<>();

        for (CarEntity entity:carEntities
             ) {
            carDetailsDtos.add(new CarDetailsDto(entity.getId(),entity.getUserName(),entity.getYear(),entity.getVehicleNumber(),entity.getPricePerDay()));
        }
        return carDetailsDtos;
    }
}

