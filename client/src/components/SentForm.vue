<template>
  <div v-if="simulationShow">
    <div v-if="simulationSend" class="text-success">
      <strong>¡Enviado!</strong> El formulario ha sido enviado.
    </div>
    <div v-else class="text-secondary">
      <strong>Cargando...</strong> Espere un momento.
    </div>
  </div>
</template>

<script setup>

import {defineProps} from 'vue'

defineProps({
  simulationShow: Boolean,
  simulationSend: Boolean
})


</script>

<style scoped>

</style>