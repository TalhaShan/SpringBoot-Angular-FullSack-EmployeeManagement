package com.springangular.span.service;

import com.springangular.span.exception.ResourceNotFoundException;
import com.springangular.span.model.Employee;
import com.springangular.span.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();

    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Employee not exist with Id:" + id));
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Employee not exist with Id:" + id));

        if (employee == null) {
            throw new ResourceNotFoundException("Employee not exist with Id:" + id);
        }
        if (employeeDetails.getEmailId() != null) {
            employee.setEmailId(employeeDetails.getEmailId());
        }

        if (employeeDetails.getFirstName() != null) {
            employee.setFirstName(employeeDetails.getFirstName());
        }
        if (employeeDetails.getLastName() != null) {
            employee.setLastName(employeeDetails.getLastName());
        }
        return employeeRepository.save(employee);

    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Employee not exist with Id:" + id));

        employeeRepository.delete(employee);
    }
}