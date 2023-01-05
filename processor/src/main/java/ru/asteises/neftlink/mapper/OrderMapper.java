package ru.asteises.neftlink.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.asteises.neftlink.dto.OrderDto;
import ru.asteises.neftlink.entity.Order;
import ru.asteises.neftlink.securityConfig.jwt.JwtAuthentication;
import ru.asteises.neftlink.service.BaseService;
import ru.asteises.neftlink.service.GasService;
import ru.asteises.neftlink.service.UserService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

/**
 * Преобразует Order в OrderDto и обратно
 */

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD,
        imports = {UUID.class, LocalDateTime.class, Collections.class},
        uses = {UserService.class, GasService.class, BaseService.class, JwtAuthentication.class})
public abstract class OrderMapper {

    public static final OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);


    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "createDate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updateDate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "user", expression = "java(userService.getByEmail(jwtAuthentication.getUsername()).get())")
    @Mapping(target = "gas", expression = "java(gasService.getGasByName(orderDto.getGasType()))")
    @Mapping(target = "base", expression = "java(baseService.getBaseByName(orderDto.getBaseName()))")
    @Mapping(target = "visible", expression = "java(Boolean.TRUE)")
    public abstract Order toOrder(OrderDto orderDto,
                                  @Context UserService userService,
                                  @Context GasService gasService,
                                  @Context BaseService baseService,
                                  @Context JwtAuthentication jwtAuthentication);

    @InheritInverseConfiguration
    @Mapping(target = "inn", source = "order.user.inn")
    @Mapping(target = "gasType", source = "order.gas.gasType")
    @Mapping(target = "baseName", source = "order.base.name")
    public abstract OrderDto toDto(Order order);
}
