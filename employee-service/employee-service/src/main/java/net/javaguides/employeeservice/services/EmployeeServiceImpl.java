package net.javaguides.employeeservice.services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import net.javaguides.employeeservice.dtos.DepartmentDto;
import net.javaguides.employeeservice.dtos.EmployeeDto;
import net.javaguides.employeeservice.dtos.EmployeeResponseDto;
import net.javaguides.employeeservice.entities.Employee;
import net.javaguides.employeeservice.exceptions.EmployeeCreationException;
import net.javaguides.employeeservice.exceptions.EmployeeNotFoundException;
import net.javaguides.employeeservice.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements  EmployeeService{

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
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

    /*

       Here we annotated @CircuitBreaker annotation to getEmployeeById(Long id) because this method made request
       end point call to department service using WebClient. In case if Department-service microservice is
       down then it will execute fallback method for default response.

 NOTE:- Default Method argument list should matach number of args, their types and return type and then it should has last argument of
               type throwable hence we are specifing Exception type argument because it is sub type of Throwable. You can give any name. This
               the requirement of Circuit Breaker default method implementation
     */

    @CircuitBreaker(name="$spring.application.name", fallbackMethod = "getDefaultDepartment")
    @Override
    public EmployeeResponseDto getEmployeeById(Long id) {

        LOGGER.info("Inside of getEmployeeById(Long id) method");

        Optional<Employee> employee =  employeeRepository.findById(id);

        if(employee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee by Id " + id + " not found");

        }

        EmployeeDto employeeDto = AutoMapper.AUTO_MAPPER.mapToEmployeeDto(employee.get());

        return getEmployeeResponseDto(employeeDto);

    }

    /*
        Fallback method for @CircuitBreaker implementation in case if response would not come from
        the dependency microservice which Department service. This method will be execute and provide
        default output to getEmployeeById() service method annotated with @CicuitBreaker annotation

 NOTE:- Default Method argument list should matach number of args, their types and return type and then it should has last argument of
               type throwable hence we are specifing Exception type argument because it is sub type of Throwable. You can give any name. This
               the requirement of Circuit Breaker default method implementation
     */
    public EmployeeResponseDto getDefaultDepartment(Long id, Exception exception) {

        LOGGER.info("Inside of getDefaultDepartment(Long id, Exception exception) method");

        Optional<Employee> employee =  employeeRepository.findById(id);

        if(employee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee by Id " + id + " not found");

        }

        EmployeeDto employeeDto = AutoMapper.AUTO_MAPPER.mapToEmployeeDto(employee.get());

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentCode("RN101");
        departmentDto.setDepartmentName("R&D");
        departmentDto.setDepartmentDescription("Reach And Development");
        departmentDto.setId(9999l);

        EmployeeResponseDto employeeResponseDto = EmployeeResponseDto.builder()
                .departmentDto(departmentDto)
                .employeeDto(employeeDto)
                .build();

        return employeeResponseDto;

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
