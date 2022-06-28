package ru.asteises.neftlink.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.asteises.neftlink.dto.BaseDto;
import ru.asteises.neftlink.entity.Base;
import ru.asteises.neftlink.entity.Order;
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
    private final BaseMapper baseMapper;

    public BaseService(BaseRepository baseRepository, BaseMapper baseMapper) {
        this.baseRepository = baseRepository;
        this.baseMapper = baseMapper;
    }

    /**
     * Создаем объект Base из BaseDto и сохраняем в базу данных
     */
    public String add(BaseDto baseDto) {
        baseRepository.save(baseMapper.baseDtoToBase(baseDto));
        return "базис успешно добавлен в репозиторий";
    }

    /**
     * Пробуем заменить base по id
     */
    public ResponseEntity<String> put(BaseDto baseDto, UUID id) {
        Optional<Base> baseOptional = baseRepository.findById(id);
        if (baseOptional.isPresent()) {
            Base base = baseOptional.get();
            base.setName(baseDto.getName());
            base.setAddress(baseDto.getAddress());
            baseRepository.save(base);
            return ResponseEntity.ok("Base успещно обновлен");
        }
        return ResponseEntity.ok("Мы не нашли подходязий Base");
    }

    /**
     * Добавляем base в лист bases
     */
    public void addOrder(Order order) {
        Base base = order.getBase();
        base.addOrder(order);
        baseRepository.save(base);
    }

    /**
     *Ищем в репозитории объект Base по указанному baseName
     */
    public Base getBaseByName(String baseName) {
        return baseRepository.findBaseByName(baseName);
    }

    public Base getBaseById(UUID baseId) {
        return baseRepository.findBaseById(baseId);
    }

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
