<template>
  <label for="toggleMenu">☰</label>
  <input type="checkbox" id="toggleMenu" />

  <div class="account-menu">
    <div class="options">
      <base-button class="options-menu" direction="Settings">
        <font-awesome-icon icon="fa-solid fa-gear" />
        <h3>Settings</h3>
      </base-button>
      <base-button class="options-menu" direction="Orders">
        <font-awesome-icon icon="fa-solid fa-arrow-rotate-left" />
        <h3>My orders</h3>
      </base-button>
      <base-button class="options-menu" direction="Securities">
        <font-awesome-icon icon="fa-solid fa-shield-halved" />
        <h3>Securities</h3>
      </base-button>
      <base-button class="options-menu" direction="Confidentiality">
        <font-awesome-icon icon="fa-solid fa-user-lock" />
        <h3>Confidentiality</h3>
      </base-button>
      <base-button class="options-menu" direction="Favorites">
        <font-awesome-icon icon="fa-solid fa-heart" />
        <h3>Favorites</h3>
      </base-button>
    </div>
    <div>
      <base-button class="options-menu" @click="$store.dispatch('logout')">
        <font-awesome-icon icon="fa-solid fa-power-off" />
        <h3>Log out</h3>
      </base-button>
    </div>
  </div>
</template>

<script>
import BaseButton from "./BaseButton.vue";
export default {
  name: "MenuAccount",
  components: { BaseButton },
};
</script>
<style scoped>
.account-menu {
  border-right: solid gray 1px;
  min-width: 16%;
  margin-left: 2%;
}
.options-menu {
  display: flex;
  align-items: center;
  gap: 5%;
}

label,
#toggleMenu {
  display: none;
}
.svg-inline--fa {
  font-size: 1.5rem;
  color: rgb(100, 117, 140);
}

@media screen and (max-width: 1120px) {
  .account-menu {
    display: none;
  }
  label {
    display: flex;
    width: 30px;
    font-size: 35px;
    color: rgb(100, 117, 140);
    margin-left: 1%;
    margin-top: 1%;
  }
  #toggleMenu:checked + .account-menu {
    display: block;
    min-width: fit-content;
    padding-right: 2%;
  }
  #toggleMenu:checked + main {
    flex-wrap: wrap;
  }
}
</style>
