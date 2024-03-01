package mx.edu.utez.server.modules.person.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.edu.utez.server.kernel.Validations;
import mx.edu.utez.server.modules.genre.controller.dto.GenreDto;
import mx.edu.utez.server.modules.martialStatus.controller.dto.MartialStatusDto;
import mx.edu.utez.server.modules.person.controller.dto.contraints.IsAdult;
import mx.edu.utez.server.modules.person.model.Person;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonDto {
    @NotNull(groups = {Validations.Update.class},
            message = "Id no debe ser nulo")
    @Positive(groups = {Validations.Update.class},
            message = "Id debe ser mayor que cero")
    @Null(groups = {Validations.Save.class}, message = "Id debe ser nulo")
    private Long id;

    @NotBlank(groups = {Validations.Save.class, Validations.Update.class},
            message = "Nombre no debe ser nulo, ni estar vacío")
    @Size(min = 1, max = 50, groups = {Validations.Save.class, Validations.Update.class},
            message = "Nombre debe tener máximo 50 carácteres")
    private String name;

    @NotBlank(groups = {Validations.Save.class, Validations.Update.class},
            message = "Primer apellido no debe ser nulo, ni estar vacío")
    @Size(min = 1, max = 50, groups = {Validations.Save.class, Validations.Update.class},
            message = "Primer apellido debe tener máximo 50 carácteres")
    private String surname;

    @Size(max = 50, groups = {Validations.Save.class, Validations.Update.class},
            message = "Segundo apellido debe tener máximo 50 carácteres")
    private String lastname;

    @NotNull(groups = {Validations.Save.class, Validations.Update.class},
            message = "Fecha de nacimiento no debe ser nula")
    @Past(groups = {Validations.Save.class, Validations.Update.class},
            message = "Fecha de nacimiento no debe ser futura")
    // Validación para saber si es mayor de edad
    @IsAdult(groups = {Validations.Save.class, Validations.Update.class},
            message = "Debe tener al menos 18 años")
    private LocalDate birthDay;

    @NotNull(groups = {Validations.Save.class, Validations.Update.class},
            message = "Número de teléfono no debe ser nulo")
    @Pattern(groups = {Validations.Save.class, Validations.Update.class},
            regexp = "^\\s*(?:\\+?(\\d{1,3}))?([-. (]*(\\d{3})[-. )]*)?((\\d{3})[-. ]*(\\d{2,4})(?:[-.x ]*(\\d+))?)\\s*$",
            message = "Formato de teléfono incorrecto")
    private String phoneNumber;

    @NotNull(groups = {Validations.Save.class, Validations.Update.class},
            message = "Correo no debe ser nula")
    @Pattern(groups = {Validations.Save.class, Validations.Update.class},
            regexp = "\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b",
            message = "Formato de correo incorrecto")
    private String email;

    @NotNull(groups = {Validations.Save.class, Validations.Update.class},
            message = "Estado no debe ser nulo")
    @AssertTrue(groups = {Validations.Save.class},
            message = "Estado debe ser verdadero")
    private Boolean status;

    @NotNull(groups = {Validations.Save.class, Validations.Update.class},
            message = "Número de hijos no debe ser nulo")
    @PositiveOrZero(groups = {Validations.Save.class, Validations.Update.class},
            message = "Número de hijos debe ser mayor o igual que 0")
    @Digits(integer = 1, fraction = 0,
            groups = {Validations.Save.class, Validations.Update.class},
            message = "Número de hijos solo puede ser de un dígito")
    @Max(value = 5, groups = {Validations.Save.class, Validations.Update.class},
            message = "Máximo 5 hijos")
    private Long numberOfSons;

    @NotNull(groups = {Validations.Save.class, Validations.Update.class},
            message = "NSS no debe ser nulo")
    @Pattern(groups = {Validations.Save.class, Validations.Update.class},
            regexp = "^(\\d{2})(\\d{2})(\\d{2})\\d{5}$",
            message = "Formato de NSS incorrecto")
    private String nss;

    @NotNull(groups = {Validations.Save.class, Validations.Update.class},
            message = "Género no debe ser nulo")
    @Valid
    private GenreDto genre;

    @NotNull(groups = {Validations.Save.class, Validations.Update.class},
            message = "Estado civil no debe ser nulo")
    @Valid
    private MartialStatusDto martialStatus;

    public Person getPerson() {
        return new Person(
                getId(),
                getName(),
                getSurname(),
                getLastname(),
                getBirthDay(),
                getPhoneNumber(),
                getEmail(),
                getStatus(),
                getNumberOfSons(),
                getNss(),
                genre.getGenreEntity(),
                martialStatus.getMartialStatusEntity()
        );
    }
}
