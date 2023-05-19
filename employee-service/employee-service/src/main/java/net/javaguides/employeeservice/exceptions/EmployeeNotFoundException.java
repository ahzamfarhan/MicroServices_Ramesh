package net.javaguides.employeeservice.exceptions;

public class EmployeeNotFoundException extends  RuntimeException {

    private String message;

    public EmployeeNotFoundException() {
        super();
        this.message = "Employee Not Found Exception";
    }

    public EmployeeNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
