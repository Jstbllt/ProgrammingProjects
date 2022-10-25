<!-- template of file src/App.vue -->
<template>
  <div>
    <base-layout>
      <router-view />
    </base-layout>
  </div>
</template>
<!-- do not remove the <script /> markup -->

<script>
import BaseLayout from "./components/BaseLayout.vue";
import { onBeforeMount } from "vue";
import { useStore } from "vuex";

export default {
  name: "App",
  components: {
    BaseLayout,
  },
  setup() {
    const store = useStore();
    onBeforeMount(() => {
      store.dispatch("fetchUser");
    });
  },
};
</script>

<style>
#app {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  margin: 0;

  width: 100%;
  height: 100%;
}
html {
  background: white;
  height: max-content;
}

body {
  margin: 0;
  height: 100%;
}

main{
  min-height: 73vh;
}
</style>
