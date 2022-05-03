package ru.asteises.neftlink.mapper;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ru.asteises.neftlink.dto.RoleDto;
import ru.asteises.neftlink.entity.Role;

/**
 * Преобразует Role в RoleDto и обратно
 */

@Service
public class RoleMapper {

    public Role roleToRoleDto(RoleDto roleDto) {
        Role role = new Role();
        role.setName(roleDto.getName());
        return role;
    }

    public RoleDto roleToRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setName(role.getName());
        return roleDto;
    }
}
