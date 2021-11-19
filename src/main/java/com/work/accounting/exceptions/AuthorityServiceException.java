package com.work.accounting.exceptions;

public class AuthorityServiceException extends RuntimeException
{
    public static final String WRONG_AUTHORITY_NAME = "Wrong authority name";

    public AuthorityServiceException(String message) {
        super(message);
    }
}
