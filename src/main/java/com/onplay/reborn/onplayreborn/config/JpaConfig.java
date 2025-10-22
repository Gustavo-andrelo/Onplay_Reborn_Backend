package com.onplay.reborn.onplayreborn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.onplay.reborn.onplayreborn.repositories")
@EnableTransactionManagement
public class JpaConfig {
}