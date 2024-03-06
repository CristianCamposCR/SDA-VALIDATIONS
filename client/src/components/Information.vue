<template>

  <b-card no-body>
    <b-card-header v-b-toggle.collapseValidations>
      <span class="border-bottom text-primary" >Información de validación del campo</span>
    </b-card-header>
    <b-collapse id="collapseValidations">
      <b-card-body>
        <b-card-text>
          <ul class="px-3">
            <li v-for="msg in validationMsg" :key="msg">{{msg}}</li>
          </ul>
        </b-card-text>
      </b-card-body>
    </b-collapse>
    <b-card-header v-b-toggle.collapseValidationFunction>
      <span class="border-bottom text-primary">Acerca de las acciones al campo</span>
    </b-card-header>
    <b-collapse id="collapseValidationFunction" >
      <b-card-body>
        <b-card-text>
          <ul class="px-3">
           <li v-for="msg in actionValidationMsg" :key="msg">{{msg}}</li>
          </ul>
        </b-card-text>
      </b-card-body>
    </b-collapse>
  </b-card>


</template>

<script setup>
import { defineProps } from 'vue'

defineProps({
  validationMsg:{
    type: Array,
    required: true
  },
  actionValidationMsg:{
    type: Array,
    required: true
  }
})

</script>

<style scoped>

</style>