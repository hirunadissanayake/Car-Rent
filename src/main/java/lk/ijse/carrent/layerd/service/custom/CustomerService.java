package lk.ijse.carrent.layerd.service.custom;

import lk.ijse.carrent.layerd.dto.CustomerDto;
import lk.ijse.carrent.layerd.service.SuperService;

import java.util.List;

public interface CustomerService extends SuperService {
    String addCustomer(CustomerDto customerDto) throws Exception;

    List<CustomerDto> getAllCustomer() throws Exception;

    CustomerDto searchCustomer(String id)throws Exception;

    String updateCustomer(CustomerDto customerDto) throws Exception;



    String delete(CustomerDto customerDto) throws Exception;
}
