package ru.asteises.neftlink.service;

import org.springframework.stereotype.Service;
import ru.asteises.neftlink.dto.UserDto;
import ru.asteises.neftlink.mapper.UserMapper;
import ru.asteises.neftlink.repositoryes.UserRepository;

/**
 * Отвечает за всю бизнес-логику связанную с User (все что может происходить с объектами типа User)
 */
@Service
public class UserService {
    private final UserRepository userRepository;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**
     *Создаем объект User из UserDto и сохраняем в базу данных
     */
    public String add(UserDto userDto) {
        userRepository.save(userMapper.userDtoToUser(userDto));
        return "юзер успешно добавлен в репозиторий";
    }
}
