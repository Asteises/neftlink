package ru.asteises.neftlink.repositoryes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.asteises.neftlink.entity.Base;
import ru.asteises.neftlink.entity.Order;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID>, PagingAndSortingRepository<Order, UUID> {

    List<Order> findAllByVisibleTrue(Sort sort);

//    List<Order> findAllByVisibleTrue(Pageable pageable);

    List<Order> findAllByCostBetween(Long from, Long to);

    List<Order> findAllByGas_GasType(String gasType);

    List<Order> findAllByBaseName(String baseName);

    List<Order> findAllByUserId(UUID userId);
}
