<template>
  <router-link :disabled="disabled" :style="cssVars" :to="{ name: direction, params:params }"
    ><slot></slot
  ></router-link>
</template>

<script>
import { colorPalette } from "../assets/style";

export default {
  name: "BaseButton",
  props: {
    disabled: {
      type: Boolean,
      default: false,
    },
    color: {
      type: String,
      default: "primary",
    },
    direction: {
      type: String,
      default: "",
    },
    params:{
      default: "",
    }
  },
  computed: {
    cssVars() {
      let colorObject = colorPalette.primary;
      if (this.color === "login") colorObject = colorPalette.login;
      else if (this.color === "signup") colorObject = colorPalette.signup;
      else if (this.color === "logo") colorObject = colorPalette.logo;
      else if (this.color == "light") colorObject = colorPalette.light;
      else if (this.color == "simple") colorObject = colorPalette.simple;
      else if (this.color == "creditCard") colorObject = colorPalette.creditCard;
      else if (this.color == "paypal") colorObject = colorPalette.paypal;

      return {
        "--bg": colorObject.bg,
        "--hoverBg": colorObject.hoverBg,
        "--fontColor": colorObject.fontColor,
        "--hoverFontColor": colorObject.hoverFontColor,
        "--border": colorObject.border,
        "--radius": colorObject.radius,
      };
    },
  },
};
</script>

<style scoped>
a {
  background: var(--bg);
  box-shadow: var(--border);
  border: 0;
  border-radius: var(--radius);
  font-size: 100%;
  text-align: justify;
  margin: 1%;
  padding: 2%;
  cursor: pointer;
  font-weight: 700;
  transition: all 0.4s ease-in-out;
  color: var(--fontColor);
  text-decoration: none;
}
a:hover {
  transition-delay: 1ms;
  background: var(--hoverBg);
  color: var(--hoverFontColor);
}
a:disabled {
  background: lightgray;
  color: rgb(242, 242, 242);
  border-color: darkgrey;
}
a:disabled:hover {
  cursor: not-allowed;
}
</style>
