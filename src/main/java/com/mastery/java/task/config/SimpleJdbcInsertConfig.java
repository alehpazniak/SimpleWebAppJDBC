package com.mastery.java.task.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

@Configuration
public class SimpleJdbcInsertConfig {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    public SimpleJdbcInsert getSimpleJdbcInsert(){
        return new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("employee")
                .usingGeneratedKeyColumns("employee_id");
    }
}
