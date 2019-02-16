package com.dyc.employee.service;

import com.dyc.employee.controller.EmployeeController;
import com.dyc.employee.model.Employee;
import com.dyc.employee.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository repository;

    public List<Employee> findByDepartment(String departmentId) {
        List<Employee> employees  = repository.findAll();
        return employees.stream().filter(a -> a.getDepartmentId().equals(departmentId)).collect(Collectors.toList());
    }


    public List<Employee> findByOrganization(String organizationId) {
        List<Employee> employees  = repository.findAll();
        return employees.stream().filter(a -> a.getOrganizationId().equals(organizationId)).collect(Collectors.toList());
    }
}
