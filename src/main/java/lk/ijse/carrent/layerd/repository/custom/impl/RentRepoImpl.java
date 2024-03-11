package lk.ijse.carrent.layerd.repository.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.carrent.layerd.entity.CarEntity;
import lk.ijse.carrent.layerd.entity.RentEntity;
import lk.ijse.carrent.layerd.repository.CrudUtil;
import lk.ijse.carrent.layerd.repository.custom.RentRepo;
import lk.ijse.carrent.layerd.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;

public class RentRepoImpl implements RentRepo {
    @Override
    public Integer add(RentEntity rentEntity) throws Exception {
        return new CrudUtil().add(rentEntity);
    }

    @Override
    public RentEntity get(String s) throws Exception {
        return (RentEntity) new CrudUtil().get("FROM RentEntity WHERE id = '" + s + "'");
    }

    @Override
    public List<RentEntity> getAll() throws Exception {
        List<Object>  t = new CrudUtil().getAll("FROM RentEntity");
        List<RentEntity> rentEntities = new ArrayList<>();
        for (Object type:t

        ) {
            RentEntity rentEntity = (RentEntity) type;
            rentEntities.add(rentEntity);

        }
        return rentEntities;
    }

    @Override
    public Integer update(RentEntity rentEntity) throws Exception {
        return null;
    }

    @Override
    public Integer delete(String s) throws Exception {
        return null;
    }

    @Override
    public Integer save(RentEntity rentEntity) throws Exception {
        return null;
    }

    @Override
    public Integer updateReturnDate(RentEntity rent) throws Exception {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Integer id = -1;

        Transaction transaction = session.getTransaction();


        String hql = "UPDATE RentEntity SET balance = :balance , total = :total ,retunDate = :return WHERE id = :rentId";
        try {
            transaction.begin();

            Query query = session.createQuery(hql);
            query.setParameter("balance",rent.getBalance());
            query.setParameter("total",rent.getTotal());
            query.setParameter("return",rent.getRetunDate());
            query.setParameter("rentId",rent.getId());

            Integer update = query.executeUpdate();

            transaction.commit();

            return id = 10;


        }catch (Exception e){
            transaction.rollback();
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return id;
        }


    }

    @Override
    public Date carReturnDate(String id) throws Exception {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        String hql = "select r.retunDate from RentEntity r where r.carEntity.id =:id order by r.retunDate desc ";
        Date returnDate = null;



        try {

            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id",id);
            query.setMaxResults(1);
            returnDate = (Date) query.uniqueResult();
            session.getTransaction().commit();
            System.out.println("Date = "+returnDate);
            return returnDate;






        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

            return returnDate ;

        }
    }

    @Override
    public Date carReturnNull(String id) throws Exception {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        String hql = "select r.retunDate from RentEntity r where r.carEntity.id =:id order by r.retunDate asc ";
        Date day = null;




        try {

            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id",id);
            query.setMaxResults(1);

            day = (Date) query.uniqueResult();

            session.getTransaction().commit();


        return day;




        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

             return day ;

        }
    }

    @Override
    public String carRentId(String id) throws Exception {

        Session session = SessionFactoryConfiguration.getInstance().getSession();
        String hql = "select r.id from RentEntity r where r.carEntity.id =:id ";
        String rentId = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id",id);
            query.setMaxResults(1);
            rentId = (String) query.uniqueResult();
            session.getTransaction().commit();
            return rentId;

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return rentId;
        }

    }

    @Override
    public String custRentId(String id) throws Exception {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        String hql = "select r.id from RentEntity r where r.customerEntity.id =:id ";
        String rentIds = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id",id);
            query.setMaxResults(1);
            rentIds = (String) query.uniqueResult();
            session.getTransaction().commit();
            return rentIds;

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return rentIds;
        }
    }

    @Override
    public Date custReturnNull(String id) throws Exception {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        String hql = "select r.retunDate from RentEntity r where r.customerEntity.id =:id order by r.retunDate asc ";
        Date day = null;




        try {

            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id",id);
            query.setMaxResults(1);

            day = (Date) query.uniqueResult();

            session.getTransaction().commit();


            return day;




        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

            return day ;

        }
    }

    @Override
    public Date custReturnDate(String id) throws Exception {

        Session session = SessionFactoryConfiguration.getInstance().getSession();
        String hql = "select r.retunDate from RentEntity r where r.customerEntity.id=:id order by r.retunDate desc ";
        Date returnDate = null;



        try {

            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id",id);
            query.setMaxResults(1);
            returnDate = (Date) query.uniqueResult();
            session.getTransaction().commit();
            return returnDate;


        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

            return returnDate ;

        }

    }

    @Override
    public List<RentEntity> carRentDetails(String id) throws Exception {

        Session session = SessionFactoryConfiguration.getInstance().getSession();
        String hql = "from RentEntity r where r.carEntity.id=:id";
        List<RentEntity> rentEntities = new ArrayList<>();
        try {
            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            rentEntities = query.list();



            session.getTransaction().commit();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return rentEntities;
        }
        return rentEntities;
    }

    @Override
    public List<RentEntity> customerRentDetails(String id) throws Exception {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        String hql = "from RentEntity r where r.customerEntity.id=:id";
        List<RentEntity> rentEntities = new ArrayList<>();
        try {
            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            rentEntities = query.list();



            session.getTransaction().commit();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return rentEntities;
        }

        return rentEntities;
    }

    @Override
    public List<RentEntity> getLateReturnList() throws Exception {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        String hql = "from RentEntity r where r.retunDate>r.toDate";
        List<RentEntity> rentEntities = new ArrayList<>();
        try {
            session.beginTransaction();
            Query query = session.createQuery(hql);

            rentEntities = query.list();



            session.getTransaction().commit();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return rentEntities;
        }

        return rentEntities;

    }
}
