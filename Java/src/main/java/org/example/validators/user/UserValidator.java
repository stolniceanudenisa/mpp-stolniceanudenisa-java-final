package org.example.validators.user;

import org.example.model.User;
import org.example.validators.ValidationException;
import org.example.validators.Validator;

import java.util.ArrayList;
import java.util.List;

public class UserValidator implements Validator<User> {
    @Override
    public boolean validate(User entity) throws ValidationException {

        List<String> errors = new ArrayList<>();
        if (entity.getUsername().isEmpty())
            errors.add("Username-ul nu poate fi null!");

        if (entity.getPassword().isEmpty())
            errors.add("Parola nu poate fi nula!");

        if (!errors.isEmpty()) {
            throw new ValidationException(String.join("\n", errors));
        }

        return true;
    }
}