package net.javaguides.departmentservice.controllers;

import net.javaguides.departmentservice.dtos.DepartmentDto;
import net.javaguides.departmentservice.services.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/departments")
public class DepartmentController {

    private DepartmentService departmentService;

    //build saveDepartment rest api
    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto) {

        /*
           @RequestBody uses HttpMessageConvertor to convert JSON Object to Java Object
           In our case it convert JSON object to DepartmentDto
         */

        DepartmentDto savedDepartmentDto = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<DepartmentDto>(savedDepartmentDto, HttpStatus.CREATED);
    }

    //build get department rest api

    @GetMapping("byDepartmentCode/{department_code}")
    public ResponseEntity<DepartmentDto> getDepartmentByDepartmentCode(
            @PathVariable("department_code") String departmentCode) {

        DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);

        ResponseEntity<DepartmentDto> responseBody = ResponseEntity.ok().body(departmentDto);

                    //OR

        //ResponseEntity<DepartmentDto> responseBody = new ResponseEntity(departmentDto, HttpStatus.OK);

        return responseBody;
    }
    @GetMapping("byDepartmentId/{department_id}")
    public ResponseEntity<DepartmentDto> getDepartmentByDepartmentId(
            @PathVariable("department_id") Long departmentId) {

        DepartmentDto departmentDto = departmentService.getDepartmentById(departmentId);

        ResponseEntity<DepartmentDto> responseBody = ResponseEntity.ok().body(departmentDto);

        //OR

        //ResponseEntity<DepartmentDto> responseBody = new ResponseEntity(departmentDto, HttpStatus.OK);

        return responseBody;
    }
}