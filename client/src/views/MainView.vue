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


        <router-view/>
    </section>


  </main>

</template>


<script>
export default {
  name: "MainView",
  data() {
    return {
      selected: 'validation',
      options: [
        {value: 'validation', text: 'Formulario A'},
        {value: 'inputText', text: 'Campo de texto'},
      ]
    };
  },
  methods: {
    handleShowComponent() {
      this.$router.push({name: this.selected});
    }
  }
}
</script>


<style scoped>

</style>