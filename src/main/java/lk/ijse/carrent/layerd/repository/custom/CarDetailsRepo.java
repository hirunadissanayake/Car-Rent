package lk.ijse.carrent.layerd.repository.custom;

import lk.ijse.carrent.layerd.entity.CarEntity;
import lk.ijse.carrent.layerd.repository.CrudRepo;

import java.util.List;

public interface CarDetailsRepo extends CrudRepo<CarEntity,String> {

    List<String> getCarBrand(String id) throws Exception;
    List<String> getCarModel(String carCategoryId,String brand) throws Exception;

    List<CarEntity> getCarAll(String id,String brand,String model) throws Exception;

}
