package lk.ijse.carrent.layerd.repository.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.carrent.layerd.entity.CarEntity;
import lk.ijse.carrent.layerd.repository.CrudUtil;
import lk.ijse.carrent.layerd.repository.custom.CarDetailsRepo;
import lk.ijse.carrent.layerd.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CarDetailsRepoImpl implements CarDetailsRepo {

    @Override
    public Integer add(CarEntity carEntity) throws Exception {
        return new CrudUtil().add(carEntity);
    }

    @Override
    public CarEntity get(String s) throws Exception {
        return (CarEntity) new CrudUtil().get("FROM CarEntity WHERE id = '" + s + "'");
    }

    @Override
    public List<CarEntity> getAll() throws Exception {
       List<Object>  t = new CrudUtil().getAll("FROM CarEntity");
       List<CarEntity> carEntities = new ArrayList<>();
        for (Object type:t
             ) {
            CarEntity carEntity = (CarEntity) type;
            carEntities.add(carEntity);

        }
        return carEntities;
    }

    @Override
    public Integer update(CarEntity carEntity) throws Exception {
        return new CrudUtil().update(carEntity);
    }

    @Override
    public Integer delete(String s) throws Exception {
        return new CrudUtil().delete("DELETE FROM CarEntity WHERE id = '"+ s +"'");
    }

    @Override
    public Integer save(CarEntity carEntity) throws Exception {





        return null;

    }

    @Override
    public List<String> getCarBrand(String id) throws Exception {


        String hql = "SELECT DISTINCT c.brand FROM CarEntity as c WHERE c.carCategoryEntity.id='"+ id +"'";
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Query query = session.createQuery(hql);
            List<String> brand = query.list();
            transaction.commit();

            return brand;

        }catch (Exception e){
            transaction.rollback();
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            new Alert(Alert.AlertType.ERROR,"Hiran").show();
            return null;

        }
    }

    @Override
    public List<String> getCarModel(String carCategoryId, String brand) throws Exception {
       String hql = "SELECT DISTINCT c.model FROM CarEntity as c where c.carCategoryEntity=:id AND c.brand=:brand";

        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Query query = session.createQuery(hql);
            query.setParameter("id",carCategoryId);
            query.setParameter("brand",brand);
            List<String> model = query.list();
            transaction.commit();

            return model;

        }catch (Exception e){
            transaction.rollback();
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            new Alert(Alert.AlertType.ERROR,"Hiran").show();
            return null;

        }
    }

    @Override
    public List<CarEntity> getCarAll(String id, String brand, String model) throws Exception {
        String hql = " FROM CarEntity as c where c.carCategoryEntity=:id AND c.brand=:brand AND c.model=:model";

        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Query query = session.createQuery(hql);
            query.setParameter("id",id);
            query.setParameter("brand",brand);
            query.setParameter("model",model);
            List<CarEntity> carEntities = query.list();


            transaction.commit();

            return carEntities;

        }catch (Exception e){
            transaction.rollback();
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            new Alert(Alert.AlertType.ERROR,"Hiran").show();
            return null;

        }
    }
}


