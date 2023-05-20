package net.javaguides.employeeservice.services;

import lombok.AllArgsConstructor;
import net.javaguides.employeeservice.dtos.DepartmentDto;
import net.javaguides.employeeservice.dtos.EmployeeDto;
import net.javaguides.employeeservice.dtos.EmployeeResponseDto;
import net.javaguides.employeeservice.entities.Employee;
import net.javaguides.employeeservice.exceptions.EmployeeCreationException;
import net.javaguides.employeeservice.exceptions.EmployeeNotFoundException;
import net.javaguides.employeeservice.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements  EmployeeService{

    /*
        Injecting the OpenFeign implementation of DepartmentFeignService
        We have to specify just interface annotated with @FeignClient and
        Spring Boot provide you implementation similar to JPA repository
        but in that case we do inheritance but in OpenFeign case no need
        to do inheritance just method declaration to send request
        to controller. and these method declaration signature and along with annotation
        must match with the request handling method at server side that handle
        request and send the response.
     */
    private DepartmentFeignService departmentFeignService;
    private EmployeeRepository employeeRepository;
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

        /*
            getDepartmentByDepartmentCode(..) is called to Spring Boot provided implementation of method
            which we just declared inside of DepartmentFeignService interface. This implementation method
            call send http request to server to get response. In Declaration we have to following following
            rules.

                . Method parameter list, annotation can be matched with server side request
                  handler method.

                . Returned type must compatible with response body payload.


        */
        DepartmentDto departmentDto =
                departmentFeignService.getDepartmentByDepartmentCode(employeeDto.getDepartmentCode());

        EmployeeResponseDto employeeResponseDto = EmployeeResponseDto.builder()
                .departmentDto(departmentDto)
                .employeeDto(employeeDto)
                .build();

        return employeeResponseDto;
    }
}