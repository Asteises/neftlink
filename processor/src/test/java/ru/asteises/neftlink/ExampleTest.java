package ru.asteises.neftlink;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.asteises.neftlink.repositoryes.GasRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NeftLinkApplication.class)
//@ContextConfiguration(classes = TestConfig.class)
@TestPropertySource("classpath:application-test.yaml")
public class ExampleTest {

    @Autowired
    private GasRepository gasRepository;
    @Value("${qwe.asd}")
    private String qwe;

    @Test
    public void exampleTest() {
        System.out.println(qwe);
    }
}
