package lk.ijse.carrent.layerd.repository;

import javafx.scene.control.Alert;
import lk.ijse.carrent.layerd.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CrudUtil {





    public Integer add(Object object) {


        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.getTransaction();



        Integer id;

        try {


             transaction.begin();
            session.save(object);
            transaction.commit();

            return id = 10;

        } catch (Exception e) {

            transaction.rollback();

            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();



            return -1;
        }finally {
            session.close();
        }
    }

    public Object get(String hql){
        Session session = SessionFactoryConfiguration.getInstance().getSession();




        Query query = session.createQuery(hql);
         Object t =  query.uniqueResult();
         return t;


    }

    public List<Object> getAll(String hql){
        Session session = SessionFactoryConfiguration.getInstance().getSession();


        Query query = session.createQuery(hql);
        List<Object> t = query.list();
        return t;
    }

    public Integer update(Object object){
        Session session = SessionFactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();
        Integer index;
       try {


         session.update(object);
         transaction.commit();
         return index = 10;


       }catch (Exception e){
           transaction.rollback();
           new Alert(Alert.AlertType.ERROR,"ID cant update").show();
           return index = -1;
       }

    }

    public Integer delete(String hql){
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction ;
        Integer index;

        try {
            Query query = session.createQuery(hql);
            session.beginTransaction();

            Integer executeUpdate = query.executeUpdate();
            session.getTransaction().commit();
             if (executeUpdate > 0){

                 return index =10;

             }else {
                 return index  = -1;
             }


        }catch (Exception e){

            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return index=-1;

        }


    }
}
