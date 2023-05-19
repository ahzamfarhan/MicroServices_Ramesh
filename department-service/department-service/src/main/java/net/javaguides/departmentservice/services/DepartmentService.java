package net.javaguides.departmentservice.services;

import net.javaguides.departmentservice.dtos.DepartmentDto;

public interface DepartmentService {

    DepartmentDto saveDepartment(DepartmentDto departmentDto);
    public DepartmentDto getDepartmentByCode(String departmentCode);
    public DepartmentDto getDepartmentById(Long departmentId);
}
