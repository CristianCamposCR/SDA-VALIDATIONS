package mx.edu.utez.server.modules.person.service;

import mx.edu.utez.server.kernel.CustomResponse;
import mx.edu.utez.server.modules.person.model.IPersonRepository;
import mx.edu.utez.server.modules.person.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonService {
    private final IPersonRepository iPersonRepository;

    public PersonService(IPersonRepository iPersonRepository) {
        this.iPersonRepository = iPersonRepository;
    }

    public CustomResponse<Person> save(Person person) {
        try {
            return new CustomResponse<>(
                    this.iPersonRepository.saveAndFlush(person),
                    HttpStatus.CREATED,
                    false,
                    "Persona creada"
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
