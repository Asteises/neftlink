package ru.asteises.userclient.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.asteises.userclient.dto.RegistrationDto;

/**
 * Действия пользователей без авторизации.
 * <p>
 * 1. Регистрация. Любой пользователь может пройти процедуру регистрации, выбрав при этом собственную роль в приложении.
 * Так как ролей пользователей будет несколько, то и форма регистрации будет отличаться.
 * Например, у ООО и ИП разные типы уставных документов и разные значения некоторых полей.
 * При этом, основные регистрационные данные могут быть одинаковыми.
 * <p>
 * 2. Просмотр главной страницы.
 */
@Slf4j
@RestController
@RequestMapping("/")
public class PublicUserController {

    /**
     * Метод регистрации всех типов пользователей.
     *
     * @param registrationDto Принимаем нужное DTO для регистрации #{@link RegistrationDto} в зависимости от типа пользователя;
     * @return Возвращаем ...;
     */
    @PostMapping("/registration")
    public <T> ResponseEntity<Object> userRegistration(@RequestParam String role,
                                                  @RequestBody T registrationDto) {

        return userProducer.userRegistration(roleId, registrationDto);
    }

    // TODO Что нужно возвращать при запросе главной страницы ИМ?
    @GetMapping
    public ResponseEntity<String> getFirstPage() {
        return ResponseEntity.ok("OK");
    }
}
