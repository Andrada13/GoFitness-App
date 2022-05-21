import Vue from 'vue';
import App from './App.vue';
import { router } from './router'; //for Vue Router
import store from './store'; //for Vuex
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import JwPagination from 'jw-vue-pagination';
import VueTimepicker from 'vue2-timepicker'
import VeeValidate from 'vee-validate';
import { library } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import {
  faHome,
  faUser,
  faUserPlus,
  faSignInAlt,
  faSignOutAlt
} from '@fortawesome/free-solid-svg-icons';

library.add(faHome, faUser, faUserPlus, faSignInAlt, faSignOutAlt);

Vue.config.productionTip = false;


Vue.use(VeeValidate);
Vue.component('font-awesome-icon', FontAwesomeIcon);
Vue.component('jw-pagination', JwPagination);
Vue.component('vue-timepicker', VueTimepicker);


new Vue({
  router,
  store, //injecting the store into the app and at this point we can access the store in any component using this.$store
  render: h => h(App)
}).$mount('#app');
