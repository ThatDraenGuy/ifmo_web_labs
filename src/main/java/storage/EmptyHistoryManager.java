package storage;

import java.util.List;

public class EmptyHistoryManager<T> implements HistoryManager<T>{
    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isSet() {
        return false;
    }

    @Override
    public void put(T element) {

    }

    @Override
    public List<T> get() {
        return null;
    }

    @Override
    public void clear() {

    }
}
