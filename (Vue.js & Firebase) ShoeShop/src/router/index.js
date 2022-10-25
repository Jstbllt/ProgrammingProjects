import { createRouter, createWebHashHistory } from "vue-router";
import HomePage from "../pages/HomePage.vue";
import AboutPage from "../pages/AboutPage.vue";
import NotFoundPage from "../pages/NotFoundPage.vue";
import LoginPage from "../pages/LoginPage.vue";
import RegisterPage from "../pages/RegisterPage.vue";
import AccountPage from "../pages/AccountPage/AccountPage.vue";
import ShoePage from "../pages/ShoePage.vue";
import SettingsPage from "../pages/AccountPage/SettingsPage.vue";
import MyOrdersPage from "../pages/AccountPage/MyOrdersPage.vue";
import ConfidentialityPage from "../pages/AccountPage/ConfidentialityPage.vue";
import FavoritesPage from "../pages/AccountPage/FavoritesPage.vue";
import LoyaltyProgramPage from "../pages/AccountPage/LoyaltyProgramPage.vue";
import SecuritiesPage from "../pages/AccountPage/SecuritiesPage.vue";
import CartPage from "../pages/CartPage.vue";
import HelpCenterPage from "../pages/HelpCenterPage.vue";
import BrowsePage from "../pages/BrowsePage";
import NewsPage from "../pages/NewsPage.vue";
import { auth } from "@/firebase";

const routes = [
  {
    name: "Home",
    path: "/",
    component: HomePage,
    meta: {
      title: "Home",
    },
  },
  {
    name: "About",
    path: "/about",
    component: AboutPage,
    meta: {
      title: "About us",
    },
  },
  {
    name: "HelpCenter",
    path: "/helpCenter",
    component: HelpCenterPage,
    meta: {
      title: "Help Center",
    },
  },
  {
    name: "BrowsePage",
    path: "/browse/:category_shoe",
    component: BrowsePage,
    props: true,
    meta: {
      title: "Browse Page",
    },
  },
  {
    name: "News",
    path: "/news",
    component: NewsPage,
    meta: {
      title: "News",
    },
  },
  {
    name: "Cart",
    path: "/cart",
    component: CartPage,
    meta: {
      require: true,
      title: "Cart",
    },
  },
  {
    name: "Account",
    path: "/account",
    component: AccountPage,
    meta: {
      requiresAuth: true,
      title: "Account",
    },
  },
  {
    name: "Login",
    path: "/login",
    component: LoginPage,
    meta: {
      title: "Login",
    },
  },
  {
    name: "Register",
    path: "/register",
    component: RegisterPage,
    meta: {
      title: "Register",
    },
  },
  {
    name: "ShoePage",
    path: "/shoe/:id",
    component: ShoePage,
    props: true,
    meta: {
      title: "Shoe",
    },
  },
  {
    name: "Settings",
    path: "/account/settings",
    component: SettingsPage,
    meta: {
      requiresAuth: true,
      title: "Settings",
    },
  },
  {
    name: "Orders",
    path: "/account/orders",
    component: MyOrdersPage,
    meta: {
      requiresAuth: true,
      title: "Orders",
    },
  },
  {
    name: "Confidentiality",
    path: "/account/confidentiality",
    component: ConfidentialityPage,
    meta: {
      requiresAuth: true,
      title: "Confidentiality",
    },
  },
  {
    name: "Favorites",
    path: "/account/favorites",
    component: FavoritesPage,
    meta: {
      requiresAuth: true,
      title: "My Favorites",
    },
  },
  {
    name: "LoyaltyProgram",
    path: "/account/loyalty-program",
    component: LoyaltyProgramPage,
    meta: {
      requiresAuth: true,
      title: "Loyalty Program",
    },
  },
  {
    name: "Securities",
    path: "/account/securities",
    component: SecuritiesPage,
    meta: {
      requiresAuth: true,
      title: "Securities",
    },
  },
  {
    name: "NotFound",
    path: "/:pathMatch(.*)",
    component: NotFoundPage,

    meta: {
      title: "404 NotFound",
    },
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

router.beforeEach((to, from, next) => {
  document.title = to.meta.title;
  if (
    to.matched.some((record) => record.meta.requiresAuth) &&
    !auth.currentUser
  )
    next({ name: "Login" });
  else next();
});

export default router;
