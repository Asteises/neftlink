package ru.asteises.neftlink.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.asteises.neftlink.dto.UserDto;
import ru.asteises.neftlink.entity.Order;
import ru.asteises.neftlink.entity.User;
import ru.asteises.neftlink.mapper.UserMapper;
import ru.asteises.neftlink.repositoryes.UserRepository;

import java.util.Optional;
import java.util.UUID;

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
    public String registration(UserDto userDto) {
        userRepository.save(userMapper.userDtoToUser(userDto));
        return "User успешно добавлен в репозиторий";
    }

    /**
     * Находим объект User по ID, меняем его и сохраняем обратно
     */
    public ResponseEntity<String> put(UserDto userDto, UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(userDto.getName());
            user.setInn(userDto.getInn());
            user.setCompany(userDto.getCompany());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            userRepository.save(user);
            return ResponseEntity.ok("User успешно обновлен");
        }
        return ResponseEntity.ok("Мы не нашли подходящего User");
    }

    //TODO Добавить метод addOrder()
    public void addOrder(Order order) {
        User user = order.getUser();
        user.addOrder(order);
        userRepository.save(user);
    }

    public User getUserByInn(int inn) {
        return userRepository.findUserByInn(inn);
    }

    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
