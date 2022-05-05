package ru.asteises.neftlink.service;

import org.springframework.stereotype.Service;
import ru.asteises.neftlink.dto.BaseDto;
import ru.asteises.neftlink.entity.Base;
import ru.asteises.neftlink.mapper.BaseMapper;
import ru.asteises.neftlink.repositoryes.BaseRepository;

/**
 * Отвечает за всю бизнес-логику связанную с Base (все что может происходить с объектами типа Base)
 */
@Service
public class BaseService {
    private final BaseRepository baseRepository;
    private final BaseMapper baseMapper;

    public BaseService(BaseRepository baseRepository, BaseMapper baseMapper) {
        this.baseRepository = baseRepository;
        this.baseMapper = baseMapper;
    }

    /**
     *Создаем объект Base из BaseDto и сохраняем в базу данных
     */
    public String add(BaseDto baseDto) {
        baseRepository.save(baseMapper.baseDtoToBase(baseDto));
        return "базис успешно добавлен в репозиторий";
    }

    /**
     *Ищем в репозитории объект Base по указанному baseName
     */
    public Base getBaseByName(String baseName) {
        return baseRepository.findBaseByName(baseName);
    }

    public Base getBaseById(Long baseId) {
        return baseRepository.findBaseById(baseId);
    }
}
