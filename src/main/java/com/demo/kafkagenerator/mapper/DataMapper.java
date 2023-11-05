package com.demo.kafkagenerator.mapper;

import com.demo.kafkagenerator.dto.DataDto;
import com.demo.kafkagenerator.model.Data;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DataMapper extends MapObject<Data, DataDto> {
}
