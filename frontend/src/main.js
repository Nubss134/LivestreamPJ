import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import firebase from "firebase/app";
import "firebase/messaging";
import "./permission.js";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

// Import Bootstrap an BootstrapVue CSS files (order is important)
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

Vue.use(ElementUI, { size: "small", zIndex: 3000 });
// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);
Vue.config.productionTip = false;

const firebaseConfig = {
  apiKey: "AIzaSyD-mr21OfXstPTBRphWvDuV0zj_4O44FSU",
  authDomain: "livestreampj-ab132.firebaseapp.com",
  databaseURL: "https://livestreampj-ab132.firebaseio.com",
  projectId: "livestreampj-ab132",
  storageBucket: "livestreampj-ab132.appspot.com",
  messagingSenderId: "87892589395",
  appId: "1:87892589395:web:0eb143b0729440e4317fa6",
  measurementId: "G-B1N9E8KWH2"
};
firebase.initializeApp(firebaseConfig);
// const messaging = firebase.messaging();
// messaging
//   .getToken({
//     vapidKey:
//       "BEDWSXqF85zZlk5m8_8M_ypxFvsyIDPOhaQWKuz7LQVV8e6_QLEUCO0oy4Qgkqdjne1FWizMZ9z954Qrg2TMRFU"
//   })
//   .then(currentToken => {
//     if (currentToken) {
//       console.log("token", currentToken);
//     } else {
//       // Show permission request.
//       console.log(
//         "No registration token available. Request permission to generate one."
//       );
//       // Show permission UI.
//     }
//   })
//   .catch(err => {
//     console.log("An error occurred while retrieving token. ", err);
//   });

// messaging.onMessage(payload => {
//   this.$notify({
//     title: "Custom Position",
//     message: "I'm at the bottom left corner",
//     position: "bottom-left"
//   });
//   console.log("Message receive", payload);
// });

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
