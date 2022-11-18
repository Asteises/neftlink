package ru.asteises.neftlink.mocktests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import ru.asteises.neftlink.dto.OrderFilterDto;
import ru.asteises.neftlink.dto.PageResponse;
import ru.asteises.neftlink.entity.Order;
import ru.asteises.neftlink.repositoryes.OrderRepository;
import ru.asteises.neftlink.service.OrderService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Эти тесты не поднимают БД, можно не обращаться к дополнительным сервисам,
 * вместо этого мы заставляем вернуть нужные нам значения
 *
 * Два способа запустить Mockito: с помощью аннотации @RunWith(MockitoJUnitRunner.class),
 *
 * или через метод @Rule MockitoRule mockitoRule = MockitoJUnit.rule();
 */
@RunWith(MockitoJUnitRunner.class)
public class MockRepositoryTest {

    @InjectMocks // Где моки вызываются;
    private OrderService orderService;


    @Mock // Что мокаем;
    private OrderRepository orderRepository;

    @Test
    public void findByFilterTest() {
        List<Order> orders = getOrders();
        OrderFilterDto orderFilterDto = getOrderFilterDto();

        //Mockito.any(OrderFilterDto.class) - можно подставить вместо первого параметра;
        //Mockito.anyInt() - - можно подставить вместо второго и третьего параметров;
        Mockito.when(orderRepository.getOrdersByFilter(orderFilterDto, 5, 1)).thenReturn(orders);
        Mockito.when(orderRepository.countByFilter(orderFilterDto)).thenReturn(1L);

        ResponseEntity<PageResponse<Order>> actualPageResponse = orderService
                .getOrdersByFilter(orderFilterDto, 5, 1);

        Assert.assertNotNull(actualPageResponse.getBody().getValues());
        Assert.assertEquals(1, actualPageResponse.getBody().getValues().size());
        Assert.assertNotNull(actualPageResponse.getBody().getPageInfo());
        Assert.assertEquals(5, actualPageResponse.getBody().getPageInfo().getElements());
        Assert.assertEquals(1, actualPageResponse.getBody().getPageInfo().getShift());
        Assert.assertEquals(1, actualPageResponse.getBody().getPageInfo().getPages());
        Assert.assertEquals(1, actualPageResponse.getBody().getPageInfo().getCurrentSize());
        Assert.assertEquals(1, actualPageResponse.getBody().getPageInfo().getElementsCount());
    }

    public List<Order> getOrders() {
        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setVisible(true);
        order.setCost(55000L);
        order.setCreateDate(LocalDateTime.now());
        order.setUpdateDate(LocalDateTime.now());
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        return orders;
    }

    public OrderFilterDto getOrderFilterDto() {
        OrderFilterDto orderFilterDto = new OrderFilterDto();
        orderFilterDto.setCostFrom(10000L);
        orderFilterDto.setCostTo(70000L);
        return orderFilterDto;
    }
}

