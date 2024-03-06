<template>
  <div>

    <Information :action-validation-msg="[
      'Las validacion comienza cuando el campo pierde el foco.',
      'El botón de limpiar reinicia el formulario.',
    ]" :validation-msg="[
      'El campo es requerido',
      'El campo no debe contener espacios',
      'El campo debe contener al menos una letra mayúscula',
      'El campo debe contener al menos un número',
      'El campo debe contener al menos una letra minúscula',
      'El campo debe tener mas de 7 caracteres',
      'El campo debe contener al menos un caracter especial: !@#$%^&*()_+{}.?._*  ',
      'El campo no puede tener más de 50 caracteres',
      'Se valida que las contraseñas coincidan'
    ]"/>


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

      </b-form>
    </b-card>


  </div>
</template>

<script>
import Vue from "vue";
import {useVuelidate} from "@vuelidate/core";
import { helpers, maxLength, minLength} from "@vuelidate/validators";

export default Vue.extend({
  name: "InputText",
  components: {
    Information: () => import('@/components/Information.vue'),
  },
  setup() {
    return {v$: useVuelidate()};
  },
  data() {
    return {
      form: {
        password: "",
        confirmPassword: "",
      },
    };
  },
  methods: {
    async onSubmit() {
      const isFormCorrect = await this.v$.$validate()
      if (!isFormCorrect) return
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
              if (!value) return true;
              return value === password;
            }
        ),
      },

    },
  },
});
</script>

<style scoped></style>
