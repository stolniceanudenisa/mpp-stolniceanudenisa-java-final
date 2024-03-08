package org.example.validators;

public interface Validator<T> {
    boolean validate(T entity) throws ValidationException;
}