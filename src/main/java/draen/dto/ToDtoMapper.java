package draen.dto;

public interface ToDtoMapper<T, V extends Dto<T>> {
    V toDto(T item);
    Iterable<V> toDtos(Iterable<T> items);
}
