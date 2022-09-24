package storage;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import coordinates.CircleQuadrant;
import coordinates.EmptyQuadrant;
import coordinates.SquareQuadrant;
import coordinates.TriangleQuadrant;

import java.util.List;

public interface HistoryManager<T> {
    String NAME = "HistoryManager";
    boolean isEmpty();
    boolean isSet();

    void put(T element);
    List<T> get();

    void clear();
}
