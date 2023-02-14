package ru.asteises.neftlink;

import com.tngtech.java.junit.dataprovider.UseDataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.asteises.neftlink.dto.OrderFilterDto;
import ru.asteises.neftlink.dto.PageResponse;
import ru.asteises.neftlink.entity.Order;
import ru.asteises.neftlink.service.OrderService;


@SpringBootTest(classes = NeftLinkApplication.class)
@ContextConfiguration(classes = TestConfig.class)
@TestPropertySource("classpath:application.yaml")
public class FindByFilterTest {

    @Autowired
    private OrderService orderService;

    @Test(dataProvider = "filterDtoData")
    public void exampleTest(OrderFilterDto orderFilterDto, int orders) {
        System.out.println(orderService.getVisibleOrders());
        //ResponseEntity<PageResponse<Order>> check = orderService.getOrdersByFilter(orderFilterDto, 10, 0);
        //Assert.assertEquals(orders, check.getBody().getValues().size());
    }

    @DataProvider(name = "filterDtoData")
    public static Object[][] filterDtoData() {
//        return new Object[][]{
//                {new OrderFilterDto(100L, 10000L, "98", "MPZ-3", 77777777, null, null)
//                        , 1},
//                {new OrderFilterDto(100L, 10000L, "92", null, 77777777, null, null)
//                        , 2}
//        };
        return null;
    }
}
