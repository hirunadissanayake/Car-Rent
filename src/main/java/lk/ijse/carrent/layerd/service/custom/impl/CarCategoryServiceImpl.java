package lk.ijse.carrent.layerd.service.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.carrent.layerd.dto.CarCategoryDto;
import lk.ijse.carrent.layerd.entity.CarCategoryEntity;
import lk.ijse.carrent.layerd.entity.UserEntity;
import lk.ijse.carrent.layerd.repository.RepoFactory;
import lk.ijse.carrent.layerd.repository.custom.CarCategoryRepo;
import lk.ijse.carrent.layerd.repository.custom.UserRepo;
import lk.ijse.carrent.layerd.service.custom.CarCategoryService;

import java.util.ArrayList;
import java.util.List;

public class CarCategoryServiceImpl implements CarCategoryService {

    CarCategoryRepo carCategoryRepo = (CarCategoryRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoType.CARCATEGORY);
    UserRepo userRepo = (UserRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoType.USER);

    @Override
    public String addCarCategory(CarCategoryDto carCategoryDto) throws Exception {




        if (!carCategoryDto.getId().equals("") && !carCategoryDto.getName().equals("")){

       CarCategoryEntity carCategoryEntity = new CarCategoryEntity(carCategoryDto.getId(),carCategoryDto.getName(),carCategoryDto.getUserid());
        Integer id = carCategoryRepo.add(carCategoryEntity);

        if (id != -1) {

            return "Successfully Added";
        } else {
            return "Fail Added";
        }}else {

            return "Id or Name wrong or Empty";
        }


    }

    @Override
    public List<CarCategoryDto> getAll() throws Exception {
        List<CarCategoryEntity> carCategoryEntities = carCategoryRepo.getAll();
        List<CarCategoryDto> dtos = new ArrayList<>();


        for (CarCategoryEntity entity : carCategoryEntities
        ) {
            dtos.add(new CarCategoryDto(entity.getId(), entity.getName(), entity.getUserName()));


        }
        return dtos;

    }

    @Override
    public String update(CarCategoryDto carCategoryDto) {

        if (!carCategoryDto.getId().equals("") && !carCategoryDto.getName().equals("")){
        try {

            CarCategoryEntity carCategoryEntity = new CarCategoryEntity(carCategoryDto.getId(), carCategoryDto.getName(), carCategoryDto.getUserid()
            );

          Integer id =  carCategoryRepo.update(carCategoryEntity);


            if(id != -1){
                return "Update Success";
            }else {
                return "Fail Success";
            }
        } catch (Exception e) {

            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return "Fail";
        }}else {
            return "Id or Name wrong or Empty";
        }


    }

    @Override
    public String delete(CarCategoryDto dto) throws Exception {
         CarCategoryEntity carCategoryEntity = new CarCategoryEntity(dto.getId());

        Integer id = carCategoryRepo.delete(carCategoryEntity.getId());

        if (id != -1){

            return "Delete Success";
        }else {
            return "Delete Fail";
        }
    }

    @Override
    public CarCategoryDto search(String id) throws Exception {
        CarCategoryEntity carCategoryEntity = carCategoryRepo.get(id);

        CarCategoryDto carCategoryDto = new CarCategoryDto(carCategoryEntity.getId());
        return carCategoryDto;

    }
}
