package ru.asteises.neftlink;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "ru.asteises.neftlink.repositoryes")
@PropertySource("classpath:application.yaml")
@EnableTransactionManagement
public class TestConfig {
}
