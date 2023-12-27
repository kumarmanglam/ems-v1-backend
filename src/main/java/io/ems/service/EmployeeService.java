package io.ems.service;

import io.ems.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long id);

    List<EmployeeDto> getAllEmployee();

    EmployeeDto updateEmployee(Long id, EmployeeDto updatedEmployee);

    void deleteEmployee(Long id);
}
