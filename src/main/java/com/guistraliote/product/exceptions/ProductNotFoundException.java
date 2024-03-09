package com.guistraliote.product.exceptions;

    public class ProductNotFoundException extends IllegalArgumentException {

        public ProductNotFoundException(String message) {
            super(message);
        }
    }
