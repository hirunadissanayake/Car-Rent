package lk.ijse.carrent.layerd.repository.custom.impl;

import lk.ijse.carrent.layerd.entity.CarCategoryEntity;
import lk.ijse.carrent.layerd.repository.CrudUtil;
import lk.ijse.carrent.layerd.repository.custom.CarCategoryRepo;

import java.util.ArrayList;
import java.util.List;

public class CarCategoryRepoImpl implements CarCategoryRepo {
    @Override
    public Integer add(CarCategoryEntity carCategoryEntity) throws Exception {
       return new CrudUtil().add(carCategoryEntity);
    }

    @Override
    public CarCategoryEntity get(String s) throws Exception {

        return (CarCategoryEntity) new CrudUtil().get("FROM CarCategoryEntity WHERE name = '" + s + "'");
    }

    @Override
    public List<CarCategoryEntity> getAll() throws Exception {
        List<Object> t = new CrudUtil().getAll("FROM CarCategoryEntity");
        List<CarCategoryEntity>carCategoryEntities = new ArrayList<>();

        for (Object type:t)
              {
            CarCategoryEntity entity = (CarCategoryEntity) type;
            carCategoryEntities.add(entity);


        }

        return carCategoryEntities;



    }

    @Override
    public Integer update(CarCategoryEntity carCategoryEntity) throws Exception {
       return new CrudUtil().update(carCategoryEntity);

    }

    @Override
    public Integer delete(String s) throws Exception {

       return new CrudUtil().delete("DELETE FROM CarCategoryEntity WHERE id = '"+ s +"'");
    }

    @Override
    public Integer save(CarCategoryEntity carCategoryEntity) throws Exception {
        return null;
    }
}
