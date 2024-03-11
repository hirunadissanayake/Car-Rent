package lk.ijse.carrent.layerd.service.custom;

import lk.ijse.carrent.layerd.dto.CarCategoryDto;
import lk.ijse.carrent.layerd.dto.CarDetailsDto;
import lk.ijse.carrent.layerd.service.SuperService;

import java.util.List;

public interface CarDetailsSrevice extends SuperService {
    String addCar(CarDetailsDto carDetailsDto) throws Exception;

    List<CarDetailsDto> getAll() throws Exception;

    CarDetailsDto search(String id) throws Exception;

    String update(CarDetailsDto carDetailsDto) throws Exception;

    String delete(String id) throws Exception;

    List<String> getBrand(String id) throws Exception;

    List<String> getCarModel(String carCategoryId,String brand) throws Exception;

    List<CarDetailsDto> getCarAll(CarDetailsDto carDetailsDto) throws Exception;

}
