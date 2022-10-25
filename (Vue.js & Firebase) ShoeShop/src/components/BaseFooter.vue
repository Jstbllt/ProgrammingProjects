<template>
  <footer :style="cssVar">
    <div class="section" id="links">
      <div class="component">
        <h4>You have question?</h4>
        <p class="description">Need answers?</p>
        <base-button color="light" to="/HelpCenter">Help Center</base-button>
      </div>

      <div class="component">
        <h4>My Orders</h4>

        <p class="description">Sign in to see your placed orders.</p>

        <base-button v-if="$store.state.user" color="light" to="/account/orders"
          >See orders</base-button
        >
      </div>
    </div>

    <div class="section" id="logo">
      <img src="../assets/logo.png" class="logo" alt="logo" />

      <div class="spans">
        <span>© 2022 ShoeShop.</span>
        <span>All rights reserved.</span>
      </div>
    </div>
  </footer>
</template>

<script>
import BaseButton from "./BaseButton.vue";
import { colors } from '@/assets/style'
export default {
  components: { BaseButton },
  name: "BaseFooter",
  computed: {
    cssVar() {
      return {
        "--bleu": colors.gris_bleu,
      };
    },
  },
};
</script>

<style scoped>
footer {
  text-align: center;
  background: var(--bleu);
  padding-top: 2%;
  padding-bottom: 2%;
  color: white;
  display: flex;
  flex-direction: row;
}
.logo {
  width: 100px;
  border-radius: 25px;
}

.section {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
}

.component,
.spans {
  display: flex;
  flex-direction: column;
  align-items: start;
}

h4 {
  margin-top: 0;
  margin-bottom: 0;
}

.description {
  font-size: small;
}

@media screen and (max-width: 850px) {
  footer {
    flex-direction: column;
    align-items: center;
  }

  .section {
    margin-top: 2%;
    margin-bottom: 2%;
  }

  #logo {
    justify-content: center;
  }

  .logo,
  .spans {
    margin: 1%;
  }
}

@media screen and (max-width: 500px) {
  .section {
    flex-direction: column;
    margin-bottom: 0;
  }

  .component {
    align-items: center;
    margin-bottom: 3%;
  }

  logo {
    margin-bottom: 2%;
  }
}
</style>
