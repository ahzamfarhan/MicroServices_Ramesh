package net.javaguides.departmentservice.exceptions;

public class DepartmentNotFoundException extends  RuntimeException {

    private String message;

    public DepartmentNotFoundException() {
        super();
        this.message = "Department Not Found Exception";
    }

    public DepartmentNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
