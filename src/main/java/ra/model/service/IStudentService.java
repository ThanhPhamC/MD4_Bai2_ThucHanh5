package ra.model.service;

import java.util.List;

public interface IStudentService<T,V> {
    List<T> findAll();
    boolean Save(T t);
    boolean update(T t);
    boolean delete(Integer id);
    List<T> searchByNameOrId(String st);
    Integer getIndex();
    List<T> getListByIndex(Integer index);
}
