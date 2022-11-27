package draen.rest.controllers;

import draen.dto.Dto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.util.function.Function;

public class ControllerUtils {

    public static<T, V extends Dto<T>> EntityModel<V> wrap(T item, Function<T, V> toDto, Function<V, EntityModel<V>> toModel) {
        return toModel.apply(toDto.apply(item));
    }

    public static<T, V extends Dto<T>> T unwrap(V item, Function<V, T> dto) {
        return dto.apply(item);
    }

    public static<T,V extends Dto<T>>CollectionModel<EntityModel<V>> wrap(Iterable<T> items,
                                                                          Function<Iterable<T>,? extends Iterable<V>> toDto,
                                                                          Function<Iterable<V>,CollectionModel<EntityModel<V>>> toModel) {
        return toModel.apply(toDto.apply(items));
    }
}
