package storage;


import java.util.List;

public interface HistoryManager<T> {
    String NAME = "HistoryManager";
    boolean isEmpty();
    boolean isSet();

    void put(T element);
    List<T> get();

    void clear();
}
