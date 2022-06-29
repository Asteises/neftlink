package ru.asteises.neftlink.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.asteises.neftlink.dto.GasDto;
import ru.asteises.neftlink.entity.Gas;

import java.util.UUID;

/**
 * Преобразует Gas в GasDto и обратно
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD, imports = {UUID.class})
public abstract class GasMapper {

    public static final GasMapper INSTANCE = Mappers.getMapper(GasMapper.class);
    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "visible", expression = "java(Boolean.TRUE)")
    public abstract Gas toGas(GasDto gasDto);
    @InheritInverseConfiguration
    public abstract GasDto toDto(Gas gas);
}