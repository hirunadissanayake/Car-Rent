package lk.ijse.carrent.layerd.repository.custom.impl;

import lk.ijse.carrent.layerd.entity.UserEntity;
import lk.ijse.carrent.layerd.repository.CrudUtil;
import lk.ijse.carrent.layerd.repository.custom.UserRepo;

import java.util.List;

public class UserRepoImpl implements UserRepo {


    @Override
    public Integer add(UserEntity t) throws Exception {

       return new CrudUtil().add(t);
    }

    @Override
    public UserEntity get(String s) throws Exception {


        return (UserEntity) new CrudUtil().get("FROM UserEntity WHERE userName = '" + s + "'");

    }

    @Override
    public List<UserEntity> getAll() throws Exception {
        return null;
    }

    @Override
    public Integer update(UserEntity userEntity) throws Exception {
        return null;
    }

    @Override
    public Integer delete(String s) throws Exception {
        return null;

    }

    @Override
    public Integer save(UserEntity userEntity) throws Exception {
return null;
    }
}
