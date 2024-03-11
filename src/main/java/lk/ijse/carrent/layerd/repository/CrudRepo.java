package lk.ijse.carrent.layerd.repository;

import java.util.List;

public interface CrudRepo<T,ID> extends SuperRepo {

    Integer add(T t) throws Exception;
    T get(ID id) throws Exception;
    List<T> getAll() throws Exception;

    Integer update(T t) throws Exception;

     Integer delete(ID id) throws Exception;

     Integer save (T t) throws Exception;
}
