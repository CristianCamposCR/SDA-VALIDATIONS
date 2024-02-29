package mx.edu.utez.server.modules.person.controller;

import jakarta.validation.Valid;
import mx.edu.utez.server.kernel.CustomResponse;
import mx.edu.utez.server.kernel.Validations;
import mx.edu.utez.server.modules.person.controller.dto.PersonDto;
import mx.edu.utez.server.modules.person.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person")
@CrossOrigin(origins = {"*"})
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<?>> save(
            @Validated({Validations.Save.class}) @RequestBody PersonDto personDto) {
        CustomResponse<?> res = this.personService.save(personDto.getPerson());
        return new ResponseEntity<>(res, res.getStatus());
    }
}
