<template>

  <main>
    <b-navbar type="dark" variant="dark">
      <b-navbar-nav>
        <b-nav-item href="#">Validaciones</b-nav-item>
      </b-navbar-nav>
    </b-navbar>


    <section class="mx-3">

      <div>
        <b-form-group
            label="Opciones:"
            label-for="inputOptions"
            description="Selecciona una de las diversas opciones."
        >
          <b-form-select v-model="selected" :options="options" id="inputOptions"></b-form-select>
        </b-form-group>
      </div>

      <div v-if="selected === 'validation'"><Validation/></div>
      <div v-else-if="selected === 'inputText'"><InputText/></div>
      <div v-else-if="selected === 'inputNumber'"><InputNumber/></div>
      <div v-else-if="selected === 'inputEmail'"><InputEmail/></div>
      <div v-else-if="selected === 'inputMultiSelect'"><InputMultiSelect/></div>
      <div v-else-if="selected === 'inputDate'"><InputDate/></div>
      <div v-else-if="selected === 'inputPassword'"><InputPassword/></div>
      <div v-else-if="selected === 'inputFile'"><InputFile/></div>

    </section>


  </main>

</template>


<script>
export default {
  name: "MainView",
  components: {
    Validation: () => import('@/views/Validations.vue'),
    InputText: () => import('./input_validations/InputText.vue'),
    InputNumber: () => import('./input_validations/InputNumber.vue'),
    InputEmail: () => import('./input_validations/InputEmail.vue'),
    InputMultiSelect: () => import('./input_validations/InputMultiSelect.vue'),
    InputDate: () => import('./input_validations/InputDates.vue'),
    InputPassword: () => import('./input_validations/InputPassword.vue'),
    InputFile: () => import('./input_validations/InputFile.vue'),

  },
  data() {
    return {
      selected: 'validation',
      options: [
        {value: 'validation', text: 'Formulario General'},
        {value: 'inputText', text: 'Campo de texto'},
        {value: 'inputNumber', text: 'Campo numérico'},
        {value: 'inputEmail', text: 'Campo de correo'},
        {value: 'inputMultiSelect', text: 'Campo de selección múltiple'},
        {value: 'inputDate', text: 'Campo de fecha'},
        {value: 'inputPassword', text: 'Campo de contraseña'},
        {value: 'inputFile', text: 'Campo de archivo'},
      ]
    };
  },
}
</script>


<style scoped>

</style>