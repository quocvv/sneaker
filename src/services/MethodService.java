package services;

import java.util.List;

public interface MethodService<T> {

    List<T> getAll();

    boolean createObject(T t);

    boolean updateObject(T t, int id);

    T getObject(int id);

}
