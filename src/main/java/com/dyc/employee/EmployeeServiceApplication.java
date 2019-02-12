package com.dyc.employee;

import com.dyc.employee.model.Employee;
import com.dyc.employee.repository.EmployeeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
public class EmployeeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeServiceApplication.class, args);
    }

    @Bean
    public Docket swaggerPersonApi10() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dyc.employee.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder().version("1.0").title("Employee API").description("Documentation Employee API v1.0").build());
    }

    @Bean
    EmployeeRepository repository() {
        EmployeeRepository repository = new EmployeeRepository();
        repository.add(new Employee(1L, 1L, "李世民", 34, "北京"));
        repository.add(new Employee(1L, 1L, "刘邦", 37, "上海"));
        repository.add(new Employee(1L, 1L, "项羽", 26, "天津"));
        repository.add(new Employee(1L, 2L, "李自成", 39, "重庆"));
        repository.add(new Employee(1L, 2L, "金正恩", 27, "西安"));
        repository.add(new Employee(2L, 3L, "王健林", 38, "成都"));
        repository.add(new Employee(2L, 3L, "祝枝山", 34, "香港"));
        repository.add(new Employee(2L, 3L, "马云", 30, "杭州"));
        repository.add(new Employee(2L, 4L, "马化腾", 25, "武汉"));
        repository.add(new Employee(2L, 4L, "李嘉诚", 30, "深圳"));
        return repository;
    }

}

