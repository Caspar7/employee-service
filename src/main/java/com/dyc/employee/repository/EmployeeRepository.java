package com.dyc.employee.repository;

import com.dyc.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>, CrudRepository<Employee, String>, JpaSpecificationExecutor {

    //使用native SQL 例子
    @Query(value = "select * from employee where name = :name", nativeQuery = true)
    List<Employee> findByName(@Param("name") String supplierCode);
}
