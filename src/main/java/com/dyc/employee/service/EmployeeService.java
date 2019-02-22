package com.dyc.employee.service;

import com.dyc.employee.controller.EmployeeController;
import com.dyc.employee.model.Employee;
import com.dyc.employee.repository.EmployeeRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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

    public List<Employee> listByPage(int page, int size) {
        //Sort sort = new Sort(Sort.Direction.DESC, "id");//指定排序字段
        PageRequest pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");
        Page<Employee> p = repository.findAll(pageable);
        return  p.getContent();
    }

    //使用Specificatio进行多条件分页查询
    public List<Employee> listBySpecificatio(String name,String position,int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");

        Specification<Employee> specification = new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                if (!StringUtils.isEmpty(name)) {
                    list.add(cb.like(root.get("name").as(String.class), "%" + name + "%"));
                }

                if (!StringUtils.isEmpty(position)) {
                    list.add(cb.equal(root.get("position").as(String.class), position));
                }
                return cb.and(list.toArray(new Predicate[list.size()]));
            }
        };

        Page<Employee> employees = repository.findAll(specification, pageable);
        return employees.getContent();
    }
}
