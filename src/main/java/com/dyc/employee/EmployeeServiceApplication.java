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
        repository.add(new Employee(1L, 1L, "zhangsan", 34, "beijing"));
        repository.add(new Employee(1L, 1L, "lisi", 37, "shanghai"));
        repository.add(new Employee(1L, 1L, "wangmazi", 26, "tianjin"));
        repository.add(new Employee(1L, 2L, "lizicheng", 39, "chongqing"));
        repository.add(new Employee(1L, 2L, "jinzhengen", 27, "xian"));
        repository.add(new Employee(2L, 3L, "wangjianlin", 38, "chengdu"));
        repository.add(new Employee(2L, 3L, "zhuzhishan", 34, "hongkong"));
        repository.add(new Employee(2L, 3L, "mayun", 30, "xiamen"));
        repository.add(new Employee(2L, 4L, "mahuateng", 25, "wuhan"));
        repository.add(new Employee(2L, 4L, "lijiacheng", 30, "shenzhen"));
        return repository;
    }

}

