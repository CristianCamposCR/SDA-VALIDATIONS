# SDA-VALIDATIONS

![Static Badge](https://img.shields.io/badge/Status-working-blue)
![Static Badge](https://img.shields.io/badge/Version-1.0.0-blue)
![Static Badge](https://img.shields.io/badge/Author-SDA-blue)
![Static Badge](https://img.shields.io/badge/Date-2024-blue)

[//]: # (https://shields.io/badges)

## Description

This is a simple project to validate the use of the Vue 2 framework with Vite. It is a simple form with some validations
using the Vuelidate library for user interface.
Also, the validation of the fields is done in the backend with the hibernate validator.

## ⚒️Features

- `General form`: It is a simple form with some validations using the Vuelidate library with diverse fields.
    - This form has consume a backend to validate the fields and assuring the data is correct.
- `Field text`: It is a simple text field with some validations.
- `Field email`: It is a simple email field with some validations.
- `Field password`: It is a simple password field with some validations.
- `Field number`: It is a simple number field with some validations.
- `Field date`: It is a simple date field with some validations.
- `Field multiselect`: It is a simple multi-select field with some validations.
- `Field file`: It is a simple file field with some validations.

### 📕 How was the implementation in the frontend?

- The frontend was implemented using vuelidate, where for each field and form,a validation was created.
- In the component has a description of the validations for each field and how to start the validations.
- In the component exists a function named `validations` that contains the validations for each field.
- In the validations was used a helper function, that function needs the message to show the user and the validation to
  do.
  Example:

```javascript
validations()
{
    return {
        form: {
            name: {
                required: helpers.withMessage(
                    "The name is required",
                    required
                ),
            }
        }
    }
}
```

- To show the error message, it was used the vuelidate object `$v` that contains the validation for each field.
  Example:

```html

<div v-if="$v.form.name.$error" class="error">
    {{ $v.form.name.$message }}
</div>
```

### 📓 How was the implementation in the backend?

- In the DTO (Data Transfer Object) was used the Hibernate Validator to validate the fields.
- In the DTO was used the annotations to validate the fields. Example:

```javascript
@NotNull(message = "The name is required", groups = {Create.class})
private
String
name;
```

- In the controller was used the annotation `@Validate({Class.interface.class})` to validate the fields. Example:

```javascript
@PostMapping("/")
public
ResponseEntity < CustomResponse < ?
>>
save(
    @Validated({Validations.Save.class}) @RequestBody
PersonDto
personDto
)
{
    CustomResponse < ?
>
    res = this.personService.save(personDto.getPersonEntity());
    return new ResponseEntity < > (res, res.getStatus());
}
```

## 📦 Built With

- Frontend
    - [Vue 2.7.16](https://v2.vuejs.org/)
    - [Vite 3](https://vitejs.dev/)
    - [Vuelidate 2](https://vuelidate.js.org/)
    - [moment 2.30.1](https://momentjs.com/)
    - [vue-multiselect](https://vue-multiselect.js.org/)
    - [Bootstrap 4.6.0](https://getbootstrap.com/docs/4.6/getting-started/introduction/)
    - [Vue router 3.5.4](https://router.vuejs.org/)
      -[Axios 1.6.0](https://axios-http.com/docs/intro)
    - [dotenv 10.0.0](https://www.npmjs.com/package/dotenv)
- Backend
    - [Hibernate Validator 3.2.3](https://hibernate.org/validator/)
    - [Spring Boot 3.2.3](https://spring.io/projects/spring-boot)
    - [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
    - [Mysql connector 8.3.0](https://dev.mysql.com/downloads/connector/j/)
    - [Lombok 1.18.30](https://projectlombok.org/)
    - [Spring Web 3.2.3](https://spring.io/guides/gs/spring-boot/)
    - [Devtools 3.2.3](https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/html/using-boot-devtools.html)
    - [Spring Data JPA 3.2.3](https://spring.io/guides/gs/accessing-data-jpa/)

### Contributor


<img src="https://avatars.githubusercontent.com/u/105768917?s=400&amp;v=4" width="30"/>
<img src="https://avatars.githubusercontent.com/u/105690822?s=60&v=4" width="30"/>
<img src="https://avatars.githubusercontent.com/u/105692302?s=60&v=4" width="30"/>






