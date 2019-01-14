package com.yador.lab1.dao.converter.impl;

import com.yador.lab1.dao.converter.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;

public abstract class JpaDtoConverter<E, M> implements EntityDtoConverter<E, M> {

    @Override
    public List<M> allToDto(List<E> es) {
        List<M> ms = new ArrayList<>();
        for(E e : es) {
            ms.add(this.toDto(e));
        }
        return ms;
    }
}
