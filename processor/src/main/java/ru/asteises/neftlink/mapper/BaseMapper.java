package ru.asteises.neftlink.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.asteises.neftlink.dto.BaseDto;
import ru.asteises.neftlink.entity.Base;

import java.util.UUID;

/**
 * Преобразует Base в BaseDto и обратно
 */

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD,
        imports = {UUID.class})
public abstract class BaseMapper {

    public static final BaseMapper INSTANCE = Mappers.getMapper(BaseMapper.class);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "visible", expression = "java(Boolean.TRUE)")
    public abstract Base toBase(BaseDto baseDto);

    @InheritInverseConfiguration
    public abstract BaseDto toDto(Base base);
}
