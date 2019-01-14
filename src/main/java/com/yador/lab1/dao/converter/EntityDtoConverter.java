package com.yador.lab1.dao.converter;

import java.util.List;

public interface EntityDtoConverter<E, M> {
    E toEntity(M m);
    M toDto(E e);

    List<M> allToDto(List<E> e);
}
