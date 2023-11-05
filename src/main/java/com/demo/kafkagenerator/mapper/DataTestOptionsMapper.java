package com.demo.kafkagenerator.mapper;

import com.demo.kafkagenerator.dto.DataTestOptionsDto;
import com.demo.kafkagenerator.model.DataTestOptions;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DataTestOptionsMapper extends MapObject<DataTestOptions, DataTestOptionsDto> {
}
