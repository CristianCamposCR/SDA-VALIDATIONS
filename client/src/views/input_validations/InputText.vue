<template>
  <div>
    <b-row>
      <b-col md="3">
        <b-card
          header="Información sobre la validación"
          header-text-variant="primary"
        >
          <ul class="px-3">
            <li>El valor del campo es requerido.</li>
            <li>Permite mayúsculas y minúsculas.</li>
            <li>Permite espacios en blanco entre las palabra</li>
            <li>Permite guiones medios o bajos</li>
            <li>Permite acentos</li>
            <li>Permite la letra ñ</li>
          </ul>
        </b-card>

        <b-card header="Función" header-text-variant="primary" class="mt-2">
          <ul class="px-3">
            <li>
              Si el campo se toca y no se ha escrito nada, se comenzará a
              validar.
            </li>
          </ul>
        </b-card>
      </b-col>
      <b-col>
        <b-card>
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
              <h5 class="mt-3">Objeto de vuelidate</h5>
              {{ v$.form }}
              <h5 class="mt-3">Errores del campo</h5>
              <p v-for="error of v$.$errors" :key="error.$uid">
                <strong>{{ error.$validator }}</strong>
                <small> on property </small>
                <strong>{{ error.$property }}</strong>
                <small> says: </small>
                <strong>{{ error.$message }}</strong>
              </p>
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
              <b-button
                type="reset"
                variant="danger"
                class="mx-3"
                @click="onReset"
                >Limpiar</b-button
              >
              <b-button type="submit" variant="primary">Enviar</b-button>
            </div>
          </b-form>
        </b-card>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import Vue from "vue";
import { useVuelidate } from "@vuelidate/core";
import { required, alphaNum } from "@vuelidate/validators";

export default Vue.extend({
  name: "InputText",
  setup() {
    return { v$: useVuelidate() };
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
    onSubmit() {
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
        required,
        valid: function (value) {
          const isValid =
            /^[a-zA-Z ÁÉÍÓÚáéíóúñÑäëïöü\-_ \s]+$/.test(value);
            // pettern que permite caracteres de la a - z mayus y minus todos los acentos dierisis y la ñ
          return isValid;
        },
      },
    },
  },
});
</script>

<style scoped></style>
