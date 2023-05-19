package net.javaguides.employeeservice.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class EmployeeResponseDto {

    private EmployeeDto employeeDto;
    private DepartmentDto departmentDto;
}
