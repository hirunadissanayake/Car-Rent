package lk.ijse.carrent.layerd.service.custom;

import lk.ijse.carrent.layerd.dto.RentDto;
import lk.ijse.carrent.layerd.service.SuperService;

import java.util.List;

public interface RentService extends SuperService {

    String addRent(RentDto rentDto) throws Exception;

    RentDto totalAndBalance(RentDto rentDto) throws Exception;

    List<RentDto> getAll() throws Exception;

    String addReturnDate(RentDto rentDto) throws  Exception;

    RentDto getRentData(String id) throws Exception;

    List<RentDto> getList(String id) throws Exception;

    List<RentDto> getCustomerRentDetailsList(String id) throws Exception;

    List<RentDto> getLateReturnList() throws Exception;
}
