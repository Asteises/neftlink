package ru.asteises.neftlink.mapper;

import org.springframework.stereotype.Service;
import ru.asteises.neftlink.dto.GasDto;
import ru.asteises.neftlink.entity.Gas;

import java.util.UUID;

/**
 * Преобразует Gas в GasDto и обратно
 */
@Service
public class GasMapper {


    public Gas gasDtoToGas(GasDto gasDto) {
        Gas gas = new Gas();
        gas.setId(UUID.randomUUID());
        gas.setGasType(gasDto.getGasType());
        gas.setVisible(Boolean.TRUE);
        return gas;
    }

    public GasDto gasToGasDto(Gas gas) {
        GasDto gasDto = new GasDto();
        gasDto.setGasType(gas.getGasType());
        return gasDto;
    }
}
