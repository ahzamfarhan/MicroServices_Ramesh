package net.javaguides.employeeservice.services;

import net.javaguides.employeeservice.dtos.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*
    Spring Boot provide use Implementation of DepartmentFeignService
    interface. we just annotated this interface with @FeignClient
    This is similar to JPA repository but in that case we do inheritance but
    in OpenFeign case no need to do inheritance just method declaration to send request
    to controller. and these method declaration signature and along with annotation
    must match with the request handling method at server side that handle
    request and send the response.
 */

@FeignClient(url = "http://localhost:8080/api/departments", name = "getDepartmentInfo")
public interface DepartmentFeignService {

    @GetMapping("byDepartmentCode/{department_code}")
    public DepartmentDto getDepartmentByDepartmentCode(
            @PathVariable("department_code") String departmentCode);
}
