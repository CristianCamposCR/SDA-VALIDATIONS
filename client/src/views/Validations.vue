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
                trim
                v-model="v$.form.lastname.$model"
                :state="
                  v$.form.lastname.$dirty ? !v$.form.lastname.$error : null
                "
                @blur="v$.form.lastname.$touch()"
              />
              <b-form-invalid-feedback
                v-for="error in v$.form.lastname.$errors"
                :key="error.$uid"
              >
                {{ error.$message }}
              </b-form-invalid-feedback>
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
                v-model.trim="v$.form.email.$model"
                :state="v$.form.email.$dirty ? !v$.form.email.$error : null"
                @blur="v$.form.email.$touch()"
                trim
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
              <label for="phoneNumber"
                >Teléfono:&nbsp;<b class="text-danger">*</b></label
              >
              <b-form-input
                id="phoneNumber"
                type="text"
                placeholder="777-777-7777"
                v-model="v$.form.phoneNumber.$model"
                :state="
                  v$.form.phoneNumber.$dirty
                    ? !v$.form.phoneNumber.$error
                    : null
                "
                @blur="v$.form.phoneNumber.$touch()"
                maxlength="10"
                @keypress="onlynumbers"
              />
              <b-form-invalid-feedback
                v-for="error in v$.form.phoneNumber.$errors"
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
                placeholder="Selecciona una fecha"
                :label-help="null"
                v-model="v$.form.birthday.$model"
                :state="
                  v$.form.birthday.$dirty ? !v$.form.birthday.$error : null
                "
                @blur="v$.form.birthday.$touch()"
                label-current-month="Fecha máxima"
                hide-header
                :date-format-options="{
                  year: 'numeric',
                  month: 'numeric',
                  day: 'numeric',
                }"
                :max="maxDate"
                @hide="v$.form.birthday.$touch()"
              ></b-form-datepicker>
              <b-form-invalid-feedback
                v-for="error in v$.form.birthday.$errors"
                :key="error.$uid"
              >
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-form-group>
          </b-col>

          <b-col cols="12" sm="12" md="4">
            <b-form-group>
              <label>NSS:</label>
              <b-form-input
                id="nss"
                type="text"
                placeholder="NSS"
                required
                maxlength="11"
                @keypress="onlynumbers"
                v-model="v$.form.nss.$model"
                :state="v$.form.nss.$dirty ? !v$.form.nss.$error : null"
                @blur="v$.form.nss.$touch()"
                :disabled="isDisabled"
              />
              <b-form-invalid-feedback
                v-for="error in v$.form.nss.$errors"
                :key="error.$uid"
              >
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-form-group>
          </b-col>
          <b-col cols="12" sm="12" md="4">
            <b-form-group>
              <label>Genero:&nbsp;<b class="text-danger">*</b></label>
              <multi-select
                :class="{
                  'is-invalid': v$.form.genre.$error,
                  'is-valid': !v$.form.genre.$invalid,
                }"
                v-model="v$.form.genre.$model"
                placeholder="Selecciona un género"
                label="name"
                :options="genders"
                track-by="name"
                :multiple="false"
                selectLabel="Presiona enter para seleccionar"
                deselectLabel="Presiona enter para eliminar"
                selectedLabel="Seleccionado"
                @close="v$.form.genre.$touch()"
              >
                <template slot="noResult">No hay resultados</template>
                <template slot="noOptions"
                  >No hay opciones</template
                ></multi-select
              >
              <b-form-invalid-feedback
                v-for="error in v$.form.genre.$errors"
                :key="error.$uid"
              >
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-form-group>
          </b-col>
          <b-col cols="12" sm="12" md="4">
            <b-form-group>
              <label for="numberOfSons"
                >Número de hijos:&nbsp;<b class="text-danger">*</b></label
              >
              <b-form-input
                id="numbreOfSons"
                type="number"
                placeholder=""
                required
                min="0"
                max="5"
                @keypress="onlynumbers"
                v-model="v$.form.numberOfSons.$model"
                :state="
                  v$.form.numberOfSons.$dirty
                    ? !v$.form.numberOfSons.$error
                    : null
                "
                @blur="v$.form.numberOfSons.$touch()"
              />
              <b-form-invalid-feedback
                v-for="error in v$.form.numberOfSons.$errors"
                :key="error.$uid"
              >
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-form-group>
          </b-col>
          <b-col cols="12" sm="12" md="4">
            <b-form-group>
              <label for="softskills"
                >Habilidades blandas&nbsp;<b class="text-danger">*</b></label
              >
              <multi-select
                id="softskills"
                :class="{
                  'is-invalid': v$.form.softSkills.$error,
                  'is-valid': !v$.form.softSkills.$invalid,
                }"
                v-model="v$.form.softSkills.$model"
                placeholder="Selecciona de 2 a 4 4 habilidades blandas"
                label="name"
                :options="softskills"
                track-by="name"
                :multiple="true"
                selectLabel="Presiona enter para seleccionar"
                deselectLabel="Presiona enter para eliminar"
                selectedLabel="Seleccionado"
                @close="v$.form.softSkills.$touch()"
                ><template slot="noResult">No hay resultados</template>
                <template slot="noOptions"
                  >No hay opciones</template
                ></multi-select
              >
              <b-form-invalid-feedback
                v-for="error in v$.form.softSkills.$errors"
                :key="error.$uid"
              >
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-form-group>
          </b-col>
          <b-col cols="12" sm="12" md="6" class="mt-2">
            <label>Estado civil</label>
            <b-form-group>
              <b-form-checkbox
                v-for="opc in martialStatusList"
                :key="opc.id"
                name="martialStatus"
                v-model="form.martialStatus"
                :state="
                  v$.form.martialStatus.$dirty
                    ? !v$.form.martialStatus.$error
                    : null
                "
                :value="opc.value"
                inline
              >
                {{ opc.text }}
              </b-form-checkbox>
              <b-form-invalid-feedback
                v-for="error in v$.form.martialStatus.$errors"
                :key="error.$uid"
                :state="
                  v$.form.martialStatus.$dirty
                    ? !v$.form.martialStatus.$error
                    : null
                "
              >
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-form-group>
          </b-col>
        </b-row>
      </b-form>
      <b-row class="justify-content-end">
        <b-col sm="auto" class="text-end">
          <b-button @click="save" variant="success" :disabled="false"
            >Guardar</b-button
          >
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
<script>
import Vue from "vue";
import { useVuelidate } from "@vuelidate/core";
import moment from "moment/moment";
import { signal } from "@/kernel/functions";
import {
  required,
  email,
  helpers,
  minLength,
  maxLength,
  requiredIf,
  minValue,
  maxValue,
} from "@vuelidate/validators";
import Axios from "axios";
import personService from "@/services/Person";
export default Vue.extend({
  name: "validations",
  setup() {
    return {
      v$: useVuelidate(),
    };
  },
  data() {
    const now = new Date();
    const today = new Date(now.getFullYear(), now.getMonth(), now.getDate());
    const maxDate = new Date(today);
    maxDate.setFullYear(maxDate.getFullYear() - 18);
    return {
      maxDate: maxDate,
      fecha: null,
      genders: [
        { name: "Masculino", id: 1 },
        { name: "Femenino", id: 2 },
      ],
      martialStatusList: [
        {
          text: "Soltero/a",
          value: "1",
        },
        { text: "Casado/a", value: "2" },
        { text: "Divorciado/a", value: "3" },
        { text: "Viudo/a", value: "4" },
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
      defaultForm: {
        name: null,
        surname: null,
        lastname: null,
        email: null,
        phoneNumber: null,
        birthday: null,
        genre: null,
        numberOfSons: null,
        softSkills: null,
        nss: null,
        martialStatus: null,
        status: true,
      },
      form: {
        name: null,
        surname: null,
        lastname: null,
        email: null,
        phoneNumber: null,
        birthday: null,
        genre: null,
        numberOfSons: null,
        softSkills: null,
        nss: null,
        martialStatus: null,
        status: true,
      },
      isDisabled: true,
    };
  },
  methods: {
    onlynumbers(evt) {
      signal(evt);
    },
    isCampos() {
      if (this.form.lastname === "Campos") {
        this.isDisabled = false;
        return true;
      }
      this.form.nss = null;
      this.isDisabled = true;
      return false;
    },
    save() {
      this.$swal
        .fire({
          title: "¿Seguro que desea realizar la acción?",
          icon: "question",
          showCancelButton: true,
          confirmButtonColor: "#3085d6",
          cancelButtonColor: "#d33",
          confirmButtonText: "Aceptar",
          cancelButtonText: "Cancelar",
          reverseButtons: true,
        })
        .then(async (result) => {
          try {
            if (result.isConfirmed) {
              const payload = {
                ...this.form,
                genre: null,
                martialStatus: { id: this.form.martialStatus },
                softSkills: JSON.stringify(this.form.softSkills),
              };
              const response = await personService.savePerson(payload);
              if (!response.error) {
                this.$swal.fire({
                  title: "Éxito",
                  icon: "success",
                  confirmButtonColor: "#3085d6",
                  confirmButtonText: "Aceptar",
                });
              } else {
                console.log("else", response);
                this.$swal.fire({
                  title: "Error",
                  text: `Los errores son: ${response.data}`,
                  icon: "error",
                  confirmButtonColor: "#3085d6",
                  confirmButtonText: "Aceptar",
                });
              }
            }
          } catch (error) {
            this.$swal.fire({
              title: "Error",
              text: `${error}`,
              icon: "error",
              confirmButtonColor: "#3085d6",
              confirmButtonText: "Aceptar",
            });
          } finally {
            this.form = { ...this.defaultForm };
            this.v$.$reset();
          }
        });
    },
  },
  validations() {
    return {
      form: {
        name: {
          required: helpers.withMessage("Campo obligatorio", required),
          valid: helpers.withMessage(
            "Campo inválido, solo se aceptan letras y puntos",
            helpers.regex(/^[a-zA-Z ÁÉÍÓÚáéíóúñÑäëïöü\. \s]+$/)
          ),
          minLength: helpers.withMessage("Mínimo 3 caracteres", minLength(3)),
          maxLength: helpers.withMessage("Máximo 50 caracteres", maxLength(50)),
        },
        surname: {
          required: helpers.withMessage("Campo obligatorio", required),
          valid: helpers.withMessage(
            "Campo inválido, solo se aceptan letras y puntos",
            helpers.regex(/^[a-zA-Z ÁÉÍÓÚáéíóúñÑäëïöü\. \s]+$/)
          ),
          minLength: helpers.withMessage("Mínimo 3 caracteres", minLength(3)),
          maxLength: helpers.withMessage("Máximo 50 caracteres", maxLength(50)),
        },
        lastname: {
          valid: helpers.withMessage(
            "Campo inválido, solo se aceptan letras y puntos",
            (value) => {
              if (!value) return true;
              return /^[a-zA-Z ÁÉÍÓÚáéíóúñÑäëïöü\. \s]+$/.test(value);
            }
          ),
        },
        email: {
          required: helpers.withMessage("Campo obligatorio", required),
          email: helpers.withMessage("Correo inválido", email),
        },
        phoneNumber: {
          required: helpers.withMessage("Campo obligatorio", required),
          validFormat: helpers.withMessage(
            "Teléfono inválido",
            helpers.regex(/(?:\d{1}\s)?\(?(\d{3})\)?-?\s?(\d{3})-?\s?(\d{4})/)
          ),
        },
        birthday: {
          required,
          maxValue: helpers.withMessage(
            "Sobrepasa la fecha máxima",
            (value) => {
              // Haciando la conversión y la comparación a mano

              // const año = new Date().getFullYear() - 18;
              // const mes = String(new Date().getMonth() + 1).padStart(2, "0"); // Sumar 1 al mes porque los meses en JavaScript van de 0 a 11
              // const día = String(new Date().getDate()).padStart(2, "0");
              // const fechaFormateada = `${año}-${mes}-${día}`;
              // return value <= fechaFormateada;

              //Usando la librería de moment

              //Se recomienda el uso de alguna librería para el manejo de fechas, como lo es moment, facilita mucho el trabajo y evita errores
              //En este caso value es un string de una fecha en formtato "YYYY-MM-DD" y para compararla tendriamos que obtener la fecha actual
              // y luego formatearla, moment nos evita eso ya que puedo hacer la comparación directa entre el string y un Date
              return moment(value).isSameOrBefore(
                new Date(
                  new Date().getFullYear() - 18,
                  new Date().getMonth(),
                  new Date().getDate()
                )
              );
            }
          ),
        },
        genre: {
          required: helpers.withMessage("Campo obligatorio", required),
        },
        softSkills: {
          required: helpers.withMessage("Campo obligatorio", required),
          minLength: helpers.withMessage("Selecciona mínimo 2", minLength(2)),
          maxLength: helpers.withMessage("Seleciona máximo 4", maxLength(4)),
        },
        numberOfSons: {
          requiredIfLastname: helpers.withMessage("Campo requerido", required),
          maxValue: helpers.withMessage("El valor máximo es 5", maxValue(5)),
        },
        nss: {
          requiredIfLastname: helpers.withMessage(
            "Campo requerido",
            requiredIf(this.isCampos())
          ),
          valid: helpers.withMessage(
            "Campo inválido",
            helpers.regex(/^(\d{2})(\d{2})(\d{2})\d{5}$/)
          ),
        },
        martialStatus: {
          required: helpers.withMessage("Campo obligatorio", (value) => {
            if (value === false) return false;
            else return true;
          }),
        },
      },
    };
  },
});
</script>

<style>
.is-invalid > .multiselect__tags {
  border-color: #dc3545;
}
.is-valid > .multiselect__tags {
  border-color: #28a745;
}
</style>
