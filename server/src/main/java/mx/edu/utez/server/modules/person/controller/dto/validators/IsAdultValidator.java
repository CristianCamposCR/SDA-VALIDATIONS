package mx.edu.utez.server.modules.person.controller.dto.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mx.edu.utez.server.modules.person.controller.dto.contraints.IsAdult;

import java.time.LocalDate;
import java.time.Period;

// Clase con la lógica de la validación personalizada
// Implementa la interfaz ConstraintValidator
// Indica IsAdult como la anotación
// Indica el tipo de dato que valida
public class IsAdultValidator implements ConstraintValidator<IsAdult, LocalDate> {

    // Sobreescribe initialize
    @Override
    public void initialize(IsAdult constraintAnnotation) {
    }

    // Sobreescribe isValid
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Se considera válido si es nulo
        }

        // Realiza la validación y retorna true o false
        LocalDate now = LocalDate.now();
        Period period = Period.between(value, now);
        return period.getYears() >= 18;
    }
}
