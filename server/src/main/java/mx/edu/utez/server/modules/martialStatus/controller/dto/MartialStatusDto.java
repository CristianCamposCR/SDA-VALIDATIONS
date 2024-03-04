package mx.edu.utez.server.modules.martialStatus.controller.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.edu.utez.server.kernel.Validations;
import mx.edu.utez.server.modules.martialStatus.model.MartialStatus;
import mx.edu.utez.server.modules.person.model.Person;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MartialStatusDto {
    // @Positive: Valida que el valor sea positivo y mayor a 0
    // - Creación
    // - Actualización
    @Positive(groups = {Validations.Save.class, Validations.Update.class},
            message = "Id debe ser mayor que cero")
    // @NotNull: Valida que el valor no sea nulo
    // - Creación
    // - Actualización
    @NotNull(groups = {Validations.Save.class, Validations.Update.class},
            message = "Id no debe ser nulo")
    private Long id;
    private String martialStatus;
    private List<Person> personList;

    public MartialStatus getMartialStatusEntity() {
        return new MartialStatus(
                getId(),
                getMartialStatus(),
                getPersonList()
        );
    }
}
