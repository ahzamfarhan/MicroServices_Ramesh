package net.javaguides.departmentservice.services;

import net.javaguides.departmentservice.dtos.DepartmentDto;
import net.javaguides.departmentservice.entities.Department;
import net.javaguides.departmentservice.exceptions.DepartmentCreationException;
import net.javaguides.departmentservice.exceptions.DepartmentNotFoundException;
import net.javaguides.departmentservice.repositories.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService{

    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        try {

            //convert DepartmentDto to Department JPA entities
            Department department = modelMapper.map(departmentDto, Department.class);

            //Saving department JPA Entity
            Department savedDepartment = departmentRepository.save(department);

            //convert saved JPA Department to DepartmentDTO to return value
            DepartmentDto savedDepartmentDto = modelMapper.map(savedDepartment, DepartmentDto.class);

            //return the departmentDto value mapped to saved Department JPA entity
            return savedDepartmentDto;

        } catch (Exception e) {
            throw new DepartmentCreationException(e.getLocalizedMessage());
        }

    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {

        Optional<Department> department = Optional
                .ofNullable(departmentRepository.findByDepartmentCode(departmentCode));

        if(department.isEmpty()) {
            throw  new DepartmentNotFoundException("Department with DepartmentCode "
                    + departmentCode + " does not exist");
        }

        DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);

        return departmentDto;
    }


    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {

        Optional<Department> department = departmentRepository.findById(departmentId);

        if(department.isEmpty()) {
            throw  new DepartmentNotFoundException("Department with DepartmentCode "
                    + departmentId + " does not exist");
        }

        DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);

        return departmentDto;
    }
}
