package io.ems.controller;

import io.ems.dto.EmployeeDto;
import io.ems.entity.Employee;
import io.ems.mapper.EmployeeMapper;
import io.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    // Build employees REST api
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // Build Get Employee REST API
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
        EmployeeDto employeeById = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeById);
    }

    // Build Get All Employee REST API
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmp(){
        List<EmployeeDto> allEmployee = employeeService.getAllEmployee();
        return ResponseEntity.ok(allEmployee);
    }

    // Build Update Employee REST API
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long id,
                                                      @RequestBody EmployeeDto employeeDto){
        EmployeeDto employeeDto1 = employeeService.updateEmployee(id, employeeDto);
        return ResponseEntity.ok(employeeDto1);
    }

    // Build Delete Employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted Successfully!!!");
    }
}