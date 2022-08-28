package ru.asteises.neftlink.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.asteises.neftlink.dto.OrderDto;
import ru.asteises.neftlink.dto.OrderFilterDto;
import ru.asteises.neftlink.entity.Order;
import ru.asteises.neftlink.mapper.OrderMapper;
import ru.asteises.neftlink.repositoryes.OrderRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Отвечает за всю бизнес-логику связанную с Order (все что может происходить с объектами типа Order)
 */
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final GasService gasService;
    private final BaseService baseService;

    @PersistenceContext //Для работы с БД и автоматического подтягивания Context из Spring
    private EntityManager entityManager;


    /**
     * Создаем объект Order из OrderDto и сохраняем в базу данных
     */
    public String add(OrderDto orderDto) {
        Order order = OrderMapper.INSTANCE.toOrder(orderDto, userService, gasService, baseService);
        orderRepository.save(order);
        return "заказ успешно добавлен в репозиторий";
    }

    /**
     * Находим объект Order по ID, меняем цену и время обновления
     */
    //TODO Методы AuthService для получения текущего пользователя
    public ResponseEntity<String> put(Principal principal,
                                      UUID orderId,
                                      Long price,
                                      UUID userId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (order.getUser().getId().equals(userId) && principal.getName().equals(order.getUser().getName())) {
                order.setCost(price);
                order.setUpdateDate(LocalDateTime.now());
                orderRepository.save(order);
                return ResponseEntity.ok("Order.Cost успешно обновлена");
            } else {
                return ResponseEntity.ok("Order не принадлежит данному User");
            }
        }
        return ResponseEntity.ok("мы не нашли подходящий Order");
    }

    /**
     * Меняем order visible
     */
    public ResponseEntity<String> setVisibleFalse(UUID orderId, UUID userId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (order.getUser().getId().equals(userId)) {
                order.setVisible(Boolean.FALSE);
                orderRepository.save(order);
                order.setUpdateDate(LocalDateTime.now());
                orderRepository.save(order);
                return ResponseEntity.ok("Order.Cost успешно обновлена");
            } else {
                return ResponseEntity.ok("Order не принадлежит данному User");
            }
        }
        return ResponseEntity.ok("Мы не нашли подходящй order");
    }

    /**
     * Достаем все order
     */
//    public ResponseEntity<List<Order>> getVisibleOrders() {
//        List<Order> orders = orderRepository.findAllByVisibleTrue(Sort.by("updateDate").descending());
//        return ResponseEntity.ok(orders);
//    }
    public ResponseEntity<Page<Order>> getVisibleOrders() {
        Pageable firstPageWithTwoElements = PageRequest.of(0, 2, Sort.by("updateDate").descending());
        Page<Order> orders = orderRepository.findAllByVisibleTrue(firstPageWithTwoElements);
        return ResponseEntity.ok(orders);
    }

    public ResponseEntity<List<OrderDto>> getOrdersByCost(Long from, Long to) {
        List<Order> orders = orderRepository.findAllByCostBetweenAndVisibleIsTrue(from, to);
        List<OrderDto> orderDtos = orders.stream()
                .map(order -> OrderMapper.INSTANCE.toDto(order))
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDtos);
    }

    public ResponseEntity<List<Order>> getOrdersByGas(String gasType) {
        List<Order> orders = orderRepository.findAllByGas_GasType(gasType);
        return ResponseEntity.ok(orders);
    }

    public ResponseEntity<List<Order>> getOrdersByBase(String baseName) {
        List<Order> orders = orderRepository.findAllByBaseName(baseName);
        return ResponseEntity.ok(orders);
    }

    public ResponseEntity<List<Order>> getOrdersByUser(UUID userId) {
        List<Order> orders = orderRepository.findAllByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    public ResponseEntity<List<Order>> getOrdersByFilter(OrderFilterDto orderFilterDto) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> query = cb.createQuery(Order.class);
        Root<Order> order = query.from(Order.class);
        List<Predicate> predicates = new ArrayList<>();
        if (orderFilterDto.getBaseName() != null) {
            Predicate baseNamePredicate = cb.equal(order.get("base").get("name"), orderFilterDto.getBaseName());
            predicates.add(baseNamePredicate);
        }
        if (orderFilterDto.getCostFrom() != null && orderFilterDto.getCostTo() != null) {
            Predicate costFromToPredicate = cb.between(order.get("cost"),
                    orderFilterDto.getCostFrom(),
                    orderFilterDto.getCostTo());
            predicates.add(costFromToPredicate);
        }
        if (orderFilterDto.getGasType() != null) {
            Predicate gasTypePredicate = cb.equal(order.get("gas").get("gasType"), orderFilterDto.getGasType());
            predicates.add(gasTypePredicate);
        }
        if (orderFilterDto.getInn() != null) {
            Predicate innPredicate = cb.equal(order.get("user").get("inn"), orderFilterDto.getInn());
            predicates.add(innPredicate);
        }
        Predicate orderPredicate = cb.and(predicates.toArray(new Predicate[0])); // Проставляем между всеми значениями AND
        query.where(orderPredicate);
        TypedQuery<Order> typedQuery = entityManager.createQuery(query);
        return ResponseEntity.ok(typedQuery.getResultList());
    }
}
