<template>
  <main>
    <menu-account />
    <div class="content">
      <div class="user-picture">
        <img :src="auth.currentUser?.photoURL" />
      </div>
      <div class="user-details">
        <h3>
          Name :
          {{ $store.state.user?.name }}
        </h3>
        <h3 color="blue">
          Surname :
          {{ $store.state.user?.surname }}
        </h3>
        <h3>
          Email address:
          {{ auth.currentUser?.email }}
        </h3>
        <h3>Shoe size: {{ $store.state.user?.size }}</h3>
        <h3>
          Currency: {{ $store.state.user?.currency.symbol }}
          {{ $store.state.user?.currency.code }}
        </h3>
      </div>
    </div>
  </main>
</template>

<script>
import MenuAccount from "@/components/MenuAccount.vue";
import { auth } from "@/firebase";
export default {
  name: "AccountPage",
  components: { MenuAccount },
  setup() {
    return auth.currentUser;
  },
};
</script>

<style scoped>
main {
  display: flex;
}
.user-picture {
  display: flex;
  justify-content: center;
  width: 100%;
  gap: 2%;
  margin-top: 1%;
  height: fit-content;
}
.user-details {
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-left: 3%;
  margin-top: 5%;
}
.content {
  display: flex;
  flex-wrap: wrap;
  width: 100%;
  flex-direction: row;
  align-content: flex-start;
}
img {
  border-radius: 50%;
  height: 75px;
}
</style>
