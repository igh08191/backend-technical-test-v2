package com.tui.proof.validator;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OneOfValidator implements ConstraintValidator<OneOf, Integer> {

	private int[] intValues;
	
	public void initialize(OneOf constraintAnnotation) {
        intValues = constraintAnnotation.value().clone();
    }
	
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		return Arrays.stream(intValues).boxed().collect(Collectors.toList()).contains(value);
	}

}
