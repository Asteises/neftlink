package ru.asteises.neftlink.service;

import org.springframework.stereotype.Service;
import ru.asteises.neftlink.dto.GasDto;
import ru.asteises.neftlink.entity.Gas;
import ru.asteises.neftlink.mapper.GasMapper;
import ru.asteises.neftlink.repositoryes.GasRepository;

/**
 * Отвечает за всю бизнес-логику связанную с Gas (все что может происходить с объектами типа Gas)
 */
@Service
public class GasService {

    private final GasRepository gasRepository;
    private final GasMapper gasMapper;

    public GasService(GasRepository gasRepository, GasMapper gasMapper) {
        this.gasRepository = gasRepository;
        this.gasMapper = gasMapper;
    }

    /**
     *Создаем объект Gas из GasDto и сохраняем в базу данных
     */
    public String add(GasDto gasDto) {
        gasRepository.save(gasMapper.gasDtoToGas(gasDto));
        return "тип топлива был успешно добавлен";
    }

    public Gas getGasByName(String gasType) {
        return gasRepository.findGasByGasType(gasType);
    }

    public Gas getGasById(Long id) {
        return gasRepository.findGasById(id);
    }
}
