package ru.asteises.neftlink.mapper;

import org.springframework.stereotype.Service;
import ru.asteises.neftlink.dto.BaseDto;
import ru.asteises.neftlink.entity.Base;

/**
 * Преобразует Base в BaseDto и обратно
 */

@Service
public class BaseMapper {

    public Base baseDtoToBase(BaseDto baseDto) {
        Base base = new Base();
        base.setName(baseDto.getName());
        base.setAddress(baseDto.getAddress());
        base.setVisible(Boolean.TRUE);
        return base;
    }

    public BaseDto baseToBaseDto(Base base) {
        BaseDto baseDto = new BaseDto();
        baseDto.setName(base.getName());
        baseDto.setAddress(base.getAddress());
        return baseDto;
    }
}
