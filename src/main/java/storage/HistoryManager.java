package storage;

import java.util.List;

public interface HistoryManager<T> {
    boolean isEmpty();
    boolean isSet();

    void put(T element);
    List<T> get();

    void clear();
}
