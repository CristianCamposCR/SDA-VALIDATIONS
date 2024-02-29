import Vue from 'vue'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import App from './App.vue'
import router from './router'
import './assets/main.css'
import Multiselect from 'vue-multiselect';


// Bootstrap
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'vue-multiselect/dist/vue-multiselect.min.css';

Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

//Multiselect se instancia como componente para poderlo usar
Vue.component('multi-select', Multiselect);



new Vue({
  router,
  render: (h) => h(App)
}).$mount('#app')
