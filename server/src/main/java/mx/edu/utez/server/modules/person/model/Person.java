package mx.edu.utez.server.modules.person.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DialectOverride;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "people")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String name;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String surname;

    @Column(columnDefinition = "VARCHAR(50)")
    private String lastname;

    @Column(columnDefinition = "TINYINT DEFAULT 1")
    private Boolean status;
}
