package ru.asteises.neftlink.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.asteises.neftlink.entity.Base;

import java.util.List;
import java.util.UUID;

@Repository
public interface BaseRepository extends JpaRepository<Base, UUID> {

    Base findBaseByName(String name);

    Base findBaseById(UUID id);

    List<Base> findAllByVisibleTrue();
}
