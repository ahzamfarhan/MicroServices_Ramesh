package net.javaguides.departmentservice.repositories;

import net.javaguides.departmentservice.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByDepartmentCode(String departmentCode);
}
