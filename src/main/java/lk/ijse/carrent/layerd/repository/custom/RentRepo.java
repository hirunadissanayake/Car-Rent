package lk.ijse.carrent.layerd.repository.custom;

import lk.ijse.carrent.layerd.entity.RentEntity;
import lk.ijse.carrent.layerd.repository.CrudRepo;

import java.util.Date;
import java.util.List;

public interface RentRepo extends CrudRepo<RentEntity,String> {

    Integer updateReturnDate(RentEntity rent) throws Exception;
    Date carReturnDate(String id) throws Exception;

    Date carReturnNull(String id) throws Exception;

    String carRentId(String id) throws Exception;
    String custRentId(String id) throws Exception;

    Date custReturnNull(String id) throws Exception;

    Date custReturnDate(String id) throws Exception;

    List<RentEntity> carRentDetails(String id) throws Exception;

    List<RentEntity> customerRentDetails(String id) throws Exception;

    List<RentEntity> getLateReturnList() throws Exception;


}
