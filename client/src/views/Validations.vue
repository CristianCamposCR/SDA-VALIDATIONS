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
        </b-row>
      </b-form>
    </b-container>
  </div>
</template>
