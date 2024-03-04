<template>
  <div>

    <b-card no-body>
      <b-card-header v-b-toggle.collapseValidations>
        <span class="border-bottom text-primary">Información de validación del campo</span>
      </b-card-header>
      <b-collapse id="collapseValidations">
        <b-card-body>
          <b-card-text>
            <ul class="px-3">
              <li>El valor del campo es requerido.</li>
              <li>Permite mayúsculas y minúsculas.</li>
              <li>Debe contener al menos 8 caracteres.</li>
              <li>Debe contener al menos una letra mayúscula.</li>
              <li>Debe contener al menos un número.</li>
              <li>Debe contener al menos un caracter especial: . _ # $</li>
            </ul>
          </b-card-text>
        </b-card-body>
      </b-collapse>


      <b-card-header v-b-toggle.collapseValidationFunction>
        <span class="border-bottom text-primary">Acerca de las acciones al campo</span>
      </b-card-header>
      <b-collapse id="collapseValidationFunction">
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
        <b-form-group>
          <label for="name">Nueva contraseña:</label>
          <b-form-input
              id="name"
              type="password"
              placeholder="*********"
              required
              v-model="v$.form.password.$model"
              @blur="v$.form.password.$touch()"
              :state="v$.form.password.$dirty ? !v$.form.password.$error : null"
          />

          <b-form-invalid-feedback v-for="error in v$.form.password.$errors" :key="error.$uid">
            {{ error.$message }}
          </b-form-invalid-feedback>


        </b-form-group>
        <b-form-group>
          <label for="name">Confirma contraseña:</label>
          <b-form-input
              id="name"
              type="password"
              placeholder="*********"
              required
              v-model="v$.form.confirmPassword.$model"
              @blur="v$.form.confirmPassword.$touch()"
              :state="v$.form.confirmPassword.$dirty ? !v$.form.confirmPassword.$error : null"
              trim
          />

          <b-form-invalid-feedback v-for="error in v$.form.confirmPassword.$errors" :key="error.$uid">
            {{ error.$message }}
          </b-form-invalid-feedback>


        </b-form-group>

        <!--    message to simulate the sending of the form    -->
        <div v-if="simulationShow">
          <div v-if="simulationSend" class="text-success">
            <strong>¡Enviado!</strong> El formulario ha sido enviado.
          </div>
          <div v-else class="text-secondary">
            <strong>Cargando...</strong> Espere un momento.
          </div>
        </div>


        <!--        <div class="text-right">-->
        <!--          <b-button type="reset" variant="danger" class="mx-3" @click="onReset">Limpiar</b-button>-->
        <!--          <b-button type="submit" variant="primary" @click="() => v$.form.$touch()">Enviar</b-button>-->
        <!--        </div>-->
      </b-form>
    </b-card>


  </div>
</template>

<script>
import Vue from "vue";
import {useVuelidate} from "@vuelidate/core";
import {required, sameAs, helpers, maxLength, minLength} from "@vuelidate/validators";

export default Vue.extend({
  name: "InputText",
  setup() {
    return {v$: useVuelidate()};
  },
  data() {
    return {
      form: {
        password: "",
        confirmPassword: "",
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
      password: {
        required: helpers.withMessage(
            "El campo es requerido",
            (value) => {
              return value.length > 0;
            }
        ),
        containsSpace: helpers.withMessage(
            "El campo no debe contener espacios",
            (value) => {
              if (!value) return true;
              return !/\s+/g.test(value);
            }
        ),


        containsUpperCase: helpers.withMessage(
            "El campo debe contener al menos una letra mayúscula",
            (value) => {
              if (!value) return true;
              return /[A-Z]/g.test(value);
            }
        ),
        containsNumber: helpers.withMessage(
            "El campo debe contener al menos un número",
            (value) => {
              if (!value) return true;
              return /[0-9]/g.test(value);
            }
        ),
        containsLowerCase: helpers.withMessage(
            "El campo debe contener al menos una letra minúscula",
            (value) => {
              if (!value) return true;
              return /[a-z]/g.test(value);
            }
        ),
        minLength: helpers.withMessage(
            "El campo debe tener mas de 7 caracteres",
            minLength(8)
        ),
        containsSpecialCharacter: helpers.withMessage(
            "El campo debe contener al menos un caracter especial: !@#$%^&*()_+{}.?._*  ",
            (value) => {
              if (!value) return true;
              return /[!@#$%^&*()_+{}.?\\|\./_*]/g.test(value);
            }
        ),

        maxLength: helpers.withMessage(
            "El campo no puede tener más de 50 caracteres",
            maxLength(50)
        ),
      },
      confirmPassword: {
        checkPassword: helpers.withMessage(
            "Las contraseñas no coinciden",
            function (value, { password }) {
              return value === password;
            }
        ),
      },

    },
  },
});
</script>

<style scoped></style>
