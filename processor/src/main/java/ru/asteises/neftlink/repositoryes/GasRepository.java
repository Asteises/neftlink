package ru.asteises.neftlink.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.asteises.neftlink.entity.Base;
import ru.asteises.neftlink.entity.Gas;

@Repository
public interface GasRepository extends JpaRepository<Gas, Long> {

    Gas findGasByGasType(String type);

    Gas findGasById(Long id);
}
