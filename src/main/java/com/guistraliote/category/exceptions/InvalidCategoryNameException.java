package com.guistraliote.category.exceptions;

public class InvalidCategoryNameException extends RuntimeException {

    public InvalidCategoryNameException(String message) {
        super(message);
    }
}