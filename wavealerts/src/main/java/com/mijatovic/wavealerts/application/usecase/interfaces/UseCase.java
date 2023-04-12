package com.mijatovic.wavealerts.application.usecase.interfaces;

import com.mijatovic.wavealerts.application.usecase.exception.InvalidInputException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface UseCase<I extends UseCase.InputValues> {
    interface InputValues {}
    void execute(I input);

    /**
     * Validates that none of the fields in the given input values are null.
     *
     * @param input the input values to validate
     * @throws InvalidInputException if any of the input fields are null
     * @throws RuntimeException if an IllegalAccessException occurs (which should not happen, as it is handled by the RestControllerAdvice class)
     */
    default void validateNotNull(I input) throws InvalidInputException {
        List<Field> fields = Arrays.stream(input.getClass().getDeclaredFields())
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .collect(Collectors.toList());

        fields.forEach(field -> {
            try {
                field.setAccessible(true);
                Object value = field.get(input);
                if (value == null) {
                    throw new InvalidInputException("Input field " + field.getName() + " is empty.");
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("This should never happen, as IllegalAccessException is handled in the RestControllerAdvice class", e);
            }
        });
    }
}