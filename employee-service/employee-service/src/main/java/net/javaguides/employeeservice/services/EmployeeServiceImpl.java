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
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements  EmployeeService{

    private EmployeeRepository employeeRepository;
    private WebClient webClient;
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

        DepartmentDto departmentDto = webClient

                //We want HTTP GET request method
                .get()

                //This is the URL resource where we want to send request
                .uri("http://localhost:8080/api/departments/byDepartmentCode/"
                        + employeeDto.getDepartmentCode())

                /*
                    Telling that retrieve the data but did specify how. It need
                    following feedback.
                     1. single response or multiple responses one by one.
                     2. synchronous or asynchronous response/responses
                     3. but just retrieve
                 */
                .retrieve()

                /*
                   It tells that we want to single response or single element.
                   But it does not specify if synchronously or asychronously.
                */
                .bodyToMono(DepartmentDto.class)

                /*
                    This request should synchronous hence we call block() method
                    in call chain
                 */
                .block();

        EmployeeResponseDto employeeResponseDto = EmployeeResponseDto.builder()
                .departmentDto(departmentDto)
                .employeeDto(employeeDto)
                .build();

        return employeeResponseDto;
    }
}