package net.javaguides.employeeservice.services;

import net.javaguides.employeeservice.dtos.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "DEPARTMENT-SERVICE")
public interface DepartmentFeignService {

    @GetMapping("api/departments/byDepartmentCode/{department_code}")
    public DepartmentDto getDepartmentByDepartmentCode(
            @PathVariable("department_code") String departmentCode);
}
