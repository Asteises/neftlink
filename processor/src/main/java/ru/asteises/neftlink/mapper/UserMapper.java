package ru.asteises.neftlink.mapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.asteises.neftlink.dto.UserDto;
import ru.asteises.neftlink.entity.User;
import ru.asteises.neftlink.service.RoleService;

import java.util.Collections;

/**
 * Преобразует User в UserDto и обратно
 */

@Service
public class UserMapper {
    private final RoleService roleService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(8);
    public UserMapper(RoleService roleService) {
        this.roleService = roleService;
    }

    public User userDtoToUser(UserDto userDto) {
        User user = new User();
        user.setInn(userDto.getInn());
        user.setCompany(userDto.getCompany());
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setName(userDto.getName());
        user.setRoles(Collections.singletonList(roleService.getRoleUser()));
        return user;
    }

    public UserDto userDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setInn(user.getInn());
        userDto.setCompany(user.getCompany());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setName(user.getName());
        return userDto;
    }
}
