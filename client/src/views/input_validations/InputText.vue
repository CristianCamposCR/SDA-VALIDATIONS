<template>
  <div>

    <Information :action-validation-msg="[
      'El campo es requerido',
      'El campo no puede tener más de 50 caracteres',
      'El campo no puede tener menos de 3 caracteres',
      'El campo no es válido, solo se permiten letras y los caracteres especiales: - _'
    ]" :validation-msg="[
      'Las validacion comienza cuando el campo pierde el foco.',
      'El botón de enviar valida el formulario.',
      'El botón de limpiar reinicia el formulario.',
      'El formulario se limpia después de ser enviado'
    ]"/>

    <b-card class="mt-3">
      <b-form @submit.prevent="onSubmit">
        <b-form-group>
          <label for="name">Nombre:</label>
          <b-form-input
              id="name"
              type="text"
              placeholder="Juan"
              required
              v-model="v$.form.name.$model"
              @blur="v$.form.name.$touch()"
              :state="v$.form.name.$dirty ? !v$.form.name.$error : null"
              trim
          />
          <b-form-invalid-feedback v-for="error in v$.form.name.$errors" :key="error.$uid">
            {{ error.$message }}
          </b-form-invalid-feedback>
        </b-form-group>

        <!--    message to simulate the sending of the form    -->
        <SentForm :simulation-show="simulation.show" :simulation-send="simulation.send"/>

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
import {required, alphaNum, helpers, maxLength, minLength} from "@vuelidate/validators";
import {onSimulateSend, onSimulateShow} from "@/assets/js/functions";

export default Vue.extend({
  name: "InputText",
  components: {
    Information: () => import('@/components/Information.vue'),
    SentForm: () => import('@/components/SentForm.vue'),
  },
  setup() {
    return {v$: useVuelidate()}; //useVuelidate() is a hook that returns the validation object
  },
  data() {
    return {
      form: {
        name: "",
      },
      simulation:{show: false, send: false}
    };
  },
  methods: {
    async onSubmit() {

      //check if the form is correct
      const isFormCorrect = await this.v$.$validate()
      if (!isFormCorrect) return //if the form is not correct, return

      //simulate the sending of the form
      this.simulation.show = true;
      this.simulation.send = false;
      this.simulation.send = await onSimulateShow()
      this.simulation.show = await onSimulateSend()

      //reset the form
      this.onReset()

    },
    onReset() {
      this.form.name = "";
      this.v$.$reset();
    },
  },
  validations() {
    return {
      form: {
        name: {
          required: helpers.withMessage(
              "El campo es requerido",
              required
          ),
          valid: helpers.withMessage(
              'El campo no es válido, solo se permiten letras y los caracteres especiales: - _',
              helpers.regex(/^[a-zA-Z ÁÉÍÓÚáéíóúñÑäëïöü\-_ \s]+$/)
          ),
          maxLength: helpers.withMessage(
              "El campo no puede tener más de 50 caracteres",
              maxLength(50)
          ),
          minLength: helpers.withMessage(
              "El campo no puede tener menos de 3 caracteres",
              minLength(3)
          ),
        },
      }
    }
  }
})
;
</script>

<style scoped></style>
