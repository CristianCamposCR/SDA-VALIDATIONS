<template>
  <div>

    <b-card no-body>
      <b-card-header v-b-toggle.collapseValidations>
        <span class="border-bottom text-primary">Información de validación de un campo de texto</span>
      </b-card-header>
      <b-collapse id="collapseValidations">
        <b-card-body>
          <b-card-text>
            <ul class="px-3">
              <li>El valor del campo es requerido.</li>
              <li>Solo se pueden seleccionar hasta 2 opciones.</li>
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
          <label for="animals">Animales:</label>

          <multi-select
              :class="{ 'is-invalid': v$.form.selected.$error, 'is-valid': !v$.form.selected.$invalid }"
              placeholder="Selecciona uno o varios animales"
              :options="['Perro', 'Gato', 'Conejo', 'Pez', 'Pájaro', 'Tortuga', 'Cerdo', 'Vaca', 'Cabra', 'Oveja', 'Conejo']"
              :multiple="multiple"
              selectLabel="Presiona enter para seleccionar"
              deselectLabel="Presiona enter para eliminar"
              selectedLabel="Seleccionado"
              v-model="form.selected"
              @close="v$.form.selected.$touch()"
          >
            <template slot="noResult">No hay resultados</template>
            <template slot="noOptions">No hay opciones</template>
          </multi-select>

          <!--    Hack to show the invalid feedback      -->
          <b-form-input type="hidden" :state="v$.form.selected.$dirty ? !v$.form.selected.$error : null" v-show="false"/>

          <b-form-invalid-feedback v-for="error in v$.form.selected.$errors" :key="error.$uid">
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
  name: "InputMultiSelect",
  setup() {
    return {v$: useVuelidate()};
  },
  data() {
    return {
      multiple: true,
      form: {
        selected: [],
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
      selected: {
        required: helpers.withMessage("Selecciona al menos una opción", required),
        maxLength: helpers.withMessage("Selecciona a lo mucho 2 opciones", maxLength(2)),
      },
    },
  },
});
</script>

<!-- This is the style for the multiselect but in all app-->
<style>
.is-invalid>.multiselect__tags{
  border-color: #dc3545;
}
.is-valid>.multiselect__tags{
  border-color: #28a745;
}
</style>

