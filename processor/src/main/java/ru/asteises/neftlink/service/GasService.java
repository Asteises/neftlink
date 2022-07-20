package ru.asteises.neftlink.service;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.asteises.neftlink.dto.GasDto;
import ru.asteises.neftlink.entity.Gas;
import ru.asteises.neftlink.entity.Order;
import ru.asteises.neftlink.mapper.GasMapper;
import ru.asteises.neftlink.repositoryes.GasRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Отвечает за всю бизнес-логику связанную с Gas (все что может происходить с объектами типа Gas)
 */
@Service
public class GasService {

    private final GasRepository gasRepository;

    public GasService(GasRepository gasRepository) {
        this.gasRepository = gasRepository;
    }

    /**
     * Создаем объект Gas из GasDto и сохраняем в базу данных
     */
    public String add(GasDto gasDto) {
        gasRepository.save(GasMapper.INSTANCE.toGas(gasDto));
        return "тип топлива был успешно добавлен";
    }

    /**
     * Пробуем заменить gasType по id
     */
    public ResponseEntity<String> put(GasDto gasDto, UUID id) {
        Optional<Gas> gasOptional = gasRepository.findById(id);
        if (gasOptional.isPresent()) {
            Gas gas = gasOptional.get();
            gas.setGasType(gasDto.getGasType());
            gasRepository.save(gas);
            return ResponseEntity.ok("Gas успешно обновлен");
        }
        return ResponseEntity.ok("Мы не нашли подходящий Gas");
    }

    /**
     * Достаем gas по gasType
     */
    public Gas getGasByName(String gasType) {
        return gasRepository.findGasByGasType(gasType);
    }

    /**
     * Достаем gas по id
     */
    public ResponseEntity<Gas> getGasById(UUID id) {
        Gas gas = gasRepository.findGasById(id);
        return ResponseEntity.ok(gas);
    }

    /**
     * Меняем gas visible
     */
    public ResponseEntity<String> setVisibleFalse(UUID id) {
        Optional<Gas> optionalGas = gasRepository.findById(id);
        if (optionalGas.isPresent()) {
            Gas gas = optionalGas.get();
            gas.setVisible(Boolean.FALSE);
            gasRepository.save(gas);
            return ResponseEntity.ok("Gas успешно удален");
        }
        return ResponseEntity.ok("Мы не нашли подходящий gas");
    }

    /**
     * Достаем все gas
     */
    public ResponseEntity<List<Gas>> getVisibleGases() {
        List<Gas> gases = gasRepository.findAllByVisibleTrue(Sort.by("gasType"));
        return ResponseEntity.ok(gases);
    }
}
