package ra.model.dao;

import java.sql.SQLException;
import java.util.List;

public interface IStudentDao<T,V> {
    List<T> findAll();
    boolean Save(T t) ;
    boolean update(T t);
    boolean delete(Integer id);
    List<T> searchByNameOrId(String st);
    Integer getIndex();
    List<T> getListByIndex(Integer index);
}
