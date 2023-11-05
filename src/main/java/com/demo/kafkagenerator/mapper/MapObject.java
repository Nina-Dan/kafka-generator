package com.demo.kafkagenerator.mapper;

import java.util.List;

public interface MapObject<E, D> {

    E toEntity(D dto);

    List<E> toEntity(List<D> dto);

    D toDto(E entity);

    List<D> toDto(List<E> entity);

}
