<template>
  <div>

    <information :action-validation-msg="[
      'Las validacion comienza cuando el campo pierde el foco.',
      'El botón de limpiar reinicia el formulario.',
    ]" :validation-msg="[
      'El valor del campo es requerido.',
      'La fecha de inicio debe ser igual o posterior a la fecha actual.',
      'La fecha de inicio debe ser igual o anterior a la fecha de fin.',
      'La fecha de fin es requerida.',
      'La fecha de fin debe ser igual o posterior a la fecha de inicio.',
      'La fecha de rango debe ser igual o posterior a 3 días antes de la fecha actual.',
      'La fecha de rango debe ser igual o anterior a 3 días después de la fecha actual.',
      'La fecha rango es opcional.',
    ]"/>


    <b-card class="mt-3">
      <b-form @submit.prevent="onSubmit">

        <b-form-group label="Fecha Incio">
          <b-form-datepicker v-model="form.starDate" class="mb-2"
                             hide-header
                             label-no-date-selected="Selecciona una fecha"
                             label-help=""
                             :state="v$.form.starDate.$dirty ? !v$.form.starDate.$error : null"
                             :min="todayDate()"
                             @hidden="v$.form.starDate.$touch()"

          ></b-form-datepicker>

          <b-form-invalid-feedback v-for="error in v$.form.starDate.$errors" :key="error.$uid">
            {{ error.$message }}
          </b-form-invalid-feedback>
        </b-form-group>

        <b-form-group label="Fecha Fin">
          <b-form-datepicker v-model="form.endDate" class="mb-2"
                             hide-header
                             label-no-date-selected="Selecciona una fecha"
                             label-help=""
                             :state="v$.form.endDate.$dirty ? !v$.form.endDate.$error : null"
                             @hidden="v$.form.endDate.$touch()"
          ></b-form-datepicker>
          <b-form-invalid-feedback v-for="error in v$.form.endDate.$errors" :key="error.$uid">
            {{ error.$message }}
          </b-form-invalid-feedback>
        </b-form-group>


        <b-form-group label="Fecha Rango">
          <b-form-datepicker v-model="form.rangeDate" class="mb-2"
                             hide-header
                             label-no-date-selected="Selecciona una fecha"
                             label-help=""
                             :state="v$.form.rangeDate.$dirty ? !v$.form.rangeDate.$error : null"
                             @hidden="v$.form.rangeDate.$touch()"
          ></b-form-datepicker>
          <b-form-invalid-feedback v-for="error in v$.form.rangeDate.$errors" :key="error.$uid">
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
import {required,  helpers} from "@vuelidate/validators";
import moment from "moment";
import Information from "@/components/Information.vue";


export default Vue.extend({
  name: "InputDates",
  components: {Information},
  setup() {
    return {v$: useVuelidate()};
  },
  data() {
    return {
      form: {
        starDate: null,
        endDate: null,
        rangeDate: null,
      },
      simulationShow: false,
      simulationSend: false,
    };
  },
  methods: {
    todayDate() {
      return moment().format("YYYY-MM-DD");
    },
    async onSubmit() {

      const isFormCorrect = await this.v$.$validate()
      if (!isFormCorrect) return

    },
    onReset() {
      this.v$.$reset();
    },
  },
  watch: {
    "form.endDate": function (value) {
      console.log(value)
      console.log(moment().subtract(18, "years").format("YYYY-MM-DD"))
    },
  },
  validations: {
    form: {
      starDate: {
        required: helpers.withMessage("La fecha de inicio es requerida", required),
        today: helpers.withMessage(
            "La fecha de inicio debe ser igual o posterior a la fecha actual",
            (value) => {
              return moment(value).isSameOrAfter(moment(), "day");
            }
        ),
        beforeOrEqual: helpers.withMessage(
            "La fecha de inicio debe ser igual o anterior a la fecha de fin",
            function (value, {endDate}) {
              console.log(value, !endDate)
              if (!endDate || !value) {
                return true;
              }
              return moment(value).isSameOrBefore(moment(endDate), "day");
            }
        ),
      },
      endDate: {
        required: helpers.withMessage("La fecha de fin es requerida", required),
        beforeOrEqual: helpers.withMessage(
            "La fecha de fin debe ser igual o posterior a la fecha de inicio",
            function (value, {starDate}) {
              if (!value || !starDate) {
                return true;
              }
              return moment(value).isSameOrAfter(moment(starDate), "day");
            }
        ),
      },
      rangeDate: {
        minThreeDayAgo: helpers.withMessage(
            "La fecha de rango debe ser igual o posterior a 3 días antes de la fecha actual",
            (value) => {
              if (!value) {
                return true;
              }
              return moment(value).isSameOrAfter(moment().subtract(1, "year"), "day");
            }
        ),
        maxThreeDayAfter: helpers.withMessage(
            "La fecha de rango debe ser igual o anterior a 3 días después de la fecha actual",
            (value) => {
              if (!value) {
                return true;
              }
              return moment(value).isSameOrBefore(moment().add(1, "year"), "day");
            }
        ),
      },
    },
  },
});
</script>

<style scoped></style>
