package mx.edu.utez.server.modules.person.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.edu.utez.server.modules.genre.model.Genre;
import mx.edu.utez.server.modules.martialStatus.model.MartialStatus;

import java.time.LocalDate;

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

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate birthday;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String phoneNumber;

    @Column(columnDefinition = "VARCHAR(150)", nullable = false)
    private String email;

    @Column(columnDefinition = "TINYINT DEFAULT 1", nullable = false)
    private Boolean status;

    @Column(columnDefinition = "INTEGER", nullable = false)
    private Long numberOfSons;

    @Column(columnDefinition = "VARCHAR(11)")
    private String nss;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String softSkills;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "martial_status_id", nullable = false)
    private MartialStatus martialStatus;

}
