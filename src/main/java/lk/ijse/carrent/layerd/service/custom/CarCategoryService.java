package lk.ijse.carrent.layerd.service.custom;

import lk.ijse.carrent.layerd.dto.CarCategoryDto;
import lk.ijse.carrent.layerd.dto.UserDto;
import lk.ijse.carrent.layerd.service.SuperService;

import java.util.List;

public interface CarCategoryService extends SuperService {

    String addCarCategory(CarCategoryDto carCategoryDto) throws Exception;
    List<CarCategoryDto> getAll()throws Exception;

    String update(CarCategoryDto carCategoryDto) throws Exception;

    String delete(CarCategoryDto carCategoryDto) throws Exception;

    CarCategoryDto search(String id) throws Exception;
}
