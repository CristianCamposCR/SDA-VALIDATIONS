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
    // @NotNull: Valida que el valor no sea nulo
    // - Actualización
    @NotNull(groups = {Validations.Update.class},
            message = "Id no debe ser nulo")
    // @Positive: Valida que el valor sea positivo y mayor a 0
    // - Actualización
    @Positive(groups = {Validations.Update.class},
            message = "Id debe ser mayor que cero")
    // @Null: Valida que el valor sea un valor nulo
    // - Creación
    @Null(groups = {Validations.Save.class}, message = "Id debe ser nulo")
    private Long id;

    // @NotBlank: Valida que el name no sea nulo, que su tamaño sea mayor a cero
    // y que su contenido no sean espacios vacíos
    // - Creación
    // - Actualización
    @NotBlank(groups = {Validations.Save.class, Validations.Update.class},
            message = "Nombre no debe ser nulo, ni estar vacío")
    // @Size: Valida que el tamaño de name esté entre 1 y 50 carácteres
    // - Creación
    // - Actualización
    @Size(min = 1, max = 50, groups = {Validations.Save.class, Validations.Update.class},
            message = "Nombre debe tener máximo 50 carácteres")
    private String name;

    // @NotBlank: Valida que el surname no sea nulo, que su tamaño sea mayor a cero
    // y que su contenido no sean espacios vacíos
    // - Creación
    // - Actualización
    @NotBlank(groups = {Validations.Save.class, Validations.Update.class},
            message = "Primer apellido no debe ser nulo, ni estar vacío")
    // @Size: Valida que el tamaño de surname esté entre 1 y 50 carácteres
    // - Creación
    // - Actualización
    @Size(min = 1, max = 50, groups = {Validations.Save.class, Validations.Update.class},
            message = "Primer apellido debe tener máximo 50 carácteres")
    private String surname;

    // @Size: Valida que el tamaño de lastname esté entre 0 y 50 carácteres
    // - Creación
    // - Actualización
    @Size(max = 50, groups = {Validations.Save.class, Validations.Update.class},
            message = "Segundo apellido debe tener máximo 50 carácteres")
    private String lastname;

    // @NotNull: Valida que el valor no sea nulo
    // - Creación
    // - Actualización
    @NotNull(groups = {Validations.Save.class, Validations.Update.class},
            message = "Fecha de nacimiento no debe ser nula")
    // @Past: Valida que la fecha sea anterior a la fecha local
    // - Creación
    // - Actualización
    @Past(groups = {Validations.Save.class, Validations.Update.class},
            message = "Fecha de nacimiento no debe ser futura")
    // Validación Personalizada
    // @IsAdult: Valida que desde la fecha ingresada a la fecha actual,
    // hay 18 o más años de diferencia
    // - Creación
    // - Actualización
    @IsAdult(groups = {Validations.Save.class, Validations.Update.class},
            message = "Debe tener al menos 18 años")
    private LocalDate birthday;

    // @NotNull: Valida que el valor no sea nulo
    // - Creación
    // - Actualización
    @NotNull(groups = {Validations.Save.class, Validations.Update.class},
            message = "Número de teléfono no debe ser nulo")
    // @Pattern: Valida el formato del valor
    // - Creación
    // - Actualización
    @Pattern(groups = {Validations.Save.class, Validations.Update.class},
            regexp = "^\\s*(?:\\+?(\\d{1,3}))?([-. (]*(\\d{3})[-. )]*)?((\\d{3})[-. ]*(\\d{2,4})(?:[-.x ]*(\\d+))?)\\s*$",
            message = "Formato de teléfono incorrecto")
    private String phoneNumber;

    // @NotNull: Valida que el valor no sea nulo
    // - Creación
    // - Actualización
    @NotNull(groups = {Validations.Save.class, Validations.Update.class},
            message = "Correo no debe ser nula")
    // @Pattern: Valida el formato del valor
    // - Creación
    // - Actualización
    @Pattern(groups = {Validations.Save.class, Validations.Update.class},
            regexp = "\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b",
            message = "Formato de correo incorrecto")
    private String email;

    // @NotNull: Valida que el valor no sea nulo
    // - Creación
    // - Actualización
    @NotNull(groups = {Validations.Save.class, Validations.Update.class},
            message = "Estado no debe ser nulo")
    // @AssertTrue: Valida que el valor sea verdadero
    // - Creación
    @AssertTrue(groups = {Validations.Save.class},
            message = "Estado debe ser verdadero")
    private Boolean status;

    // @NotNull: Valida que el valor no sea nulo
    // - Creación
    // - Actualización
    @NotNull(groups = {Validations.Save.class, Validations.Update.class},
            message = "Número de hijos no debe ser nulo")
    // @PositiveOrZero: Valida que el valor sea positivo o 0
    // - Creación
    // - Actualización
    @PositiveOrZero(groups = {Validations.Save.class, Validations.Update.class},
            message = "Número de hijos debe ser mayor o igual que 0")
    // @Digits: Valida que el valor solo sea de un dígito y sin números decimales
    // - Creación
    // - Actualización
    @Digits(integer = 1, fraction = 0,
            groups = {Validations.Save.class, Validations.Update.class},
            message = "Número de hijos solo puede ser de un dígito")
    // @Max: Valida el valor máximo
    // - Creación
    // - Actualización
    @Max(value = 5, groups = {Validations.Save.class, Validations.Update.class},
            message = "Máximo 5 hijos")
    private Long numberOfSons;

    // @Pattern: Valida el formato del valor
    // - Creación
    // - Actualización
    @Pattern(groups = {Validations.Save.class, Validations.Update.class},
            regexp = "^(\\d{2})(\\d{2})(\\d{2})\\d{5}$",
            message = "Formato de NSS incorrecto")
    private String nss;

    // @NotBlank: Valida que el surname no sea nulo, que su tamaño sea mayor a cero
    // y que su contenido no sean espacios vacíos
    // - Creación
    // - Actualización
    @NotBlank(groups = {Validations.Save.class, Validations.Update.class},
            message = "Lista de habilidades no debe ser nula, ni estar vacío")
    private String softSkills;

    // @NotNull: Valida que el valor no sea nulo
    // - Creación
    // - Actualización
    @NotNull(groups = {Validations.Save.class, Validations.Update.class},
            message = "Género no debe ser nulo")
    // @Valid: Indica que se debe hacer la validación recursiva del DTO anidado
    @Valid
    private GenreDto genre;

    // @NotNull: Valida que el valor no sea nulo
    // - Creación
    // - Actualización
    @NotNull(groups = {Validations.Save.class, Validations.Update.class},
            message = "Estado civil no debe ser nulo")
    // @Valid: Indica que se debe hacer la validación recursiva del DTO anidado
    @Valid
    private MartialStatusDto martialStatus;

    public Person getPersonEntity() {
        return new Person(
                getId(),
                getName(),
                getSurname(),
                getLastname(),
                getBirthday(),
                getPhoneNumber(),
                getEmail(),
                getStatus(),
                getNumberOfSons(),
                getNss(),
                getSoftSkills(),
                genre.getGenreEntity(),
                martialStatus.getMartialStatusEntity()
        );
    }
}
