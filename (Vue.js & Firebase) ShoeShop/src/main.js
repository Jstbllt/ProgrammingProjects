import { createApp } from "vue";
import App from "./App.vue";
import { library } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import {
  faHouse,
  faUser,
  faCircleNotch,
  faSearch,
  faBell,
  faStar,
  faGear,
  faArrowRotateLeft,
  faShieldHalved,
  faUserLock,
  faPiggyBank,
  faHeart,
  faPowerOff,
  faShoePrints,
  faMoneyBill,
  faEarthEurope,
  faLock,
  faMessage,
  faBasketShopping,
  faMinus,
  faPlus,
} from "@fortawesome/free-solid-svg-icons";
import { faGoogle } from "@fortawesome/free-brands-svg-icons";
import router from "./router";
import store from "./store";
import { TroisJSVuePlugin } from "troisjs";

library.add(
  faHouse,
  faUser,
  faCircleNotch,
  faSearch,
  faBell,
  faGoogle,
  faStar,
  faGear,
  faArrowRotateLeft,
  faShieldHalved,
  faUserLock,
  faPiggyBank,
  faHeart,
  faPowerOff,
  faShoePrints,
  faMoneyBill,
  faEarthEurope,
  faShieldHalved,
  faLock,
  faMessage,
  faBasketShopping,
  faMinus,
  faPlus
);

createApp(App)
  .component("font-awesome-icon", FontAwesomeIcon)
  .use(store)
  .use(router)
  .use(TroisJSVuePlugin)
  .mount("#app");
