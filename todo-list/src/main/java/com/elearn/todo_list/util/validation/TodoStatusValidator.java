package com.elearn.todo_list.util.validation;

import com.elearn.todo_list.model.Todo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class TodoStatusValidator implements ConstraintValidator<ValidStatus, Todo.Status> {

    @Override
    public boolean isValid(Todo.Status value, ConstraintValidatorContext context) {
        return false;
    }

}
