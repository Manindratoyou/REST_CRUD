package com.manindra.rest_crud.repository;

import com.manindra.rest_crud.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
