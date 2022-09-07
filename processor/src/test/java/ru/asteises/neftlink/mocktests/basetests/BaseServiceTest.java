package ru.asteises.neftlink.mocktests.basetests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.asteises.neftlink.dto.BaseDto;
import ru.asteises.neftlink.entity.Base;
import ru.asteises.neftlink.service.BaseService;

@SpringBootTest
public class BaseServiceTest {
    //TODO Настроить тестовую БД H2
    @Autowired
    private BaseService baseService;


    @Test
    public void addBaseTest() {
        BaseDto baseDto = getBaseDtoTest();
        Base base = baseService.add(baseDto);

        Assert.assertNotNull(base);
        Assert.assertEquals("Test Base", base.getName());
        Assert.assertEquals("Test Base Address", base.getAddress());
        Assert.assertNotNull(base.getId());
    }

    private BaseDto getBaseDtoTest() {
        BaseDto baseDto = new BaseDto();
        baseDto.setName("Test Base");
        baseDto.setAddress("Test Base Address");
        return baseDto;
    }
}
