<template>
  <div>
    <h3 class="text-center mt-3">Validations form</h3>
    <b-container fluid class="mt-5">
      <b-form>
        <b-row class="mb-2">
          <b-col cols="12" sm="12" md="4">
            <b-form-group>
              <label for="name"
                >Nombre:&nbsp;<b class="text-danger">*</b></label
              >
              <b-form-input
                id="name"
                type="text"
                placeholder="Pablo"
                required
                trim
                v-model="v$.form.name.$model"
                :state="v$.form.name.$dirty ? !v$.form.name.$error : null"
                @blur="v$.form.name.$touch()"
              />
              <b-form-invalid-feedback
                v-for="error in v$.form.name.$errors"
                :key="error.$uid"
              >
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-form-group>
          </b-col>
          <b-col cols="12" sm="12" md="4">
            <b-form-group>
              <label for="surname"
                >Primer apellido:&nbsp;<b class="text-danger">*</b></label
              >
              <b-form-input
                id="surname"
                type="text"
                placeholder="Herrera"
                required
                trim
                v-model="v$.form.surname.$model"
                :state="v$.form.surname.$dirty ? !v$.form.surname.$error : null"
                @blur="v$.form.surname.$touch()"
              />
              <b-form-invalid-feedback
                v-for="error in v$.form.surname.$errors"
                :key="error.$uid"
              >
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-form-group>
          </b-col>
          <b-col cols="12" sm="12" md="4">
            <b-form-group>
              <label for="lastname">Segundo apellido:</label>
              <b-form-input
                id="lastname"
                type="text"
                placeholder="Roman"
                required
              />
            </b-form-group>
          </b-col>
        </b-row>
        <b-row class="mb-2">
          <b-col cols="12" sm="12" md="4">
            <b-form-group>
              <label for="email"
                >Correo electrónico:&nbsp;<b class="text-danger">*</b></label
              >
              <b-form-input
                id="email"
                type="text"
                placeholder="admin@localhost.com"
                required
                v-model="v$.form.email.$model"
                :state="v$.form.email.$dirty ? !v$.form.email.$error : null"
                @blur="v$.form.email.$touch()"
              />
              <b-form-invalid-feedback
                v-for="error in v$.form.email.$errors"
                :key="error.$uid"
              >
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-form-group>
          </b-col>
          <b-col cols="12" sm="12" md="4">
            <b-form-group>
              <label for="phone"
                >Teléfono:&nbsp;<b class="text-danger">*</b></label
              >
              <b-form-input
                id="phone"
                type="text"
                placeholder="777-777-7777"
                v-model="v$.form.phone.$model"
                :state="v$.form.phone.$dirty ? !v$.form.phone.$error : null"
                @blur="v$.form.phone.$touch()"
              />
              <b-form-invalid-feedback
                v-for="error in v$.form.phone.$errors"
                :key="error.$uid"
              >
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-form-group>
          </b-col>
          <b-col cols="12" sm="12" md="4">
            <b-form-group>
              <label for="birthdate"
                >Fecha de nacimiento:&nbsp;<b class="text-danger">*</b></label
              >
              <b-form-datepicker
                id="example-datepicker"
                class="mb-2"
              ></b-form-datepicker>
            </b-form-group>
          </b-col>
          <b-col cols="12" sm="12" md="4">
            <b-form-group>
              <label>Genero:&nbsp;<b class="text-danger">*</b></label>
              <multi-select
                placeholder="Selecciona los impuestos necesarios"
                label="name"
                :options="genders"
                track-by="name"
                :multiple="false"
                :taggable="true"
                selectLabel="Presiona enter para seleccionar"
                deselectLabel="Presiona enter para eliminar"
                selectedLabel="Seleccionado"
                :showNoOptions="false"
              ></multi-select>
            </b-form-group>
          </b-col>
          <b-col cols="12" sm="12" md="4" class="mt-4">
            <b-form-group>
              <b-form-checkbox-group
                id="checkbox-group-1"
                :options="civilStatus"
                name="flavour-1"
              >
              </b-form-checkbox-group>
            </b-form-group>
          </b-col>
          <b-col cols="12" sm="12" md="4">
            <b-form-group>
              <label for="childrenQuantity"
                >Número de hijos:&nbsp;<b class="text-danger">*</b></label
              >
              <b-form-input
                id="childrenQuantity"
                type="number"
                placeholder=""
                required
                min="0"
                max="100"
              />
            </b-form-group>
          </b-col>
          <b-col cols="12" sm="12" md="4">
            <b-form-group>
              <label for="softskills"
                >Habilidades blandas&nbsp;<b class="text-danger">*</b></label
              >
              <multi-select
                id="softskills"
                placeholder="Selecciona 3 habilidades blandas"
                label="name"
                :options="softskills"
                track-by="name"
                :multiple="true"
                :taggable="true"
                selectLabel="Presiona enter para seleccionar"
                deselectLabel="Presiona enter para eliminar"
                selectedLabel="Seleccionado"
                :showNoOptions="false"
              ></multi-select>
            </b-form-group>
          </b-col>
          <b-col cols="12" sm="12" md="4">
            <b-form-group>
              <label for="socialMedia"
                >Redes sociales:&nbsp;<b class="text-danger">*</b></label
              >
              <b-form-checkbox-group
                id="checkbox-group-1"
                :options="socialNetworks"
                name="flavour-1"
              ></b-form-checkbox-group>
            </b-form-group>
          </b-col>
        </b-row>
      </b-form>
    </b-container>
  </div>
</template>
<script>
import Vue from "vue";
import { useVuelidate } from "@vuelidate/core";
import {
  required,
  email,
  helpers,
  minLength,
  maxLength,
  requiredIf,
} from "@vuelidate/validators";
export default Vue.extend({
  name: "validations",
  setup() {
    return {
      v$: useVuelidate(),
    };
  },
  data() {
    return {
      genders: [
        { name: "Masculino", id: 1 },
        { name: "Femenino", id: 2 },
      ],
      civilStatus: [
        { text: "Soltero/a", value: "Soltero" },
        { text: "Casado/a", value: "Casado" },
        { text: "Divorciado/a", value: "Divorciado" },
        { text: "Viudo/a", value: "Viudo" },
      ],
      softskills: [
        { name: "Lidesrazgo", id: 1 },
        { name: "Comunicación efectiva", id: 2 },
        { name: "Trabajo en equipo", id: 3 },
        { name: "Resolución de problemas", id: 4 },
        { name: "Empatía", id: 5 },
        { name: "Adaptabilidad", id: 6 },
        { name: "Creatividad", id: 7 },
        { name: "Gestión del tiempo", id: 8 },
        { name: "Pensamiento crítico", id: 9 },
        { name: "Negociación", id: 10 },
        { name: "Toma de decisiones", id: 11 },
      ],
      socialNetworks: [
        { text: "Facebook", value: 1 },
        { text: "Instagram", value: 2 },
        { text: "Twitter", value: 3 },
        { text: "LinkedIn", value: 4 },
        { text: "YouTube", value: 5 },
        { text: "TikTok", value: 6 },
        { text: "Pinterest", value: 7 },
        { text: "Snapchat", value: 8 },
        { text: "Reddit", value: 9 },
        { text: "WhatsApp", value: 10 },
      ],
      form: {
        name: null,
        surname: null,
        lastname: null,
        email: null,
        phone: null,
        birthDate: null,
        gender: null,
        civilStatus: null,
        numberOfSons: null,
        softskills: null,
        socialNetworks: null,
      },
    };
  },
  validations: {
    form: {
      name: {
        required: helpers.withMessage("Campo obligatorio", required),
        valid: helpers.withMessage(
          "Campo inválido, solo se aceptan letras y -_",
          helpers.regex(/^[a-zA-Z ÁÉÍÓÚáéíóúñÑäëïöü\-_ \s]+$/)
        ),
        minLength: helpers.withMessage("Mínimo 3 caracteres", minLength(3)),
        maxLength: helpers.withMessage("Máximo 50 caracteres", maxLength(50)),
      },
      surname: {
        required: helpers.withMessage("Campo obligatorio", required),
        valid: helpers.withMessage(
          "Campo inválido, solo se aceptan letras y -_",
          helpers.regex(/^[a-zA-Z ÁÉÍÓÚáéíóúñÑäëïöü\-_ \s]+$/)
        ),
        minLength: helpers.withMessage("Mínimo 3 caracteres", minLength(3)),
        maxLength: helpers.withMessage("Máximo 50 caracteres", maxLength(50)),
      },
      lastname: {
        valid: (value) => {
          return /^[a-zA-Z ÁÉÍÓÚáéíóúñÑäëïöü\-_ \s]+$/.test(value);
        },
      },
      email: {
        required: helpers.withMessage("Campo obligatorio", required),
        email: helpers.withMessage("Correo inválido", email),
      },
      phone: {
        required: helpers.withMessage("Campo obligatorio", required),
        validFormat: helpers.withMessage(
          "Teléfono inválido",
          helpers.regex(
            /^\s*(?:\+?(\d{1,3}))?([-. (]*(\d{3})[-. )]*)?(\d{10})\s*$/
          )
        ),
      },
    },
  },
});
</script>
