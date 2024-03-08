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

## 💻 Installation

### Backend (Spring Boot)

1. Clone the repository to your local machine:

```bash
git clone https://github.com/CristianCamposCR/SDA-VALIDATIONS.git
```

2. Open the project in IntelliJ IDEA or your preferred IDE.

3. Navigate to the application.properties file located in the src/main/resources directory.

4. Update the database connection configuration according to your local setup.

5. Start the Spring Boot service.

### Frontend (Vue.js)

1. Navigate to the client directory within your project:

``` bash
cd SDA-VALIDATIONS/client
```

2. Install the dependencies

```bash
npm i
```

3. Create a .env file at the root of the client directory and add the following environment variable:

```bash
VITE_APP_BASE_URL=http://localhost:8080/api
```
This environment variable points to the base URL of your Spring Boot API.

4. Start the Vue.js development server:

```bash
npm run dev
```

5. Once the development server is running, you can access the application in your web browser at http://localhost:5173.

6. Try out the fields validation example.


## ⚒️Features

- `General form`: It is a simple form with some validations using the Vuelidate library with diverse fields.
    - This form has consume a backend to validate the fields and assuring the data is correct.
- `Field text`: It has some validations like required, min and max length.
- `Field email`: It has some validations like required and email format.
- `Field password`: It has some validations like required, min and max length, confirm password and password strength.
- `Field number`: It has some validations like required, numbers only, number integer and number decimal.
- `Field date`: It  has some validation, like required and date format, the date min and max.
- `Field multiselect`: It has some validations like required and only two options selected.
- `Field file`: It has some validations like required and only image files, dimensions and size.

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

```java
@NotNull(message = "The name is required", groups = {Create.class})
private String name;
```

- In the controller was used the annotation `@Validate({Class.interface.class})` to validate the fields. Example:

```java
@PostMapping("/")
public ResponseEntity<CustomResponse<?>> save(
        @Validated({Validations.Save.class}) @RequestBody PersonDto personDto) {
  CustomResponse<?> res = this.personService.save(personDto.getPersonEntity());
  return new ResponseEntity<>(res, res.getStatus());
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
<img src="https://avatars.githubusercontent.com/u/105668860?v=4" width="30"/>
<img src="https://avatars.githubusercontent.com/u/105619807?v=4" width="30"/>




