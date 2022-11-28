package draen.dto;

public interface FullDtoMapper<T, V extends Dto<T>> extends ToDtoMapper<T,V>, FromDtoMapper<T,V> {
}
