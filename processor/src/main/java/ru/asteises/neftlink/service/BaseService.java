package ru.asteises.neftlink.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.asteises.neftlink.dto.BaseDto;
import ru.asteises.neftlink.entity.Base;
import ru.asteises.neftlink.handler.exception.BaseNotFound;
import ru.asteises.neftlink.mapper.BaseMapper;
import ru.asteises.neftlink.repositoryes.BaseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Отвечает за всю бизнес-логику связанную с Base (все что может происходить с объектами типа Base)
 */
@Service
public class BaseService {
    private final BaseRepository baseRepository;

    public BaseService(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    /**
     * Создаем объект Base из BaseDto и сохраняем в базу данных
     */
    public Base add(BaseDto baseDto) {
        return baseRepository.save(BaseMapper.INSTANCE.toBase(baseDto));
    }

    /**
     * Пробуем заменить base по id
     */
    public ResponseEntity<String> put(BaseDto baseDto, UUID id) throws BaseNotFound {
        Optional<Base> baseOptional = baseRepository.findById(id);
        if (baseOptional.isPresent()) {
            Base base = baseOptional.get();
            base.setName(baseDto.getName());
            base.setAddress(baseDto.getAddress());
            baseRepository.save(base);
            return ResponseEntity.ok("Base успешно обновлен");
        }
        throw new BaseNotFound(String.format("Base with %s id not found", id));

    }

    /**
     *Ищем в репозитории объект Base по указанному baseName
     */
    public Base getBaseByName(String baseName) {
        return baseRepository.findBaseByName(baseName)
                .orElseThrow(() -> new BaseNotFound(String.format("Base with %s name not found", baseName)));
    }

    public Base getBaseById(UUID baseId) {
        return baseRepository.findBaseById(baseId);
    }

    /**
     * Чтобы не удалять из БД, меняем значение видимости.
     */
    public ResponseEntity<String> setVisibleFalse(UUID id) {
        Optional<Base> baseOptional = baseRepository.findById(id);
        if (baseOptional.isPresent()) {
            Base base = baseOptional.get();
            base.setVisible(Boolean.FALSE);
            baseRepository.save(base);
            return ResponseEntity.ok("Base успешно удален");
        }
        return ResponseEntity.ok("Мы не смогли найти такой base");
    }

    public ResponseEntity<List<Base>> getVisibleBases() {
        List<Base> bases = baseRepository.findAllByVisibleTrue();
        return ResponseEntity.ok(bases);
    }
}
