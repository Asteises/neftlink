package ru.asteises.neftlink.service;

import org.springframework.stereotype.Service;
import ru.asteises.neftlink.dto.BaseDto;
import ru.asteises.neftlink.mapper.BaseMapper;
import ru.asteises.neftlink.repositoryes.BaseRepository;

/**
 * Отвечает за всю бизнес-логику связанную с Base (все что может происходить с объектами типа Base)
 */
@Service
public class BaseService {
    private final BaseRepository baseRepository;
    private BaseMapper baseMapper;

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
}
