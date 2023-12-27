package io.ems.service.impl;

import io.ems.dto.EmployeeDto;
import io.ems.entity.Employee;
import io.ems.exception.ResourceNotFoundException;
import io.ems.mapper.EmployeeMapper;
import io.ems.repository.EmployeeRepository;
import io.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with the given id " + id));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeDto> employeeDtoList = employeeList.stream()
                .map((item) -> EmployeeMapper.mapToEmployeeDto(item))
                .collect(Collectors.toList());
        return employeeDtoList;
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with the given id " + id));
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        Employee updatedEmp = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmp);
    }

    // delete Employee Service implementation
    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with given id "));
        employeeRepository.deleteById(id);
    }
}