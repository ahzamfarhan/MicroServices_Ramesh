package net.javaguides.employeeservice.services;

import net.javaguides.employeeservice.dtos.EmployeeDto;
import net.javaguides.employeeservice.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoMapper {

    AutoMapper AUTO_MAPPER = Mappers.getMapper(AutoMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "departmentCode", target = "departmentCode")
    })
    public EmployeeDto mapToEmployeeDto(Employee employee);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "departmentCode", target = "departmentCode")
    })
    public Employee mapToEmployee(EmployeeDto employeeDto);

}
