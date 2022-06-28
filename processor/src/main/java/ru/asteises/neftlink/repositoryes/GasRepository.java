package ru.asteises.neftlink.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.asteises.neftlink.entity.Gas;

import java.util.List;
import java.util.UUID;

@Repository
public interface GasRepository extends JpaRepository<Gas, UUID> {

    Gas findGasByGasType(String type);

    Gas findGasById(UUID id);

    List<Gas> findAllByVisibleTrue();
}
