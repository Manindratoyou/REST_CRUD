package com.manindra.rest_crud.service.impl;

import com.manindra.rest_crud.entity.Employee;
import com.manindra.rest_crud.repository.EmployeeRepository;
import com.manindra.rest_crud.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee create(Employee employee) {
        employee.setId(null);
        Employee savedEmployee = employeeRepository.save(employee);
        log.info("Created employee with id={}", savedEmployee.getId());
        return savedEmployee;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        log.debug("Retrieved {} employees", employees.size());
        return employees;
    }

    @Override
    public Optional<Employee> findById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            log.debug("Found employee with id={}", id);
        } else {
            log.warn("Employee with id={} was not found", id);
        }
        return employee;
    }

    @Override
    public Optional<Employee> update(Long id, Employee details) {
        Optional<Employee> updatedEmployee = employeeRepository.findById(id).map(employee -> {
            employee.setName(details.getName());
            employee.setEmail(details.getEmail());
            employee.setDepartment(details.getDepartment());
            return employeeRepository.save(employee);
        });
        if (updatedEmployee.isPresent()) {
            log.info("Updated employee with id={}", id);
        } else {
            log.warn("Could not update employee; id={} was not found", id);
        }
        return updatedEmployee;
    }

    @Override
    public boolean delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            log.warn("Could not delete employee; id={} was not found", id);
            return false;
        }
        employeeRepository.deleteById(id);
        log.info("Deleted employee with id={}", id);
        return true;
    }
}
