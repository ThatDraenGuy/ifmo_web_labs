package draen.dto;

import draen.exceptions.DtoException;

public interface FromDtoMapper<T, V extends Dto<T>> {
    T fromDto(V dto) throws DtoException;
}
