package ru.asteises.neftlink.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.asteises.neftlink.dto.UserChangeDto;
import ru.asteises.neftlink.dto.UserDto;
import ru.asteises.neftlink.entity.User;
import ru.asteises.neftlink.handler.exception.UserNotFound;
import ru.asteises.neftlink.mapper.UserMapper;
import ru.asteises.neftlink.repositoryes.UserRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Отвечает за всю бизнес-логику связанную с User (все что может происходить с объектами типа User)
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    /**
     * Создаем объект User из UserDto и сохраняем в базу данных
     */
    public String registration(UserDto userDto) {
        userRepository.save(UserMapper.INSTANCE.toUser(userDto, roleService));
        return "User успешно добавлен в репозиторий";
    }

    /**
     * Находим объект User по ID, меняем его и сохраняем обратно
     */
    public ResponseEntity<String> put(UserChangeDto userChangeDto, UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (userChangeDto.getSurname() != null && !userChangeDto.getSurname().equals("")) {
                user.setSurname(userChangeDto.getSurname());
            }
            if (userChangeDto.getFirstname() != null && !userChangeDto.getFirstname().equals("")) {
                user.setFirstname(userChangeDto.getFirstname());
            }
            if (userChangeDto.getLastname() != null && !userChangeDto.getLastname().equals("")) {
                user.setLastname(userChangeDto.getLastname());
            }
            if (userChangeDto.getEmail() != null && !userChangeDto.getEmail().equals("")) {
                user.setEmail(userChangeDto.getEmail());
            }
            if (userChangeDto.getPhone() != null) {
                user.setPhone(userChangeDto.getPhone());
            }
            if (userChangeDto.getInn() != null) {
                user.setInn(userChangeDto.getInn());
            }
            if (userChangeDto.getPassword() != null && !userChangeDto.getPassword().equals("")) {
                user.setPassword(userChangeDto.getPassword());
            }
            userRepository.save(user);
            return ResponseEntity.ok("User успешно обновлен");
        }
        return ResponseEntity.ok("Мы не нашли подходящего User");
    }

    public User getUserByInn(int inn) {
        return userRepository.findUserByInn(inn)
                .orElseThrow(() -> new UserNotFound(String.format("User by inn= %s not found", inn)));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFound(String.format("User by email= %s not found", email)));
    }

    public User getUserByPhone(String phone) {
        return userRepository.findByPhone(phone)
                .orElseThrow(() -> new UserNotFound(String.format("User by phone= %s not found", phone)));
    }
}
