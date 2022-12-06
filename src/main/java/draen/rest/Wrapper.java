package draen.rest;

import draen.dto.Dto;
import draen.dtomappers.FromDtoMapper;
import draen.dtomappers.ToDtoMapper;
import draen.exceptions.DtoException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class Wrapper {
    private final List<ToDtoMapper<?,?>> toMappers;
    private final List<FromDtoMapper<?,?>> fromMappers;

    public<T, V extends Dto<T>> V wrap(T item, Class<V> targetClass) {
        return toMapper(item.getClass(), targetClass).toDto(item);
    }

    public<T, V extends Dto<T>> ResponseEntity<V> wrapOk(T item, Class<V> targetClass) {
        V dto = wrap(item, targetClass);
        return ResponseEntity.ok(wrap(item, targetClass));
    }

    public<T, V extends Dto<T>> ResponseEntity<Iterable<V>> wrapAllOk(Iterable<T> items, Class<V> targetClass) {
        if (!items.iterator().hasNext()) return ResponseEntity.ok(null);
        Iterable<V> dtos = toMapper(items.iterator().next().getClass(), targetClass).toDtos(items);
        return ResponseEntity.ok(dtos);
    }

    public<T, V extends Dto<T>> T unwrap(V dto, Class<T> itemClass) throws DtoException {
        return fromMapper(itemClass, dto.getClass()).fromDto(dto);
    }

    private<T, V extends Dto<T>> ToDtoMapper<T,V> toMapper(Class<?> itemClass, Class<V> targetClass) {
        ResolvableType mapperType = ResolvableType.forClassWithGenerics(ToDtoMapper.class, itemClass, targetClass);
       return (ToDtoMapper<T, V>) toMappers.stream().filter(mapperType::isInstance).findFirst().get();
    }
    private<T, V extends Dto<T>> FromDtoMapper<T,V> fromMapper(Class<T> itemClass, Class<?> dtoClass) {
        ResolvableType mapperType = ResolvableType.forClassWithGenerics(FromDtoMapper.class, itemClass, dtoClass);
        return (FromDtoMapper<T, V>) fromMappers.stream().filter(mapperType::isInstance).findFirst().get();
    }
}
