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
              <li>El valor del campo debe ser mayor a 0.</li>
              <li>El valor del campo debe ser solo números.</li>
              <li>El valor del campo debe ser numeros enteros (Si selecciona la opción).</li>
              <li>El valor del campo debe ser numeros decimales (Si selecciona la opción).</li>
              <li>El valor de campo debe ser menor o igual a 100</li>
              <li>Si es de formato decimal solo se permiten dos decimales</li>
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

export default Vue.extend({
  name: "InputNumber",
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
      } else {
        isFormCorrect = await this.v$.form.numberDecimal.$validate()
      }
      // check if the form is correct
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
