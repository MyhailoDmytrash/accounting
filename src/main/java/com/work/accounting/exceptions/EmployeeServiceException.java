package com.work.accounting.exceptions;

public class EmployeeServiceException extends RuntimeException
{
    public static final String WRONG_EMAIL = "Wrong email";

    public EmployeeServiceException(String message) {
        super(message);
    }
}
