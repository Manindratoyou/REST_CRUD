package com.manindra.rest_crud.service.impl;

import com.manindra.rest_crud.entity.Employee;
import com.manindra.rest_crud.repository.EmployeeRepository;
import com.manindra.rest_crud.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee create(Employee employee) {
        employee.setId(null);
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Optional<Employee> update(Long id, Employee details) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(details.getName());
            employee.setEmail(details.getEmail());
            employee.setDepartment(details.getDepartment());
            return employeeRepository.save(employee);
        });
    }

    @Override
    public boolean delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            return false;
        }
        employeeRepository.deleteById(id);
        return true;
    }
}
