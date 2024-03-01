<template>
  <div>

    <b-card no-body>
      <b-card-header v-b-toggle.collapseValidations>
        <span class="border-bottom text-primary" >Información de validación de un campo de texto</span>
      </b-card-header>
      <b-collapse id="collapseValidations">
        <b-card-body>
          <b-card-text>
            <ul class="px-3">
              <li>El valor del campo es requerido.</li>
              <li>Permite mayúsculas y minúsculas.</li>
              <li>Los caracteres ingresados se convierten a minúsculas.</li>
              <li>No permite dos puntos seguidos.</li>
              <li>No permite espacios en blanco</li>
              <li>Los dominios permitidos son: gmail.com, hotmail.com, yahoo.com, utez.edu.mx</li>
              <li>El formato del correo debe ser válido, entiendo por válido que debe tener un @ y un dominio.</li>
              <li>Los caracteres permitidos son: [ @./*-?']</li>
            </ul>
          </b-card-text>
        </b-card-body>
      </b-collapse>


      <b-card-header v-b-toggle.collapseValidationFunction>
        <span class="border-bottom text-primary">Acerca de las acciones al campo</span>
      </b-card-header>
      <b-collapse id="collapseValidationFunction" >
        <b-card-body>
          <b-card-text>
            <ul class="px-3">
              <li>Las validacion comienza cuando el campo pierde el foco.</li>
              <li>El botón de enviar valida el formulario.</li>
              <li>El botón de limpiar reinicia el formulario.</li>
            </ul>
          </b-card-text>
        </b-card-body>
      </b-collapse>
    </b-card>

    <b-card class="mt-3">
      <b-form @submit.prevent="onSubmit">
        <b-form-group label="Correo electrónico" label-for="email">
          <b-form-input
              id="email"
              type="text"
              placeholder="juan@gmail.com"
              required
              v-model="v$.form.name.$model"
              @blur="v$.form.name.$touch()"
              :state="v$.form.name.$dirty ? !v$.form.name.$error : null"
              :formatter="value => value.toLowerCase()"
              trim
          />

          <b-form-invalid-feedback v-for="error in v$.form.name.$errors" :key="error.$uid">
            {{ error.$message }}
          </b-form-invalid-feedback>


        </b-form-group>

        <!--    message to simulate the sending of the form    -->
        <div v-if="simulationShow">
          <div v-if="simulationSend" class="text-success">
            <strong>¡Enviado!</strong> El formulario ha sido enviado.
            <p>
              <small>Nombre:&nbsp; {{ form.name }}</small>
            </p>
          </div>
          <div v-else class="text-secondary">
            <strong>Cargando...</strong> Espere un momento.
          </div>
        </div>


        <div class="text-right">
          <b-button type="reset" variant="danger" class="mx-3" @click="onReset">Limpiar</b-button>
          <b-button type="submit" variant="primary" @click="() => v$.form.$touch()">Enviar</b-button>
        </div>
      </b-form>
    </b-card>


  </div>
</template>

<script>
import Vue from "vue";
import {useVuelidate} from "@vuelidate/core";
import {required, alphaNum, helpers, maxLength, minLength, email} from "@vuelidate/validators";

export default Vue.extend({
  name: "InputText",
  setup() {
    return {v$: useVuelidate()};
  },
  data() {
    return {
      form: {
        name: "",
      },
      simulationShow: false,
      simulationSend: false,
    };
  },
  methods: {
    async onSubmit() {

      const isFormCorrect = await this.v$.$validate()
      if (!isFormCorrect) {
        return
      }


      this.simulationShow = true;
      this.simulationSend = false;
      setTimeout(() => {
        this.simulationSend = true;
      }, 1000);
      setTimeout(() => {
        this.simulationShow = false;
      }, 2000);
    },
    onReset() {
      this.v$.$reset();
    },
  },
  validations: {
    form: {
      name: {
        required: helpers.withMessage(
            "El campo es requerido",
            required
        ),
        valid: helpers.withMessage(
            "El formato del correo no es válido",
            email
        ),
        whiteListDomains: helpers.withMessage(
            "El dominio no es válido",
            value => {
              if(!value.includes("@")) return true
              return ["gmail.com", "hotmail.com", "yahoo.com", "utez.edu.mx"].includes(value.split("@")[1])
            }
        ),
        validCharacters: helpers.withMessage(
            "El campo no es válido, solo se permiten letras y los caracteres especiales [ @./*-?']",
            value => {
              if(!value) return true
              return /^[a-zA-Z0-9@./*-]+$/.test(value)
            }
        ),
      },
    },
  },
});
</script>

<style scoped></style>
