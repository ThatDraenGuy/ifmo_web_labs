package draen.dtomappers;

import draen.dto.Dto;

public interface FullDtoMapper<T, V extends Dto<T>> extends ToDtoMapper<T,V>, FromDtoMapper<T,V> {
}
