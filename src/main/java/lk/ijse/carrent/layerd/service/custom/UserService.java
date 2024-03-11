package lk.ijse.carrent.layerd.service.custom;

import lk.ijse.carrent.layerd.dto.UserDto;
import lk.ijse.carrent.layerd.service.SuperService;

public interface UserService extends SuperService {

   String addUser(UserDto userDto) throws Exception;
   Boolean searchPassword(UserDto userDto) throws Exception;

}
