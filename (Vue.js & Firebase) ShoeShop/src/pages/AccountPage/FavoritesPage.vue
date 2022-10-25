<template>
  <main>
    <menu-account />
    <div class="content">
      <div class="menu-title">
        <h2>My favorites</h2>
      </div>
      <section class="list">
        <div v-for="shoe in favorites" v-bind:key="shoe.id" class="box">
          <shoe-box :shoe="shoe" />
        </div>
        <div v-if="favorites.length===0">
          <p>You don't have any favourites yet !</p>
      </div>
      </section>
    </div>
  </main>
</template>
<script>
import MenuAccount from "@/components/MenuAccount.vue";
import ShoeBox from "@/components/ShoeBox.vue";
export default {
  name: "FavoritesPage",
  components: { MenuAccount, ShoeBox },
  computed: {
    favorites() {
      return this.$store.getters.getFavorites;
    },
  },
};
</script>
<style scoped>
main {
  display: flex;
}
.content {
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  width: 100%;
  align-content: center;
  margin-bottom: 2rem;
}
.box {
  border: solid black 1px;
  margin-bottom: 10%;
  width: 350px;
  margin: 15px;
  padding: 15px;
  display: flex;
  flex-direction: column;
}
.menu-title {
  text-align: center;
  margin-top: 1rem;
}
.list {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}
</style>
