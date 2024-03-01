package mx.edu.utez.server.modules.person.controller.dto.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mx.edu.utez.server.modules.person.controller.dto.contraints.IsAdult;

import java.time.LocalDate;
import java.time.Period;

public class IsAdultValidator implements ConstraintValidator<IsAdult, LocalDate> {

    @Override
    public void initialize(IsAdult constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Se considera válido si es nulo
        }

        LocalDate now = LocalDate.now();
        Period period = Period.between(value, now);
        return period.getYears() >= 18;
    }
}
