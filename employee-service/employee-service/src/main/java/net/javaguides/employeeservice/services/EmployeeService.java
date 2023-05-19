package net.javaguides.employeeservice.services;

import net.javaguides.employeeservice.dtos.DepartmentDto;
import net.javaguides.employeeservice.dtos.EmployeeDto;
import net.javaguides.employeeservice.dtos.EmployeeResponseDto;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {
    public EmployeeDto saveEmployee(EmployeeDto employeeDto);

    public EmployeeResponseDto getEmployeeById(Long id);
    public EmployeeResponseDto getEmployeeByEmail(String email);

    public EmployeeResponseDto getEmployeeResponseDto(EmployeeDto employeeDto);
}
