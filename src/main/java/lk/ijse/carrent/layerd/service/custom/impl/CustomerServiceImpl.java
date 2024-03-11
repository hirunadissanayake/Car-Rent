package lk.ijse.carrent.layerd.service.custom.impl;

import lk.ijse.carrent.layerd.dto.CustomerDto;
import lk.ijse.carrent.layerd.entity.CustomerEntity;
import lk.ijse.carrent.layerd.repository.RepoFactory;
import lk.ijse.carrent.layerd.repository.custom.CustomerRepo;
import lk.ijse.carrent.layerd.service.custom.CustomerService;

import java.util.ArrayList;
import java.util.List;


public class CustomerServiceImpl implements CustomerService {

    CustomerRepo customerRepo = (CustomerRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoType.CUSTOMER);

    @Override
    public String addCustomer(CustomerDto customerDto) throws Exception {

        if (!customerDto.getId().equals("") && !customerDto.getNic().equals("")){
        CustomerEntity customerEntity = new CustomerEntity(customerDto.getId(),customerDto.getNic(),customerDto.getName(),customerDto.getAddress(),customerDto.getDob(),customerDto.getUserName(),customerDto.getMobil());

        Integer id = customerRepo.add(customerEntity);


        if (id != -1) {

            return "Successfully Added";
        } else {
            return "Fail Added";
        }}
        else {
            return "Wrong values";
        }
    }

    @Override
    public List<CustomerDto> getAllCustomer() throws Exception {
        List<CustomerEntity> customerEntities = customerRepo.getAll();
        List<CustomerDto>customerDtos = new ArrayList<>();

        for (CustomerEntity entity:customerEntities
             ) {
            customerDtos.add(new CustomerDto(entity.getId(),entity.getNic(),entity.getName(),entity.getAddress(),entity.getDob(), entity.getUserName(),entity.getMobil()));
        }
        return customerDtos;
    }

    @Override
    public CustomerDto searchCustomer(String id) throws Exception {
        CustomerEntity customerEntity = customerRepo.get(id);
        CustomerDto customerDto = new CustomerDto(customerEntity.getId(),customerEntity.getNic(),customerEntity.getName(),customerEntity.getAddress(),customerEntity.getDob(),customerEntity.getUserName(),customerEntity.getMobil());

        return customerDto;
    }

    @Override
    public String updateCustomer(CustomerDto customerDto) throws Exception {
        if (!customerDto.getId().equals("") && !customerDto.getNic().equals("")) {
            CustomerEntity customerEntity = new CustomerEntity(customerDto.getId(),
                    customerDto.getNic(),
                    customerDto.getName(),
                    customerDto.getAddress(),
                    customerDto.getDob(),
                    customerDto.getUserName(),
                    customerDto.getMobil());

            Integer id = customerRepo.update(customerEntity);


            if (id != -1) {
                return " Update Success";
            } else {
                return "Fail Update";
            }
        }else {
            return "Wrong values";
        }
    }



    @Override
    public String delete(CustomerDto customerDto) throws Exception {

        CustomerEntity customerEntity = new CustomerEntity(customerDto.getId(),
                customerDto.getNic(),
                customerDto.getName(),
                customerDto.getAddress(),
                customerDto.getDob(),
                customerDto.getUserName(),
                customerDto.getMobil());


        Integer ids = customerRepo.deleteCus(customerEntity);

        if (ids != -1){

            return "Delete Success";
        }else {
            return "Delete Fail";
        }



    }
}
