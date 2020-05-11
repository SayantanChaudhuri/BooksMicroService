package com.sayantan.books.exceptions;

public class InvalidAuthorNameException extends RuntimeException
{
    public InvalidAuthorNameException(String exception) {
        super(exception);
    }
}