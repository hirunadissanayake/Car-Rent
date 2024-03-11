package lk.ijse.carrent.layerd.service;

import lk.ijse.carrent.layerd.service.custom.impl.*;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;
    private ServiceFactory(){}

    public static ServiceFactory getInstance(){

        if(serviceFactory == null){

            serviceFactory = new ServiceFactory();

        }

        return serviceFactory;


    }

    public SuperService getService(ServiceType type){

        switch(type){

            case USER:
                return new UserServiceImpl();
            case CARCATEGORY:
                return new CarCategoryServiceImpl();
            case CARDETAILS:
                return new CarDetailsServiceImpl();
            case CUSTOMER:
                return new CustomerServiceImpl();
            case RENT:
                return new RentServiceImpl();




            default:
                return null;
        }


    }

    public enum ServiceType{

       USER,CARCATEGORY,CARDETAILS,CUSTOMER,RENT

    }

}
