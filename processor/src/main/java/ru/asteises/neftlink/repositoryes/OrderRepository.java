package ru.asteises.neftlink.repositoryes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.asteises.neftlink.dto.OrderFilterDto;
import ru.asteises.neftlink.entity.Order;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID>, FindOrderByFilter {

//    List<Order> findAllByVisibleTrue(Sort sort);

    Page<Order> findAllByVisibleTrue(Pageable pageable);

    List<Order> findAllByCostBetweenAndVisibleIsTrue(Long from, Long to);

    List<Order> findAllByGas_GasType(String gasType);

    List<Order> findAllByBaseName(String baseName);

    List<Order> findAllByUserId(UUID userId);

    Page<Order> findAllByBase(OrderFilterDto orderFilterDto, Pageable pageable);

}
