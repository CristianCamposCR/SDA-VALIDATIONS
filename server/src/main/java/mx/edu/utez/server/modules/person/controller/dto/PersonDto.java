package mx.edu.utez.server.modules.person.controller.dto;

import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.edu.utez.server.kernel.Validations;
import mx.edu.utez.server.modules.person.model.Person;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonDto {
    @NotNull(groups = {Validations.Update.class})
    @Positive(groups = {Validations.Update.class})
    private Long id;

    @NotNull(groups = {Validations.Save.class, Validations.Update.class})
    @NotEmpty(groups = {Validations.Save.class, Validations.Update.class})
    @Size(min = 1, max = 50, groups = {Validations.Save.class, Validations.Update.class})
    private String name;

    @NotNull(groups = {Validations.Save.class, Validations.Update.class})
    @NotEmpty(groups = {Validations.Save.class, Validations.Update.class})
    @Size(min = 1, max = 50, groups = {Validations.Save.class, Validations.Update.class})
    private String surname;

    @Size(max = 50, groups = {Validations.Save.class, Validations.Update.class})
    private String lastname;

    @AssertTrue(groups = {Validations.Save.class})
    @NotNull(groups = {Validations.Save.class})
    private Boolean status;

    public Person getPerson() {
        return new Person(
                getId(),
                getName(),
                getSurname(),
                getLastname(),
                getStatus()
        );
    }
}
