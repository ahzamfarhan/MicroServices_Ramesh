package net.javaguides.employeeservice.controllers;


import lombok.AllArgsConstructor;
import net.javaguides.employeeservice.dtos.EmployeeDto;
import net.javaguides.employeeservice.dtos.EmployeeResponseDto;
import net.javaguides.employeeservice.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    private EmployeeService employeeService;
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {

        EmployeeDto savedEmployeeDto = employeeService.saveEmployee(employeeDto);

        ResponseEntity<EmployeeDto> responseEntity =
                new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);

        return  responseEntity;

    }


    @GetMapping("byId/{empId}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable("empId") Long id) {

        EmployeeResponseDto employeeResponseDto = employeeService.getEmployeeById(id);

        ResponseEntity<EmployeeResponseDto> responseEntity =
                ResponseEntity.ok(employeeResponseDto);

        return  responseEntity;

    }
    @GetMapping("byEmail/{email}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeByEmail(@PathVariable String email) {

        EmployeeResponseDto employeeResponseDto = employeeService.getEmployeeByEmail(email);

        ResponseEntity<EmployeeResponseDto> responseEntity =
                new ResponseEntity<>(employeeResponseDto, HttpStatus.CREATED);

        return responseEntity;

    }


}
