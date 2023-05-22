package net.javaguides.employeeservice.services;

import lombok.AllArgsConstructor;
import net.javaguides.employeeservice.dtos.DepartmentDto;
import net.javaguides.employeeservice.dtos.EmployeeDto;
import net.javaguides.employeeservice.dtos.EmployeeResponseDto;
import net.javaguides.employeeservice.entities.Employee;
import net.javaguides.employeeservice.exceptions.EmployeeCreationException;
import net.javaguides.employeeservice.exceptions.EmployeeNotFoundException;
import net.javaguides.employeeservice.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements  EmployeeService{

    private EmployeeRepository employeeRepository;
    private DepartmentFeignService departmentFeignService;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = AutoMapper.AUTO_MAPPER.mapToEmployee(employeeDto);

        try {

            Employee savedEmployee  = employeeRepository.save(employee);

            EmployeeDto savedEmployeeDto = AutoMapper.AUTO_MAPPER
                    .mapToEmployeeDto(savedEmployee);

            return  savedEmployeeDto;

        } catch (Exception exception) {
            throw  new EmployeeCreationException(exception.getMessage());
        }

    }
    @Override
    public EmployeeResponseDto getEmployeeById(Long id) {

        Optional<Employee> employee =  employeeRepository.findById(id);

        if(employee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee by Id " + id + " not found");

        }

        EmployeeDto employeeDto = AutoMapper.AUTO_MAPPER.mapToEmployeeDto(employee.get());

        return getEmployeeResponseDto(employeeDto);

    }

    public EmployeeResponseDto getEmployeeByEmail(String email) {

        Optional<Employee> foundEmployee = Optional.ofNullable(employeeRepository.findByEmail(email));

        if (foundEmployee.isEmpty() ) {
            throw new EmployeeNotFoundException("Employee by email " + email + " not found");
        }

        EmployeeDto employeeDto =  AutoMapper.AUTO_MAPPER.mapToEmployeeDto(foundEmployee.get());

        return getEmployeeResponseDto(employeeDto);

    }
    public EmployeeResponseDto getEmployeeResponseDto(EmployeeDto employeeDto) {

        DepartmentDto departmentDto = departmentFeignService.getDepartmentByDepartmentCode(employeeDto.getDepartmentCode());

        EmployeeResponseDto employeeResponseDto = EmployeeResponseDto.builder()
                .departmentDto(departmentDto)
                .employeeDto(employeeDto)
                .build();

        return employeeResponseDto;
    }
}
