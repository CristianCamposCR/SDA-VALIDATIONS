import Vue from 'vue'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import Vuelidate from "vuelidate";
import App from './App.vue'
import router from './router'
import './assets/main.css'



// Bootstrap
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

//validaciones
Vue.use(Vuelidate);


new Vue({
  router,
  render: (h) => h(App)
}).$mount('#app')
