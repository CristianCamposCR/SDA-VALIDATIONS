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


  },
  data() {
    return {
      selected: 'inputNumber',
      options: [
        {value: 'validation', text: 'Formulario A'},
        {value: 'inputText', text: 'Campo de texto'},
        {value: 'inputNumber', text: 'Campo numérico'},
      ]
    };
  },
}
</script>


<style scoped>

</style>