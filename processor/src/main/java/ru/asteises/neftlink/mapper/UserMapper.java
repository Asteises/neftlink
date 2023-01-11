package ru.asteises.neftlink.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.asteises.neftlink.dto.UserDto;
import ru.asteises.neftlink.entity.User;
import ru.asteises.neftlink.service.RoleService;

import java.util.Collections;
import java.util.UUID;

/**
 * Преобразует User в UserDto и обратно
 */

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD,
        imports = {UUID.class, Collections.class},
        uses = {RoleService.class})
public abstract class UserMapper {

    protected static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(8);

    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    //TODO Простые поля, вроде String, преобразуются самостоятельно, без дополнительного кода?
    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "password", expression = "java(encoder.encode(userDto.getPassword()))")
    @Mapping(target = "roles", expression = "java(Collections.singletonList(roleService.getRoleUser()))")
    public abstract User toUser(UserDto userDto, @Context RoleService roleService);

    @InheritInverseConfiguration
    @Mapping(target = "password", ignore = true)
    public abstract UserDto toDto(User user);
}
