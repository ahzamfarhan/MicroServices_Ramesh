package net.javaguides.departmentservice.exceptions;

public class DepartmentCreationException extends  RuntimeException {

    String message;

    public DepartmentCreationException() {
        super("Exception occurred while creating and saving department to DB");
        this.message = "Exception occurred while creating and saving department to DB";
    }

    public DepartmentCreationException(String message) {
        super(message);
        this.message = message;
    }
}
