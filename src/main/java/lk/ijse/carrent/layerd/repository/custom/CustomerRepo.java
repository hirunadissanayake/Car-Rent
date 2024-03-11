package lk.ijse.carrent.layerd.repository.custom;

import lk.ijse.carrent.layerd.entity.CustomerEntity;
import lk.ijse.carrent.layerd.repository.CrudRepo;

public interface CustomerRepo extends CrudRepo<CustomerEntity,String> {

    Integer addMobile(CustomerEntity customerEntity) throws Exception;
    Integer deleteCus(CustomerEntity customerEntity) throws Exception;
}
