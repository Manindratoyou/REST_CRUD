package com.manindra.rest_crud.service;

import com.manindra.rest_crud.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee create(Employee employee);

    List<Employee> findAll();

    Optional<Employee> findById(Long id);

    Optional<Employee> update(Long id, Employee details);

    boolean delete(Long id);
}
