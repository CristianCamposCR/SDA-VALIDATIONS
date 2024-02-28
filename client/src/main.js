import Vue from 'vue'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'

// Bootstrap
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

import App from './App.vue'
import router from './router'

import './assets/main.css'

new Vue({
  router,
  render: (h) => h(App)
}).$mount('#app')
