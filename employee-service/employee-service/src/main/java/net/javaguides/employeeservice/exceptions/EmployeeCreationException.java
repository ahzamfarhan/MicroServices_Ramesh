package net.javaguides.employeeservice.exceptions;

public class EmployeeCreationException extends  RuntimeException {

    String message;

    public EmployeeCreationException() {
        super("Exception occurred while creating and saving employee to DB");
        this.message = "Exception occurred while creating and saving employee to DB";
    }

    public EmployeeCreationException(String message) {
        super(message);
        this.message = message;
    }
}
