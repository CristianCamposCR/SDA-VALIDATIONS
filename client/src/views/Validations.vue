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
              />
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
              />
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
              />
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
                required
              />
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
          <b-col cols="12" sm="12" md="4">
            <div class="mt-5">
              <b-form-group>
                <b-form-checkbox-group
                  id="checkbox-group-1"
                  v-model="selected"
                  :options="civilStatus"
                  :aria-describedby="ariaDescribedby"
                  name="flavour-1"
                >
                </b-form-checkbox-group>
              </b-form-group>
            </div>
          </b-col>
          <b-col cols="12" sm="12" md="4">
            <b-form-group>
              <label for="birthdate"
                >Fecha de nacimiento:&nbsp;<b class="text-danger">*</b></label
              >
              <b-form-input
                id="phone"
                type="text"
                placeholder="777-777-7777"
                required
              />
            </b-form-group>
          </b-col>
        </b-row>
      </b-form>
    </b-container>
  </div>
</template>
<script>
import Vue from "vue";
export default Vue.extend({
  name: "validations",
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
    };
  },
});
</script>
