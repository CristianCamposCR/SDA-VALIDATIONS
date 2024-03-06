<template>
  <div>

    <information :action-validation-msg="[
      'Las validacion comienza cuando el campo pierde el foco.',
      'El botón de limpiar reinicia el formulario.',
      'El formulario se limpia después de ser enviado'
    ]" :validation-msg="[
      'El valor del campo es requerido.',
      'Solo se pueden seleccionar hasta 2 opciones.'
    ]"/>


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
        <SentForm :simulation-show="simulationShow" :simulation-send="simulationSend"/>


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
import {onSimulateSend, onSimulateShow} from "@/assets/js/functions";

export default Vue.extend({
  name: "InputMultiSelect",
  components: {
    Information: () => import('@/components/Information.vue'),
    SentForm: () => import('@/components/SentForm.vue'),
  },
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
      this.simulationSend = await onSimulateSend()
      this.simulationShow = await onSimulateShow()

      this.onReset()
    },
    onReset() {
      this.form.selected = [];
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

