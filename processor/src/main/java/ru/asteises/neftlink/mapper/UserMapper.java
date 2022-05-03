package ru.asteises.neftlink.mapper;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ru.asteises.neftlink.dto.UserDto;
import ru.asteises.neftlink.entity.User;

/**
 * Преобразует User в UserDto и обратно
 */

@Service
public class UserMapper {

    public User userDtoToUser(UserDto userDto) {
        User user = new User();
        user.setInn(userDto.getInn());
        user.setCompany(userDto.getCompany());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
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
