package com.work.accounting.exceptions;

public class EmployeeServiceException extends RuntimeException
{
    public static final String WRONG_EMAIL = "Wrong email";
    public static final String EXISTS_BY_EMAIL = "Exists by email";
    public static final String WRONG_LOGIN_OR_EMAIL = "Wrong login or email";
    public static final String WRONG_AUTHENTICATION_DATA = "Wrong authentication data";
    public static final String WRONG_ENTITY_UUID = "Wrong entity UUID";
    public static final String EMPLOYEE_ALREADY_HAS_DEPARTMENT = "Employee already has department";
    public static final String CAN_NOT_MANIPULATE_WITH_THIS_EMPLOYEE = "You can't manipulate with this employee";

    public EmployeeServiceException(String message) {
        super(message);
    }
}
