package com.guistraliote.category.exceptions;

public class CategoryNotFoundException extends IllegalArgumentException {

    public CategoryNotFoundException(String message) {
        super(message);
    }
}