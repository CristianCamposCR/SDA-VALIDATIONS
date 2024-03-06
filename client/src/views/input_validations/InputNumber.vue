<template>
  <div>

    <information :action-validation-msg="[
      'Las validacion comienza cuando el campo pierde el foco.',
      'El botón de limpiar reinicia el formulario.',
    ]" :validation-msg="[
      'El valor del campo es requerido.',
      'El valor del campo debe ser mayor a 0.',
      'El valor del campo debe ser solo números.',
      'El valor del campo debe ser numeros enteros (Si selecciona la opción).',
      'El valor del campo debe ser numeros decimales (Si selecciona la opción).',
      'El valor de campo debe ser menor o igual a 100',
      'Si es de formato decimal solo se permiten dos decimales'
    ]"/>

    <b-card class="mt-3">
      <b-form-group label="Opciones">
        <b-form-radio-group
            id="radio-group-1"
            v-model="form.format"
            :options="['Entero', 'Decimal']"
            name="radio-options"
        ></b-form-radio-group>
      </b-form-group>

      <div v-if="form.format === 'Entero'">
        <b-form @submit.prevent="onSubmit">
          <b-form-group label="Número entero" label-for="numberInteger">
            <b-form-input
                id="numberInteger"
                type="text"
                v-model="v$.form.numberInteger.$model"
                @blur="v$.form.numberInteger.$touch()"
                :state="v$.form.numberInteger.$dirty ? !v$.form.numberInteger.$error : null"
                trim
                required
            />
            <b-form-invalid-feedback v-for="error in v$.form.numberInteger.$errors" :key="error.$uid">
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
          <!-- button action form -->
          <div class="text-right">
            <b-button type="reset" variant="danger" class="mx-3" @click="onReset">Limpiar</b-button>
            <b-button type="submit" variant="primary" @click="() => v$.form.numberInteger.$touch()">Enviar</b-button>
          </div>
        </b-form>
      </div>
      <div v-else>
        <b-form @submit.prevent="onSubmit">
          <b-form-group label="Número decimal" label-for="numberDecimal">
            <b-form-input
                id="numberDecimal"
                type="text"
                v-model="v$.form.numberDecimal.$model"
                @blur="v$.form.numberDecimal.$touch()"
                :state="v$.form.numberDecimal.$dirty ? !v$.form.numberDecimal.$error : null"
                trim
                required
            />
            <b-form-invalid-feedback v-for="error in v$.form.numberDecimal.$errors" :key="error.$uid">
              {{ error.$message }}
            </b-form-invalid-feedback>
          </b-form-group>
          <!--    message to simulate the sending of the form    -->
          <sent-form :simulation-show="simulationShow" :simulation-send="simulationSend"/>
          <!-- button action form -->
          <div class="text-right">
            <b-button type="reset" variant="danger" class="mx-3" @click="onReset">Limpiar</b-button>
            <b-button type="submit" variant="primary" @click="() => v$.form.numberDecimal.$touch()">Enviar</b-button>
          </div>
        </b-form>
      </div>
    </b-card>


  </div>
</template>

<script>
import Vue from "vue";
import {useVuelidate} from "@vuelidate/core";
import {
  required,
  alphaNum,
  helpers,
  maxLength,
  minLength,
  integer,
  decimal,
  minValue,
  maxValue
} from "@vuelidate/validators";
import {onSimulateSend, onSimulateShow} from "@/assets/js/functions";

export default Vue.extend({
  name: "InputNumber",
  components: {
    Information: () => import('@/components/Information.vue'),
    SentForm: () => import('@/components/SentForm.vue'),
  },
  setup() {
    return {v$: useVuelidate()};
  },
  data() {
    return {
      form: {
        numberInteger: "",
        numberDecimal: "",
        format: "Entero",
      },
      simulationShow: false,
      simulationSend: false,
    };
  },
  methods: {
    async onSubmit() {

      let isFormCorrect = false
      if (this.form.format === "Entero") {
        isFormCorrect = await this.v$.form.numberInteger.$validate()
      } else isFormCorrect = await this.v$.form.numberDecimal.$validate()
      // check if the form is correct
      if (!isFormCorrect) return
      this.simulationShow = true;
      this.simulationSend = false;
      this.simulationShow = await onSimulateShow()
      this.simulationSend = await onSimulateSend()

      this.onReset()
    },
    onReset() {
      this.form.numberInteger = ""
      this.form.numberDecimal = ""
      this.v$.$reset();
    },
  },
  validations: {
    form: {
      numberInteger: {
        required: helpers.withMessage("El campo es requerido.", required),
        integer: helpers.withMessage("El campo debe ser un número entero.", integer),
        minValue: helpers.withMessage("El campo debe ser mayor a 0.", minValue(1)),
        maxValue: helpers.withMessage("El campo debe ser menor a 100.", maxValue(100)),
      },
      numberDecimal: {
        required: helpers.withMessage("El campo es requerido.", required),
        decimal: helpers.withMessage("El campo debe ser un número decimal.", decimal),
        minValue: helpers.withMessage("El campo debe ser mayor a 0.", (value) => value > 0),
        maxValue: helpers.withMessage("El campo debe ser menor a 100.", maxValue(100)),
        numberOfDecimals: helpers.withMessage("El campo debe tener solo dos decimales.", (value) => {
          const decimals = value.toString().split(".")[1]
          return decimals ? decimals.length <= 2 : true
        }),
      },

      format: {}

    },
  },
});
</script>

<style scoped></style>
