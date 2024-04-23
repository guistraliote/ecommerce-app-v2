package com.guistraliote.order.exceptions;

public class OrderNotFoundException extends IllegalArgumentException{

    public OrderNotFoundException(String message) {
        super(message);
    }
}
