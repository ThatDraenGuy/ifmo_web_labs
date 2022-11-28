package draen.rest;

import draen.dto.Dto;
import draen.dto.FromDtoMapper;
import draen.dto.ToDtoMapper;
import draen.exceptions.DtoException;
import draen.rest.modelassemblers.DtoModelAssembler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class Wrapper {
    private final List<ToDtoMapper<?,?>> toMappers;
    private final List<FromDtoMapper<?,?>> fromMappers;
    private final List<DtoModelAssembler<?>> assemblers;

    public<T, V extends Dto<T>> EntityModel<V> wrap(T item, Class<V> targetClass) {

        V dto = toMapper(item.getClass(), targetClass).toDto(item);

        return assembler(targetClass).toModel(dto);
    }

    public<T, V extends Dto<T>> CollectionModel<EntityModel<V>> wrapAll(Iterable<T> items, Class<V> targetClass) {
        if (!items.iterator().hasNext()) return assembler(targetClass).toCollectionModel(new ArrayList<>());
        Iterable<V> dtos = toMapper(items.iterator().next().getClass(), targetClass).toDtos(items);
        return assembler(targetClass).toCollectionModel(dtos);
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
    private<T, V extends Dto<T>> DtoModelAssembler<V> assembler(Class<V> targetClass) {
        ResolvableType assemblerType = ResolvableType.forClassWithGenerics(DtoModelAssembler.class, targetClass);
        return (DtoModelAssembler<V>) assemblers.stream().filter(assemblerType::isInstance).findFirst().get();
    }
}
