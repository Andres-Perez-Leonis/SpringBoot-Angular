package com.andres.planning.repository;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(scripts = "/clean-data.sql")
public class SubTaskTest {
    
}
