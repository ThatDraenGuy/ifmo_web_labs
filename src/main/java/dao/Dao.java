package dao;

import java.util.List;

public interface Dao<T> {
    void save(T t);

    T get(long id);

    List<T> getAll();

    boolean isEmpty();

    void clear();

}
