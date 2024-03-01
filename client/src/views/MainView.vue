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
          <b-form-select v-model="selected" :options="options" id="inputOptions"  @input="handleShowComponent">
          </b-form-select>
        </b-form-group>
      </div>


      <div v-if="selected === 'validation'">
        <Validation/>
      </div>
      <div v-else-if="selected === 'inputText'">
        <InputText/>
      </div>
      <div v-else-if="selected === 'inputNumber'">
        <InputNumber/>
      </div>
      <div v-else-if="selected === 'inputEmail'">
        <InputEmail/>
      </div>
      <div v-else-if="selected === 'inputMultiSelect'">
        <InputMultiSelect/>
      </div>

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

  },
  data() {
    return {
      selected: 'inputMultiSelect',
      options: [
        {value: 'validation', text: 'Formulario A'},
        {value: 'inputText', text: 'Campo de texto'},
        {value: 'inputNumber', text: 'Campo numérico'},
        {value: 'inputEmail', text: 'Campo de correo'},
        {value: 'inputMultiSelect', text: 'Campo MultiSelect'},
      ]
    };
  },
}
</script>


<style scoped>

</style>