package com.tomlucksted.springbootcourse.shared.error;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String type, Object id) {
        super(type + " with id " + id + " not found");
    }
}