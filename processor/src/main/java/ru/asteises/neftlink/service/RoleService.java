package ru.asteises.neftlink.service;

import org.springframework.stereotype.Service;
import ru.asteises.neftlink.dto.RoleDto;
import ru.asteises.neftlink.entity.Role;
import ru.asteises.neftlink.mapper.RoleMapper;
import ru.asteises.neftlink.repositoryes.RoleRepository;

/**
 * Отвечает за всю бизнес-логику связанную с Role (все что может происходить с объектами типа Role)
 */
@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    /**
     *Создаем объект Role из RoleDto и сохраняем в базу данных
     */
    public String add(RoleDto roleDto) {
        roleRepository.save(roleMapper.roleToRoleDto(roleDto));
        return "роль успешно добавлена в репозиторий";
    }

    public Role getRoleUser() {
        return roleRepository.findRoleByName("user");
    }
}
