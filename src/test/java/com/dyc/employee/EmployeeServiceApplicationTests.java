package com.dyc.employee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ConfigurationProperties(prefix = "dev")
public class EmployeeServiceApplicationTests {

    @Test
    public void contextLoads() {
    }

}

