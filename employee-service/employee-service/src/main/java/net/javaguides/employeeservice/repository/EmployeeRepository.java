package net.javaguides.employeeservice.repository;

import net.javaguides.employeeservice.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public Employee findByEmail(String email);
}
