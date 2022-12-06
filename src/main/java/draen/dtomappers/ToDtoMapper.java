package draen.dtomappers;

import draen.dto.Dto;

public interface ToDtoMapper<T, V extends Dto<T>> {
    V toDto(T item);
    Iterable<V> toDtos(Iterable<T> items);
}
