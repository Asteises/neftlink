package ru.asteises.neftlink.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.asteises.neftlink.dto.OrderFilterDto;
import ru.asteises.neftlink.entity.Order;
import ru.asteises.neftlink.repositoryes.FindOrderByFilter;
import ru.asteises.neftlink.repositoryes.OrderRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindOrderByFilterImpl implements FindOrderByFilter {

    @Lazy
    private final OrderRepository orderRepository;
    @PersistenceContext //Для работы с БД и автоматического подтягивания Context из Spring;
    private EntityManager entityManager;

    /**
     * Для пагинации необходимо: размер страницы, сдвиг, общее число элементов и общее число страниц.
     */
    @Override
    public List<Order> getOrdersByFilter(OrderFilterDto orderFilterDto, int elements, int shift) {
        // Менеджер предикатов;
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> query = cb.createQuery(Order.class);
        // Что-то вроде RawMapper, то что позволяет работать с объектами определенных классов;
        Root<Order> order = query.from(Order.class);
        List<Predicate> predicates = getPredicates(orderFilterDto, cb, order);
        // BASE = ? AND BETWEEN ? AND ?
        // .and - проставляем между всеми значениями AND;
        Predicate orderPredicate = cb.and(predicates.toArray(new Predicate[0]));
        // WHERE BASE = ? AND BETWEEN ? AND ?
        query.where(orderPredicate);
        // SELECT * FROM order WHERE BASE = ? ...
        TypedQuery<Order> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public long countByFilter(OrderFilterDto orderFilterDto) {
        // Менеджер предикатов;
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        // Какой объект мы хотим получить в результате обработки запроса;
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        // Что-то вроде RowMapper, то что позволяет работать с объектами определенных классов;
        Root<Order> order = query.from(Order.class);
        // Создаем лист предикатов используя метод getPredicates();
        List<Predicate> predicates = getPredicates(orderFilterDto, cb, order);
        // SELECT COUNT(*)
        query.select(cb.count(order));
        // BASE = ? AND BETWEEN ? AND ?
        // .and - проставляем между всеми значениями AND;
        Predicate orderPredicate = cb.and(predicates.toArray(new Predicate[0]));
        // WHERE BASE = ? AND BETWEEN ? AND ?
        query.where(orderPredicate);
        // SELECT COUNT(*) FROM order WHERE BASE = ? ...
        TypedQuery<Long> typedQuery = entityManager.createQuery(query);
        return typedQuery.getSingleResult();
    }

    public List<Predicate> getPredicates(OrderFilterDto orderFilterDto,
                                         CriteriaBuilder cb,
                                         Root<Order> order) {
        List<Predicate> predicates = new ArrayList<>();
        if (orderFilterDto.getBaseName() != null) {
            // BASE = ?
            Predicate baseNamePredicate = cb.equal(order.get("base").get("name"), orderFilterDto.getBaseName());
            predicates.add(baseNamePredicate);
        }
        if (orderFilterDto.getCostFrom() != null && orderFilterDto.getCostTo() != null) {
            // BETWEEN ? and ?
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

        return predicates;
    }
}
