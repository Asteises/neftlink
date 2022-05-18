package ru.asteises.neftlink.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.asteises.neftlink.entity.Base;

import java.util.List;

@Repository
public interface BaseRepository extends JpaRepository<Base, Long> {

    Base findBaseByName(String name);

    Base findBaseById(Long id);

    List<Base> findAllByVisibleTrue();

}
