package mx.edu.utez.server.modules.genre.controller.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.edu.utez.server.kernel.Validations;
import mx.edu.utez.server.modules.genre.model.Genre;
import mx.edu.utez.server.modules.person.model.Person;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GenreDto {
    @Positive(groups = {Validations.Save.class, Validations.Update.class},
            message = "Id debe ser mayor que cero")
    @NotNull(groups = {Validations.Save.class, Validations.Update.class},
            message = "Id no debe ser nulo")
    private Long id;
    private String genre;
    private List<Person> personList;

    public Genre getGenreEntity() {
        return new Genre(
                getId(),
                getGenre(),
                getPersonList()
        );
    }
}
