package com.work.accounting.exceptions;

public class DepartmentServiceException extends RuntimeException
{
    public static final String EXISTS_BY_NAME = "Exists by name";
    public static final String NOT_EXISTS_DEPARTMENT_BY_CURRENT_DIRECTOR = "Not exists department by current director";

    public DepartmentServiceException(String message) {
        super(message);
    }
}
