package com.dyc.employee.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.dyc.employee.dto.EmployeeRequestDto;
import com.dyc.employee.dto.ApiResponse;
import com.dyc.employee.exception.ErrorCode;
import com.dyc.employee.exception.BusinessException;
import com.dyc.employee.model.Employee;
import com.dyc.employee.repository.EmployeeRepository;
import com.dyc.employee.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
//@RequestMapping("employee")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository repository;

    @PostMapping("/")
    @ResponseBody
    public Employee add(@RequestBody @Valid EmployeeRequestDto requestDto) {
        Employee employee = new Employee();
        employee.setId(UUID.randomUUID().toString());
        employee.setName(requestDto.getName());
        employee.setAge(requestDto.getAge());
        employee.setDepartmentId(requestDto.getDepartmentId());
        employee.setOrganizationId(requestDto.getOrganizationId());
        employee.setPosition(requestDto.getPosition());
        LOGGER.info("Employee add: {}", employee);
        return repository.save(employee);
    }


    @GetMapping("/{id}")
    public Optional<Employee> findById(@PathVariable("id") String id) {
        LOGGER.info("Employee find: id={}", id);
        return repository.findById(id);
    }

    @GetMapping("/")
    public List<Employee> findAll() {
        LOGGER.info("Employee find");
        return repository.findAll();
    }

    @GetMapping("/department/{departmentId}")
    public List<Employee> findByDepartment(@PathVariable("departmentId") String departmentId) {
        LOGGER.info("Employee find: departmentId={}", departmentId);
        return employeeService.findByDepartment(departmentId);
    }

    @GetMapping("/organization/{organizationId}")
    public List<Employee> findByOrganization(@PathVariable("organizationId") String organizationId) {
        LOGGER.info("Employee find: organizationId={}", organizationId);
        return employeeService.findByOrganization(organizationId);
    }

    @RequestMapping("/list/page")
    public List<Employee> listByPage(int page, int size) {
        return employeeService.listByPage(page, size);
    }

    //返回通用结构体  成功
    @GetMapping("/dto1/{id}")
    public ApiResponse findByEmployeeId(@PathVariable("id") String id) {
        LOGGER.info("Employee find: id={}", id);
        return ApiResponse.ok(repository.findById(id));
    }

    //返回通用结构体  错误
    @GetMapping("/dto2/{id}")
    public ApiResponse findByEmployeeId2(@PathVariable("id") String id) {
        Integer.parseInt("abc");
        return ApiResponse.ok(repository.findById(id));
    }


    @GetMapping("/error/{id}")
    public Optional<Employee> testOther(@PathVariable("id") String id) {
        Integer.parseInt("abc");
        return repository.findById(id);
    }

    @GetMapping("/error/notfound/{id}")
    public Optional<Employee> testNotFondError(@PathVariable("id") String id) {
        if (2 > 1) {
            throw new BusinessException(ErrorCode.EMPLOYEE_NOT_FOUND, "EMPLOYEE not found in db.");
        }
        return repository.findById(id);
    }

}
